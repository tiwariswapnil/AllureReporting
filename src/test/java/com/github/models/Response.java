package com.github.models;

import lombok.Data;

import java.util.List;

@Data
public class Response {

    public List<com.github.models.Data> data;
    public Support support;

    public class Support {
        public String url;
        public String text;
    }
}
