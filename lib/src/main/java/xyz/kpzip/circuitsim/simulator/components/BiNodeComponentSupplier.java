package xyz.kpzip.circuitsim.simulator.components;

import java.util.function.BiFunction;

import xyz.kpzip.circuitsim.simulator.Circuit;

@FunctionalInterface
public interface BiNodeComponentSupplier<C extends Component> extends BiFunction<Circuit.ConnectionPoint, Circuit.ConnectionPoint, C> {

}
