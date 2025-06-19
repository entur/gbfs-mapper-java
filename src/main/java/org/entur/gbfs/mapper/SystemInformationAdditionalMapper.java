package org.entur.gbfs.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mobilitydata.gbfs.v2_3.system_hours.Day;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(imports = {List.class})
public abstract class SystemInformationAdditionalMapper {

    @SystemInformationDataMapper
    @Mapping(target = "languages", expression = "java(List.of(language))")
    abstract org.mobilitydata.gbfs.v3_0.system_information.GBFSData mapData(org.mobilitydata.gbfs.v2_3.system_information.GBFSData source, @Context String language);

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

    private String convertDaysToString(List<org.mobilitydata.gbfs.v2_3.system_hours.Day> days) {
        if (days == null || days.isEmpty()) {
            return null;
        }
        if (days.size() == 1) {
            return convertDayToTwoLetters(days.get(0));
        } else {
            return convertDayToTwoLetters(days.get(0)) + "-" + convertDayToTwoLetters(days.get(days.size() - 1));
        }
    }

    @Mapping(target = "openingHours")
    String mapOpeningHours(@Context org.mobilitydata.gbfs.v2_3.system_hours.GBFSData systemHours) {
        if (!systemHours.getRentalHours().isEmpty()) {
            return systemHours.getRentalHours().stream()
                    .map(rentalHour -> String.format("%s %s-%s",
                            convertDaysToString(rentalHour.getDays()),
                            convertDateTo24HourFormat(rentalHour.getStartTime()),
                            convertDateTo24HourFormat(rentalHour.getEndTime())))
                    .reduce((first, second) -> first + "; " + second)
                    .orElse(null);
        }
        return null;
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
