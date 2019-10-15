package ua.dmytrokashchenko.floristry.domain.bouquet;

import ua.dmytrokashchenko.floristry.domain.flower.Flower;
import ua.dmytrokashchenko.floristry.domain.accessory.Accessory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bouquet {
    private static Long counter = 0L;
    private final Long id;
    private final String name;
    private int price;
    private List<Flower> flowers;
    private List<Accessory> accessories;

    public Bouquet(String name) {
        this.id = ++counter;
        this.name = name;
        flowers = new ArrayList<>();
        accessories = new ArrayList<>();
        price = 0;
    }

    public Long getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public void addFlower(Flower flower) {
        if (flower == null) {
            return;
        }
        price += flower.getPrice();
        flowers.add(flower);
    }

    public void addAccessory(Accessory accessory) {
        if (accessory == null) {
            return;
        }
        price += accessory.getPrice();
        accessories.add(accessory);
    }

    public void deleteFlower(Flower flower) {
        if (flower == null) {
            return;
        }
        if (flowers.contains(flower)) {
            price -= flower.getPrice();
            flowers.remove(flower);
        }
    }

    public void deleteAccessory(Accessory accessory) {
        if (accessory == null) {
            return;
        }
        if (accessories.contains(accessory)) {
            price -= accessory.getPrice();
            accessories.remove(accessory);
        }
    }

    public List<Flower> getFlowers() {
        return new ArrayList<>(flowers);
    }

    public List<Accessory> getAccessories() {
        return new ArrayList<>(accessories);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Bouquet:\n");
        sb.append("\n").append(name).append(" ");
        sb.append("Flowers:\n");
        for (Flower flower : flowers) {
            sb.append(flower).append("\n");
        }
        sb.append("Accessories:\n");
        for (Accessory accessory : accessories) {
            sb.append(accessory).append("\n");
        }
        sb.append("price: ").append((double) price / 100);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bouquet)) {
            return false;
        }
        Bouquet bouquet = (Bouquet) o;
        return Objects.equals(getId(), bouquet.getId()) &&
                Objects.equals(getName(), bouquet.getName()) &&
                Objects.equals(getFlowers(), bouquet.getFlowers()) &&
                Objects.equals(getAccessories(), bouquet.getAccessories());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(), getFlowers(), getAccessories());
    }
}
