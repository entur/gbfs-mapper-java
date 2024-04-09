package org.entur.gbfs.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Date;

class DateMapperTest {

    long TEST_EPOCH_SECONDS = 1712608531;
    Date TEST_DATE = Date.from(OffsetDateTime.parse("2024-04-08T20:35:31Z").toInstant());


    @Test
    void testMapDateToDoubleMapsCorrectly() {
        var mapper = new DateMapper();
        var mapped = mapper.mapDateToDouble(TEST_DATE);
        Assertions.assertEquals(TEST_EPOCH_SECONDS, mapped.longValue());
    }

    @Test
    void testMapDateToDoubleHandlesNull() {
        var mapper = new DateMapper();
        var mapped = mapper.mapDateToDouble(null);
        Assertions.assertNull(mapped);
    }

    @Test
    void testMapDateToIntegerMapsCorrectly() {
        var date = Date.from(Instant.ofEpochSecond(TEST_EPOCH_SECONDS));
        var mapper = new DateMapper();
        var mapped = mapper.mapDateToInteger(date);
        Assertions.assertEquals(TEST_EPOCH_SECONDS, mapped.intValue());
    }

    @Test
    void testMapDateToIntegerHandlesNull() {
        var mapper = new DateMapper();
        var mapped = mapper.mapDateToInteger(null);
        Assertions.assertNull(mapped);
    }

    @Test
    void testMapDoubleToDateMapsCorrectly() {
        var mapper = new DateMapper();
        var mapped = mapper.mapDoubleToDate((double) TEST_EPOCH_SECONDS);
        Assertions.assertEquals(TEST_DATE, mapped);
    }

    @Test
    void testMapDoubleToDateHandlesNull() {
        var mapper = new DateMapper();
        var mapped = mapper.mapDoubleToDate(null);
        Assertions.assertNull(mapped);
    }

    @Test
    void testMapIntegerToDateMapsCorrectly() {
        var mapper = new DateMapper();
        var mapped = mapper.mapIntegerToDate((int) TEST_EPOCH_SECONDS);
        Assertions.assertEquals(TEST_DATE, mapped);
    }

    @Test
    void testMapIntegerToDateHandlesNull() {
        var mapper = new DateMapper();
        var mapped = mapper.mapIntegerToDate(null);
        Assertions.assertNull(mapped);
    }
}
