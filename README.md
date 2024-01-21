[![build](https://github.com/kpzip/Java-Circuit-Demo/actions/workflows/gradle.yml/badge.svg)](https://github.com/kpzip/Java-Circuit-Demo/actions/workflows/gradle.yml)

# Java Circuit Demo

Simple demonstration and GUI for a java circuit simulator.

## Table of Contents
1. [Beginning](#java-circuit-demo)
2. [Quick Start](#quick-start-guide)
3. [License](#license)
4. [Documentation](#documentation)
5. [Dependencies](#dependencies)
6. [Building](#building)


## Quick Start Guide

Download the latest release and run the jar file. You should see a gui pop up. You can navigate this UI to design a circuit and run the siumlation.
> [!NOTE]
> If you pass in the commandline argument --nogui (or -n) the program will run in command line mode and run a simulation based on a provided circuit.json file.

## License

Java Circuit Demo © 2024 is licensed under [CC BY-NC-ND 4.0](https://creativecommons.org/licenses/by-nc-nd/4.0/?ref=chooser-v1)

## Documentation



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
chmod +x gradlew.bat
```
The compiled `.jar` file will be in the `/lib/build/libs/` directory.
