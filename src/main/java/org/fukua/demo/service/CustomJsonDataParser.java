package org.fukua.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fukua.demo.exception.ParsingValueFromCustomJsonException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CustomJsonDataParser {
    public String getSingleJsonParamAsString(String jsonData, String param){
        ObjectMapper objectMapper = new ObjectMapper();

        //read JSON like DOM Parser
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(jsonData.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }


        JsonNode idNode;
        String value = null;
        if (rootNode != null) {
            idNode = rootNode.path(param);
            value = idNode.asText();
            value = (value.equals("")) ? null : value;
        }

        if (value == null) {
            throw new ParsingValueFromCustomJsonException();
        }

        return value;
    }
}
