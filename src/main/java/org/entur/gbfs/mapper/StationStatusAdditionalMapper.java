package org.entur.gbfs.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {
        DateMapper.class
})
public abstract class StationStatusAdditionalMapper {
    @Mapping(target = "numVehiclesAvailable", source = "numBikesAvailable")
    @Mapping(target = "numVehiclesDisabled", source = "numBikesDisabled")
    abstract org.mobilitydata.gbfs.v3_0.station_status.GBFSStation mapStation(org.mobilitydata.gbfs.v2_3.station_status.GBFSStation source);

    @InheritInverseConfiguration
    abstract org.mobilitydata.gbfs.v2_3.station_status.GBFSStation mapStationInverse(org.mobilitydata.gbfs.v3_0.station_status.GBFSStation source);
}
