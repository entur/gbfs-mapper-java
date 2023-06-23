package org.entur.gbfs.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = GbfsSystemInformationFileAdditionalMapping.class)
public interface GbfsSystemInformationFileMapper {
    GbfsSystemInformationFileMapper INSTANCE = Mappers.getMapper( GbfsSystemInformationFileMapper.class );

    @Mapping(target = "version", constant = "3.0-RC")
    org.entur.gbfs.v3_0_RC.system_information.GBFSSystemInformation map(org.entur.gbfs.v2_3.system_information.GBFSSystemInformation source, @Context String language);

    @Mapping(target = "version", constant = "2.3")
    org.entur.gbfs.v2_3.system_information.GBFSSystemInformation map(org.entur.gbfs.v3_0_RC.system_information.GBFSSystemInformation source, @Context String language);
}
