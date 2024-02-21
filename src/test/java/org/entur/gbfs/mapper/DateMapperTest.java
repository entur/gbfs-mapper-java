package org.entur.gbfs.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

/**
 * This test class is slightly contrived, since it duplicates the actual implementation of one side of the
 * conversion (epoch -> date), but the other direction is relevant, as is the null-handling.
 */
class DateMapperTest {

    long TEST_EPOCH_SECONDS = 1708520282;
    Date TEST_DATE = Date.from(Instant.ofEpochSecond(TEST_EPOCH_SECONDS));

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
