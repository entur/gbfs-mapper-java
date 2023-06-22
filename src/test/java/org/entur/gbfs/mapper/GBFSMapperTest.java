package org.entur.gbfs.mapper;

import au.com.origin.snapshots.Expect;
import au.com.origin.snapshots.annotations.SnapshotName;
import au.com.origin.snapshots.junit5.SnapshotExtension;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.entur.gbfs.v2_3.gbfs.GBFS;
import org.entur.gbfs.v3_0_RC.gbfs.GBFSGbfs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.net.URL;

@ExtendWith({SnapshotExtension.class})
class GBFSMapperTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Expect expect;

    @SnapshotName("gbfs_v2_2_discovery_file_snapshot")
    @Test
    public void testMapDiscoveryFile() throws IOException {
        URL resource = getClass().getClassLoader().getResource("fixtures/v2_3/gbfs.json");
        GBFS testSubject = objectMapper.readValue(resource, GBFS.class);
        GBFSGbfs mapped = GBFSMapper.INSTANCE.map(testSubject, "en");
        expect
            .serializer("json")
            .toMatchSnapshot(mapped);
    }

}
