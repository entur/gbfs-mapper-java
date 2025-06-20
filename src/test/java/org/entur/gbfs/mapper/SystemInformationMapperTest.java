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
        org.mobilitydata.gbfs.v2_3.system_information.GBFSSystemInformation testSubject = objectMapper.readValue(resource, org.mobilitydata.gbfs.v2_3.system_information.GBFSSystemInformation.class);
        org.mobilitydata.gbfs.v3_0.system_information.GBFSSystemInformation mapped = GBFSMapper.INSTANCE.map(testSubject, "en");
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
        org.mobilitydata.gbfs.v3_0.system_information.GBFSSystemInformation testSubject = objectMapper.readValue(resource, org.mobilitydata.gbfs.v3_0.system_information.GBFSSystemInformation.class);
        org.mobilitydata.gbfs.v2_3.system_information.GBFSSystemInformation mapped = GBFSMapper.INSTANCE.map(testSubject, "en");
        assertDoesNotThrow(() -> {
            expect
                    .serializer("json")
                    .toMatchSnapshot(mapped);
        });
    }

    @SnapshotName("gbfs_v2_3_to_v3_0_system_information_with_system_hours_file_snapshot")
    @Test
    void testMapSystemInformationWithSystemHoursFile() throws IOException {
        URL systemInfoResource = getClass().getClassLoader().getResource("fixtures/v2_3/system_information.json");
        URL systemHoursResource = getClass().getClassLoader().getResource("fixtures/v2_3/system_hours.json");
        
        org.mobilitydata.gbfs.v2_3.system_information.GBFSSystemInformation systemInfo = objectMapper.readValue(systemInfoResource, org.mobilitydata.gbfs.v2_3.system_information.GBFSSystemInformation.class);
        org.mobilitydata.gbfs.v2_3.system_hours.GBFSSystemHours systemHours = objectMapper.readValue(systemHoursResource, org.mobilitydata.gbfs.v2_3.system_hours.GBFSSystemHours.class);
        
        org.mobilitydata.gbfs.v3_0.system_information.GBFSSystemInformation mapped = GBFSMapper.INSTANCE.map(systemInfo, systemHours, "en");
        assertDoesNotThrow(() -> {
            expect
                    .serializer("json")
                    .toMatchSnapshot(mapped);
        });
    }
}
