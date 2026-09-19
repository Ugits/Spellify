# Spellify

A Java and Spring Boot REST API for browsing and managing Dungeons & Dragons spells. Spellify stores a local spell collection in PostgreSQL and integrates with an external spell API to retrieve and synchronise data.

## Features

- Browse stored spells by name, level, maximum level, ritual status, concentration, casting time, duration, and range.
- Retrieve spell details directly from an external API.
- Add individual spells or batches, update existing records, and delete spells through management endpoints.
- Synchronise external spell data with the local database and export spell names to a JSON file.
- Validate incoming data and return structured errors through a central exception handler.

## Technology and structure

Built with **Java 17**, **Spring Boot 3.3.3**, **Spring Data JPA**, **PostgreSQL**, and **Gradle**. External HTTP requests use Spring WebClient and Reactor.

Controllers handle HTTP requests, services contain application logic, and repositories handle persistence. DTOs separate request and external API formats from database entities. The external API integration lives in its own package.

| Package | Responsibility |
| --- | --- |
| `controller` | Client and management endpoints |
| `service` | Spell queries, updates, synchronisation, and file access |
| `repository` | Database queries through Spring Data JPA |
| `model` | Entities, DTOs, and validation |
| `api` | External API client, models, service, and endpoints |
| `exception` | Application exceptions and HTTP error handling |

Source packages are under `src/main/java/org/jonas/spellify`.

## Run locally

### Requirements

- JDK 17
- A running PostgreSQL instance and an empty development database
- Access to an external spell API compatible with the models in `api/model`

The Gradle wrapper is included. Local application configuration is intentionally excluded from Git.

### Configuration

Clone the repository and create `src/main/resources/application.properties`. The following is a **local development template**; replace the placeholders with your own values:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/spellify
spring.datasource.username=YOUR_DATABASE_USER
spring.datasource.password=YOUR_DATABASE_PASSWORD

# Convenient for a disposable local database; not a migration strategy.
spring.jpa.hibernate.ddl-auto=update

API_URL=YOUR_COMPATIBLE_EXTERNAL_API_BASE_URL
spell.names.file.path=src/main/resources/spell_names.json

spring.security.user.name=localuser
spring.security.user.password=YOUR_LOCAL_PASSWORD
```

The external client appends `/spells` and `/spells/{index}` to `API_URL`. Use a base URL whose response format matches the checked-in DTOs.

Create `src/main/resources/spell_names.json` with `[]` as its initial content. Synchronisation later writes the spell names to this location. Run the application from the repository root so that the relative file path resolves correctly.

### Start

macOS / Linux:

```sh
chmod +x gradlew
./gradlew bootRun
```

Windows PowerShell:

```powershell
.\gradlew.bat bootRun
```

With the template above, Spring Boot uses its default HTTP port, 8080.

## API examples

Spring Security is included, but no custom security configuration is checked in. Under Spring Boot's default security behaviour, requests require authentication. These read-only examples use the local username configured above; curl prompts for the password.

```sh
# Browse the local collection.
curl --user localuser "http://localhost:8080/spells/all"

# Filter the local collection by spell level.
curl --user localuser "http://localhost:8080/spells/level/1"

# Find ritual spells up to a maximum level.
curl --user localuser "http://localhost:8080/spells/ritual/true?max-level=3"

# Retrieve the external spell catalogue.
curl --user localuser "http://localhost:8080/api/spells"
```

In Windows PowerShell, use `curl.exe` if `curl` resolves to a PowerShell alias. A new local database contains no spells until populated.

| Endpoint group | Purpose |
| --- | --- |
| `/spells` | Read and filter locally stored spells |
| `/api/spells` | Retrieve data from the external API |
| `/admin/spells` | Add, update, delete, and synchronise stored spells |

Detailed endpoint documentation:

- [Client endpoints](src/main/java/org/jonas/spellify/documentation/client_docs.md)
- [Management endpoints](src/main/java/org/jonas/spellify/documentation/admin_docs.md)
- [External API endpoints](src/main/java/org/jonas/spellify/documentation/external_api_docs.md)

## Current limitations

- Local configuration and spell data are not bundled. External API compatibility and availability must be verified for the chosen provider.
- Client and management controllers are separated in code, but that is not role-based authorisation. No custom access rules are checked in; default Spring Security and CSRF protection apply. Management writes need an appropriate security configuration or authenticated CSRF flow before they can be exercised.
- Synchronisation writes the spell-name file to a hard-coded source-tree path, while reading uses a configurable path. Keep the paths aligned for local use; packaged deployments need a different file-storage approach.
- Bulk external retrieval requests details for every spell. It depends on the provider's capacity and response format.
- The repository includes an application-context test, rather than a comprehensive automated test suite.

## Build and tests

```sh
./gradlew build
./gradlew test
```

On Windows, use `.\gradlew.bat` instead of `./gradlew`. The context test requires valid application configuration and a reachable database. These commands are provided as instructions; no successful build or test run is claimed by this README.

## Related projects

- [RoleMate Backend](https://github.com/Ugits/RoleMate-Backend)
- [RoleMate Frontend](https://github.com/Ugits/RoleMate-Frontend)
