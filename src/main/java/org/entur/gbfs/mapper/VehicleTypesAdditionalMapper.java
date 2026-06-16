package org.entur.gbfs.mapper;

import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;

import java.util.List;

@Mapper
public abstract class VehicleTypesAdditionalMapper {
    @Mapping(source = "ecoLabel", target = "ecoLabels")
    abstract org.mobilitydata.gbfs.v3_0.vehicle_types.GBFSVehicleType mapVehicleType(org.mobilitydata.gbfs.v2_3.vehicle_types.GBFSVehicleType value, @Context String language);

    @InheritInverseConfiguration
    abstract org.mobilitydata.gbfs.v2_3.vehicle_types.GBFSVehicleType mapVehicleTypeInverse(org.mobilitydata.gbfs.v3_0.vehicle_types.GBFSVehicleType value, @Context String language);

    // The deprecated v2.x "scooter" form factor was removed in v3.0; map it to its
    // recommended replacement "scooter_standing". A dedicated inverse keeps every v3
    // value mapping to its identically-named v2 counterpart (rather than inheriting
    // the inverse of the value mapping above).
    @ValueMapping(source = "SCOOTER", target = "SCOOTER_STANDING")
    abstract org.mobilitydata.gbfs.v3_0.vehicle_types.GBFSVehicleType.FormFactor mapFormFactor(org.mobilitydata.gbfs.v2_3.vehicle_types.GBFSVehicleType.FormFactor value);

    abstract org.mobilitydata.gbfs.v2_3.vehicle_types.GBFSVehicleType.FormFactor mapFormFactorInverse(org.mobilitydata.gbfs.v3_0.vehicle_types.GBFSVehicleType.FormFactor value);

    List<org.mobilitydata.gbfs.v3_0.vehicle_types.GBFSName> mapVehicleTypeName(String value, @Context String language) {
        if (value == null) {
            return null;
        }
        return List.of(
                new org.mobilitydata.gbfs.v3_0.vehicle_types.GBFSName()
                        .withText(value)
                        .withLanguage(language)
        );
    }

    String mapVehicleTypeNameInverse(List<org.mobilitydata.gbfs.v3_0.vehicle_types.GBFSName> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.mobilitydata.gbfs.v3_0.vehicle_types.GBFSName::getText)
                .findFirst().orElse(null);
    }

    List<org.mobilitydata.gbfs.v3_0.vehicle_types.GBFSMake> mapVehicleTypeMake(String value, @Context String language) {
        if (value == null) {
            return null;
        }
        return List.of(
                new org.mobilitydata.gbfs.v3_0.vehicle_types.GBFSMake()
                        .withText(value)
                        .withLanguage(language)
        );
    }

    String mapVehicleTypeMakeInverse(List<org.mobilitydata.gbfs.v3_0.vehicle_types.GBFSMake> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.mobilitydata.gbfs.v3_0.vehicle_types.GBFSMake::getText)
                .findFirst().orElse(null);
    }

    List<org.mobilitydata.gbfs.v3_0.vehicle_types.GBFSModel> mapVehicleTypeModel(String value, @Context String language) {
        if (value == null) {
            return null;
        }
        return List.of(
                new org.mobilitydata.gbfs.v3_0.vehicle_types.GBFSModel()
                        .withText(value)
                        .withLanguage(language)
        );
    }

    String mapVehicleTypeModelInverse(List<org.mobilitydata.gbfs.v3_0.vehicle_types.GBFSModel> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.mobilitydata.gbfs.v3_0.vehicle_types.GBFSModel::getText)
                .findFirst().orElse(null);
    }
}
