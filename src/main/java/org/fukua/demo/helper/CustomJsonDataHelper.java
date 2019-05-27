package org.fukua.demo.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.fukua.demo.exception.ParsingValueFromCustomJsonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CustomJsonDataHelper {

    // Class made to return as valid empty Json
    @JsonSerialize
    private class EmptyJsonResponse { }

    public String parseSingleJsonParamAsString(String jsonData, String param){
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

    // If object is empty returns valid empty Json
    public ResponseEntity<Object> buildResponse(Object object) {
        if (object == null) {
            return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(object, HttpStatus.OK);
        }
    }
}
