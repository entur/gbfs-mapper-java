package org.entur.gbfs.mapper;

import org.mapstruct.Context;

import java.util.List;

public class SystemPricingPlansAdditionalMapper {
    List<org.entur.gbfs.v3_0_RC2.system_pricing_plans.GBFSName> mapName(String value, @Context String language) {
        if (value == null) {
            return null;
        }
        return List.of(
                new org.entur.gbfs.v3_0_RC2.system_pricing_plans.GBFSName()
                        .withText(value)
                        .withLanguage(language)
        );
    }

    String mapNameInverse(List<org.entur.gbfs.v3_0_RC2.system_pricing_plans.GBFSName> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.entur.gbfs.v3_0_RC2.system_pricing_plans.GBFSName::getText)
                .findFirst().orElse(null);
    }

    List<org.entur.gbfs.v3_0_RC2.system_pricing_plans.GBFSDescription> mapDescription(String value, @Context String language) {
        if (value == null) {
            return null;
        }
        return List.of(
                new org.entur.gbfs.v3_0_RC2.system_pricing_plans.GBFSDescription()
                        .withText(value)
                        .withLanguage(language)
        );
    }

    String mapDescriptionInverse(List<org.entur.gbfs.v3_0_RC2.system_pricing_plans.GBFSDescription> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.entur.gbfs.v3_0_RC2.system_pricing_plans.GBFSDescription::getText)
                .findFirst().orElse(null);
    }
}
