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
class GbfsVersionsMapperTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Expect expect;

    @SnapshotName("gbfs_v2_3_to_v3_0_gbfs_versions_file_snapshot")
    @Test
    void testMapGbfsVersionsFile() throws IOException {
        URL resource = getClass().getClassLoader().getResource("fixtures/v2_3/gbfs_versions.json");
        org.entur.gbfs.v2_3.gbfs_versions.GBFSGbfsVersions testSubject = objectMapper.readValue(resource, org.entur.gbfs.v2_3.gbfs_versions.GBFSGbfsVersions.class);
        org.entur.gbfs.v3_0.gbfs_versions.GBFSGbfsVersions mapped = GBFSMapper.INSTANCE.map(testSubject, "en");
        assertDoesNotThrow(() -> {
            expect
                    .serializer("json")
                    .toMatchSnapshot(mapped);
        });
    }

    @SnapshotName("gbfs_v3_0_to_v2_3_gbfs_versions_file_snapshot")
    @Test
    void testMapGbfsVersionsFileInverse() throws IOException {
        URL resource = getClass().getClassLoader().getResource("fixtures/v3_0/gbfs_versions.json");
        org.entur.gbfs.v3_0.gbfs_versions.GBFSGbfsVersions testSubject = objectMapper.readValue(resource, org.entur.gbfs.v3_0.gbfs_versions.GBFSGbfsVersions.class);
        org.entur.gbfs.v2_3.gbfs_versions.GBFSGbfsVersions mapped = GBFSMapper.INSTANCE.map(testSubject, "en");
        assertDoesNotThrow(() -> {
            expect
                    .serializer("json")
                    .toMatchSnapshot(mapped);
        });
    }
}
