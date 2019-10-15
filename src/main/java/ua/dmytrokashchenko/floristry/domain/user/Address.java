package ua.dmytrokashchenko.floristry.domain.user;

import java.util.Objects;

public class Address {
    private String city;
    private String street;
    private int buildingNumber;
    private int index;

    public Address(String city, String street, int buildingNumber, int index) {
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.index = index;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return city + " " + street + " " + buildingNumber + " " + index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return getBuildingNumber() == address.getBuildingNumber() &&
                getIndex() == address.getIndex() &&
                Objects.equals(getCity(), address.getCity()) &&
                Objects.equals(getStreet(), address.getStreet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getBuildingNumber(), getIndex());
    }
}
