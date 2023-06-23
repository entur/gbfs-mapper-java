package org.entur.gbfs.mapper;

//import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GbfsVersionsFileMapper {
    GbfsVersionsFileMapper INSTANCE = Mappers.getMapper( GbfsVersionsFileMapper.class );

    @Mapping(target = "withVersion", ignore = true)
    @Mapping(target = "withTtl", ignore = true)
    @Mapping(target = "withLastUpdated", ignore = true)
    @Mapping(target = "withData", ignore = true)
    @Mapping(target = "version", constant = "3.0-RC")
    org.entur.gbfs.v3_0_RC.gbfs_versions.GBFSGbfsVersions map(org.entur.gbfs.v2_3.gbfs_versions.GBFSGbfsVersions source);

/**
 * Not able to map inverse due to missing "forward" enum values in schema
 * E.g. There's no 3.0-RC enum value in 2.3 schema
 */
//    @Mapping(target = "version", constant = "2.3")
//    @InheritInverseConfiguration
//    org.entur.gbfs.v2_3.gbfs_versions.GBFSGbfsVersions map(org.entur.gbfs.v3_0_RC.gbfs_versions.GBFSGbfsVersions source);
}
