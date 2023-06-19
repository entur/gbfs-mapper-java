package org.entur.gbfs.mapper;

import org.entur.gbfs.v2_3.gbfs.GBFSFeedName;
import org.entur.gbfs.v3_0_RC.gbfs.GBFSFeed;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper
public interface GBFS2to3Mapper {
    GBFS2to3Mapper INSTANCE = Mappers.getMapper( GBFS2to3Mapper.class );

    @Mapping(target = "version", constant = "3.0-RC")
    @Mapping(target = "data", source = "feedsData")
    org.entur.gbfs.v3_0_RC.gbfs.GBFSGbfs mapDiscovery(org.entur.gbfs.v2_3.gbfs.GBFS gbfs, @Context String language);

    default org.entur.gbfs.v3_0_RC.gbfs.GBFSData mapDiscoveryFeedsData(Map<String, org.entur.gbfs.v2_3.gbfs.GBFSFeeds> source, @Context String sourceLanguage) {
        List<org.entur.gbfs.v3_0_RC.gbfs.GBFSFeed> mappedFeeds = source.get(sourceLanguage).getFeeds().stream()
                    .filter(this::filterSourceFeeds)
                    .map(this::mapSourceFeed).collect(Collectors.toList());

        return new org.entur.gbfs.v3_0_RC.gbfs.GBFSData()
                .withFeeds(mappedFeeds);
    }

    private boolean filterSourceFeeds(org.entur.gbfs.v2_3.gbfs.GBFSFeed sourceFeed) {
        return !List.of(
                GBFSFeedName.SystemHours,
                GBFSFeedName.SystemCalendar
        ).contains(sourceFeed.getName());
    }

    private org.entur.gbfs.v3_0_RC.gbfs.GBFSFeed mapSourceFeed(org.entur.gbfs.v2_3.gbfs.GBFSFeed sourceFeed) {
        org.entur.gbfs.v3_0_RC.gbfs.GBFSFeed targetFeed = new org.entur.gbfs.v3_0_RC.gbfs.GBFSFeed();
        if (sourceFeed.getName().equals(GBFSFeedName.FreeBikeStatus)) {
            targetFeed.setName(GBFSFeed.Name.VEHICLE_STATUS);
        } else {
            targetFeed.setName(org.entur.gbfs.v3_0_RC.gbfs.GBFSFeed.Name.fromValue(sourceFeed.getName().value()));
        }
        targetFeed.setUrl(sourceFeed.getUrl().toString());
        return targetFeed;
    }
}
