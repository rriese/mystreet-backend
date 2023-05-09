package com.github.riese.rafael.mystreet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Chart {
    private String title;
    Map<String, Double> data = new HashMap<>();
}
