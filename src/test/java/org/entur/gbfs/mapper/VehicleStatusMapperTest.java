package org.entur.gbfs.mapper;

import au.com.origin.snapshots.Expect;
import au.com.origin.snapshots.annotations.SnapshotName;
import au.com.origin.snapshots.junit5.SnapshotExtension;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.net.URL;

@ExtendWith({SnapshotExtension.class})
class VehicleStatusMapperTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private Expect expect;

    @SnapshotName("gbfs_v2_3_to_v3_0_vehicle_status_file_snapshot")
    @Test
    void testMapVehicleStatusFile() throws IOException {
        URL resource = getClass().getClassLoader().getResource("fixtures/v2_3/free_bike_status.json");
        org.entur.gbfs.v2_3.free_bike_status.GBFSFreeBikeStatus testSubject = objectMapper.readValue(resource, org.entur.gbfs.v2_3.free_bike_status.GBFSFreeBikeStatus.class);
        org.entur.gbfs.v3_0_RC.vehicle_status.GBFSVehicleStatus mapped = GBFSMapper.INSTANCE.map(testSubject);

        expect
                .serializer("json")
                .toMatchSnapshot(mapped);
    }

    @SnapshotName("gbfs_v3_0_to_v2_3_vehicle_status_file_snapshot")
    @Test
    void testMapVehicleStatusFileInverse() throws IOException {
        URL resource = getClass().getClassLoader().getResource("fixtures/v3_0-RC/vehicle_status.json");
        org.entur.gbfs.v3_0_RC.vehicle_status.GBFSVehicleStatus testSubject = objectMapper.readValue(resource, org.entur.gbfs.v3_0_RC.vehicle_status.GBFSVehicleStatus.class);
        org.entur.gbfs.v2_3.free_bike_status.GBFSFreeBikeStatus mapped = GBFSMapper.INSTANCE.map(testSubject);
        expect
                .serializer("json")
                .toMatchSnapshot(mapped);
    }
}
