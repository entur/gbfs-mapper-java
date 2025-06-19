package org.entur.gbfs.mapper;

import org.junit.jupiter.api.Test;
import org.mobilitydata.gbfs.v2_3.system_hours.Day;
import org.mobilitydata.gbfs.v2_3.system_hours.GBFSRentalHour;
import org.mobilitydata.gbfs.v3_0.system_information.GBFSData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SystemInformationAdditionalMapperUnitTest {

    static class TestSubject extends SystemInformationAdditionalMapper {
        @Override
        GBFSData mapData(org.mobilitydata.gbfs.v2_3.system_information.GBFSData source, String language) {
            return null;
        }

        @Override
        org.mobilitydata.gbfs.v2_3.system_information.GBFSData mapDataInverse(GBFSData source, String language) {
            return null;
        }
    }

    @Test
    void mapOpeningHours_test_all_days_in_one_rental_hours() {
        TestSubject testSubject = new TestSubject();
        org.mobilitydata.gbfs.v2_3.system_hours.GBFSData systemHours = new org.mobilitydata.gbfs.v2_3.system_hours.GBFSData();

        systemHours.setRentalHours(List.of(
                new GBFSRentalHour().withStartTime("00:00:00").withEndTime("23:59:59").withDays(List.of(Day.MON, Day.TUE, Day.WED, Day.THU, Day.FRI, Day.SAT, Day.SUN))
        ));

        String result = testSubject.mapOpeningHours(systemHours);
        assertEquals("Mo-Su 00:00-23:59", result);
    }

    @Test
    void mapOpeningHours_test_separate_weekdays_and_weekends() {
        TestSubject testSubject = new TestSubject();
        org.mobilitydata.gbfs.v2_3.system_hours.GBFSData systemHours = new org.mobilitydata.gbfs.v2_3.system_hours.GBFSData();

        systemHours.setRentalHours(List.of(
                new GBFSRentalHour().withStartTime("00:00:00").withEndTime("22:00:00").withDays(List.of(Day.MON, Day.TUE, Day.WED, Day.THU, Day.FRI)),
                new GBFSRentalHour().withStartTime("00:00:00").withEndTime("23:59:59").withDays(List.of(Day.SAT, Day.SUN))
        ));

        String result = testSubject.mapOpeningHours(systemHours);
        assertEquals("Mo-Fr 00:00-22:00; Sa-Su 00:00-23:59", result);
    }

    @Test
    void mapOpeningHours_test_all_days_separate() {
        TestSubject testSubject = new TestSubject();
        org.mobilitydata.gbfs.v2_3.system_hours.GBFSData systemHours = new org.mobilitydata.gbfs.v2_3.system_hours.GBFSData();

        systemHours.setRentalHours(List.of(
                new GBFSRentalHour().withStartTime("00:00:00").withEndTime("01:00:00").withDays(List.of(Day.MON)),
                new GBFSRentalHour().withStartTime("01:00:00").withEndTime("02:00:00").withDays(List.of(Day.TUE)),
                new GBFSRentalHour().withStartTime("02:00:00").withEndTime("03:00:00").withDays(List.of(Day.WED)),
                new GBFSRentalHour().withStartTime("03:00:00").withEndTime("04:00:00").withDays(List.of(Day.THU)),
                new GBFSRentalHour().withStartTime("04:00:00").withEndTime("05:00:00").withDays(List.of(Day.FRI)),
                new GBFSRentalHour().withStartTime("05:00:00").withEndTime("06:00:00").withDays(List.of(Day.SAT)),
                new GBFSRentalHour().withStartTime("06:00:00").withEndTime("07:00:00").withDays(List.of(Day.SUN))
        ));

        String result = testSubject.mapOpeningHours(systemHours);
        assertEquals("Mo 00:00-01:00; Tu 01:00-02:00; We 02:00-03:00; Th 03:00-04:00; Fr 04:00-05:00; Sa 06:00-07:00; Su 07:00-08:00", result);
    }
}