package org.entur.gbfs.mapper;

import org.junit.jupiter.api.Test;
import org.mobilitydata.gbfs.v2_3.system_hours.Day;
import org.mobilitydata.gbfs.v2_3.system_hours.GBFSRentalHour;
import org.mobilitydata.gbfs.v2_3.system_hours.GBFSSystemHours;
import org.mobilitydata.gbfs.v3_0.system_information.GBFSData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SystemInformationAdditionalMapperUnitTest {

    static class TestSubject extends SystemInformationAdditionalMapper {
        @Override
        GBFSData mapData(org.mobilitydata.gbfs.v2_3.system_information.GBFSData source, String language) {
            return null;
        }

        @Override
        GBFSData mapDataWithSystemHours(org.mobilitydata.gbfs.v2_3.system_information.GBFSData source, GBFSSystemHours systemHours, String language) {
            return null;
        }

        @Override
        org.mobilitydata.gbfs.v2_3.system_information.GBFSData mapDataInverse(GBFSData source, String language) {
            return null;
        }
    }

    @Test
    void mapOpeningHours_test_null_system_hours_returns_default() {
        TestSubject subject = new TestSubject();
        String result = subject.mapOpeningHours(null);
        assertEquals("24/7", result);
    }

    @Test
    void mapOpeningHours_test_all_days_in_one_rental_hours() {
        TestSubject testSubject = new TestSubject();
        org.mobilitydata.gbfs.v2_3.system_hours.GBFSSystemHours systemHours = new org.mobilitydata.gbfs.v2_3.system_hours.GBFSSystemHours();

        systemHours.setData(new org.mobilitydata.gbfs.v2_3.system_hours.GBFSData());

        systemHours.getData().setRentalHours(List.of(
                new GBFSRentalHour().withStartTime("00:00:00").withEndTime("23:59:59").withDays(List.of(Day.MON, Day.TUE, Day.WED, Day.THU, Day.FRI, Day.SAT, Day.SUN))
        ));

        String result = testSubject.mapOpeningHours(systemHours);
        assertEquals("Su 00:00-23:59; Mo 00:00-23:59; Tu 00:00-23:59; We 00:00-23:59; Th 00:00-23:59; Fr 00:00-23:59; Sa 00:00-23:59", result);
    }

    @Test
    void mapOpeningHours_test_separate_weekdays_and_weekends() {
        TestSubject testSubject = new TestSubject();
        org.mobilitydata.gbfs.v2_3.system_hours.GBFSSystemHours systemHours = new org.mobilitydata.gbfs.v2_3.system_hours.GBFSSystemHours();

        systemHours.setData(new org.mobilitydata.gbfs.v2_3.system_hours.GBFSData());

        systemHours.getData().setRentalHours(List.of(
                new GBFSRentalHour().withStartTime("00:00:00").withEndTime("22:00:00").withDays(List.of(Day.MON, Day.TUE, Day.WED, Day.THU, Day.FRI)),
                new GBFSRentalHour().withStartTime("00:00:00").withEndTime("23:59:59").withDays(List.of(Day.SAT, Day.SUN))
        ));

        String result = testSubject.mapOpeningHours(systemHours);
        assertEquals("Su 00:00-23:59; Mo 00:00-22:00; Tu 00:00-22:00; We 00:00-22:00; Th 00:00-22:00; Fr 00:00-22:00; Sa 00:00-23:59", result);
    }

    @Test
    void mapOpeningHours_test_all_days_separate() {
        TestSubject testSubject = new TestSubject();
        org.mobilitydata.gbfs.v2_3.system_hours.GBFSSystemHours systemHours = new org.mobilitydata.gbfs.v2_3.system_hours.GBFSSystemHours();

        systemHours.setData(new org.mobilitydata.gbfs.v2_3.system_hours.GBFSData());

        systemHours.getData().setRentalHours(List.of(
                new GBFSRentalHour().withStartTime("00:00:00").withEndTime("01:00:00").withDays(List.of(Day.MON)),
                new GBFSRentalHour().withStartTime("01:00:00").withEndTime("02:00:00").withDays(List.of(Day.TUE)),
                new GBFSRentalHour().withStartTime("02:00:00").withEndTime("03:00:00").withDays(List.of(Day.WED)),
                new GBFSRentalHour().withStartTime("03:00:00").withEndTime("04:00:00").withDays(List.of(Day.THU)),
                new GBFSRentalHour().withStartTime("04:00:00").withEndTime("05:00:00").withDays(List.of(Day.FRI)),
                new GBFSRentalHour().withStartTime("05:00:00").withEndTime("06:00:00").withDays(List.of(Day.SAT)),
                new GBFSRentalHour().withStartTime("06:00:00").withEndTime("07:00:00").withDays(List.of(Day.SUN))
        ));

        String result = testSubject.mapOpeningHours(systemHours);
        assertEquals("Su 06:00-07:00; Mo 00:00-01:00; Tu 01:00-02:00; We 02:00-03:00; Th 03:00-04:00; Fr 04:00-05:00; Sa 05:00-06:00", result);
    }

    @Test
    void mapOpeningHours_test_same_days_in_different_rental_hours() {
        TestSubject testSubject = new TestSubject();
        org.mobilitydata.gbfs.v2_3.system_hours.GBFSSystemHours systemHours = new org.mobilitydata.gbfs.v2_3.system_hours.GBFSSystemHours();

        systemHours.setData(new org.mobilitydata.gbfs.v2_3.system_hours.GBFSData());

        systemHours.getData().setRentalHours(List.of(
                new GBFSRentalHour().withStartTime("20:00:00").withEndTime("01:00:00").withDays(List.of(Day.MON, Day.THU)),
                new GBFSRentalHour().withStartTime("02:00:00").withEndTime("03:00:00").withDays(List.of(Day.MON)),
                new GBFSRentalHour().withStartTime("18:00:00").withEndTime("20:00:00").withDays(List.of(Day.THU)),
                new GBFSRentalHour().withStartTime("13:00:00").withEndTime("17:00:00").withDays(List.of(Day.THU))
        ));

        String result = testSubject.mapOpeningHours(systemHours);
        assertEquals("Mo 02:00-03:00,20:00-25:00; Th 13:00-17:00,18:00-20:00,20:00-25:00", result);
    }

    @Test
    void mapOpeningHours_test_extended_time_format_input() {
        TestSubject testSubject = new TestSubject();
        org.mobilitydata.gbfs.v2_3.system_hours.GBFSSystemHours systemHours = new org.mobilitydata.gbfs.v2_3.system_hours.GBFSSystemHours();

        systemHours.setData(new org.mobilitydata.gbfs.v2_3.system_hours.GBFSData());

        // Test explicit extended time notation (25:00:00 = 1 AM next day)
        systemHours.getData().setRentalHours(List.of(
                new GBFSRentalHour()
                        .withStartTime("23:00:00")
                        .withEndTime("25:00:00")
                        .withDays(List.of(Day.MON, Day.TUE))
        ));

        String result = testSubject.mapOpeningHours(systemHours);
        assertEquals("Mo 23:00-25:00; Tu 23:00-25:00", result);
    }

    @Test
    void mapOpeningHours_test_extended_hours_beyond_25() {
        TestSubject testSubject = new TestSubject();
        org.mobilitydata.gbfs.v2_3.system_hours.GBFSSystemHours systemHours = new org.mobilitydata.gbfs.v2_3.system_hours.GBFSSystemHours();

        systemHours.setData(new org.mobilitydata.gbfs.v2_3.system_hours.GBFSData());

        // Test hours extending to 27:00 (3 AM next day)
        systemHours.getData().setRentalHours(List.of(
                new GBFSRentalHour()
                        .withStartTime("20:00:00")
                        .withEndTime("27:00:00")
                        .withDays(List.of(Day.FRI, Day.SAT))
        ));

        String result = testSubject.mapOpeningHours(systemHours);
        assertEquals("Fr 20:00-27:00; Sa 20:00-27:00", result);
    }

    @Test
    void mapOpeningHours_test_mixed_standard_and_extended_hours() {
        TestSubject testSubject = new TestSubject();
        org.mobilitydata.gbfs.v2_3.system_hours.GBFSSystemHours systemHours = new org.mobilitydata.gbfs.v2_3.system_hours.GBFSSystemHours();

        systemHours.setData(new org.mobilitydata.gbfs.v2_3.system_hours.GBFSData());

        // Weekdays: standard hours (same-day)
        // Weekend: extended hours (overnight)
        systemHours.getData().setRentalHours(List.of(
                new GBFSRentalHour()
                        .withStartTime("06:00:00")
                        .withEndTime("22:00:00")
                        .withDays(List.of(Day.MON, Day.TUE, Day.WED, Day.THU, Day.FRI)),
                new GBFSRentalHour()
                        .withStartTime("18:00:00")
                        .withEndTime("03:00:00")  // Detected as midnight crossing
                        .withDays(List.of(Day.SAT, Day.SUN))
        ));

        String result = testSubject.mapOpeningHours(systemHours);
        assertEquals("Su 18:00-27:00; Mo 06:00-22:00; Tu 06:00-22:00; We 06:00-22:00; Th 06:00-22:00; Fr 06:00-22:00; Sa 18:00-27:00", result);
    }

    @Test
    void mapOpeningHours_test_24_hour_midnight_representation() {
        TestSubject testSubject = new TestSubject();
        org.mobilitydata.gbfs.v2_3.system_hours.GBFSSystemHours systemHours = new org.mobilitydata.gbfs.v2_3.system_hours.GBFSSystemHours();

        systemHours.setData(new org.mobilitydata.gbfs.v2_3.system_hours.GBFSData());

        // Test 24:00:00 (midnight as end time)
        systemHours.getData().setRentalHours(List.of(
                new GBFSRentalHour()
                        .withStartTime("00:00:00")
                        .withEndTime("24:00:00")
                        .withDays(List.of(Day.MON, Day.TUE, Day.WED, Day.THU, Day.FRI, Day.SAT, Day.SUN))
        ));

        String result = testSubject.mapOpeningHours(systemHours);
        assertEquals("Su 00:00-24:00; Mo 00:00-24:00; Tu 00:00-24:00; We 00:00-24:00; Th 00:00-24:00; Fr 00:00-24:00; Sa 00:00-24:00", result);
    }
}
