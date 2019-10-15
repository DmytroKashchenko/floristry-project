package ua.dmytrokashchenko.floristry.domain.enums;

public enum Color {
    RED("Red"),
    ORANGE("Orange"),
    YELLOW("Yellow"),
    GREEN("Green"),
    BLUE("Blue"),
    VIOLET("Violet"),
    PINK("Pink"),
    BLACK("Black"),
    WHITE("White");

    String name;

    Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
