# AGENTS.md

This file provides guidance to AI agents when working with code in this repository.

## Project Overview

This is a Java library that provides bidirectional mapping between GBFS (General Bikeshare Feed Specification) versions 2.x and 3.x. The project uses MapStruct for compile-time type-safe mapping between the two versions.

Key dependency: [gbfs-java-model](https://github.com/entur/gbfs-java-model) provides the GBFS data models for both versions.

## Build and Test Commands

### Building the project
```bash
mvn clean install
```

### Running tests
```bash
mvn test
```

### Running a single test class
```bash
mvn test -Dtest=SystemInformationMapperTest
```

### Running a single test method
```bash
mvn test -Dtest=SystemInformationMapperTest#testMapSystemInformationFile
```

### Verify build with code coverage
```bash
mvn verify
```

### Check for dependency vulnerabilities
```bash
mvn org.owasp:dependency-check-maven:check
```

## Architecture

### MapStruct-based Mapping System

The architecture uses MapStruct annotation processors to generate mapping code at compile time. The main entry point is `GBFSMapper.INSTANCE`, which provides bidirectional mapping methods for all GBFS file types.

**Mapper Categories:**

1. **Main Mapper** (`GBFSMapper.java`): Central interface defining all bidirectional mapping methods. Uses `@Mapper(uses = {...})` to compose all specialized mappers.

2. **Additional Mappers** (`*AdditionalMapper.java`): Handle complex field transformations that MapStruct cannot auto-generate, particularly:
   - Internationalization: Converting between v2.x single-language strings and v3.x multi-language arrays
   - Custom business logic: e.g., `SystemInformationAdditionalMapper` converts v2.x system_hours data into v3.x OpenStreetMap-formatted opening_hours strings

3. **Data Mappers** (`*DataMapper.java`): Custom qualifiers (annotations) used to distinguish between different mapping methods with the same signature.

4. **Utility Mappers**:
   - `DateMapper.java`: Converts between Java Date objects and Unix timestamps (Integer/Double)
   - `GBFSFeedNameMapper.java`: Maps feed name enums between versions

### Language Handling

GBFS v2.x uses single-language files with language as context, while v3.x embeds language in fields. All mapping methods require a `@Context String language` parameter:
- **Mapping to v3.x**: Language parameter populates all language fields in arrays
- **Mapping from v3.x**: Language parameter selects which translation to extract (other translations are lost)

Example usage:
```java
var v3File = GBFSMapper.INSTANCE.map(v2File, "en");
var v2File = GBFSMapper.INSTANCE.map(v3File, "en");
```

### Special Case: System Hours

GBFS v2.x has a separate `system_hours.json` file, which was merged into `system_information.json` in v3.x. The mapper provides an overloaded method to handle this:

```java
// Without system_hours (uses default "24/7")
GBFSMapper.INSTANCE.map(systemInfo, "en");

// With system_hours (converts to opening_hours format)
GBFSMapper.INSTANCE.map(systemInfo, systemHours, "en");
```

The `SystemInformationAdditionalMapper.mapOpeningHours()` method transforms v2.x rental hours into OpenStreetMap opening_hours format (e.g., "Mo 08:00-18:00; Tu 09:00-17:00").

## Testing Strategy

Tests use **snapshot testing** via the `java-snapshot-testing` library. Snapshots are stored in `src/test/java/org/entur/gbfs/mapper/__snapshots__/`.

Test fixtures are JSON files in `src/test/resources/fixtures/`:
- `v2_3/` - GBFS v2.3 sample files
- `v3_0/` - GBFS v3.0 sample files

Each mapper test typically includes:
1. Forward mapping test (v2.x → v3.x)
2. Inverse mapping test (v3.x → v2.x)
3. Snapshot assertions to detect unintended changes

When modifying mappers, regenerate snapshots if the output changes intentionally.

## Important Implementation Notes

### Geofencing Zone Semantics

The semantics of polygon geometries in `geofencing_zones.json` changed in v3.x (clockwise vs counterclockwise meanings were reversed). The mapper does NOT handle this semantic change - polygons are mapped as-is. Consumers must handle the semantic differences.

### Maven Compiler Configuration

MapStruct annotation processing is configured in `pom.xml` under `maven-compiler-plugin` with `annotationProcessorPaths`. If you add new mappers, ensure they follow the existing patterns to be picked up by the processor.

### Java Version

Project requires Java 17 (specified in `pom.xml`). The enforcer plugin will fail builds on other versions.
