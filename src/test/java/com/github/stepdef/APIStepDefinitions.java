package com.github.stepdef;

import com.github.api.RestAssuredUtil;
import com.github.util.JSONUtils;
import com.github.util.Properties;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class APIStepDefinitions {

    @Given("User invokes the GET API")
    public void userInvokesGetAPI() {
        List<Header> headerList = new ArrayList<>();
        Header authorization = new Header("authorization", "Basic cmVzdDoxMjM0NTY=");
        Header contentType = new Header("content-type", "application/json; charset=utf-8");
        Header acceptType = new Header("Accept", "application/json");
        headerList.add(authorization);
        headerList.add(contentType);
        headerList.add(acceptType);
        Headers headers = new Headers(headerList);

        String getUrl = Properties.getProperty("GET");
        Response response = RestAssuredUtil.getResponseWithHeaders(null, getUrl, headers);
        JSONUtils.convertFromJsonUsingJackson(response.toString(), com.github.models.Response.class);
    }

    @Then("Verify the response")
    public void verifyResponse() {

    }

}
