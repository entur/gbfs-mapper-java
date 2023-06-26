package org.entur.gbfs.mapper;

import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
        DiscoveryFileAdditionalMapper.class,
        SystemInformationFileAdditionalMapper.class,
        VehicleTypesAdditionalMapper.class
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
    org.entur.gbfs.v3_0_RC.system_information.GBFSSystemInformation map(org.entur.gbfs.v2_3.system_information.GBFSSystemInformation source, @Context String language);

    @Mapping(target = "version", constant = "2.3")
    org.entur.gbfs.v2_3.system_information.GBFSSystemInformation map(org.entur.gbfs.v3_0_RC.system_information.GBFSSystemInformation source, @Context String language);

    @Mapping(target = "version", constant = "_3_0_RC")
    org.entur.gbfs.v3_0_RC.vehicle_types.GBFSVehicleTypes map(org.entur.gbfs.v2_3.vehicle_types.GBFSVehicleTypes source, @Context String language);

    @Mapping(target = "version", constant = "2.3")
    org.entur.gbfs.v2_3.vehicle_types.GBFSVehicleTypes map(org.entur.gbfs.v3_0_RC.vehicle_types.GBFSVehicleTypes source, @Context String language);
}
