package org.jaggeryjs.hostobjects.car;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jaggeryjs.scriptengine.exceptions.ScriptException;
import org.jaggeryjs.scriptengine.util.HostObjectUtil;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.jaggeryjs.test.car.Engine;

public class CarHostObject extends ScriptableObject {

	private static final Log log = LogFactory.getLog(CarHostObject.class);
	private static final String MODULE_NAME = "car";
	private static CarHostObject car = null;
	private static Engine engine = null;

	/**
	 * Constructor for the use by Rhino
	 */
	public CarHostObject() {
	}

	/**
	 * Constructor the user will be using inside javaScript
	 */
	public static Scriptable jsConstructor(Context cx, Object[] args,
			Function ctorObj, boolean inNewExpr) throws ScriptException {
		car = new CarHostObject();
		engine = new Engine();
		return car;
	}

	public static void jsFunction_start(Context cx, Scriptable thisObj,
			Object[] args, Function funObj) throws ScriptException {
		String functionName = "start";
		int argsCount = args.length;
		if (argsCount != 0) {
			HostObjectUtil.invalidNumberOfArgs(MODULE_NAME, functionName,
					argsCount, true);
		}

		engine.start();
	}

	public static void jsFunction_stop(Context cx, Scriptable thisObj,
			Object[] args, Function funObj) throws ScriptException {
		String functionName = "stop";
		int argsCount = args.length;
		if (argsCount != 0) {
			HostObjectUtil.invalidNumberOfArgs(MODULE_NAME, functionName,
					argsCount, true);
		}

		engine.stop();
	}

	public static String jsGet_carStatus(Context cx, Scriptable thisObj,
			Object[] args, Function funObj) throws ScriptException {
		String functionName = "carStatus";
		int argsCount = args.length;
		if (argsCount != 0) {
			HostObjectUtil.invalidNumberOfArgs(MODULE_NAME, functionName,
					argsCount, true);
		}

		return engine.getEngineStatus();
	}

	public static String drive(Context cx, Scriptable thisObj, Object[] args,
			Function funObj) throws ScriptException {
		String functionName = "drive";
		int argsCount = args.length;
		if (argsCount != 0 && argsCount != 2) {
			HostObjectUtil.invalidNumberOfArgs(MODULE_NAME, functionName,
					argsCount, true);
		}
		if (argsCount == 2) {
			if (!(args[0] instanceof String)) {
				HostObjectUtil.invalidArgsError(MODULE_NAME, functionName, "1",
						"string", args[1], true);
			}

			if (!(args[1] instanceof Number)) {
				HostObjectUtil.invalidArgsError(MODULE_NAME, functionName, "2",
						"number", args[1], true);
			}
			String driverName = args[0].toString();
			Float speed = Float.parseFloat(args[1].toString());
			return driverName + " is driving car with speed" + speed;
		}
		Engine eng = new Engine();
		eng.start();
		eng.getEngineStatus();
		return "car is driving";
	}

	@Override
	public String getClassName() {
		return "car";
	}
}
