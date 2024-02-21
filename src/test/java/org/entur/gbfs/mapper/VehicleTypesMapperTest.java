package org.entur.gbfs.mapper;

import au.com.origin.snapshots.Expect;
import au.com.origin.snapshots.annotations.SnapshotName;
import au.com.origin.snapshots.junit5.SnapshotExtension;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith({SnapshotExtension.class})
class VehicleTypesMapperTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private Expect expect;

    @SnapshotName("gbfs_v2_3_to_v3_0_vehicle_types_file_snapshot")
    @Test
    void testMapVehicleTypesFile() throws IOException {
        URL resource = getClass().getClassLoader().getResource("fixtures/v2_3/vehicle_types.json");
        org.entur.gbfs.v2_3.vehicle_types.GBFSVehicleTypes testSubject = objectMapper.readValue(resource, org.entur.gbfs.v2_3.vehicle_types.GBFSVehicleTypes.class);
        org.entur.gbfs.v3_0_RC2.vehicle_types.GBFSVehicleTypes mapped = GBFSMapper.INSTANCE.map(testSubject, "en");
        assertDoesNotThrow(() -> {
            expect
                    .serializer("json")
                    .toMatchSnapshot(mapped);
        });
    }

    @SnapshotName("gbfs_v3_0_to_v2_3_vehicle_types_file_snapshot")
    @Test
    void testMapVehicleTypesFileInverse() throws IOException {
        URL resource = getClass().getClassLoader().getResource("fixtures/v3_0-RC2/vehicle_types.json");
        org.entur.gbfs.v3_0_RC2.vehicle_types.GBFSVehicleTypes testSubject = objectMapper.readValue(resource, org.entur.gbfs.v3_0_RC2.vehicle_types.GBFSVehicleTypes.class);
        org.entur.gbfs.v2_3.vehicle_types.GBFSVehicleTypes mapped = GBFSMapper.INSTANCE.map(testSubject, "en");
        assertDoesNotThrow(() -> {
            expect
                    .serializer("json")
                    .toMatchSnapshot(mapped);
        });
    }
}
