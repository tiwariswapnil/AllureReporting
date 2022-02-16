package com.github.api;

import io.restassured.http.Headers;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
public class RestAssuredUtil {

    public static Response getResponse(String URL, Map<String, String> headers,
                                       Map<String, String> parameterMap) {
        Response response;
        RequestSpecification request = given();

        if (null != parameterMap) {
            for (Map.Entry<String, String> keyValue : parameterMap.entrySet()) {
                request.param(keyValue.getKey(), keyValue.getValue());
            }
        }
        if (null != headers) {
            response = request.headers(headers).get(URL);

        } else {
            response = request.get(URL);
        }

        log.info("GET Response for " + URL + " " + response.asString());

        return response;
    }

    public static Response getResponseWithHeaders(Map<String, String> parameterMap,
                                                  String URL, Headers headers) {
        RequestSpecification request = given();
        RequestSpecificationImpl rs = (RequestSpecificationImpl) request;

        if (null != parameterMap) {
            for (Map.Entry<String, String> keyValue : parameterMap.entrySet()) {
                request.param(keyValue.getKey(), keyValue.getValue());
            }
        }
        Response response = request.headers(headers).get(URL);
        return response;
    }

}
