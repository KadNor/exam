package com.frequentis.training.p2;

import java.util.Arrays;
import java.util.StringJoiner;
import org.junit.Test;

import com.frequentis.training.p2.exception.InvalidCrewMemberException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApolloTest {

    private static final String CREW_MEMBER_NAME = "Crew_Member_";
    private static final String PLANET_1 = "Planet_1";
    private static final String PLANET_2 = "Planet_2";
    private static final int LONG_FLIGHT = Integer.MAX_VALUE;
    private static final int SHORT_FLIGHT = 10;
    private static final int GREAT_AMOUNT_OF_FOOD = Integer.MAX_VALUE;
    private static final int STANDARD_AMOUNT_OF_FOOD = 300;

    private Spaceship sut;

    @Test
    public void flyTo_notEnoughFood_shallNotModifyTheListOfTheVisitedPlanets() {
        // Given
        sut = new Apollo(STANDARD_AMOUNT_OF_FOOD);
        boardCrewMembers(6);

        // When
        boolean flightSuccess = sut.flyTo(PLANET_1, LONG_FLIGHT);

        // Then
        assertFalse(flightSuccess);
        assertEquals(buildExpectedPlanetsString(), sut.getPlanetsVisited());
    }

    @Test
    public void flyTo_enoughFood_shallAddPlanetsToTheVisitedList() {
        // Given
        sut = new Apollo(STANDARD_AMOUNT_OF_FOOD);
        boardCrewMembers(6);

        // When
        boolean flightSuccess = sut.flyTo(PLANET_1, SHORT_FLIGHT) && sut.flyTo(PLANET_2, SHORT_FLIGHT);

        // Then
        assertTrue(flightSuccess);
        assertEquals(buildExpectedPlanetsString(PLANET_1, PLANET_2), sut.getPlanetsVisited());
    }

    @Test(expected = IllegalArgumentException.class)
    public void flyTo_invalidParameters_shallThrowException() {
        // Given
        sut = new Apollo(STANDARD_AMOUNT_OF_FOOD);

        // When
        sut.flyTo(null, -1);
    }

    @Test
    public void unboard_oneCrewMemberExists_shallBeAbleToFlyAnyDistanceWithoutFood() {
        // Given
        sut = new Apollo(0);

        String crewMemberName = CREW_MEMBER_NAME + "0";
        sut.board(crewMemberName, GREAT_AMOUNT_OF_FOOD);

        // When
        sut.unboard(CREW_MEMBER_NAME + "0");

        // Then
        assertTrue(sut.flyTo(PLANET_1, LONG_FLIGHT));
        assertEquals(buildExpectedPlanetsString(PLANET_1), sut.getPlanetsVisited());
    }

    @Test
    public void unboard_twoCrewMembersExistWithTheSameName_shallBeAbleToFlyAnyDistanceWithoutFood() {
        // Given
        sut = new Apollo(0);

        String crewMemberName = CREW_MEMBER_NAME + "0";
        sut.board(crewMemberName, GREAT_AMOUNT_OF_FOOD);
        sut.board(crewMemberName, GREAT_AMOUNT_OF_FOOD);

        // When
        sut.unboard(crewMemberName);

        // Then
        assertTrue(sut.flyTo(PLANET_1, LONG_FLIGHT));
        assertEquals(buildExpectedPlanetsString(PLANET_1), sut.getPlanetsVisited());
    }

    @Test
    public void board_oneCrewMemberWithGreatFoodNeed_shallFailTheShortFlight() {
        // Given
        sut = new Apollo(0);
        String crewMemberName = CREW_MEMBER_NAME + "0";

        // When
        sut.board(crewMemberName, GREAT_AMOUNT_OF_FOOD);

        // Then
        assertFalse(sut.flyTo(PLANET_1, SHORT_FLIGHT));
        assertEquals(buildExpectedPlanetsString(), sut.getPlanetsVisited());
    }

    @Test(expected = InvalidCrewMemberException.class)
    public void board_invalidCrewMemberData_shallThrowException() {
        // Given
        sut = new Apollo(0);

        // When
        sut.board(null, -1);
    }

    private void boardCrewMembers(int numberOfCrewMembersToAdd) {
        for (int i = 0; i < numberOfCrewMembersToAdd; ++i) {
            sut.board(CREW_MEMBER_NAME + i, i);
        }
    }

    private String buildExpectedPlanetsString(String... planets) {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");

        Arrays.stream(planets).forEach(joiner::add);

        return joiner.toString();
    }
}