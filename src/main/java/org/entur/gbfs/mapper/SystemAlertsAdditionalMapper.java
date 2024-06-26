package org.entur.gbfs.mapper;

import org.mapstruct.Context;

import java.util.List;

public class SystemAlertsAdditionalMapper {
    List<org.mobilitydata.gbfs.v3_0.system_alerts.GBFSUrl> mapUrl(String value, @Context String language) {
        if (value == null) {
            return null;
        }
        return List.of(
                new org.mobilitydata.gbfs.v3_0.system_alerts.GBFSUrl()
                        .withText(value)
                        .withLanguage(language)
        );
    }

    String mapUrlInverse(List<org.mobilitydata.gbfs.v3_0.system_alerts.GBFSUrl> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.mobilitydata.gbfs.v3_0.system_alerts.GBFSUrl::getText)
                .findFirst().orElse(null);
    }

    List<org.mobilitydata.gbfs.v3_0.system_alerts.GBFSSummary> mapSummary(String value, @Context String language) {
        if (value == null) {
            return null;
        }
        return List.of(
                new org.mobilitydata.gbfs.v3_0.system_alerts.GBFSSummary()
                        .withText(value)
                        .withLanguage(language)
        );
    }

    String mapSummaryInverse(List<org.mobilitydata.gbfs.v3_0.system_alerts.GBFSSummary> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.mobilitydata.gbfs.v3_0.system_alerts.GBFSSummary::getText)
                .findFirst().orElse(null);
    }

    List<org.mobilitydata.gbfs.v3_0.system_alerts.GBFSDescription> mapDescription(String value, @Context String language) {
        if (value == null) {
            return null;
        }
        return List.of(
                new org.mobilitydata.gbfs.v3_0.system_alerts.GBFSDescription()
                        .withText(value)
                        .withLanguage(language)
        );
    }

    String mapDescriptionInverse(List<org.mobilitydata.gbfs.v3_0.system_alerts.GBFSDescription> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.mobilitydata.gbfs.v3_0.system_alerts.GBFSDescription::getText)
                .findFirst().orElse(null);
    }
}
