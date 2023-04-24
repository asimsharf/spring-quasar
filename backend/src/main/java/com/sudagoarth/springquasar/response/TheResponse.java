package com.sudagoarth.springquasar.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TheResponse {

    // This is a static method that returns a ResponseEntity<Object> object without pagination
    public static ResponseEntity<Object> getResponse(String message, HttpStatus status, Object data, Integer code) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", code);
        response.put("http_status", status);
        response.put("message", message);
        response.put("data", data);
        response.put("timestamp", System.currentTimeMillis());
        response.put("path", "/api/v1/users");

        return new ResponseEntity<>(response, status);
    }

    // This is a static method that returns a ResponseEntity<Object> object with pagination
    public static ResponseEntity<Object> getResponse(String message, HttpStatus status, Object data, Integer code, Integer currentPage, Integer totalPage, Long startCount, Long endCount, String path) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> links = new HashMap<>();

        if (!Objects.equals(currentPage, totalPage)) {
            links.put("next_page_url", path + "/page/" + (currentPage + 1));
            links.put("nextPage", currentPage + 1);
        }

        if (currentPage > 1) {
            links.put("previousPage", currentPage - 1);
            links.put("prev_page_url", path + "/page/" + (currentPage - 1));
        }

        links.put("startCount", startCount);
        links.put("endCount", endCount);
        links.put("totalItems", endCount - startCount + 1);
        links.put("totalPage", totalPage);
        links.put("currentPage", currentPage);

        links.put("first_page_url", path + "/page/1");
        links.put("last_page_url", path + "/page/" + totalPage);

        response.put("code", code);
        response.put("http_status", status);
        response.put("message", message);
        response.put("data", data);
        response.put("path", path);
        response.put("links", links);

        return new ResponseEntity<>(response, status);
    }
}