package ua.dmytrokashchenko.floristry.domain.accessory;

import ua.dmytrokashchenko.floristry.domain.enums.Color;

public class Accessory {
    private static Long counter = 0L;
    private Long id;
    private String name;
    private Color color;
    private int price;

    public Accessory(String name, int price, Color color) {
        this.id = ++counter;
        this.name = name;
        this.price = price;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return id.toString() + " " + name + " " + color.toString() + " " + (double)price/100;
    }
}
