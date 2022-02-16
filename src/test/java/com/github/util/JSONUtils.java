package com.github.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Date;

public class JSONUtils {

    public static Object convertFromJsonUsingJackson(String jsonData,
                                                     Class<?> classType) {
        ObjectMapper mapper = new ObjectMapper();
        Object obj = new Object();
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
            mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, false);
            obj = mapper.readValue(jsonData, classType);
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object convertFromJsonUsingJackson(String jsonData,
                                                     TypeReference typeReference) {
        ObjectMapper mapper = new ObjectMapper();
        Object obj = new Object();
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
            obj = mapper.readValue(jsonData, typeReference);

            return obj;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertToJson(Object obj) {
        Gson gsonConverter = new GsonBuilder().disableHtmlEscaping()
                .setPrettyPrinting().serializeNulls().create();
        String jsonData = gsonConverter.toJson(obj);
        return jsonData;
    }

//    public static Object convertFromJson(String jsonData, Class<?> classType) {
//        Gson gsonConverter = new GsonBuilder().disableHtmlEscaping()
//                .setPrettyPrinting().serializeNulls()
//                .registerTypeAdapter(Date.class, new GsonUTCDateAdapter())
//                .create();
//
//        Object objFromJson = gsonConverter.fromJson(jsonData, classType);
//        return objFromJson;
//    }

    public static <T> String getJsonFromObject(T inpuTPojo) {
        String json = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);
        try {
            json = mapper.writeValueAsString(inpuTPojo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;

    }

    public static <T> T buildObjectFromJSONWithTypeRef(String json, TypeReference<T> typeReference) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ObjectMapper mapperObj = mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            return (T) mapperObj.readValue(json, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
