package com.buildlight.respository.bamboo.model;

/**
 * @author zutherb
 */
public class Result {
    private Link link;
    private String key;
    private State state;
    private LifeCycleState lifeCycleState;
    private int number;
    private int id;

    public Link getLink() {
        return link;
    }

    public String getKey() {
        return key;
    }

    public State getState() {
        return state;
    }

    public LifeCycleState getLifeCycleState() {
        return lifeCycleState;
    }

    public int getNumber() {
        return number;
    }

    public int getId() {
        return id;
    }

    public static class Builder {

        private Result result = new Result();

        public Builder number(int number) {
            result.number = number;
            return this;
        }

        public Builder id(int id) {
            result.id = id;
            return this;
        }

        public Builder state(State state) {
            result.state = state;
            return this;
        }

        public Builder lifeCycleState(LifeCycleState lifeCycleState) {
            result.lifeCycleState = lifeCycleState;
            return this;
        }

        public Result build() {
            return result;
        }
    }
}
