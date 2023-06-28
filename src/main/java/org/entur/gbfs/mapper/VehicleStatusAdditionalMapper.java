package org.entur.gbfs.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface VehicleStatusAdditionalMapper {

    @VehicleStatusDataMapper
    @Mapping(target = "bikes", source = "vehicles")
    org.entur.gbfs.v2_3.free_bike_status.GBFSData mapData(org.entur.gbfs.v3_0_RC.vehicle_status.GBFSData source);

    @VehicleStatusDataMapper
    @InheritInverseConfiguration
    org.entur.gbfs.v3_0_RC.vehicle_status.GBFSData mapDataInverse(org.entur.gbfs.v2_3.free_bike_status.GBFSData source);

    @Mapping(target = "bikeId", source = "vehicleId")
    org.entur.gbfs.v2_3.free_bike_status.GBFSBike mapVehicle(org.entur.gbfs.v3_0_RC.vehicle_status.GBFSVehicle source);

    @InheritInverseConfiguration
    org.entur.gbfs.v3_0_RC.vehicle_status.GBFSVehicle mapVehicleInverse(org.entur.gbfs.v2_3.free_bike_status.GBFSBike source);
}
