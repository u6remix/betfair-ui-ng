package by.betfair.core.ng.util.deserializer;

import java.lang.reflect.Type;

import by.betfair.ui.model.Competition;
import by.betfair.ui.model.Event;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class EventDeserializer extends AbstractCustomDeserializer implements JsonDeserializer<Event> {

	@Override
	public Event deserialize(JsonElement jsonElement, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		JsonObject jsonEvent = jsonElement.getAsJsonObject().get("event").getAsJsonObject();
		Event event = gson.fromJson(jsonEvent, Event.class);
		return event;
	}

}
