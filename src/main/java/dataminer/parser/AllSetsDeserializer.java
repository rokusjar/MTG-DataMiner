package dataminer.parser;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class AllSetsDeserializer implements JsonDeserializer {

    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {

        AllSets allSets = new AllSets();
        List<CardSet> sets = new ArrayList<>();

        final JsonObject jsonObject = jsonElement.getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> entries = jsonObject.entrySet();

        for(Map.Entry<String, JsonElement> entry : entries){

            CardSet cardSet = new CardSet();
            JsonObject set = entry.getValue().getAsJsonObject();

            final String name = set.get("name").getAsString();
            final Card[] cards = jsonDeserializationContext.deserialize(set.get("cards"), Card[].class);

            cardSet.setCode(entry.getKey());
            cardSet.setName(name);
            cardSet.setCards(cards);

            sets.add(cardSet);
        }
        allSets.setCardSets(sets);
        return allSets;
    }
}
