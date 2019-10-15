package ua.dmytrokashchenko.floristry.domain.enums;

public enum StemLength {
    SHORT("Short"),
    MEDIUM("Medium"),
    LONG("Long"),
    PREMIUM("Premium"),
    WOW("WOW");

    String name;

    StemLength(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
