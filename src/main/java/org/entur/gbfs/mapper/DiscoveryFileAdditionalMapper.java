package org.entur.gbfs.mapper;

import org.entur.gbfs.v2_3.gbfs.GBFSFeedName;
import org.entur.gbfs.v2_3.gbfs.GBFSFeeds;
import org.entur.gbfs.v3_0_RC2.gbfs.GBFSFeed;
import org.mapstruct.Context;

import java.net.URI;
import java.util.List;
import java.util.Map;

public class DiscoveryFileAdditionalMapper {
    private static GBFSFeedNameMapper feedNameMapper = GBFSFeedNameMapper.INSTANCE;

    public org.entur.gbfs.v3_0_RC2.gbfs.GBFSData map(Map<String, GBFSFeeds> source, @Context String sourceLanguage) {
        List<GBFSFeed> mappedFeeds = source.get(sourceLanguage).getFeeds().stream()
                .filter(this::filterLegacySourceFeeds)
                .map(this::map)
                .toList();

        return new org.entur.gbfs.v3_0_RC2.gbfs.GBFSData()
                .withFeeds(mappedFeeds);
    }

    public Map<String, org.entur.gbfs.v2_3.gbfs.GBFSFeeds> map(org.entur.gbfs.v3_0_RC2.gbfs.GBFSData source, @Context String targetLanguage) {
        org.entur.gbfs.v2_3.gbfs.GBFSFeeds mappedFeeds = new org.entur.gbfs.v2_3.gbfs.GBFSFeeds();
        mappedFeeds.setFeeds(source.getFeeds().stream().map(this::map).toList());

        return Map.of(
                targetLanguage,
                mappedFeeds
        );
    }

    private boolean filterLegacySourceFeeds(org.entur.gbfs.v2_3.gbfs.GBFSFeed sourceFeed) {
        return !List.of(
                GBFSFeedName.SystemHours,
                GBFSFeedName.SystemCalendar
        ).contains(sourceFeed.getName());
    }

    private org.entur.gbfs.v3_0_RC2.gbfs.GBFSFeed map(org.entur.gbfs.v2_3.gbfs.GBFSFeed sourceFeed) {
        org.entur.gbfs.v3_0_RC2.gbfs.GBFSFeed targetFeed = new org.entur.gbfs.v3_0_RC2.gbfs.GBFSFeed();
        targetFeed.setName(feedNameMapper.map(sourceFeed.getName()));
        targetFeed.setUrl(sourceFeed.getUrl().toString());
        return targetFeed;
    }

    private org.entur.gbfs.v2_3.gbfs.GBFSFeed map(org.entur.gbfs.v3_0_RC2.gbfs.GBFSFeed sourceFeed) {
        org.entur.gbfs.v2_3.gbfs.GBFSFeed targetFeed = new org.entur.gbfs.v2_3.gbfs.GBFSFeed();
        targetFeed.setName(feedNameMapper.map(sourceFeed.getName()));
        targetFeed.setUrl(URI.create(sourceFeed.getUrl()));
        return targetFeed;
    }
}
