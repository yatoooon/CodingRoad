package com.yatoooon.design_patterns.builder;


public class ActualBuilder extends Builder {

    StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void builderPart1() {
        stringBuilder.append("Part1");
    }

    @Override
    public void builderPart2() {
        stringBuilder.append("Part2");
    }

    @Override
    public void builderPart3() {
        stringBuilder.append("Part3");
    }

    public String getResult() {
        return stringBuilder.toString();
    }
}
