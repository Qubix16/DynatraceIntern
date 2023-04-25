package com.example.demo;

import com.example.demo.entities.Rate;
import com.example.demo.entities.RateA;
import com.example.demo.entities.RateC;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RateDeserializer extends JsonDeserializer<List<Rate>> {

    @Override
    public List<Rate> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException
    {
        List<Rate> rates = new ArrayList<Rate>();
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        for (JsonNode node : root)
        {
            Rate rate;
            if(node.has("mid"))
            {
                rate = jsonParser.getCodec().treeToValue(node, RateA.class);
            }
            else if(node.has("bid"))
            {
                rate = jsonParser.getCodec().treeToValue(node, RateC.class);
            }
            else{
                throw new RuntimeException("Unknown rate type: ");
            }
            rates.add(rate);
        }
        return rates;
    }
}
