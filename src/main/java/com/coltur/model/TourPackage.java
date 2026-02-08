package com.coltur.model;

import java.util.Objects;

public class TourPackage {
    private Integer id;
    private String name;
    private String description;
    private double priceCop;
    private int durationDays;

    public TourPackage(Integer id, String name, String description, double priceCop, int durationDays) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priceCop = priceCop;
        this.durationDays = durationDays;
    }

    public TourPackage(String name, String description, double priceCop, int durationDays) {
        this(null, name, description, priceCop, durationDays);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPriceCop() {
        return priceCop;
    }

    public void setPriceCop(double priceCop) {
        this.priceCop = priceCop;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public void setDurationDays(int durationDays) {
        this.durationDays = durationDays;
    }

    @Override
    public String toString() {
        return "TourPackage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priceCop=" + priceCop +
                ", durationDays=" + durationDays +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TourPackage that = (TourPackage) o;
        return Double.compare(that.priceCop, priceCop) == 0
                && durationDays == that.durationDays
                && Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, priceCop, durationDays);
    }
}
