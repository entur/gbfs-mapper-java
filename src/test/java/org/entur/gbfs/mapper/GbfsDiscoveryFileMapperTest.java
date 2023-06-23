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
public class GbfsDiscoveryFileMapperTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Expect expect;

    @SnapshotName("gbfs_v2_2_discovery_file_snapshot")
    @Test
    void testMapDiscoveryFile() throws IOException {
        URL resource = getClass().getClassLoader().getResource("fixtures/v2_3/gbfs.json");
        org.entur.gbfs.v2_3.gbfs.GBFS testSubject = objectMapper.readValue(resource, org.entur.gbfs.v2_3.gbfs.GBFS.class);
        org.entur.gbfs.v3_0_RC.gbfs.GBFSGbfs mapped = GbfsDiscoveryFileMapper.INSTANCE.map(testSubject, "en");
        expect
                .serializer("json")
                .toMatchSnapshot(mapped);
    }

    @SnapshotName("gbfs_v3_0_discovery_file_snapshot")
    @Test
    void testMapDiscoveryFileInverse() throws IOException {
        URL resource = getClass().getClassLoader().getResource("fixtures/v3_0-RC/gbfs.json");
        org.entur.gbfs.v3_0_RC.gbfs.GBFSGbfs testSubject = objectMapper.readValue(resource, org.entur.gbfs.v3_0_RC.gbfs.GBFSGbfs.class);
        org.entur.gbfs.v2_3.gbfs.GBFS mapped = GbfsDiscoveryFileMapper.INSTANCE.map(testSubject, "en");
        expect
                .serializer("json")
                .toMatchSnapshot(mapped);
    }
}
