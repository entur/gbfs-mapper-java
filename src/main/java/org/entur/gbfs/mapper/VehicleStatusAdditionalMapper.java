package org.entur.gbfs.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        uses = {
                DateMapper.class
        }
)
public abstract class  VehicleStatusAdditionalMapper {

    @VehicleStatusDataMapper
    @Mapping(target = "bikes", source = "vehicles")
    abstract org.entur.gbfs.v2_3.free_bike_status.GBFSData mapData(org.entur.gbfs.v3_0_RC2.vehicle_status.GBFSData source);

    @VehicleStatusDataMapper
    @InheritInverseConfiguration
    abstract org.entur.gbfs.v3_0_RC2.vehicle_status.GBFSData mapDataInverse(org.entur.gbfs.v2_3.free_bike_status.GBFSData source);

    @Mapping(target = "bikeId", source = "vehicleId")
    abstract org.entur.gbfs.v2_3.free_bike_status.GBFSBike mapVehicle(org.entur.gbfs.v3_0_RC2.vehicle_status.GBFSVehicle source);

    @InheritInverseConfiguration
    abstract org.entur.gbfs.v3_0_RC2.vehicle_status.GBFSVehicle mapVehicleInverse(org.entur.gbfs.v2_3.free_bike_status.GBFSBike source);
}
