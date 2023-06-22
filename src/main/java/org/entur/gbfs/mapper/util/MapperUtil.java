package org.entur.gbfs.mapper.util;

import org.entur.gbfs.v2_3.gbfs.GBFSFeedName;
import org.entur.gbfs.v3_0_RC.gbfs.GBFSFeed;

import java.util.List;

public class MapperUtil {
    public static boolean filterSourceFeeds(org.entur.gbfs.v2_3.gbfs.GBFSFeed sourceFeed) {
        return !List.of(
                GBFSFeedName.SystemHours,
                GBFSFeedName.SystemCalendar
        ).contains(sourceFeed.getName());
    }

    public static org.entur.gbfs.v3_0_RC.gbfs.GBFSFeed mapSourceFeed(org.entur.gbfs.v2_3.gbfs.GBFSFeed sourceFeed) {
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
