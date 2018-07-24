package com.diego.sistemaacademicoconsumer.utils.pojo;

import java.util.ArrayList;
import java.util.List;


public class RandomResponse {

    private List<Result> results;

    public RandomResponse() {
        results = new ArrayList<Result>();
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}