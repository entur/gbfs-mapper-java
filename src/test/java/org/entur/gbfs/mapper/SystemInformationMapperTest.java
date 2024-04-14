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
class SystemInformationMapperTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Expect expect;

    @SnapshotName("gbfs_v2_3_to_v3_0_system_information_file_snapshot")
    @Test
    void testMapSystemInformationFile() throws IOException {
        URL resource = getClass().getClassLoader().getResource("fixtures/v2_3/system_information.json");
        org.entur.gbfs.v2_3.system_information.GBFSSystemInformation testSubject = objectMapper.readValue(resource, org.entur.gbfs.v2_3.system_information.GBFSSystemInformation.class);
        org.entur.gbfs.v3_0.system_information.GBFSSystemInformation mapped = GBFSMapper.INSTANCE.map(testSubject, "en");
        assertDoesNotThrow(() -> {
            expect
                    .serializer("json")
                    .toMatchSnapshot(mapped);
        });
    }

    @SnapshotName("gbfs_v3_0_to_v2_3_system_information_file_snapshot")
    @Test
    void testMapSystemInformationFileInverse() throws IOException {
        URL resource = getClass().getClassLoader().getResource("fixtures/v3_0/system_information.json");
        org.entur.gbfs.v3_0.system_information.GBFSSystemInformation testSubject = objectMapper.readValue(resource, org.entur.gbfs.v3_0.system_information.GBFSSystemInformation.class);
        org.entur.gbfs.v2_3.system_information.GBFSSystemInformation mapped = GBFSMapper.INSTANCE.map(testSubject, "en");
        assertDoesNotThrow(() -> {
            expect
                    .serializer("json")
                    .toMatchSnapshot(mapped);
        });
    }
}
