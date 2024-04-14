package org.entur.gbfs.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public abstract class GeofencingZonesAdditionalMapper {
    List<org.entur.gbfs.v3_0.geofencing_zones.GBFSName> mapName(String value, @Context String language) {
        if (value == null) {
            return null;
        }
        return List.of(
                new org.entur.gbfs.v3_0.geofencing_zones.GBFSName()
                        .withText(value)
                        .withLanguage(language)
        );
    }

    String mapNameInverse(List<org.entur.gbfs.v3_0.geofencing_zones.GBFSName> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.entur.gbfs.v3_0.geofencing_zones.GBFSName::getText)
                .findFirst().orElse(null);
    }

    @Mapping(target = "rideStartAllowed", source = "rideAllowed")
    @Mapping(target = "rideEndAllowed", source = "rideAllowed")
    @Mapping(target = "vehicleTypeIds", source = "vehicleTypeId")
    abstract org.entur.gbfs.v3_0.geofencing_zones.GBFSRule mapRule(org.entur.gbfs.v2_3.geofencing_zones.GBFSRule source);

    @Mapping(target = "rideAllowed", expression = "java(source.getRideStartAllowed() && source.getRideEndAllowed())")
    @Mapping(target = "vehicleTypeId", source = "vehicleTypeIds")
    abstract org.entur.gbfs.v2_3.geofencing_zones.GBFSRule mapRuleInverse(org.entur.gbfs.v3_0.geofencing_zones.GBFSRule source);
}
