package org.entur.gbfs.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public abstract class SystemRegionsAdditionalMapper {

    List<org.entur.gbfs.v3_0_RC.system_regions.GBFSName> mapName(String value, @Context String language) {
        if (value == null) {
            return null;
        }
        return List.of(
                new org.entur.gbfs.v3_0_RC.system_regions.GBFSName()
                        .withText(value)
                        .withLanguage(language)
        );
    }

    String mapNameInverse(List<org.entur.gbfs.v3_0_RC.system_regions.GBFSName> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.entur.gbfs.v3_0_RC.system_regions.GBFSName::getText)
                .findFirst().orElse(null);
    }
}
