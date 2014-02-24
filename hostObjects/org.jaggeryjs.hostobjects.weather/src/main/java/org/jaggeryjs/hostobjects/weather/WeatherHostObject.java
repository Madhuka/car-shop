package org.jaggeryjs.hostobjects.weather;

import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jaggeryjs.scriptengine.exceptions.ScriptException;
import org.jaggeryjs.scriptengine.util.HostObjectUtil;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;


public class WeatherHostObject  extends ScriptableObject {

	private static final Log log = LogFactory.getLog(WeatherHostObject.class);
	private static final String MODULE_NAME = "weather";

	
  
	public static String report(Context cx, Scriptable thisObj, Object[] args,
			Function funObj) throws ScriptException {
		String functionName = "report";
		int argsCount = args.length;
		WeatherHostObject weatherHostObject = new WeatherHostObject();
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
			String cityName = args[0].toString();
			Float raceTrackNo = Float.parseFloat(args[1].toString());
			return cityName + " Weather Report for RaceTrack No, " + raceTrackNo+": "+weatherHostObject.getWeather();
		}

		return "Weather Report: "+ weatherHostObject.getWeather();
	}


	public String getWeather() {
		String []weatherConditionList = {"Fair"," Clear"," Fair with Haze"," Mostly Cloudy "," Fair and Breezy"," Clear and Breezy"," Partly Cloudy","Partial Fog"};
		int idx = new Random().nextInt(weatherConditionList.length);
		String randomWeather = (weatherConditionList[idx]);
		return randomWeather;
	}


	@Override
	public String getClassName() {
		return "report";
	}
}
