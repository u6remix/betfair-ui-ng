package by.betfair.core.ng.util.deserializer;

import java.lang.reflect.Type;

import by.betfair.ui.model.Market;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class MarketDeserializer  extends AbstractCustomDeserializer implements JsonDeserializer<Market> {

	@Override
	public Market deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		// need to use the default deserializers for all inner objects of market
		Market market = gson.fromJson(arg0, Market.class);
		return market;
	}

}
