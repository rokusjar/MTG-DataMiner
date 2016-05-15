package dataminer.parser;


import com.google.gson.*;
import java.lang.reflect.Type;

public class CardSetDeserializer implements JsonDeserializer{

    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {

        final JsonObject jsonObject = jsonElement.getAsJsonObject();

        final String name = jsonObject.get("name").getAsString();
        final Card[] cards = jsonDeserializationContext.deserialize(jsonObject.get("cards"), Card[].class);

        final CardSet cardSet = new CardSet();
        cardSet.setName(name);
        cardSet.setCards(cards);

        return cardSet;
    }
}
