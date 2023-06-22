package org.entur.gbfs.mapper;

import org.entur.gbfs.mapper.util.DiscoveryFileMapperUtil;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper
public interface GBFSMapper {
    GBFSMapper INSTANCE = Mappers.getMapper( GBFSMapper.class );

    @Mapping(target = "version", constant = "3.0-RC")
    @Mapping(target = "data", source = "feedsData")
    org.entur.gbfs.v3_0_RC.gbfs.GBFSGbfs map(org.entur.gbfs.v2_3.gbfs.GBFS source, @Context String sourceLanguage);

    default org.entur.gbfs.v3_0_RC.gbfs.GBFSData map(Map<String, org.entur.gbfs.v2_3.gbfs.GBFSFeeds> source, @Context String sourceLanguage) {
        List<org.entur.gbfs.v3_0_RC.gbfs.GBFSFeed> mappedFeeds = source.get(sourceLanguage).getFeeds().stream()
                    .filter(DiscoveryFileMapperUtil::filterLegacySourceFeeds)
                    .map(DiscoveryFileMapperUtil::map).collect(Collectors.toList());

        return new org.entur.gbfs.v3_0_RC.gbfs.GBFSData()
                .withFeeds(mappedFeeds);
    }


    @Mapping(target = "version", constant = "2.3")
    @Mapping(target = "feedsData", source = "data")
    @Mapping(target = "data", ignore = true)
    org.entur.gbfs.v2_3.gbfs.GBFS map(org.entur.gbfs.v3_0_RC.gbfs.GBFSGbfs source, @Context String targetLanguage);

    default Map<String, org.entur.gbfs.v2_3.gbfs.GBFSFeeds> map(org.entur.gbfs.v3_0_RC.gbfs.GBFSData source, @Context String targetLanguage) {
        org.entur.gbfs.v2_3.gbfs.GBFSFeeds mappedFeeds = new org.entur.gbfs.v2_3.gbfs.GBFSFeeds();
        mappedFeeds.setFeeds(source.getFeeds().stream().map(DiscoveryFileMapperUtil::map).collect(Collectors.toList()));

        return Map.of(
                targetLanguage,
                mappedFeeds
        );
    }
}
