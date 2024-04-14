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
    abstract org.entur.gbfs.v3_0.station_status.GBFSStation mapStation(org.entur.gbfs.v2_3.station_status.GBFSStation source);

    @InheritInverseConfiguration
    abstract org.entur.gbfs.v2_3.station_status.GBFSStation mapStationInverse(org.entur.gbfs.v3_0.station_status.GBFSStation source);
}
