package org.entur.gbfs.mapper;

import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {GbfsDiscoveryFileAdditionalMapping.class})
public interface GbfsDiscoveryFileMapper {
    GbfsDiscoveryFileMapper INSTANCE = Mappers.getMapper( GbfsDiscoveryFileMapper.class );

    @Mapping(target = "withVersion", ignore = true)
    @Mapping(target = "withTtl", ignore = true)
    @Mapping(target = "withRequired", ignore = true)
    @Mapping(target = "withLastUpdated", ignore = true)
    @Mapping(target = "withData", ignore = true)
    @Mapping(target = "required", ignore = true)
    @Mapping(target = "version", constant = "3.0-RC")
    @Mapping(target = "data", source = "feedsData")
    org.entur.gbfs.v3_0_RC.gbfs.GBFSGbfs map(org.entur.gbfs.v2_3.gbfs.GBFS source, @Context String sourceLanguage);

    @Mapping(target = "withVersion", ignore = true)
    @Mapping(target = "withTtl", ignore = true)
    @Mapping(target = "withLastUpdated", ignore = true)
    @Mapping(target = "withData", ignore = true)
    @Mapping(target = "additionalProperties", ignore = true)
    @Mapping(target = "version", constant = "2.3")
    @Mapping(target = "data", ignore = true)
    @InheritInverseConfiguration
    org.entur.gbfs.v2_3.gbfs.GBFS map(org.entur.gbfs.v3_0_RC.gbfs.GBFSGbfs source, @Context String targetLanguage);
}
