package by.betfair.core.ng.util;

import by.betfair.core.ng.util.deserializer.CompetitionDeserializer;
import by.betfair.core.ng.util.deserializer.DateDeserializer;
import by.betfair.core.ng.util.deserializer.EventDeserializer;
import by.betfair.core.ng.util.deserializer.MarketDeserializer;
import by.betfair.ui.model.Competition;
import by.betfair.ui.model.Event;
import by.betfair.ui.model.Market;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * Useful class to convert to and from Json
 * In this example we use Google gson
 */
public class JsonConverter {
	protected static final Logger logger = Logger.getLogger(JsonConverter.class);
    protected static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    		.registerTypeAdapter(Date.class, new DateDeserializer())
    		.registerTypeAdapter(Competition.class, new CompetitionDeserializer())
    		.registerTypeAdapter(Event.class, new EventDeserializer())
    		.registerTypeAdapter(Market.class, new MarketDeserializer()).create();
    protected static final JsonParser parser = new JsonParser();
    private static final String RESULT_PROPERTY="result";

    /** This method deserializes the specified Json into an object of the specified class.
     *
     */
    public static  <T> T convertFromJson(String toConvert,  Type  typeOfT){
        return gson.fromJson(toConvert, typeOfT);
    }

    /**
     * This method serializes the specified object into its equivalent Json representation.
     */
    public static String convertToJson(Object toConvert){
        return gson.toJson(toConvert);

    }

    public static JsonObject parseToJsonObject(String str){
    	return (JsonObject)parser.parse(str);
    }
   
   /**
    *  Converts 'result' content only
    */
   public static <T> T convertResultFromJson(String toConvert,  Type  typeOfT){
		JsonObject jsonObject = JsonConverter.parseToJsonObject(toConvert);
		JsonElement jsonResult = jsonObject.get(RESULT_PROPERTY);
		return convertFromJson(jsonResult.toString(), typeOfT);
   }

}
