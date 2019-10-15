package ua.dmytrokashchenko.floristry.domain.accessory;

import ua.dmytrokashchenko.floristry.domain.enums.Color;

public class Cord extends Accessory {
    private int length;

    public Cord(String name, int price, Color color, int length) {
        super(name, price, color);
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
