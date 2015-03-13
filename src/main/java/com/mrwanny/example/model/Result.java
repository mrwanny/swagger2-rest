package com.mrwanny.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by wannym on 3/11/15.
 */
@ApiModel(description = "result of an Operation")
public class Result {

    private Double result = null;

    /*
     */
    @ApiModelProperty(required = true)
    @JsonProperty("result")
    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    @Override
    public String toString()  {
        StringBuilder sb = new StringBuilder();
        sb.append("class Result {\n");

        sb.append("  code: ").append(result).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
