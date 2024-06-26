package org.entur.gbfs.mapper;

import org.mapstruct.Context;

import java.util.List;

public class SystemRegionsAdditionalMapper {

    List<org.mobilitydata.gbfs.v3_0.system_regions.GBFSName> mapName(String value, @Context String language) {
        if (value == null) {
            return null;
        }
        return List.of(
                new org.mobilitydata.gbfs.v3_0.system_regions.GBFSName()
                        .withText(value)
                        .withLanguage(language)
        );
    }

    String mapNameInverse(List<org.mobilitydata.gbfs.v3_0.system_regions.GBFSName> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.mobilitydata.gbfs.v3_0.system_regions.GBFSName::getText)
                .findFirst().orElse(null);
    }
}
