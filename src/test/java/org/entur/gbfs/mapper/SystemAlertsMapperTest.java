package org.entur.gbfs.mapper;

import au.com.origin.snapshots.Expect;
import au.com.origin.snapshots.annotations.SnapshotName;
import au.com.origin.snapshots.junit5.SnapshotExtension;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith({SnapshotExtension.class})
class SystemAlertsMapperTest {
    private static final ObjectMapper objectMapper = JsonMapper.builder().build();
    private Expect expect;

    @SnapshotName("gbfs_v2_3_to_v3_0_system_alerts_file_snapshot")
    @Test
    void testMapSystemAlertsFile() throws IOException {
        URL resource = getClass().getClassLoader().getResource("fixtures/v2_3/system_alerts.json");
        org.mobilitydata.gbfs.v2_3.system_alerts.GBFSSystemAlerts testSubject = objectMapper.readValue(resource.openStream(), org.mobilitydata.gbfs.v2_3.system_alerts.GBFSSystemAlerts.class);
        org.mobilitydata.gbfs.v3_0.system_alerts.GBFSSystemAlerts mapped = GBFSMapper.INSTANCE.map(testSubject, "en");
        assertDoesNotThrow(() -> {
            expect
                    .serializer("json")
                    .toMatchSnapshot(mapped);
        });
    }

    @SnapshotName("gbfs_v3_0_to_v2_3_system_alerts_file_snapshot")
    @Test
    void testMapSystemAlertsFileInverse() throws IOException {
        URL resource = getClass().getClassLoader().getResource("fixtures/v3_0/system_alerts.json");
        org.mobilitydata.gbfs.v3_0.system_alerts.GBFSSystemAlerts testSubject = objectMapper.readValue(resource.openStream(), org.mobilitydata.gbfs.v3_0.system_alerts.GBFSSystemAlerts.class);
        org.mobilitydata.gbfs.v2_3.system_alerts.GBFSSystemAlerts mapped = GBFSMapper.INSTANCE.map(testSubject, "en");
        assertDoesNotThrow(() -> {
            expect
                    .serializer("json")
                    .toMatchSnapshot(mapped);
        });
    }
}
