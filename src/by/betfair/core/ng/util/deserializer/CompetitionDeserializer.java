package by.betfair.core.ng.util.deserializer;

import java.lang.reflect.Type;
import by.betfair.ui.model.Competition;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class CompetitionDeserializer extends AbstractCustomDeserializer implements JsonDeserializer<Competition> {
	
	@Override
	public Competition deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonContext)
			throws JsonParseException {
		
		logger.debug("start deserialize competition");
		// call standard deserializer
		Competition competition = gson.fromJson(jsonElement, Competition.class);
		// get custom values
		JsonElement jsonCompElement = jsonElement.getAsJsonObject().get("competition");
		if(jsonCompElement!=null){
			JsonObject jsonCompetition = jsonCompElement.getAsJsonObject();
			String name = jsonCompetition.get("name").getAsString();
			String id = jsonCompetition.get("id").getAsString();
			competition.setId(id);
			competition.setName(name);
		}
		
		return competition;
	}

}
