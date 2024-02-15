[![build](https://github.com/kpzip/Java-Circuit-Demo/actions/workflows/gradle.yml/badge.svg)](https://github.com/kpzip/Java-Circuit-Demo/actions/workflows/gradle.yml)

# JCircuit

Java based gui circuit simulator based on [KSPICE](https://github.com/kpzip/KSPICE)

## Table of Contents
1. [Beginning](#jcircuit)
2. [Quick Start](#quick-start-guide)
3. [License](#license)
4. [Planned Features](#planned-features-improvements)
5. [Dependencies](#dependencies)
6. [Building](#building)


## Quick Start Guide

Download the latest release and run the jar file. You should see a gui pop up. You can navigate this UI to design a circuit and run the siumlation.
Click on components on the right and then click on the grid to place them. Use the wire tool to conenct components together and make sure to place a ground node!

## License

Java Circuit Demo © 2024 is licensed under [CC BY-NC-ND 4.0](https://creativecommons.org/licenses/by-nc-nd/4.0/?ref=chooser-v1)

## Planned Features / Improvements

in order of priority:
1. Saving/Loading Functionality \(Ideally use .asc format\)
2. Improved Simulator code to better approximate real world components
3. Support for semiconductors
4. Fix project setup to not have a nested 'lib' subproject and to properly depend on KSPICE


## Dependencies

- Java 17
- [Apache Commons Math 3](https://commons.apache.org/proper/commons-math/)
- [JUnit Jupiter](https://junit.org/junit5/docs/current/user-guide/) (For testing only)

## Building

first, clone the repository
```bash
git clone https://github.com/kpzip/Java-Circuit-Demo.git
```
edit the source files to your hearts content. When you want to build the project, open a terminal in the project's root directory and type
```bash
./gradlew build
```
If you're on linux, you may need to make the file executable for this to work
```bash
chmod +x gradlew
```
The compiled `.jar` file will be in the `/lib/build/libs/` directory.
