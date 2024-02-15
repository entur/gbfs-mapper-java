package org.entur.gbfs.mapper;

import org.mapstruct.Mapper;

import java.time.Instant;
import java.util.Date;

@Mapper
public class DateMapper {
    Double mapDateToDouble(Date lastReported) {
        return (double) lastReported.getTime();
    }

    Date mapDoubleToDate(Double value) {
        return Date.from(Instant.ofEpochMilli(value.longValue()));
    }

    Integer mapDateToInteger(Date lastReported) {
        return (int) lastReported.getTime();
    }

    Date mapIntegerToDate(Integer value) {
        return Date.from(Instant.ofEpochMilli(value.longValue()));
    }
}
