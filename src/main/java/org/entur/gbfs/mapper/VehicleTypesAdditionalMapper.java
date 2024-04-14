package org.entur.gbfs.mapper;

import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public abstract class VehicleTypesAdditionalMapper {
    @Mapping(source = "ecoLabel", target = "ecoLabels")
    abstract org.entur.gbfs.v3_0.vehicle_types.GBFSVehicleType mapVehicleType(org.entur.gbfs.v2_3.vehicle_types.GBFSVehicleType value, @Context String language);

    @InheritInverseConfiguration
    abstract org.entur.gbfs.v2_3.vehicle_types.GBFSVehicleType mapVehicleTypeInverse(org.entur.gbfs.v3_0.vehicle_types.GBFSVehicleType value, @Context String language);

    List<org.entur.gbfs.v3_0.vehicle_types.GBFSName> mapVehicleTypeName(String value, @Context String language) {
        if (value == null) {
            return null;
        }
        return List.of(
                new org.entur.gbfs.v3_0.vehicle_types.GBFSName()
                        .withText(value)
                        .withLanguage(language)
        );
    }

    String mapVehicleTypeNameInverse(List<org.entur.gbfs.v3_0.vehicle_types.GBFSName> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.entur.gbfs.v3_0.vehicle_types.GBFSName::getText)
                .findFirst().orElse(null);
    }

    List<org.entur.gbfs.v3_0.vehicle_types.GBFSMake> mapVehicleTypeMake(String value, @Context String language) {
        if (value == null) {
            return null;
        }
        return List.of(
                new org.entur.gbfs.v3_0.vehicle_types.GBFSMake()
                        .withText(value)
                        .withLanguage(language)
        );
    }

    String mapVehicleTypeMakeInverse(List<org.entur.gbfs.v3_0.vehicle_types.GBFSMake> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.entur.gbfs.v3_0.vehicle_types.GBFSMake::getText)
                .findFirst().orElse(null);
    }

    List<org.entur.gbfs.v3_0.vehicle_types.GBFSModel> mapVehicleTypeModel(String value, @Context String language) {
        if (value == null) {
            return null;
        }
        return List.of(
                new org.entur.gbfs.v3_0.vehicle_types.GBFSModel()
                        .withText(value)
                        .withLanguage(language)
        );
    }

    String mapVehicleTypeModelInverse(List<org.entur.gbfs.v3_0.vehicle_types.GBFSModel> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.entur.gbfs.v3_0.vehicle_types.GBFSModel::getText)
                .findFirst().orElse(null);
    }
}
