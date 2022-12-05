package com.enigmacamp.restapi.repository.spec;

import com.enigmacamp.restapi.util.QueryOperator;
import lombok.Getter;
import lombok.Setter;

public class SearchCriteria {
    @Getter
    private String key;
    @Getter
    private QueryOperator operator;
    @Getter
    private Object value;

    private SearchCriteria(Builder builder){
        this.key = builder.key;
        this.operator = builder.operator;
        this.value = builder.value;
    }

    public static class Builder{
        private String key;
        private QueryOperator operator;
        private Object value;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Builder setOperator(QueryOperator operator) {
            this.operator = operator;
            return this;
        }

        public Builder setValue(Object value) {
            this.value = value;
            return this;
        }

        public SearchCriteria build(){
            return new SearchCriteria(this);
        }
    }
}