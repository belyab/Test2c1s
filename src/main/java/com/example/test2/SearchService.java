package com.example.test2;

import java.io.IOException;
import java.util.Map;

public interface SearchService {
    String getBusJson(String url);
    Map<String, Object> parseGson(StringBuilder jsonString) throws IOException;
}

