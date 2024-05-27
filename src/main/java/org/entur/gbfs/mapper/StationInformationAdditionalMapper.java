package org.entur.gbfs.mapper;

import org.mobilitydata.gbfs.v3_0.station_information.GBFSVehicleTypesCapacity;
import org.mobilitydata.gbfs.v3_0.station_information.GBFSVehicleDocksCapacity;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public abstract class StationInformationAdditionalMapper {
    List<org.mobilitydata.gbfs.v3_0.station_information.GBFSName> mapName(String value, @Context String language) {
        if (value == null) {
            return null;
        }
        return List.of(
                new org.mobilitydata.gbfs.v3_0.station_information.GBFSName()
                        .withText(value)
                        .withLanguage(language)
        );
    }

    String mapNameInverse(List<org.mobilitydata.gbfs.v3_0.station_information.GBFSName> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.mobilitydata.gbfs.v3_0.station_information.GBFSName::getText)
                .findFirst().orElse(null);
    }

    List<org.mobilitydata.gbfs.v3_0.station_information.GBFSShortName> mapShortName(String value, @Context String language) {
        if (value == null) {
            return null;
        }
        return List.of(
                new org.mobilitydata.gbfs.v3_0.station_information.GBFSShortName()
                        .withText(value)
                        .withLanguage(language)
        );
    }

    String mapShortNameInverse(List<org.mobilitydata.gbfs.v3_0.station_information.GBFSShortName> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.mobilitydata.gbfs.v3_0.station_information.GBFSShortName::getText)
                .findFirst().orElse(null);
    }

    @Mapping(source = "vehicleCapacity", target = "vehicleTypesCapacity")
    @Mapping(source = "vehicleTypeCapacity", target = "vehicleDocksCapacity")
    abstract org.mobilitydata.gbfs.v3_0.station_information.GBFSStation mapStation(org.mobilitydata.gbfs.v2_3.station_information.GBFSStation source, @Context String language);

    List<org.mobilitydata.gbfs.v3_0.station_information.GBFSVehicleTypesCapacity> mapVehicleTypeAreaCapacity(org.mobilitydata.gbfs.v2_3.station_information.GBFSVehicleCapacity source) {
        if (source == null) {
            return null;
        }
        return source.getAdditionalProperties().entrySet().stream().map(
                entry -> new GBFSVehicleTypesCapacity()
                        .withVehicleTypeIds(List.of(entry.getKey()))
                        .withCount(entry.getValue().intValue())
        ).toList();
    }

    List<org.mobilitydata.gbfs.v3_0.station_information.GBFSVehicleDocksCapacity> mapVehicleTypeDockCapacity(org.mobilitydata.gbfs.v2_3.station_information.GBFSVehicleTypeCapacity source) {
        if (source == null) {
            return null;
        }
        return source.getAdditionalProperties().entrySet().stream().map(
                entry -> new GBFSVehicleDocksCapacity()
                        .withVehicleTypeIds(List.of(entry.getKey()))
                        .withCount(entry.getValue().intValue())
        ).toList();
    }

    @InheritInverseConfiguration
    abstract org.mobilitydata.gbfs.v2_3.station_information.GBFSStation mapStationInverse(org.mobilitydata.gbfs.v3_0.station_information.GBFSStation source, @Context String language);

    org.mobilitydata.gbfs.v2_3.station_information.GBFSVehicleCapacity mapVehicleTypeAreaCapacityInverse(List<org.mobilitydata.gbfs.v3_0.station_information.GBFSVehicleTypesCapacity> source) {
        if (source == null) {
            return null;
        }
        org.mobilitydata.gbfs.v2_3.station_information.GBFSVehicleCapacity mapped = new org.mobilitydata.gbfs.v2_3.station_information.GBFSVehicleCapacity();
        source.forEach(gbfsVehicleTypesCapacity -> gbfsVehicleTypesCapacity.getVehicleTypeIds().forEach(vehicleTypeId -> mapped.setAdditionalProperty(vehicleTypeId, gbfsVehicleTypesCapacity.getCount().doubleValue())));
        return mapped;
    }

    org.mobilitydata.gbfs.v2_3.station_information.GBFSVehicleTypeCapacity mapVehicleTypeDockCapacityInverse(List<org.mobilitydata.gbfs.v3_0.station_information.GBFSVehicleDocksCapacity> source) {
        if (source == null) {
            return null;
        }

        org.mobilitydata.gbfs.v2_3.station_information.GBFSVehicleTypeCapacity mapped = new org.mobilitydata.gbfs.v2_3.station_information.GBFSVehicleTypeCapacity();
        source.forEach(gbfsVehicleTypeDockCapacity -> gbfsVehicleTypeDockCapacity.getVehicleTypeIds().forEach(vehicleTypeId -> mapped.setAdditionalProperty(vehicleTypeId, gbfsVehicleTypeDockCapacity.getCount().doubleValue())));
        return mapped;
    }
}
