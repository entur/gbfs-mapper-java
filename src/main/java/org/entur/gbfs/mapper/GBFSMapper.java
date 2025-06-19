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
        SystemAlertsAdditionalMapper.class,
        DateMapper.class
})
public interface GBFSMapper {
    GBFSMapper INSTANCE = Mappers.getMapper( GBFSMapper.class );

    @Mapping(target = "version", constant = "3.0")
    @Mapping(target = "data", source = "feedsData")
    org.mobilitydata.gbfs.v3_0.gbfs.GBFSGbfs map(org.mobilitydata.gbfs.v2_3.gbfs.GBFS source, @Context String language);

    @Mapping(target = "version", constant = "2.3")
    @Mapping(target = "data", ignore = true)
    @InheritInverseConfiguration
    org.mobilitydata.gbfs.v2_3.gbfs.GBFS map(org.mobilitydata.gbfs.v3_0.gbfs.GBFSGbfs source, @Context String language);

    @Mapping(target = "version", constant = "3.0")
    org.mobilitydata.gbfs.v3_0.gbfs_versions.GBFSGbfsVersions map(org.mobilitydata.gbfs.v2_3.gbfs_versions.GBFSGbfsVersions source, @Context String language);

    @Mapping(target = "version", constant = "2.3")
    org.mobilitydata.gbfs.v2_3.gbfs_versions.GBFSGbfsVersions map(org.mobilitydata.gbfs.v3_0.gbfs_versions.GBFSGbfsVersions source, @Context String language);

    @Mapping(target = "version", constant = "3.0")
    @Mapping(target = "data", qualifiedBy = SystemInformationDataMapper.class)
    org.mobilitydata.gbfs.v3_0.system_information.GBFSSystemInformation map(org.mobilitydata.gbfs.v2_3.system_information.GBFSSystemInformation source, @Context String language);


    @Mapping(target = "version", constant = "3.0")
    @Mapping(target = "data", qualifiedBy = SystemInformationDataMapper.class)
    org.mobilitydata.gbfs.v3_0.system_information.GBFSSystemInformation map(org.mobilitydata.gbfs.v2_3.system_information.GBFSSystemInformation source, @Context org.mobilitydata.gbfs.v2_3.system_hours.GBFSData systemHours, @Context String language);

    @Mapping(target = "version", constant = "2.3")
    @Mapping(target = "data", qualifiedBy = SystemInformationDataMapper.class)
    org.mobilitydata.gbfs.v2_3.system_information.GBFSSystemInformation map(org.mobilitydata.gbfs.v3_0.system_information.GBFSSystemInformation source, @Context String language);

    @Mapping(target = "version", constant = "3.0")
    org.mobilitydata.gbfs.v3_0.vehicle_types.GBFSVehicleTypes map(org.mobilitydata.gbfs.v2_3.vehicle_types.GBFSVehicleTypes source, @Context String language);

    @Mapping(target = "version", constant = "2.3")
    org.mobilitydata.gbfs.v2_3.vehicle_types.GBFSVehicleTypes map(org.mobilitydata.gbfs.v3_0.vehicle_types.GBFSVehicleTypes source, @Context String language);

    @Mapping(target = "version", constant = "3.0")
    @Mapping(target = "data", qualifiedBy = VehicleStatusDataMapper.class)
    org.mobilitydata.gbfs.v3_0.vehicle_status.GBFSVehicleStatus map(org.mobilitydata.gbfs.v2_3.free_bike_status.GBFSFreeBikeStatus source, @Context String language);

    @Mapping(target = "version", constant = "2.3")
    @Mapping(target = "data", qualifiedBy = VehicleStatusDataMapper.class)
    org.mobilitydata.gbfs.v2_3.free_bike_status.GBFSFreeBikeStatus map(org.mobilitydata.gbfs.v3_0.vehicle_status.GBFSVehicleStatus source, @Context String language);

    @Mapping(target = "version", constant = "3.0")
    org.mobilitydata.gbfs.v3_0.station_information.GBFSStationInformation map(org.mobilitydata.gbfs.v2_3.station_information.GBFSStationInformation source, @Context String language);

    @Mapping(target = "version", constant = "2.3")
    org.mobilitydata.gbfs.v2_3.station_information.GBFSStationInformation map(org.mobilitydata.gbfs.v3_0.station_information.GBFSStationInformation source, @Context String language);

    @Mapping(target = "version", constant = "3.0")
    org.mobilitydata.gbfs.v3_0.station_status.GBFSStationStatus map(org.mobilitydata.gbfs.v2_3.station_status.GBFSStationStatus source, @Context String language);

    @Mapping(target = "version", constant = "2.3")
    org.mobilitydata.gbfs.v2_3.station_status.GBFSStationStatus map(org.mobilitydata.gbfs.v3_0.station_status.GBFSStationStatus source, @Context String language);

    @Mapping(target = "version", constant = "3.0")
    org.mobilitydata.gbfs.v3_0.system_pricing_plans.GBFSSystemPricingPlans map(org.mobilitydata.gbfs.v2_3.system_pricing_plans.GBFSSystemPricingPlans source, @Context String language);

    @Mapping(target = "version", constant = "2.3")
    org.mobilitydata.gbfs.v2_3.system_pricing_plans.GBFSSystemPricingPlans map(org.mobilitydata.gbfs.v3_0.system_pricing_plans.GBFSSystemPricingPlans source, @Context String language);

    @Mapping(target = "version", constant = "3.0")
    org.mobilitydata.gbfs.v3_0.geofencing_zones.GBFSGeofencingZones map(org.mobilitydata.gbfs.v2_3.geofencing_zones.GBFSGeofencingZones source, @Context String language);

    @Mapping(target = "version", constant = "2.3")
    org.mobilitydata.gbfs.v2_3.geofencing_zones.GBFSGeofencingZones map(org.mobilitydata.gbfs.v3_0.geofencing_zones.GBFSGeofencingZones source, @Context String language);

    @Mapping(target = "version", constant = "3.0")
    org.mobilitydata.gbfs.v3_0.system_regions.GBFSSystemRegions map(org.mobilitydata.gbfs.v2_3.system_regions.GBFSSystemRegions source, @Context String language);

    @Mapping(target = "version", constant = "2.3")
    org.mobilitydata.gbfs.v2_3.system_regions.GBFSSystemRegions map(org.mobilitydata.gbfs.v3_0.system_regions.GBFSSystemRegions source, @Context String language);

    @Mapping(target = "version", constant = "3.0")
    org.mobilitydata.gbfs.v3_0.system_alerts.GBFSSystemAlerts map(org.mobilitydata.gbfs.v2_3.system_alerts.GBFSSystemAlerts source, @Context String language);

    @Mapping(target = "version", constant = "2.3")
    org.mobilitydata.gbfs.v2_3.system_alerts.GBFSSystemAlerts map(org.mobilitydata.gbfs.v3_0.system_alerts.GBFSSystemAlerts source, @Context String language);
}
