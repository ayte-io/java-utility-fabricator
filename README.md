# Ayte :: Utility :: Fabricator

![CircleCI (all branches)](https://img.shields.io/circleci/project/github/ayte-io/java-utility-fabricator.svg?style=flat-square)
![Maven Central](https://img.shields.io/maven-central/v/io.ayte.utility.fabricator/parent.svg?style=flat-square)

A throwing supplier.

```java
public interface Fabricator<T, E extends Throwable> {
    T fabricate() throws E;
}
```

This is a part of [Utility](https://github.com/ayte-io/java-utility)
project. Please read it's documentation to understand code organization 
better.

## Coordinates

Fabricator interface is distributed in artifact
`io.ayte.utility.fabricator:api`, while implementations
and `Fabricators` helper class reside in 
`io.ayte.utility.fabricator:kit`.

Exported modules/packages are `io.ayte.utility.fabricator.api` and 
`io.ayte.utility.fabricator.kit` correspondingly.

## Licensing

MIT & UPL-1.0

Ayte Labs, 2019

Do whatever you want
