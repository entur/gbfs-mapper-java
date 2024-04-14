package org.entur.gbfs.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GBFSFeedNameMapper {
    GBFSFeedNameMapper INSTANCE = Mappers.getMapper( GBFSFeedNameMapper.class );

    @ValueMapping(target = "GBFS", source = "GBFS")
    @ValueMapping(target = "GBFSVersions", source = "GBFS_VERSIONS")
    @ValueMapping(target = "SystemInformation", source = "SYSTEM_INFORMATION")
    @ValueMapping(target = "VehicleTypes", source = "VEHICLE_TYPES")
    @ValueMapping(target = "StationInformation", source = "STATION_INFORMATION")
    @ValueMapping(target = "StationStatus", source = "STATION_STATUS")
    @ValueMapping(target = "FreeBikeStatus", source = "VEHICLE_STATUS")
    @ValueMapping(target = "SystemAlerts", source = "SYSTEM_ALERTS")
    @ValueMapping(target = "SystemRegions", source = "SYSTEM_REGIONS")
    @ValueMapping(target = "SystemPricingPlans", source = "SYSTEM_PRICING_PLANS")
    @ValueMapping(target = "GeofencingZones", source = "GEOFENCING_ZONES")
    org.entur.gbfs.v2_3.gbfs.GBFSFeedName map(org.entur.gbfs.v3_0.gbfs.GBFSFeed.Name source);

    @InheritInverseConfiguration
    @ValueMapping(source = MappingConstants.ANY_REMAINING, target = MappingConstants.NULL)
    org.entur.gbfs.v3_0.gbfs.GBFSFeed.Name map(org.entur.gbfs.v2_3.gbfs.GBFSFeedName source);
}
