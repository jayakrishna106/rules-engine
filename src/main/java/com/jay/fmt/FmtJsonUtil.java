package com.jay.fmt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FmtJsonUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Converts a JSON string to a map of key-value pairs.
     *
     * @param  json  the JSON string to be converted
     * @return       a map containing the key-value pairs from the JSON string
     * @throws IOException if there is an error while reading the JSON string
     */
    public static Map<String, Object> jsonToMap(String json) throws IOException {
        return OBJECT_MAPPER.readValue(json, new TypeReference<HashMap<String, Object>>(){});

    }
}
