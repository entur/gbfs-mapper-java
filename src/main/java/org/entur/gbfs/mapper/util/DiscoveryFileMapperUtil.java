package org.entur.gbfs.mapper.util;

import org.entur.gbfs.v2_3.gbfs.GBFSFeedName;
import org.entur.gbfs.v2_3.gbfs.GBFSFeeds;
import org.entur.gbfs.v3_0_RC.gbfs.GBFSFeed;
import org.mapstruct.Context;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DiscoveryFileMapperUtil {

    public org.entur.gbfs.v3_0_RC.gbfs.GBFSData map(Map<String, GBFSFeeds> source, @Context String sourceLanguage) {
        List<org.entur.gbfs.v3_0_RC.gbfs.GBFSFeed> mappedFeeds = source.get(sourceLanguage).getFeeds().stream()
                .filter(DiscoveryFileMapperUtil::filterLegacySourceFeeds)
                .map(DiscoveryFileMapperUtil::map).collect(Collectors.toList());

        return new org.entur.gbfs.v3_0_RC.gbfs.GBFSData()
                .withFeeds(mappedFeeds);
    }

    public Map<String, org.entur.gbfs.v2_3.gbfs.GBFSFeeds> map(org.entur.gbfs.v3_0_RC.gbfs.GBFSData source, @Context String targetLanguage) {
        org.entur.gbfs.v2_3.gbfs.GBFSFeeds mappedFeeds = new org.entur.gbfs.v2_3.gbfs.GBFSFeeds();
        mappedFeeds.setFeeds(source.getFeeds().stream().map(DiscoveryFileMapperUtil::map).collect(Collectors.toList()));

        return Map.of(
                targetLanguage,
                mappedFeeds
        );
    }

    public static boolean filterLegacySourceFeeds(org.entur.gbfs.v2_3.gbfs.GBFSFeed sourceFeed) {
        return !List.of(
                GBFSFeedName.SystemHours,
                GBFSFeedName.SystemCalendar
        ).contains(sourceFeed.getName());
    }

    public static org.entur.gbfs.v3_0_RC.gbfs.GBFSFeed map(org.entur.gbfs.v2_3.gbfs.GBFSFeed sourceFeed) {
        org.entur.gbfs.v3_0_RC.gbfs.GBFSFeed targetFeed = new org.entur.gbfs.v3_0_RC.gbfs.GBFSFeed();
        if (sourceFeed.getName().equals(GBFSFeedName.FreeBikeStatus)) {
            targetFeed.setName(GBFSFeed.Name.VEHICLE_STATUS);
        } else {
            targetFeed.setName(org.entur.gbfs.v3_0_RC.gbfs.GBFSFeed.Name.fromValue(sourceFeed.getName().value()));
        }
        targetFeed.setUrl(sourceFeed.getUrl().toString());
        return targetFeed;
    }

    public static org.entur.gbfs.v2_3.gbfs.GBFSFeed map(org.entur.gbfs.v3_0_RC.gbfs.GBFSFeed sourceFeed) {
        org.entur.gbfs.v2_3.gbfs.GBFSFeed targetFeed = new org.entur.gbfs.v2_3.gbfs.GBFSFeed();

        if (sourceFeed.getName().equals(GBFSFeed.Name.VEHICLE_STATUS)) {
            targetFeed.setName(GBFSFeedName.FreeBikeStatus);
        } else {
            targetFeed.setName(GBFSFeedName.fromValue(sourceFeed.getName().value()));
        }
        targetFeed.setUrl(URI.create(sourceFeed.getUrl()));

        return targetFeed;
    }
}
