package org.entur.gbfs.mapper;

import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
        DiscoveryFileAdditionalMapper.class,
        SystemInformationAdditionalMapper.class,
        VehicleTypesAdditionalMapper.class,
        VehicleStatusAdditionalMapper.class,
        StationInformationAdditionalMapper.class,
        StationStatusAdditionalMapper.class,
        SystemPricingPlansAdditionalMapper.class,
        GeofencingZonesAdditionalMapper.class,
        SystemRegionsAdditionalMapper.class,
        SystemAlertsAdditionalMapper.class
})
public interface GBFSMapper {
    GBFSMapper INSTANCE = Mappers.getMapper( GBFSMapper.class );

    @Mapping(target = "version", constant = "_3_0_RC")
    @Mapping(target = "data", source = "feedsData")
    org.entur.gbfs.v3_0_RC.gbfs.GBFSGbfs map(org.entur.gbfs.v2_3.gbfs.GBFS source, @Context String language);

    @Mapping(target = "version", constant = "2.3")
    @Mapping(target = "data", ignore = true)
    @InheritInverseConfiguration
    org.entur.gbfs.v2_3.gbfs.GBFS map(org.entur.gbfs.v3_0_RC.gbfs.GBFSGbfs source, @Context String language);

    @Mapping(target = "version", constant = "_3_0_RC")
    org.entur.gbfs.v3_0_RC.gbfs_versions.GBFSGbfsVersions map(org.entur.gbfs.v2_3.gbfs_versions.GBFSGbfsVersions source);

    @Mapping(target = "version", constant = "2.3")
    org.entur.gbfs.v2_3.gbfs_versions.GBFSGbfsVersions map(org.entur.gbfs.v3_0_RC.gbfs_versions.GBFSGbfsVersions source);

    @Mapping(target = "version", constant = "_3_0_RC")
    @Mapping(target = "data", qualifiedBy = SystemInformationDataMapper.class)
    org.entur.gbfs.v3_0_RC.system_information.GBFSSystemInformation map(org.entur.gbfs.v2_3.system_information.GBFSSystemInformation source, @Context String language);

    @Mapping(target = "version", constant = "2.3")
    @Mapping(target = "data", qualifiedBy = SystemInformationDataMapper.class)
    org.entur.gbfs.v2_3.system_information.GBFSSystemInformation map(org.entur.gbfs.v3_0_RC.system_information.GBFSSystemInformation source, @Context String language);

    @Mapping(target = "version", constant = "_3_0_RC")
    org.entur.gbfs.v3_0_RC.vehicle_types.GBFSVehicleTypes map(org.entur.gbfs.v2_3.vehicle_types.GBFSVehicleTypes source, @Context String language);

    @Mapping(target = "version", constant = "2.3")
    org.entur.gbfs.v2_3.vehicle_types.GBFSVehicleTypes map(org.entur.gbfs.v3_0_RC.vehicle_types.GBFSVehicleTypes source, @Context String language);

    @Mapping(target = "version", constant = "_3_0_RC")
    @Mapping(target = "data", qualifiedBy = VehicleStatusDataMapper.class)
    org.entur.gbfs.v3_0_RC.vehicle_status.GBFSVehicleStatus map(org.entur.gbfs.v2_3.free_bike_status.GBFSFreeBikeStatus source);

    @Mapping(target = "version", constant = "2.3")
    @Mapping(target = "data", qualifiedBy = VehicleStatusDataMapper.class)
    org.entur.gbfs.v2_3.free_bike_status.GBFSFreeBikeStatus map(org.entur.gbfs.v3_0_RC.vehicle_status.GBFSVehicleStatus source);

    @Mapping(target = "version", constant = "_3_0_RC")
    org.entur.gbfs.v3_0_RC.station_information.GBFSStationInformation map(org.entur.gbfs.v2_3.station_information.GBFSStationInformation source, @Context String language);

    @Mapping(target = "version", constant = "2.3")
    org.entur.gbfs.v2_3.station_information.GBFSStationInformation map(org.entur.gbfs.v3_0_RC.station_information.GBFSStationInformation source, @Context String language);

    @Mapping(target = "version", constant = "_3_0_RC")
    org.entur.gbfs.v3_0_RC.station_status.GBFSStationStatus map(org.entur.gbfs.v2_3.station_status.GBFSStationStatus source);

    @Mapping(target = "version", constant = "2.3")
    org.entur.gbfs.v2_3.station_status.GBFSStationStatus map(org.entur.gbfs.v3_0_RC.station_status.GBFSStationStatus source);

    @Mapping(target = "version", constant = "_3_0_RC")
    org.entur.gbfs.v3_0_RC.system_pricing_plans.GBFSSystemPricingPlans map(org.entur.gbfs.v2_3.system_pricing_plans.GBFSSystemPricingPlans source, @Context String language);

    @Mapping(target = "version", constant = "2.3")
    org.entur.gbfs.v2_3.system_pricing_plans.GBFSSystemPricingPlans map(org.entur.gbfs.v3_0_RC.system_pricing_plans.GBFSSystemPricingPlans source, @Context String language);

    @Mapping(target = "version", constant = "_3_0_RC")
    org.entur.gbfs.v3_0_RC.geofencing_zones.GBFSGeofencingZones map(org.entur.gbfs.v2_3.geofencing_zones.GBFSGeofencingZones source, @Context String language);

    @Mapping(target = "version", constant = "2.3")
    org.entur.gbfs.v2_3.geofencing_zones.GBFSGeofencingZones map(org.entur.gbfs.v3_0_RC.geofencing_zones.GBFSGeofencingZones source, @Context String language);

    @Mapping(target = "version", constant = "_3_0_RC")
    org.entur.gbfs.v3_0_RC.system_regions.GBFSSystemRegions map(org.entur.gbfs.v2_3.system_regions.GBFSSystemRegions source, @Context String language);

    @Mapping(target = "version", constant = "2.3")
    org.entur.gbfs.v2_3.system_regions.GBFSSystemRegions map(org.entur.gbfs.v3_0_RC.system_regions.GBFSSystemRegions source, @Context String language);

    @Mapping(target = "version", constant = "_3_0_RC")
    org.entur.gbfs.v3_0_RC.system_alerts.GBFSSystemAlerts map(org.entur.gbfs.v2_3.system_alerts.GBFSSystemAlerts source, @Context String language);

    @Mapping(target = "version", constant = "2.3")
    org.entur.gbfs.v2_3.system_alerts.GBFSSystemAlerts map(org.entur.gbfs.v3_0_RC.system_alerts.GBFSSystemAlerts source, @Context String language);
}
