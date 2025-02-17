package io.github.dreamylost.graphql.codegen

import java.util

import com.kobylynskyi.graphql.codegen.model._
import sbt._

/** @author
 *    梦境迷离
 *  @version 1.0,2020/7/15
 */
trait GraphQLCodegenKeys {

  // Conflict with SBT key
  val generatePackageName = settingKey[Option[String]]("generatePackageName")

  // Scala collection and asJava cannot be used. The latter one uses the put method, which is not supported by Scala collection
  val customTypesMapping = settingKey[util.Map[String, String]]("customTypesMapping")

  val apiNamePrefix = settingKey[Option[String]]("apiNamePrefix")

  val apiNameSuffix = settingKey[String]("apiNameSuffix")

  val apiRootInterfaceStrategy = settingKey[ApiRootInterfaceStrategy]("apiRootInterfaceStrategy")

  val apiNamePrefixStrategy = settingKey[ApiNamePrefixStrategy]("apiNamePrefixStrategy")

  val modelNamePrefix = settingKey[Option[String]]("Prefix to append to the model class names.")

  val modelNameSuffix = settingKey[Option[String]]("Suffix to append to the model class names.")

  val apiPackageName = settingKey[Option[String]]("Java package to use when generating the API classes.")

  val modelPackageName = settingKey[Option[String]]("Java package to use when generating the model classes.")

  val generateBuilder = settingKey[Boolean]("Specifies whether generated model classes should have builder.")

  val generateApis = settingKey[Boolean]("Specifies whether api classes should be generated as well as model classes.")

  val typeResolverPrefix = settingKey[Option[String]]("typeResolverPrefix")

  val typeResolverSuffix = settingKey[String]("typeResolverSuffix")

  val customAnnotationsMapping = settingKey[util.Map[String, util.List[String]]]("customAnnotationsMapping")

  val customTemplatesRoot = settingKey[File]("customTemplatesRoot")

  val customTemplates = settingKey[util.Map[String, String]]("customTemplates")

  val generateEqualsAndHashCode =
    settingKey[Boolean]("Specifies whether generated model classes should have equals and hashCode methods defined.")

  val generateImmutableModels = settingKey[Boolean]("generateImmutableModels")

  val generateToString =
    settingKey[Boolean]("Specifies whether generated model classes should have toString method defined.")

  val subscriptionReturnType = settingKey[Option[String]]("subscriptionReturnType")

  val modelValidationAnnotation = settingKey[String]("Annotation for mandatory (NonNull) fields. Can be None/empty.")

  val generateParameterizedFieldsResolvers = settingKey[Boolean](
    "If true, then generate separate Resolver interface for parametrized fields. If false, then add field to the type definition and ignore field parameters."
  )

  val generateExtensionFieldsResolvers = settingKey[Boolean](
    "Specifies whether all fields in extensions (extend type and extend interface) should be present in Resolver interface instead of the type class itself."
  )

  val addGeneratedAnnotation =
    settingKey[Boolean]("Specifies whether generated classes should be annotated with @Generated")

  val generatedAnnotation = settingKey[Option[String]](
    "Qualified class name (with package) of the @Generated annotation that will be added on top of every generated class (if addGeneratedAnnotation is true)"
  )

  val generateDataFetchingEnvironmentArgumentInApis = settingKey[Boolean](
    "If true, then graphql.schema.DataFetchingEnvironment env will be added as a last argument to all methods of root type resolvers and field resolvers."
  )

  val generateModelsForRootTypes = settingKey[Boolean]("generateModelsForRootTypes")

  val fieldsWithResolvers = settingKey[util.Set[String]]("fieldsWithResolvers")

  val fieldsWithoutResolvers = settingKey[util.Set[String]]("fieldsWithoutResolvers")

  val fieldsToExcludeFromGeneration = settingKey[util.Set[String]]("fieldsToExcludeFromGeneration")

  val typesAsInterfaces = settingKey[util.Set[String]]("typesAsInterfaces")

  val generateClient = settingKey[Boolean]("generateClient")

  val requestSuffix = settingKey[String](
    "Specifies whether client-side classes should be generated for each query, mutation and subscription. This includes: Request class (contains input data) and ResponseProjection class (contains response fields)."
  )

  val responseSuffix = settingKey[String]("responseSuffix")

  val responseProjectionSuffix = settingKey[String](
    "Specifies whether client-side classes should be generated for each query, mutation and subscription. This includes: Request class (contains input data) and ResponseProjection class (contains response fields)."
  )

  val parametrizedInputSuffix = settingKey[String]("parametrizedInputSuffix")

  val useObjectMapperForRequestSerialization = settingKey[util.Set[String]]("useObjectMapperForRequestSerialization")

  val configurationFiles = settingKey[Seq[String]](
    "configurationFiles, either JSON or HOCON. The same key is used in order, so the default configuration should be placed at the end."
  )

  val parentInterfaces = settingKey[ParentInterfacesConfig]("parentInterfaces")

  val graphqlSchemas = settingKey[SchemaFinderConfig]("graphqlSchemas")

  val outputDir = settingKey[File]("Where to store generated files")

  val graphqlSchemaPaths = settingKey[Seq[String]]("Locations of GraphQL schemas.")

  // use different paths
  val graphqlSchemaValidate = inputKey[Seq[String]]("graphqlSchemaValidatePaths")

  val graphqlCodegen = taskKey[Seq[File]]("Generate code")

  val graphqlCodegenValidate = taskKey[Unit]("Validate graphql schema")

  val apiReturnType = settingKey[Option[String]]("apiReturnType")

  val apiReturnListType = settingKey[Option[String]]("apiReturnListType")

  val directiveAnnotationsMapping = settingKey[util.Map[String, util.List[String]]]("directiveAnnotationsMapping")

  val apiInterfaceStrategy = settingKey[ApiInterfaceStrategy]("apiInterfaceStrategy")

  val useOptionalForNullableReturnTypes = settingKey[Boolean]("useOptionalForNullableReturnTypes")

  val generateApisWithThrowsException = settingKey[Boolean]("generateApisWithThrowsException")

  val graphqlQueryIntrospectionResultPath = settingKey[Option[String]]("graphqlQueryIntrospectionResultPath")

  val generateAllMethodInProjection = settingKey[Boolean]("generateAllMethodInProjection")

  val responseProjectionMaxDepth = settingKey[Int]("limit depth when the projection is constructed automatically")

  val relayConfig = settingKey[RelayConfig]("Can be used to supply a custom configuration for Relay support.")

  val generatedLanguage = settingKey[GeneratedLanguage]("Generate code with language, like java/scala.")

  val generateModelOpenClasses = settingKey[Boolean](
    "The class type of the generated model. If true, generate normal classes, else generate case class."
  )

  val generateSealedInterfaces = settingKey[Boolean](
    "If true, generate sealed interfaces for GraphQL unions and interfaces, else generate normal interfaces."
  )

  val generateJacksonTypeIdResolver = settingKey[Boolean](
    "Specifies whether generated union interfaces should be annotated with a custom Jackson type id resolver generated in model package."
  )

  // not supported in scala
  val generateNoArgsConstructorOnly = settingKey[Boolean](
    "Specifies whether model classes should only have a no-args constructor. All-args constructor will not be generated in case value is .true."
  )

  // not supported in scala
  val generateModelsWithPublicFields = settingKey[Boolean](
    "Specifies whether model classes should have public fields and NO getters/setters. By default, fields are private and there are getters/setters for each field."
  )

  // for version
  val javaxValidationApiVersion = settingKey[Option[String]]("javax-validation-api version")
  val graphqlJavaCodegenVersion = settingKey[Option[String]]("graphql-java-codegen version")

  // some others for sbt
  val generateCodegenTargetPath = settingKey[File](
    "Where to store generated files and add the generated code to the classpath, so that they can be referenced."
  )

  val supportUnknownFields      = settingKey[Boolean]("supportUnknownFields")
  val unknownFieldsPropertyName = settingKey[String]("unknownFieldsPropertyName")
  val skipSchemaSizeLimit       = settingKey[Boolean]("Skip schema size limit checks.")

}
