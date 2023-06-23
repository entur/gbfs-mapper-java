package org.entur.gbfs.mapper;

import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
        GbfsDiscoveryFileAdditionalMapping.class,
        GbfsSystemInformationFileAdditionalMapping.class
})
public interface GBFSMapper {
    GBFSMapper INSTANCE = Mappers.getMapper( GBFSMapper.class );

    @Mapping(target = "version", constant = "3.0-RC")
    @Mapping(target = "data", source = "feedsData")
    org.entur.gbfs.v3_0_RC.gbfs.GBFSGbfs map(org.entur.gbfs.v2_3.gbfs.GBFS source, @Context String sourceLanguage);

    @Mapping(target = "version", constant = "2.3")
    @Mapping(target = "data", ignore = true)
    @InheritInverseConfiguration
    org.entur.gbfs.v2_3.gbfs.GBFS map(org.entur.gbfs.v3_0_RC.gbfs.GBFSGbfs source, @Context String targetLanguage);

    @Mapping(target = "version", constant = "3.0-RC")
    org.entur.gbfs.v3_0_RC.gbfs_versions.GBFSGbfsVersions map(org.entur.gbfs.v2_3.gbfs_versions.GBFSGbfsVersions source);

    /**
     * Not able to map inverse due to missing "forward" enum values in schema
     * E.g. There's no 3.0-RC enum value in 2.3 schema
     */
//    @Mapping(target = "version", constant = "2.3")
//    org.entur.gbfs.v2_3.gbfs_versions.GBFSGbfsVersions map(org.entur.gbfs.v3_0_RC.gbfs_versions.GBFSGbfsVersions source);

    @Mapping(target = "version", constant = "3.0-RC")
    org.entur.gbfs.v3_0_RC.system_information.GBFSSystemInformation map(org.entur.gbfs.v2_3.system_information.GBFSSystemInformation source, @Context String language);

    @Mapping(target = "version", constant = "2.3")
    org.entur.gbfs.v2_3.system_information.GBFSSystemInformation map(org.entur.gbfs.v3_0_RC.system_information.GBFSSystemInformation source, @Context String language);
}
