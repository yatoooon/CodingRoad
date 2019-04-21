package com.yatoooon.design_patterns.builder;

public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public void construct1() {
        builder.builderPart1();
        builder.builderPart2();
        builder.builderPart3();
    }

    public void construct2() {
        builder.builderPart3();
        builder.builderPart2();
        builder.builderPart1();
    }
}
