package com.frequentis.training.p2;

public class CrewMember {

    private final String name;
    private final int foodRequiredForADay;

    public CrewMember(final String name, final int foodRequiredForADay) {
        this.name = name;
        this.foodRequiredForADay = foodRequiredForADay;
    }

    public String getName() {
        return name;
    }

    public int getFoodRequiredForADay() {
        return foodRequiredForADay;
    }
}
