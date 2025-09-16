package org.entur.gbfs.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mobilitydata.gbfs.v2_3.system_hours.Day;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Mapper(imports = {List.class})
public abstract class SystemInformationAdditionalMapper {
    public static final String DEFAULT_OPENING_HOURS = "Mo-Su,PH 00:00-24:00";

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

    private String convertDateTo24HourFormat(String date) {
        return LocalTime.parse(date).format(DateTimeFormatter.ofPattern("HH:mm"));
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
                .map(rh -> String.format("%s-%s",
                        convertDateTo24HourFormat(rh.getStartTime()),
                        convertDateTo24HourFormat(rh.getEndTime())))
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
