package org.entur.gbfs.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mobilitydata.gbfs.v2_3.system_hours.Day;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Mapper(imports = {List.class})
public abstract class SystemInformationAdditionalMapper {
    public static final String DEFAULT_OPENING_HOURS = "24/7";

    @SystemInformationDataMapper
    @Mapping(target = "languages", expression = "java(List.of(language))")
    abstract org.mobilitydata.gbfs.v3_0.system_information.GBFSData mapData(org.mobilitydata.gbfs.v2_3.system_information.GBFSData source, @Context String language);

    @SystemInformationDataMapper
    @Named("mapDataWithSystemHours")
    @Mapping(target = "languages", expression = "java(List.of(language))")
    @Mapping(target = "openingHours", expression = "java(mapOpeningHours(systemHours))")
    abstract org.mobilitydata.gbfs.v3_0.system_information.GBFSData mapDataWithSystemHours(org.mobilitydata.gbfs.v2_3.system_information.GBFSData source, @Context org.mobilitydata.gbfs.v2_3.system_hours.GBFSSystemHours systemHours, @Context String language);

    @SystemInformationDataMapper
    @Mapping(target = "language", expression = "java(language)")
    abstract org.mobilitydata.gbfs.v2_3.system_information.GBFSData mapDataInverse(org.mobilitydata.gbfs.v3_0.system_information.GBFSData source, @Context String language);

    /**
     * Converts start and end times to OpenStreetMap time range format (HH:mm-HH:mm).
     * Automatically detects and converts midnight-crossing times to extended notation.
     * Supports extended hours (0-47) per GBFS specification.
     *
     * Examples:
     * - "08:00:00", "17:00:00" → "08:00-17:00" (same-day)
     * - "23:00:00", "25:00:00" → "23:00-25:00" (extended format input)
     * - "20:00:00", "01:00:00" → "20:00-25:00" (detected midnight crossing)
     *
     * @param startTime Start time in HH:mm:ss format, hours can be 0-47
     * @param endTime End time in HH:mm:ss format, hours can be 0-47
     * @return Time range in format "HH:mm-HH:mm"
     */
    private String convertToTimeRange(String startTime, String endTime) {
        // Parse start time
        String[] startParts = startTime.split(":");
        int startHour = Integer.parseInt(startParts[0]);
        int startMinute = Integer.parseInt(startParts[1]);

        // Parse end time
        String[] endParts = endTime.split(":");
        int endHour = Integer.parseInt(endParts[0]);
        int endMinute = Integer.parseInt(endParts[1]);

        // If end time is earlier than start time (and both are < 24),
        // assume it crosses midnight and add 24 hours to end time
        if (endHour < startHour && endHour < 24 && startHour < 24) {
            endHour += 24;
        }

        return String.format("%02d:%02d-%02d:%02d",
            startHour, startMinute, endHour, endMinute);
    }

    private String convertDayToTwoLetters(Day day) {
        switch (day) {
            case MON -> { return "Mo"; }
            case TUE -> { return "Tu"; }
            case WED -> { return "We"; }
            case THU -> { return "Th"; }
            case FRI -> { return "Fr"; }
            case SAT -> { return "Sa"; }
            case SUN -> { return "Su"; }
        }
        return null;
    }

    private String convertListOfRentalHourToOneDate(List<org.mobilitydata.gbfs.v2_3.system_hours.GBFSRentalHour> rentalHours) {
        return rentalHours.stream()
                .sorted(Comparator.comparing(org.mobilitydata.gbfs.v2_3.system_hours.GBFSRentalHour::getStartTime))
                .map(rh -> convertToTimeRange(rh.getStartTime(), rh.getEndTime()))
                .collect(Collectors.joining(","));
    }

    String mapOpeningHours(@Context org.mobilitydata.gbfs.v2_3.system_hours.GBFSSystemHours systemHours) {
        if (systemHours != null && systemHours.getData() != null && !systemHours.getData().getRentalHours().isEmpty()) {
            Map<Day, List<org.mobilitydata.gbfs.v2_3.system_hours.GBFSRentalHour>> groupedByDay =
                    systemHours.getData().getRentalHours().stream()
                            .flatMap(rh -> rh.getDays().stream().map(day -> Map.entry(day, rh)))
                            .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

            Map<Day, List<org.mobilitydata.gbfs.v2_3.system_hours.GBFSRentalHour>> sortedMap = new TreeMap<>(Comparator.comparingInt(Day::ordinal));
            sortedMap.putAll(groupedByDay);

            return sortedMap.entrySet().stream().map(entry -> {
                String day = convertDayToTwoLetters(entry.getKey());
                List<org.mobilitydata.gbfs.v2_3.system_hours.GBFSRentalHour> rentalHours = entry.getValue();
                String dates = convertListOfRentalHourToOneDate(rentalHours);
                return String.format("%s %s", day, dates);
            }).collect(Collectors.joining("; "));
        }
        return DEFAULT_OPENING_HOURS;
    }

    List<org.mobilitydata.gbfs.v3_0.system_information.GBFSName> mapName(String value, @Context String language) {
        if (value == null) {
            return null;
        }
        return List.of(
                new org.mobilitydata.gbfs.v3_0.system_information.GBFSName()
                        .withText(value)
                        .withLanguage(language)
        );
    }

    String mapNameInverse(List<org.mobilitydata.gbfs.v3_0.system_information.GBFSName> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.mobilitydata.gbfs.v3_0.system_information.GBFSName::getText)
                .findFirst().orElse(null);
    }

    List<org.mobilitydata.gbfs.v3_0.system_information.GBFSShortName> mapShortName(String value, @Context String language) {
        if (value == null) {
            return null;
        }
        return List.of(
                new org.mobilitydata.gbfs.v3_0.system_information.GBFSShortName()
                        .withText(value)
                        .withLanguage(language)
        );
    }

    String mapShortNameInverse(List<org.mobilitydata.gbfs.v3_0.system_information.GBFSShortName> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.mobilitydata.gbfs.v3_0.system_information.GBFSShortName::getText)
                .findFirst().orElse(null);
    }

    List<org.mobilitydata.gbfs.v3_0.system_information.GBFSOperator> mapOperator(String operator, @Context String language) {
        if (operator == null) {
            return null;
        }
        return List.of(
                new org.mobilitydata.gbfs.v3_0.system_information.GBFSOperator()
                        .withText(operator)
                        .withLanguage(language)
        );
    }

    String mapOperatorInverse(List<org.mobilitydata.gbfs.v3_0.system_information.GBFSOperator> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.mobilitydata.gbfs.v3_0.system_information.GBFSOperator::getText)
                .findFirst().orElse(null);
    }

    List<org.mobilitydata.gbfs.v3_0.system_information.GBFSTermsUrl> mapTermsUrl(String termsUrl, @Context String language) {
        if (termsUrl == null) {
            return null;
        }
        return List.of(
                new org.mobilitydata.gbfs.v3_0.system_information.GBFSTermsUrl()
                        .withText(termsUrl)
                        .withLanguage(language)
        );
    }

    String mapTermsUrlInverse(List<org.mobilitydata.gbfs.v3_0.system_information.GBFSTermsUrl> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.mobilitydata.gbfs.v3_0.system_information.GBFSTermsUrl::getText)
                .findFirst().orElse(null);
    }

    List<org.mobilitydata.gbfs.v3_0.system_information.GBFSPrivacyUrl> mapPrivacyUrl(String privacyUrl, @Context String language) {
        if (privacyUrl == null) {
            return null;
        }
        return List.of(
                new org.mobilitydata.gbfs.v3_0.system_information.GBFSPrivacyUrl()
                        .withText(privacyUrl)
                        .withLanguage(language)
        );
    }

    String mapPrivacyUrlInverse(List<org.mobilitydata.gbfs.v3_0.system_information.GBFSPrivacyUrl> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.mobilitydata.gbfs.v3_0.system_information.GBFSPrivacyUrl::getText)
                .findFirst().orElse(null);
    }
}
