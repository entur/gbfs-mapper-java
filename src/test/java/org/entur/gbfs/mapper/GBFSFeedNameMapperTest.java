package org.entur.gbfs.mapper;

import org.entur.gbfs.v2_3.gbfs.GBFSFeedName;
import org.entur.gbfs.v3_0.gbfs.GBFSFeed;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GBFSFeedNameMapperTest {

    @Test
    void testFeedNameMapper() {
        assertEquals(GBFSFeedName.StationStatus, GBFSFeedNameMapper.INSTANCE.map(GBFSFeed.Name.STATION_STATUS));
        assertEquals(GBFSFeedName.FreeBikeStatus, GBFSFeedNameMapper.INSTANCE.map(GBFSFeed.Name.VEHICLE_STATUS));
    }

    @Test
    void testInverseFeedNameMapper() {
        assertEquals(GBFSFeed.Name.STATION_STATUS, GBFSFeedNameMapper.INSTANCE.map(GBFSFeedName.StationStatus));
        assertEquals(GBFSFeed.Name.VEHICLE_STATUS, GBFSFeedNameMapper.INSTANCE.map(GBFSFeedName.FreeBikeStatus));
        assertNull(GBFSFeedNameMapper.INSTANCE.map(GBFSFeedName.SystemHours));
    }
}
