package com.kobylynskyi.graphql.codegen.model;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * The type Mapping config.
 *
 * @author kobylynskyi
 * @author valinha
 */
public class MappingConfig implements GraphQLCodegenConfiguration, Combinable<MappingConfig> {

    // package name configs:
    private String packageName;
    private String apiPackageName;
    private String modelPackageName;

    // suffix/prefix/strategies:
    private String modelNamePrefix;
    private String modelNameSuffix;
    private String apiNamePrefix;
    private String apiNameSuffix;
    private String typeResolverPrefix;
    private String typeResolverSuffix;
    private ApiRootInterfaceStrategy apiRootInterfaceStrategy;
    private ApiInterfaceStrategy apiInterfaceStrategy;
    private ApiNamePrefixStrategy apiNamePrefixStrategy;
    private String modelValidationAnnotation;
    private String apiReturnType;
    private String apiReturnListType;
    private String subscriptionReturnType;
    private String unknownFieldsPropertyName;
    private String generatedAnnotation;

    // Relay config:
    private RelayConfig relayConfig = new RelayConfig();

    // various toggles:
    private Boolean generateApis;
    private Boolean generateBuilder;
    private Boolean generateEqualsAndHashCode;
    private Boolean generateToString;
    private Boolean generateImmutableModels;
    private Boolean generateParameterizedFieldsResolvers;
    private Boolean generateExtensionFieldsResolvers;
    private Boolean generateDataFetchingEnvironmentArgumentInApis;
    private Boolean generateModelsForRootTypes;
    private Boolean useOptionalForNullableReturnTypes;
    private Boolean generateApisWithThrowsException;
    private Boolean generateApisWithSuspendFunctions;
    private Boolean addGeneratedAnnotation;
    private Boolean generateJacksonTypeIdResolver;
    private Boolean supportUnknownFields;
    private Boolean generateNoArgsConstructorOnly;
    private Boolean generateModelsWithPublicFields;

    // field resolvers configs:
    private Set<String> fieldsWithResolvers = new HashSet<>();
    private Set<String> fieldsWithoutResolvers = new HashSet<>();
    private Set<String> fieldsToExcludeFromGeneration = new HashSet<>();

    // parent interfaces configs:
    private String queryResolverParentInterface;
    private String mutationResolverParentInterface;
    private String subscriptionResolverParentInterface;
    private String resolverParentInterface;

    // client-side codegen configs:
    private Boolean generateClient;
    private String requestSuffix;
    private String responseSuffix;
    private String responseProjectionSuffix;
    private String parametrizedInputSuffix;
    private Boolean generateAllMethodInProjection;
    private Integer responseProjectionMaxDepth;
    private Set<String> useObjectMapperForRequestSerialization = new HashSet<>();

    // annotations:
    private Map<String, List<String>> customAnnotationsMapping = new HashMap<>();
    private Map<String, List<String>> directiveAnnotationsMapping = new HashMap<>();
    private Set<String> resolverArgumentAnnotations = new HashSet<>();
    private Set<String> parametrizedResolverAnnotations = new HashSet<>();

    private Map<String, String> customTypesMapping = new HashMap<>();

    private File customTemplatesRoot = new File(".");
    private Map<String, String> customTemplates = new HashMap<>();

    private Set<String> typesAsInterfaces = new HashSet<>();

    private boolean generateModelOpenClasses;
    private boolean initializeNullableTypes;
    private boolean generateSealedInterfaces;

    private GeneratedLanguage generatedLanguage;

    private static <K, T> Map<K, T> combineMap(Map<K, T> thisMap, Map<K, T> otherMap) {
        if (thisMap != null && otherMap != null) {
            Map<K, T> resultMap = new HashMap<>();
            resultMap.putAll(thisMap);
            resultMap.putAll(otherMap);
            return resultMap;
        } else if (thisMap == null) {
            return otherMap;
        } else {
            return thisMap;
        }
    }

    private static Set<String> combineSet(Set<String> thisSet, Set<String> otherSet) {
        if (thisSet != null && otherSet != null) {
            Set<String> resultSet = new HashSet<>();
            resultSet.addAll(thisSet);
            resultSet.addAll(otherSet);
            return resultSet;
        } else if (thisSet == null) {
            return otherSet;
        } else {
            return thisSet;
        }
    }

    @Override
    public void combine(MappingConfig source) {
        if (source == null) {
            return;
        }
        generateApis = getValueOrDefaultToThis(source, GraphQLCodegenConfiguration::getGenerateApis);
        packageName = getValueOrDefaultToThis(source, GraphQLCodegenConfiguration::getPackageName);
        apiPackageName = getValueOrDefaultToThis(source, GraphQLCodegenConfiguration::getApiPackageName);
        modelPackageName = getValueOrDefaultToThis(source, GraphQLCodegenConfiguration::getModelPackageName);
        modelNamePrefix = getValueOrDefaultToThis(source, GraphQLCodegenConfiguration::getModelNamePrefix);
        modelNameSuffix = getValueOrDefaultToThis(source, GraphQLCodegenConfiguration::getModelNameSuffix);
        apiNamePrefix = getValueOrDefaultToThis(source, GraphQLCodegenConfiguration::getApiNamePrefix);
        apiNameSuffix = getValueOrDefaultToThis(source, GraphQLCodegenConfiguration::getApiNameSuffix);
        apiRootInterfaceStrategy = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getApiRootInterfaceStrategy);
        apiInterfaceStrategy = getValueOrDefaultToThis(source, GraphQLCodegenConfiguration::getApiInterfaceStrategy);
        apiNamePrefixStrategy = getValueOrDefaultToThis(source, GraphQLCodegenConfiguration::getApiNamePrefixStrategy);
        typeResolverPrefix = getValueOrDefaultToThis(source, GraphQLCodegenConfiguration::getTypeResolverPrefix);
        typeResolverSuffix = getValueOrDefaultToThis(source, GraphQLCodegenConfiguration::getTypeResolverSuffix);
        modelValidationAnnotation = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getModelValidationAnnotation);
        apiReturnType = getValueOrDefaultToThis(source, GraphQLCodegenConfiguration::getApiReturnType);
        apiReturnListType = getValueOrDefaultToThis(source, GraphQLCodegenConfiguration::getApiReturnListType);
        subscriptionReturnType = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getSubscriptionReturnType);
        generateBuilder = getValueOrDefaultToThis(source, GraphQLCodegenConfiguration::getGenerateBuilder);
        generateEqualsAndHashCode = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getGenerateEqualsAndHashCode);
        generateImmutableModels = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getGenerateImmutableModels);
        generateToString = getValueOrDefaultToThis(source, GraphQLCodegenConfiguration::getGenerateToString);
        generateParameterizedFieldsResolvers = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getGenerateParameterizedFieldsResolvers);
        generateExtensionFieldsResolvers = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getGenerateExtensionFieldsResolvers);
        generateDataFetchingEnvironmentArgumentInApis = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getGenerateDataFetchingEnvironmentArgumentInApis);
        generateModelsForRootTypes = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getGenerateModelsForRootTypes);
        useOptionalForNullableReturnTypes = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getUseOptionalForNullableReturnTypes);
        generateApisWithThrowsException = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getGenerateApisWithThrowsException);
        generateApisWithSuspendFunctions = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getGenerateApisWithSuspendFunctions);
        addGeneratedAnnotation = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getAddGeneratedAnnotation);
        generateJacksonTypeIdResolver = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getGenerateJacksonTypeIdResolver);
        relayConfig = getValueOrDefaultToThis(source, GraphQLCodegenConfiguration::getRelayConfig);
        queryResolverParentInterface = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getQueryResolverParentInterface);
        mutationResolverParentInterface = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getMutationResolverParentInterface);
        subscriptionResolverParentInterface = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getSubscriptionResolverParentInterface);
        resolverParentInterface = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getResolverParentInterface);
        generateClient = getValueOrDefaultToThis(source, GraphQLCodegenConfiguration::getGenerateClient);
        requestSuffix = getValueOrDefaultToThis(source, GraphQLCodegenConfiguration::getRequestSuffix);
        responseSuffix = getValueOrDefaultToThis(source, GraphQLCodegenConfiguration::getResponseSuffix);
        responseProjectionSuffix = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getResponseProjectionSuffix);
        parametrizedInputSuffix = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getParametrizedInputSuffix);
        fieldsWithResolvers = combineSet(fieldsWithResolvers, source.fieldsWithResolvers);
        fieldsWithoutResolvers = combineSet(fieldsWithoutResolvers, source.fieldsWithoutResolvers);
        fieldsToExcludeFromGeneration = combineSet(fieldsToExcludeFromGeneration, source.fieldsToExcludeFromGeneration);
        customTypesMapping = combineMap(customTypesMapping, source.customTypesMapping);
        customTemplates = combineMap(customTemplates, source.customTemplates);
        customAnnotationsMapping = combineMap(customAnnotationsMapping, source.customAnnotationsMapping);
        directiveAnnotationsMapping = combineMap(directiveAnnotationsMapping, source.directiveAnnotationsMapping);
        resolverArgumentAnnotations = combineSet(resolverArgumentAnnotations, source.resolverArgumentAnnotations);
        parametrizedResolverAnnotations = combineSet(parametrizedResolverAnnotations,
                source.parametrizedResolverAnnotations);
        generateAllMethodInProjection = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getGenerateAllMethodInProjection);
        responseProjectionMaxDepth = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getResponseProjectionMaxDepth);
        useObjectMapperForRequestSerialization = combineSet(useObjectMapperForRequestSerialization,
                source.useObjectMapperForRequestSerialization);
        typesAsInterfaces = combineSet(typesAsInterfaces, source.typesAsInterfaces);
        generatedLanguage = getValueOrDefaultToThis(source, GraphQLCodegenConfiguration::getGeneratedLanguage);
        generateModelOpenClasses = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::isGenerateModelOpenClasses);
        initializeNullableTypes = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::isInitializeNullableTypes);
        generateSealedInterfaces = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::isGenerateSealedInterfaces);
        supportUnknownFields = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::isSupportUnknownFields);
        unknownFieldsPropertyName = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getUnknownFieldsPropertyName);
        generatedAnnotation = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::getGeneratedAnnotation);
        generateNoArgsConstructorOnly = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::isGenerateNoArgsConstructorOnly);
        generateModelsWithPublicFields = getValueOrDefaultToThis(source,
                GraphQLCodegenConfiguration::isGenerateModelsWithPublicFields);
    }

    private <T> T getValueOrDefaultToThis(MappingConfig source, Function<MappingConfig, T> getValueFunction) {
        T sourceValue = getValueFunction.apply(source);
        return sourceValue != null ? sourceValue : getValueFunction.apply(this);
    }

    /**
     * Put custom type mapping if absent.
     *
     * @param from the from
     * @param to   the to
     */
    public void putCustomTypeMappingIfAbsent(String from, String to) {
        if (customTypesMapping == null) {
            customTypesMapping = new HashMap<>();
        }
        customTypesMapping.computeIfAbsent(from, k -> to);
    }

    @Override
    public Map<String, String> getCustomTypesMapping() {
        return customTypesMapping;
    }

    public void setCustomTypesMapping(Map<String, String> customTypesMapping) {
        this.customTypesMapping = customTypesMapping;
    }

    @Override
    public File getCustomTemplatesRoot() {
        return customTemplatesRoot;
    }

    public void setCustomTemplatesRoot(File customTemplatesRoot) {
        this.customTemplatesRoot = customTemplatesRoot;
    }

    @Override
    public Map<String, String> getCustomTemplates() {
        return customTemplates;
    }

    public void setCustomTemplates(Map<String, String> customTemplates) {
        this.customTemplates = customTemplates;
    }

    /**
     * Provide a path to a custom template for the specific FreeMarker template type (if absent).
     *
     * @param from the from
     * @param to   the to
     */
    public void putCustomTemplatesIfAbsent(String from, String to) {
        if (customTemplates == null) {
            customTemplates = new HashMap<>();
        }
        customTemplates.computeIfAbsent(from, k -> to);
    }

    @Override
    public Map<String, List<String>> getCustomAnnotationsMapping() {
        return customAnnotationsMapping;
    }

    public void setCustomAnnotationsMapping(Map<String, List<String>> customAnnotationsMapping) {
        this.customAnnotationsMapping = customAnnotationsMapping;
    }

    @Override
    public Map<String, List<String>> getDirectiveAnnotationsMapping() {
        return directiveAnnotationsMapping;
    }

    public void setDirectiveAnnotationsMapping(Map<String, List<String>> directiveAnnotationsMapping) {
        this.directiveAnnotationsMapping = directiveAnnotationsMapping;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String getApiPackageName() {
        return apiPackageName;
    }

    public void setApiPackageName(String apiPackageName) {
        this.apiPackageName = apiPackageName;
    }

    @Override
    public String getModelPackageName() {
        return modelPackageName;
    }

    public void setModelPackageName(String modelPackageName) {
        this.modelPackageName = modelPackageName;
    }

    @Override
    public String getModelNamePrefix() {
        return modelNamePrefix;
    }

    public void setModelNamePrefix(String modelNamePrefix) {
        this.modelNamePrefix = modelNamePrefix;
    }

    @Override
    public String getModelNameSuffix() {
        return modelNameSuffix;
    }

    public void setModelNameSuffix(String modelNameSuffix) {
        this.modelNameSuffix = modelNameSuffix;
    }

    @Override
    public String getApiNamePrefix() {
        return apiNamePrefix;
    }

    public void setApiNamePrefix(String apiNamePrefix) {
        this.apiNamePrefix = apiNamePrefix;
    }

    @Override
    public String getApiNameSuffix() {
        return apiNameSuffix;
    }

    public void setApiNameSuffix(String apiNameSuffix) {
        this.apiNameSuffix = apiNameSuffix;
    }

    @Override
    public String getTypeResolverPrefix() {
        return typeResolverPrefix;
    }

    public void setTypeResolverPrefix(String typeResolverPrefix) {
        this.typeResolverPrefix = typeResolverPrefix;
    }

    @Override
    public String getTypeResolverSuffix() {
        return typeResolverSuffix;
    }

    public void setTypeResolverSuffix(String typeResolverSuffix) {
        this.typeResolverSuffix = typeResolverSuffix;
    }

    @Override
    public ApiRootInterfaceStrategy getApiRootInterfaceStrategy() {
        return apiRootInterfaceStrategy;
    }

    public void setApiRootInterfaceStrategy(ApiRootInterfaceStrategy apiRootInterfaceStrategy) {
        this.apiRootInterfaceStrategy = apiRootInterfaceStrategy;
    }

    @Override
    public ApiInterfaceStrategy getApiInterfaceStrategy() {
        return apiInterfaceStrategy;
    }

    public void setApiInterfaceStrategy(ApiInterfaceStrategy apiInterfaceStrategy) {
        this.apiInterfaceStrategy = apiInterfaceStrategy;
    }

    @Override
    public ApiNamePrefixStrategy getApiNamePrefixStrategy() {
        return apiNamePrefixStrategy;
    }

    public void setApiNamePrefixStrategy(ApiNamePrefixStrategy apiNamePrefixStrategy) {
        this.apiNamePrefixStrategy = apiNamePrefixStrategy;
    }

    @Override
    public String getModelValidationAnnotation() {
        return modelValidationAnnotation;
    }

    public void setModelValidationAnnotation(String modelValidationAnnotation) {
        this.modelValidationAnnotation = modelValidationAnnotation;
    }

    @Override
    public String getApiReturnType() {
        return apiReturnType;
    }

    public void setApiReturnType(String apiReturnType) {
        this.apiReturnType = apiReturnType;
    }

    @Override
    public String getApiReturnListType() {
        return apiReturnListType;
    }

    public void setApiReturnListType(String apiReturnListType) {
        this.apiReturnListType = apiReturnListType;
    }

    @Override
    public String getSubscriptionReturnType() {
        return subscriptionReturnType;
    }

    public void setSubscriptionReturnType(String subscriptionReturnType) {
        this.subscriptionReturnType = subscriptionReturnType;
    }

    @Override
    public Boolean getGenerateApis() {
        return generateApis;
    }

    public void setGenerateApis(Boolean generateApis) {
        this.generateApis = generateApis;
    }

    @Override
    public Boolean getGenerateBuilder() {
        return generateBuilder;
    }

    public void setGenerateBuilder(Boolean generateBuilder) {
        this.generateBuilder = generateBuilder;
    }

    @Override
    public Boolean getGenerateEqualsAndHashCode() {
        return generateEqualsAndHashCode;
    }

    public void setGenerateEqualsAndHashCode(Boolean generateEqualsAndHashCode) {
        this.generateEqualsAndHashCode = generateEqualsAndHashCode;
    }

    @Override
    public Boolean getGenerateToString() {
        return generateToString;
    }

    public void setGenerateToString(Boolean generateToString) {
        this.generateToString = generateToString;
    }

    @Override
    public Boolean getGenerateImmutableModels() {
        return generateImmutableModels;
    }

    public void setGenerateImmutableModels(Boolean generateImmutableModels) {
        this.generateImmutableModels = generateImmutableModels;
    }

    @Override
    public Boolean getGenerateParameterizedFieldsResolvers() {
        return generateParameterizedFieldsResolvers;
    }

    public void setGenerateParameterizedFieldsResolvers(Boolean generateParameterizedFieldsResolvers) {
        this.generateParameterizedFieldsResolvers = generateParameterizedFieldsResolvers;
    }

    @Override
    public Boolean getGenerateExtensionFieldsResolvers() {
        return generateExtensionFieldsResolvers;
    }

    public void setGenerateExtensionFieldsResolvers(Boolean generateExtensionFieldsResolvers) {
        this.generateExtensionFieldsResolvers = generateExtensionFieldsResolvers;
    }

    @Override
    public Boolean getGenerateDataFetchingEnvironmentArgumentInApis() {
        return generateDataFetchingEnvironmentArgumentInApis;
    }

    public void setGenerateDataFetchingEnvironmentArgumentInApis(
            Boolean generateDataFetchingEnvironmentArgumentInApis) {
        this.generateDataFetchingEnvironmentArgumentInApis = generateDataFetchingEnvironmentArgumentInApis;
    }

    @Override
    public Boolean getGenerateApisWithThrowsException() {
        return generateApisWithThrowsException;
    }

    public void setGenerateApisWithThrowsException(Boolean generateApisWithThrowsException) {
        this.generateApisWithThrowsException = generateApisWithThrowsException;
    }

    @Override
    public Boolean getGenerateApisWithSuspendFunctions() {
        return generateApisWithSuspendFunctions;
    }

    public void setGenerateApisWithSuspendFunctions(Boolean generateApisWithSuspendFunctions) {
        this.generateApisWithSuspendFunctions = generateApisWithSuspendFunctions;
    }

    @Override
    public Boolean getAddGeneratedAnnotation() {
        return addGeneratedAnnotation;
    }

    public void setAddGeneratedAnnotation(Boolean addGeneratedAnnotation) {
        this.addGeneratedAnnotation = addGeneratedAnnotation;
    }

    @Override
    public Boolean getGenerateJacksonTypeIdResolver() {
        return generateJacksonTypeIdResolver;
    }

    public void setGenerateJacksonTypeIdResolver(Boolean generateJacksonTypeIdResolver) {
        this.generateJacksonTypeIdResolver = generateJacksonTypeIdResolver;
    }

    @Override
    public RelayConfig getRelayConfig() {
        return relayConfig;
    }

    public void setRelayConfig(RelayConfig relayConfig) {
        this.relayConfig = relayConfig;
    }

    @Override
    public Boolean getGenerateModelsForRootTypes() {
        return generateModelsForRootTypes;
    }

    public void setGenerateModelsForRootTypes(Boolean generateModelsForRootTypes) {
        this.generateModelsForRootTypes = generateModelsForRootTypes;
    }

    @Override
    public Boolean getUseOptionalForNullableReturnTypes() {
        return useOptionalForNullableReturnTypes;
    }

    public void setUseOptionalForNullableReturnTypes(Boolean useOptionalForNullableReturnTypes) {
        this.useOptionalForNullableReturnTypes = useOptionalForNullableReturnTypes;
    }

    @Override
    public Set<String> getFieldsWithResolvers() {
        return fieldsWithResolvers;
    }

    public void setFieldsWithResolvers(Set<String> fieldsWithResolvers) {
        this.fieldsWithResolvers = fieldsWithResolvers;
    }

    @Override
    public Set<String> getFieldsWithoutResolvers() {
        return fieldsWithoutResolvers;
    }

    public void setFieldsWithoutResolvers(Set<String> fieldsWithoutResolvers) {
        this.fieldsWithoutResolvers = fieldsWithoutResolvers;
    }

    @Override
    public Set<String> getFieldsToExcludeFromGeneration() {
        return fieldsToExcludeFromGeneration;
    }

    public void setFieldsToExcludeFromGeneration(Set<String> fieldsToExcludeFromGeneration) {
        this.fieldsToExcludeFromGeneration = fieldsToExcludeFromGeneration;
    }

    @Override
    public String getQueryResolverParentInterface() {
        return queryResolverParentInterface;
    }

    public void setQueryResolverParentInterface(String queryResolverParentInterface) {
        this.queryResolverParentInterface = queryResolverParentInterface;
    }

    @Override
    public String getMutationResolverParentInterface() {
        return mutationResolverParentInterface;
    }

    public void setMutationResolverParentInterface(String mutationResolverParentInterface) {
        this.mutationResolverParentInterface = mutationResolverParentInterface;
    }

    @Override
    public String getSubscriptionResolverParentInterface() {
        return subscriptionResolverParentInterface;
    }

    public void setSubscriptionResolverParentInterface(String subscriptionResolverParentInterface) {
        this.subscriptionResolverParentInterface = subscriptionResolverParentInterface;
    }

    @Override
    public String getResolverParentInterface() {
        return resolverParentInterface;
    }

    public void setResolverParentInterface(String resolverParentInterface) {
        this.resolverParentInterface = resolverParentInterface;
    }

    @Override
    public Boolean getGenerateClient() {
        return generateClient;
    }

    public void setGenerateClient(Boolean generateClient) {
        this.generateClient = generateClient;
    }

    @Override
    public String getRequestSuffix() {
        return requestSuffix;
    }

    public void setRequestSuffix(String requestSuffix) {
        this.requestSuffix = requestSuffix;
    }

    @Override
    public String getResponseSuffix() {
        return responseSuffix;
    }

    public void setResponseSuffix(String responseSuffix) {
        this.responseSuffix = responseSuffix;
    }

    @Override
    public String getResponseProjectionSuffix() {
        return responseProjectionSuffix;
    }

    public void setResponseProjectionSuffix(String responseProjectionSuffix) {
        this.responseProjectionSuffix = responseProjectionSuffix;
    }

    @Override
    public String getParametrizedInputSuffix() {
        return parametrizedInputSuffix;
    }

    public void setParametrizedInputSuffix(String parametrizedInputSuffix) {
        this.parametrizedInputSuffix = parametrizedInputSuffix;
    }

    @Override
    public Boolean getGenerateAllMethodInProjection() {
        return generateAllMethodInProjection;
    }

    public void setGenerateAllMethodInProjection(Boolean generateAllMethodInProjection) {
        this.generateAllMethodInProjection = generateAllMethodInProjection;
    }

    @Override
    public Integer getResponseProjectionMaxDepth() {
        return responseProjectionMaxDepth;
    }

    public void setResponseProjectionMaxDepth(Integer responseProjectionMaxDepth) {
        this.responseProjectionMaxDepth = responseProjectionMaxDepth;
    }

    @Override
    public Set<String> getUseObjectMapperForRequestSerialization() {
        return useObjectMapperForRequestSerialization;
    }

    public void setUseObjectMapperForRequestSerialization(Set<String> useObjectMapperForRequestSerialization) {
        this.useObjectMapperForRequestSerialization = useObjectMapperForRequestSerialization;
    }

    @Override
    public Set<String> getResolverArgumentAnnotations() {
        return resolverArgumentAnnotations;
    }

    public void setResolverArgumentAnnotations(Set<String> resolverArgumentAnnotations) {
        this.resolverArgumentAnnotations = resolverArgumentAnnotations;
    }

    @Override
    public Set<String> getParametrizedResolverAnnotations() {
        return parametrizedResolverAnnotations;
    }

    public void setParametrizedResolverAnnotations(Set<String> parametrizedResolverAnnotations) {
        this.parametrizedResolverAnnotations = parametrizedResolverAnnotations;
    }

    @Override
    public Set<String> getTypesAsInterfaces() {
        return typesAsInterfaces;
    }

    public void setTypesAsInterfaces(Set<String> typesAsInterfaces) {
        this.typesAsInterfaces = typesAsInterfaces;
    }

    @Override
    public String getUnknownFieldsPropertyName() {
        return unknownFieldsPropertyName;
    }

    public void setUnknownFieldsPropertyName(String unknownFieldsPropertyName) {
        this.unknownFieldsPropertyName = unknownFieldsPropertyName;
    }

    @Override
    public String getGeneratedAnnotation() {
        return generatedAnnotation;
    }

    public void setGeneratedAnnotation(String generatedAnnotation) {
        this.generatedAnnotation = generatedAnnotation;
    }

    @Override
    public Boolean isSupportUnknownFields() {
        return supportUnknownFields;
    }

    public void setSupportUnknownFields(Boolean supportUnknownFields) {
        this.supportUnknownFields = supportUnknownFields;
    }

    @Override
    public GeneratedLanguage getGeneratedLanguage() {
        return generatedLanguage;
    }

    public void setGeneratedLanguage(GeneratedLanguage generatedLanguage) {
        this.generatedLanguage = generatedLanguage;
    }

    @Override
    public Boolean isGenerateModelOpenClasses() {
        return generateModelOpenClasses;
    }

    public void setGenerateModelOpenClasses(boolean generateModelOpenClasses) {
        this.generateModelOpenClasses = generateModelOpenClasses;
    }

    @Override
    public Boolean isInitializeNullableTypes() {
        return initializeNullableTypes;
    }

    public void setInitializeNullableTypes(boolean initializeNullableTypes) {
        this.initializeNullableTypes = initializeNullableTypes;
    }

    @Override
    public Boolean isGenerateSealedInterfaces() {
        return generateSealedInterfaces;
    }

    public void setGenerateSealedInterfaces(boolean generateSealedInterfaces) {
        this.generateSealedInterfaces = generateSealedInterfaces;
    }

    @Override
    public Boolean isGenerateNoArgsConstructorOnly() {
        return generateNoArgsConstructorOnly;
    }

    public void setGenerateNoArgsConstructorOnly(Boolean generateNoArgsConstructorOnly) {
        this.generateNoArgsConstructorOnly = generateNoArgsConstructorOnly;
    }

    @Override
    public Boolean isGenerateModelsWithPublicFields() {
        return generateModelsWithPublicFields;
    }

    public void setGenerateModelsWithPublicFields(Boolean generateModelsWithPublicFields) {
        this.generateModelsWithPublicFields = generateModelsWithPublicFields;
    }
}
