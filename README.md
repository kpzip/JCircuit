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

The GUI is designed to be relatively intuitive and easy to use. Simply build an electric circuit by clicking on component icons and placing them on the drawing. Hit the run button to run the simulation and you will be able to see the current represented by the animated dotted lines, and the voltage, represented by the color of the components and connection points.




Class ``Circuit`` extends ``Object``
> Contains data about an electric circuit, such as a set of connection points and a list of components
> Contains a method, simulationStep which calculates the voltage of each connection point and the current through each component, updates the components and connection points' values, and advances the siumlation by the specified time step.
> Contains a factory method for Connection Points
Class ``ConnectionPoint`` extends ``Object`` implements ``Comparable<ConnectionPoint>``
> Represents a point which components can connect to.
> Stores an id, a voltage, and a reference to the circuit it is a member of.
> Contains equals, hashCode, toString, and compareTo methods
Interface ``Component``
> Represents a component
> contains a method connectionPointCount, which returns the number of points in the circuit that this component connects to.
> contains a method connectionCount, which returns the number of paths current can take through this device
> contains a method connections, which returns the pairs of points representing each path current can take through this device
> contains a method constraints, which returns a ``double`` array representing this components mathematical dependencies on the votages across and the currents through each of its connections
> contains a method updateCurrent, which takes in an array of doubles, representing the calculated currents through this device
> contains a method differential, which takes in the elapsed time and updates this component's properties according to the time elapsed. By default does nothing
> contains a method reset, which resets all time varying properties of this component
Abstract Class ``Abstract2NodeComponent`` extends ``Object`` implements ``Component``
> Contains functionality common to all components that connect only 2 points.
> Stores a reference to both Connection Points which it connects to, and a value for the current through this device.
> Provides an isReversed method to be used by subclasses in order to determine the orientation of this components
> Provides implementations for all methods defined in ``Component``
> contains 3 abstract methods currentDependence, voltageDependence, and constantDependence, which make it easier for implementors to define the mathematical behavior of this component


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
