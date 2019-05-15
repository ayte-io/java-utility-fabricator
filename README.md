# Ayte :: Utility :: Fabricator

[![CircleCI](https://img.shields.io/circleci/project/github/ayte-io/java-utility-fabricator.svg?style=flat-square)](https://circleci.com/gh/ayte-io/java-utility-fabricator)
[![Maven Central](https://img.shields.io/maven-central/v/io.ayte.utility.fabricator/parent.svg?style=flat-square)](https://mvnrepository.com/artifact/io.ayte.utility.fabricator)
[![Code Climate maintainability](https://img.shields.io/codeclimate/maintainability/ayte-io/java-utility-fabricator.svg?style=flat-square)](https://codeclimate.com/github/ayte-io/java-utility-fabricator)
[![Sonar Tech Debt](https://img.shields.io/sonar/https/sonarcloud.io/io.ayte.utility.fabricator:parent/tech_debt.svg?style=flat-square)](https://sonarcloud.io/dashboard?id=io.ayte.utility.fabricator%3Aparent)

[![MIT License](https://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat-square)](LICENSE-MIT)
[![UPL-1.0 License](https://img.shields.io/badge/license-UPL&dash;1.0-brightgreen.svg?style=flat-square)](LICENSE-UPL-1.0)

A throwing supplier.

```java
public interface Fabricator<T, E extends Throwable> {
    T fabricate() throws E;
}
```

This is a part of Ayte [utility](https://github.com/ayte-io/java-utility)
project. Please read it's documentation to understand code organization 
better.

## Coordinates

Fabricator interface is distributed in artifact
[`io.ayte.utility.fabricator:api`](https://mvnrepository.com/artifact/io.ayte.utility.fabricator/api), 
while implementations and `Fabricators` helper class reside in 
[`io.ayte.utility.fabricator:kit`](https://mvnrepository.com/artifact/io.ayte.utility.fabricator/kit).

Project is both java 8 compatible and exports 
`io.ayte.utility.fabricator.api` and `io.ayte.utility.fabricator.kit` 
modules.

## Licensing

MIT & UPL-1.0

Ayte Labs, 2019

Do whatever you want.
