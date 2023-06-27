package org.entur.gbfs.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(imports = {List.class})
public abstract class SystemInformationFileAdditionalMapper {

    @SystemInformationDataMapper
    @Mapping(target = "languages", expression = "java(List.of(language))")
    abstract org.entur.gbfs.v3_0_RC.system_information.GBFSData mapData(org.entur.gbfs.v2_3.system_information.GBFSData source, @Context String language);

    List<org.entur.gbfs.v3_0_RC.system_information.GBFSName> mapName(String value, @Context String language) {
        if (value == null) {
            return null;
        }
        return List.of(
                new org.entur.gbfs.v3_0_RC.system_information.GBFSName()
                        .withText(value)
                        .withLanguage(language)
        );
    }

    String mapNameInverse(List<org.entur.gbfs.v3_0_RC.system_information.GBFSName> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.entur.gbfs.v3_0_RC.system_information.GBFSName::getText)
                .findFirst().orElse(null);
    }

    List<org.entur.gbfs.v3_0_RC.system_information.GBFSShortName> mapShortName(String value, @Context String language) {
        if (value == null) {
            return null;
        }
        return List.of(
                new org.entur.gbfs.v3_0_RC.system_information.GBFSShortName()
                        .withText(value)
                        .withLanguage(language)
        );
    }

    String mapShortNameInverse(List<org.entur.gbfs.v3_0_RC.system_information.GBFSShortName> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.entur.gbfs.v3_0_RC.system_information.GBFSShortName::getText)
                .findFirst().orElse(null);
    }

    List<org.entur.gbfs.v3_0_RC.system_information.GBFSOperator> mapOperator(String operator, @Context String language) {
        if (operator == null) {
            return null;
        }
        return List.of(
                new org.entur.gbfs.v3_0_RC.system_information.GBFSOperator()
                        .withText(operator)
                        .withLanguage(language)
        );
    }

    String mapOperatorInverse(List<org.entur.gbfs.v3_0_RC.system_information.GBFSOperator> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.entur.gbfs.v3_0_RC.system_information.GBFSOperator::getText)
                .findFirst().orElse(null);
    }

    List<org.entur.gbfs.v3_0_RC.system_information.GBFSTermsUrl> mapTermsUrl(String termsUrl, @Context String language) {
        if (termsUrl == null) {
            return null;
        }
        return List.of(
                new org.entur.gbfs.v3_0_RC.system_information.GBFSTermsUrl()
                        .withText(termsUrl)
                        .withLanguage(language)
        );
    }

    String mapTermsUrlInverse(List<org.entur.gbfs.v3_0_RC.system_information.GBFSTermsUrl> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.entur.gbfs.v3_0_RC.system_information.GBFSTermsUrl::getText)
                .findFirst().orElse(null);
    }

    List<org.entur.gbfs.v3_0_RC.system_information.GBFSPrivacyUrl> mapPrivacyUrl(String privacyUrl, @Context String language) {
        if (privacyUrl == null) {
            return null;
        }
        return List.of(
                new org.entur.gbfs.v3_0_RC.system_information.GBFSPrivacyUrl()
                        .withText(privacyUrl)
                        .withLanguage(language)
        );
    }

    String mapPrivacyUrlInverse(List<org.entur.gbfs.v3_0_RC.system_information.GBFSPrivacyUrl> values, @Context String language) {
        if (values == null) {
            return null;
        }

        return values.stream()
                .filter(name -> name.getLanguage().equals(language))
                .map(org.entur.gbfs.v3_0_RC.system_information.GBFSPrivacyUrl::getText)
                .findFirst().orElse(null);
    }
}
