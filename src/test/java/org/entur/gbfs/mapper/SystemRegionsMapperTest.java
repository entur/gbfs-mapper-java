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
class SystemRegionsMapperTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private Expect expect;

    @SnapshotName("gbfs_v2_3_to_v3_0_system_regions_file_snapshot")
    @Test
    void testMapStationInformationFile() throws IOException {
        URL resource = getClass().getClassLoader().getResource("fixtures/v2_3/system_regions.json");
        org.entur.gbfs.v2_3.system_regions.GBFSSystemRegions testSubject = objectMapper.readValue(resource, org.entur.gbfs.v2_3.system_regions.GBFSSystemRegions.class);
        org.entur.gbfs.v3_0_RC2.system_regions.GBFSSystemRegions mapped = GBFSMapper.INSTANCE.map(testSubject, "en");
        assertDoesNotThrow(() -> {
            expect
                    .serializer("json")
                    .toMatchSnapshot(mapped);
        });
    }

    @SnapshotName("gbfs_v3_0_to_v2_3_system_regions_file_snapshot")
    @Test
    void testMapStationInformationFileInverse() throws IOException {
        URL resource = getClass().getClassLoader().getResource("fixtures/v3_0-RC2/system_regions.json");
        org.entur.gbfs.v3_0_RC2.system_regions.GBFSSystemRegions testSubject = objectMapper.readValue(resource, org.entur.gbfs.v3_0_RC2.system_regions.GBFSSystemRegions.class);
        org.entur.gbfs.v2_3.system_regions.GBFSSystemRegions mapped = GBFSMapper.INSTANCE.map(testSubject, "en");
        assertDoesNotThrow(() -> {
            expect
                    .serializer("json")
                    .toMatchSnapshot(mapped);
        });
    }
}