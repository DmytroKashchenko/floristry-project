package ua.dmytrokashchenko.floristry.domain.accessory;

import ua.dmytrokashchenko.floristry.domain.enums.Color;

public class Tape extends Accessory {
    private int length;
    private int width;

    public Tape(String name, int price, Color color, int length, int width) {
        super(name, price, color);
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
