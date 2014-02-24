package org.jaggeryjs.test.car;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Engine {

	private final Log log = LogFactory.getLog(Engine.class);
	private String engineStatus = "non started";

	public void start() {
		log.info("starting Engine");
		// Engine is starting.
		engineStatus = "Engine Started";
	}

	public void stop() {
		log.info("stopping Engine");
		// Engine is stopping.
		engineStatus = "Engine stopped";
	}

	public String getEngineStatus() {
		log.info("Checking Engine Status");
		return engineStatus;
	}

	public String changeGear(int gear) {
		log.info("changeGear to " + gear);
		// Changing gears
		return "Gear Changed to " + gear;
	}
}
