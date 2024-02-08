package xyz.kpzip.circuitsim.util;

import java.util.TreeMap;

import xyz.kpzip.circuitsim.util.math.Function;

public class SimulationGraph implements Function {
	
	TreeMap<Double, Double> values = new TreeMap<Double, Double>();
	
	public SimulationGraph() {
		
	}
	
	public void addValue(double x, double y) {
		values.put(x, y);
	}

	/**
	 * perform linear interpolation
	 */
	@Override
	public double sample(double x) {
		double prevkey = values.firstKey();
		double prevvalue = values.firstEntry().getValue();
		double ysample;
		for (double xsample : values.keySet()) {
			ysample = values.get(xsample);
			if (xsample > x) {
				return (ysample - prevvalue)/(xsample - prevkey) * (x - xsample) + ysample;
			}
			prevkey = xsample;
			prevvalue = ysample;
		}
		return prevvalue;
	}

}
