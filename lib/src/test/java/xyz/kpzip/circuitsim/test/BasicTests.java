package xyz.kpzip.circuitsim.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import xyz.kpzip.circuitsim.simulator.Circuit;
import xyz.kpzip.circuitsim.simulator.components.passive.Resistor;
import xyz.kpzip.circuitsim.simulator.components.source.Battery;

class BasicTests {
	
    @Test 
    void javaTest() {
        assertTrue(true, "true is true");
    }
    
    @Test 
    void batteryLightTest() {
    	Circuit c = new Circuit();
    	Circuit.ConnectionPoint v = c.createConnectionPoint();
    	Battery b = new Battery(c.getGround(), v, 10.0);
    	Resistor r = new Resistor(v, c.getGround(), 10.0);
    	c.addComponent(b);
    	c.addComponent(r);
    	c.simulationStep(0);
    	assertEquals(1.0, r.getCurrent());
    }
    
}
