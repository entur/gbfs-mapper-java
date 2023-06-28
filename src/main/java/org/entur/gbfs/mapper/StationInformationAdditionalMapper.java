package org.entur.gbfs.mapper;

import org.entur.gbfs.v3_0_RC.station_information.GBFSVehicleTypeAreaCapacity;
import org.entur.gbfs.v3_0_RC.station_information.GBFSVehicleTypeDockCapacity;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;


@Mapper
public abstract class StationInformationAdditionalMapper {
    List<org.entur.gbfs.v3_0_RC.station_information.GBFSName> mapName(String value, @Context String language) {
        if (value == null) {
            return null;
        }
        return List.of(
                new org.entur.gbfs.v3_0_RC.station_information.GBFSName()
                        .withText(value)
                        .withLanguage(language)
        );
    }

    String mapNameInverse(List<org.entur.gbfs.v3_0_RC.station_information.GBFSName> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.entur.gbfs.v3_0_RC.station_information.GBFSName::getText)
                .findFirst().orElse(null);
    }

    List<org.entur.gbfs.v3_0_RC.station_information.GBFSShortName> mapShortName(String value, @Context String language) {
        if (value == null) {
            return null;
        }
        return List.of(
                new org.entur.gbfs.v3_0_RC.station_information.GBFSShortName()
                        .withText(value)
                        .withLanguage(language)
        );
    }

    String mapShortNameInverse(List<org.entur.gbfs.v3_0_RC.station_information.GBFSShortName> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.entur.gbfs.v3_0_RC.station_information.GBFSShortName::getText)
                .findFirst().orElse(null);
    }

    @Mapping(source = "vehicleCapacity", target = "vehicleTypeAreaCapacity")
    @Mapping(source = "vehicleTypeCapacity", target = "vehicleTypeDockCapacity")
    abstract org.entur.gbfs.v3_0_RC.station_information.GBFSStation mapStation(org.entur.gbfs.v2_3.station_information.GBFSStation source, @Context String language);

    List<org.entur.gbfs.v3_0_RC.station_information.GBFSVehicleTypeAreaCapacity> mapVehicleTypeAreaCapacity(org.entur.gbfs.v2_3.station_information.GBFSVehicleCapacity source) {
        if (source == null) {
            return null;
        }
        return source.getAdditionalProperties().entrySet().stream().map(
                entry -> new GBFSVehicleTypeAreaCapacity()
                        .withVehicleTypeId(entry.getKey())
                        .withCount(entry.getValue().intValue())
        ).collect(Collectors.toList());
    }

    List<org.entur.gbfs.v3_0_RC.station_information.GBFSVehicleTypeDockCapacity> mapVehicleTypeDockCapacity(org.entur.gbfs.v2_3.station_information.GBFSVehicleTypeCapacity source) {
        if (source == null) {
            return null;
        }
        return source.getAdditionalProperties().entrySet().stream().map(
                entry -> new GBFSVehicleTypeDockCapacity()
                        .withVehicleTypeId(entry.getKey())
                        .withCount(entry.getValue().intValue())
        ).collect(Collectors.toList());
    }

    @InheritInverseConfiguration
    abstract org.entur.gbfs.v2_3.station_information.GBFSStation mapStationInverse(org.entur.gbfs.v3_0_RC.station_information.GBFSStation source, @Context String language);

    org.entur.gbfs.v2_3.station_information.GBFSVehicleCapacity mapVehicleTypeAreaCapacityInverse(List<org.entur.gbfs.v3_0_RC.station_information.GBFSVehicleTypeAreaCapacity> source) {
        if (source == null) {
            return null;
        }
        org.entur.gbfs.v2_3.station_information.GBFSVehicleCapacity mapped = new org.entur.gbfs.v2_3.station_information.GBFSVehicleCapacity();
        source.forEach(gbfsVehicleTypeAreaCapacity -> mapped.setAdditionalProperty(gbfsVehicleTypeAreaCapacity.getVehicleTypeId(), gbfsVehicleTypeAreaCapacity.getCount().doubleValue()));
        return mapped;
    }

    org.entur.gbfs.v2_3.station_information.GBFSVehicleTypeCapacity mapVehicleTypeDockCapacityInverse(List<org.entur.gbfs.v3_0_RC.station_information.GBFSVehicleTypeDockCapacity> source) {
        if (source == null) {
            return null;
        }

        org.entur.gbfs.v2_3.station_information.GBFSVehicleTypeCapacity mapped = new org.entur.gbfs.v2_3.station_information.GBFSVehicleTypeCapacity();
        source.forEach(gbfsVehicleTypeDockCapacity -> mapped.setAdditionalProperty(gbfsVehicleTypeDockCapacity.getVehicleTypeId(), gbfsVehicleTypeDockCapacity.getCount().doubleValue()));
        return mapped;
    }
}
