package com.frequentis.training.p2;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.frequentis.training.p2.exception.InvalidCrewMemberException;

public class Apollo implements Spaceship {

    private int foodLeft;
    private Set<String> visitedPlanets;
    private List<CrewMember> crewMembers;

    public Apollo(final int foodLeft) {
        this.foodLeft = foodLeft;
        this.visitedPlanets = new LinkedHashSet<>();
        this.crewMembers = new ArrayList<>();
    }

    public void board(final String crewMemberName, final int foodPerDay) {
        if (crewMemberName == null || foodPerDay < 0) {
            throw new InvalidCrewMemberException("Unexpected name or food per day data: name=" + crewMemberName + ", foodPerDay=" + foodPerDay);
        }
        crewMembers.add(new CrewMember(crewMemberName, foodPerDay));
    }

    public void unboard(final String crewMemberName) {
        crewMembers = crewMembers.stream()
                                 .filter(member -> !member.getName().equals(crewMemberName))
                                 .collect(Collectors.toList());
    }

    public String getPlanetsVisited() {
        return visitedPlanets.toString();
    }

    public boolean flyTo(final String planetName, final int daysRequired) {
        if (planetName == null || daysRequired < 0) {
            throw new IllegalArgumentException("Unexpected planet name or days required data: planetName=" + planetName + " daysRequired=" + daysRequired);
        }

        long requiredFood = getRequiredFoodForDays(daysRequired);
        boolean successfulFlight = false;

        if (requiredFood <= foodLeft) {
            foodLeft -= requiredFood;
            visitedPlanets.add(planetName);
            successfulFlight = true;
        }

        return successfulFlight;
    }

    private long getRequiredFoodForDays(final int days) {
        return crewMembers.stream()
                          .mapToLong(member -> ((long) member.getFoodRequiredForADay()) * days)
                          .sum();
    }
}
