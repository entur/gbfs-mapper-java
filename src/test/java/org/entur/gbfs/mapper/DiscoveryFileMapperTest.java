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
class DiscoveryFileMapperTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Expect expect;

    @SnapshotName("gbfs_v2_3_to_v3_0_discovery_file_snapshot")
    @Test
    void testMapDiscoveryFile() throws IOException {
        URL resource = getClass().getClassLoader().getResource("fixtures/v2_3/gbfs.json");
        org.mobilitydata.gbfs.v2_3.gbfs.GBFS testSubject = objectMapper.readValue(resource, org.mobilitydata.gbfs.v2_3.gbfs.GBFS.class);
        org.mobilitydata.gbfs.v3_0.gbfs.GBFSGbfs mapped = GBFSMapper.INSTANCE.map(testSubject, "en");
        assertDoesNotThrow(() -> {
            expect
                    .serializer("json")
                    .toMatchSnapshot(mapped);
        });
    }

    @SnapshotName("gbfs_v3_0_to_v2_3_discovery_file_snapshot")
    @Test
    void testMapDiscoveryFileInverse() throws IOException {
        URL resource = getClass().getClassLoader().getResource("fixtures/v3_0/gbfs.json");
        org.mobilitydata.gbfs.v3_0.gbfs.GBFSGbfs testSubject = objectMapper.readValue(resource, org.mobilitydata.gbfs.v3_0.gbfs.GBFSGbfs.class);
        org.mobilitydata.gbfs.v2_3.gbfs.GBFS mapped = GBFSMapper.INSTANCE.map(testSubject, "en");
        assertDoesNotThrow(() -> {
            expect
                    .serializer("json")
                    .toMatchSnapshot(mapped);
        });
    }
}
