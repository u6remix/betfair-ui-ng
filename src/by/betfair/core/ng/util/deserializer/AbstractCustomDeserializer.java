package by.betfair.core.ng.util.deserializer;

import java.util.Date;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AbstractCustomDeserializer {
	protected static final Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
	protected static final Logger logger = Logger.getLogger(AbstractCustomDeserializer.class);
}
