# Changelog

All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2026-10-27

First documented release of the Catch-Up Platform: a Spring Boot REST service
for managing users' favorite news sources, organized into the `news` and
`shared` bounded contexts following Domain-Driven Design.

### Added

- Favorite sources REST API under `/api/v1/favorite-sources`:
  - `POST /api/v1/favorite-sources` to create a favorite source.
  - `GET /api/v1/favorite-sources/{id}` to retrieve a favorite source by its identifier.
  - `GET /api/v1/favorite-sources` to list favorite sources by News API key, or retrieve one by News API key and source id.
- `news` bounded context:
  - `FavoriteSource` aggregate, `NewsApiKey` and `SourceId` value objects, and the `CreateFavoriteSourceCommand` command.
  - Queries to get favorite sources by id, by News API key, and by News API key and source id.
  - Command and query services with their application-layer contracts and `FavoriteSourceCommandFailure`.
  - Spring Data JPA `FavoriteSourceRepository` and JPA attribute converters for `NewsApiKey` and `SourceId`.
  - REST resources and assemblers that map commands, queries, and their results to HTTP responses.
- `shared` bounded context:
  - `Result` abstraction for command outcomes.
  - `SnakeCasePhysicalNamingStrategy`, converting identifiers to snake_case and using plural table names.
- Environment-specific configuration for the default, `dev`, and `prod` Spring profiles, with database credentials resolved from environment variables.
- Internationalized validation and error messages (`messages.properties`, `messages_es.properties`).
- JPA auditing is enabled for the application.
- SpringDoc OpenAPI / Swagger UI for API documentation.
- Project documentation: `README.md`, `CONTRIBUTING.md`, `LICENSE.md` (MIT), architecture decision records (`docs/adrs.md`), a class diagram (`docs/class-diagram.puml`), and technical user stories (`docs/user-stories.md`).
- Project metadata in `pom.xml`: MIT license and developer information.

### Changed

- Project version updated from `0.0.1-SNAPSHOT` to `1.0.0`.
- Dependency and build updates in `pom.xml`, including Lombok, SpringDoc, MySQL connector, Jackson, Logback, Tomcat, and `maven-compiler-plugin`.
- `NewsApiKey` and `SourceId` now share the allowed pattern `^[A-Za-z0-9._:-]+$`, so values may include dots, colons, underscores, and hyphens. Previously `NewsApiKey` accepted only alphanumeric characters and `SourceId` also allowed underscores and hyphens.

### Removed

- Empty `scm` placeholders from `pom.xml`.

[1.0.0]: https://github.com/upc-pre-202620-1asi0729-7742/catch-up-platform/releases/tag/v1.0.1
