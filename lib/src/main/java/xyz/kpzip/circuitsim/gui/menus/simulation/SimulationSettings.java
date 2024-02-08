package xyz.kpzip.circuitsim.gui.menus.simulation;

public class SimulationSettings {
	
	private volatile long simulationTimeMs;
	private volatile boolean resetUponStart;
	private volatile long maxStepMs;
	

	public SimulationSettings() {
		simulationTimeMs = 10000;
		resetUponStart = true;
		maxStepMs = 10;
	}

	public long getSimulationTimeMs() {
		return simulationTimeMs;
	}


	public void setSimulationTimeMs(long simulationTimeMs) {
		this.simulationTimeMs = simulationTimeMs;
	}


	public boolean isResetUponStart() {
		return resetUponStart;
	}


	public void setResetUponStart(boolean resetUponStart) {
		this.resetUponStart = resetUponStart;
	}


	public long getMaxStepMs() {
		return maxStepMs;
	}


	public void setMaxStepMs(long maxStepMs) {
		this.maxStepMs = maxStepMs;
	}

}
