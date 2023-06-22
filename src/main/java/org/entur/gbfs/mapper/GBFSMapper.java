package org.entur.gbfs.mapper;

import org.entur.gbfs.mapper.util.DiscoveryFileMapperUtil;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = DiscoveryFileMapperUtil.class)
public interface GBFSMapper {
    GBFSMapper INSTANCE = Mappers.getMapper( GBFSMapper.class );

    @Mapping(target = "version", constant = "3.0-RC")
    @Mapping(target = "data", source = "feedsData")
    org.entur.gbfs.v3_0_RC.gbfs.GBFSGbfs map(org.entur.gbfs.v2_3.gbfs.GBFS source, @Context String sourceLanguage);

    @Mapping(target = "version", constant = "2.3")
    @Mapping(target = "feedsData", source = "data")
    @Mapping(target = "data", ignore = true)
    org.entur.gbfs.v2_3.gbfs.GBFS map(org.entur.gbfs.v3_0_RC.gbfs.GBFSGbfs source, @Context String targetLanguage);

    @Mapping(target = "version", constant = "3.0-RC")
    org.entur.gbfs.v3_0_RC.gbfs_versions.GBFSGbfsVersions map(org.entur.gbfs.v2_3.gbfs_versions.GBFSGbfsVersions source);
}
