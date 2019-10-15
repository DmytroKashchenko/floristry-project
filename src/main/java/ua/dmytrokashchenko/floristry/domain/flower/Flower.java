package ua.dmytrokashchenko.floristry.domain.flower;

import org.jetbrains.annotations.NotNull;
import ua.dmytrokashchenko.floristry.domain.enums.Color;
import ua.dmytrokashchenko.floristry.domain.enums.StemLength;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Flower implements Comparable<Flower> {
    private static Long counter = 0L;
    private Long id;
    private String name;
    private Color color;
    private StemLength stemLength;
    private int price;
    private LocalDate dateOfManufacture;
    private int shelfLifeDays;

    public Flower() {
    }

    protected Flower(FlowerBuilder<? extends FlowerBuilder> flowerFlowerBuilder) {
        this.id = ++counter;
        this.name = flowerFlowerBuilder.name;
        this.color = flowerFlowerBuilder.color;
        this.stemLength = flowerFlowerBuilder.stemLength;
        this.price = flowerFlowerBuilder.price;
        this.dateOfManufacture = flowerFlowerBuilder.dateOfManufacture;
        this.shelfLifeDays = flowerFlowerBuilder.shelfLifeDays;
    }

    public Flower(String name, Color color, StemLength stemLength,
                  int price, LocalDate dateOfManufacture, int shelfLifeDays) {
        this.name = name;
        this.color = color;
        this.stemLength = stemLength;
        this.price = price;
        this.dateOfManufacture = dateOfManufacture;
        this.shelfLifeDays = shelfLifeDays;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public StemLength getStemLength() {
        return stemLength;
    }

    public void setStemLength(StemLength stemLength) {
        this.stemLength = stemLength;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(LocalDate dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public int getShelfLifeDays() {
        return shelfLifeDays;
    }

    public void setShelfLifeDays(int shelfLifeDays) {
        this.shelfLifeDays = shelfLifeDays;
    }

    @Override
    public String toString() {
        return name + " " + color.getName() + " " + stemLength.getName() + " " + dateOfManufacture.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flower)) return false;
        Flower flower = (Flower) o;
        return getPrice() == flower.getPrice() &&
                getShelfLifeDays() == flower.getShelfLifeDays() &&
                Objects.equals(getName(), flower.getName()) &&
                getColor() == flower.getColor() &&
                getStemLength() == flower.getStemLength() &&
                Objects.equals(getDateOfManufacture(), flower.getDateOfManufacture());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getColor(), getStemLength(),
                getPrice(), getDateOfManufacture(), getShelfLifeDays());
    }

    @Override
    public int compareTo(@NotNull Flower o) {
        return this.dateOfManufacture.compareTo(o.dateOfManufacture);
    }

    public abstract static class FlowerBuilder<T extends FlowerBuilder<T>> {
        private String name;
        private Color color;
        private StemLength stemLength;
        private int price;
        private LocalDate dateOfManufacture;
        private int shelfLifeDays;

        @SuppressWarnings("unchecked")
        public T self() {
            return (T) this;
        }

        public T withName(String name) {
            this.name = name;
            return self();
        }

        public T withColor(Color color) {
            this.color = color;
            return self();
        }

        public T withStemLength(StemLength stemLength) {
            this.stemLength = stemLength;
            return self();
        }

        public T withPrice(int price) {
            this.price = price;
            return self();
        }

        public T withDateOfManufacture(LocalDate dateOfManufacture) {
            this.dateOfManufacture = dateOfManufacture;
            return self();
        }

        public T withShelfLifeDays(int shelfLifeDays) {
            this.shelfLifeDays = shelfLifeDays;
            return self();
        }
    }
}
