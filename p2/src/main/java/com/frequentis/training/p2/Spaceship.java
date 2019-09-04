package com.frequentis.training.p2;

public interface Spaceship {

    /**
     * Boards a crew member with the given food intake. This crew member will now consume food during trips.
     *
     * @throws com.frequentis.training.p2.exception.InvalidCrewMemberException if the crewMemberName is null or the foodPerDay is less than 0.
     */
    void board(String crewMemberName, int foodPerDay);

    /**
     * Unboards a crew member with the given name from the ship. This crew member is no longer on the ship and no longer consumes food.
     */
    void unboard(String crewMemberName);

    /**
     * Returns a String of visited planets, in order of visit. The string should be formatted like “[Earth, Mars, Venus]”
     */
    String getPlanetsVisited();

    /**
     * Attempts to fly to a planet, which takes the given number of days.
     *
     * @throws IllegalArgumentException if the planetName is null or the daysRequired is negative.
     */
    boolean flyTo(String planetName, int daysRequired);
}
