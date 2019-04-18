package com.yatoooon.design_patterns.factory;

public class ShapeFactory {

    public static final String TYPE_CIRCLE = "CIRCLE";
    public static final String TYPE_RECTANGLE = "RECTANGLE";
    public static final String TYPE_SQUARE = "SQUARE";

    public static Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equals(TYPE_CIRCLE)) {
            return new Circle();
        } else if (shapeType.equals(TYPE_RECTANGLE)) {
            return new Rectangle();
        } else if (shapeType.equals(TYPE_SQUARE)) {
            return new Square();
        }
        return null;
    }
}
