package org.entur.gbfs.mapper;

import org.mapstruct.Mapper;

import java.time.Instant;
import java.util.Date;

@Mapper
public class DateMapper {
    Double mapDateToDouble(Date value) {
        if (value == null) {
            return null;
        }
        return (double) value.getTime() / 1000;
    }

    Date mapDoubleToDate(Double value) {
        if (value == null) {
            return null;
        }
        return Date.from(Instant.ofEpochSecond(value.longValue()));
    }

    Integer mapDateToInteger(Date value) {
        if (value == null) {
            return null;
        }
        return (int) (value.getTime() / 1000);
    }

    Date mapIntegerToDate(Integer value) {
        if (value == null) {
            return null;
        }
        return Date.from(Instant.ofEpochSecond(value.longValue()));
    }
}
