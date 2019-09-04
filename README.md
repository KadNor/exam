# Java Training Exam

## Warming up
   
Write a method named isSubMap that accepts two hash maps from strings to strings as its parameters and returns true if every key in the first map is also contained in the second map and maps to the same value in the second map.
For example:

    { Smith=949–0504, Marty=206–9024 }
is a sub-map of:
        
     { Marty=206–9024, Hawking=123–4567, Smith=949–0504, Newton=123– 4567 }
       
Therefore if the first map is map1 and the second is map2, here isSubMap(map1, map2) would return true. 
The empty map is considered to be a sub-map of every map. 
Constraints: You may not declare any auxiliary data structures in solving this problem.

## Designing a spaceship
   
Write a class called Spaceship that models some characteristics of a Spaceship. 
Specifically, a Spaceship:
-	keeps track of the amount of food on board (in pounds), 
-	a list of names of visited planets (in order of visit), 
-	the name of each crew member on board, 
-	how much food (in pounds) each crew member consumes each day

You can create a Spaceship by specifying the amount of food initially on board, like so: 
Spaceship myShip = new Spaceship(50); // 50 pounds of food initially

A Spaceship should have the following public methods:
```java
public interface Spaceship {
   /**
    * Boards a crew member with the given food intake. This crew member will now consume food during trips.
    */
   public void board(String crewMemberName, int foodPerDay);

   /**
    * Unboards a crew member with the given name from the ship. This crew member is no longer on the ship and no longer consumes food.
    */
   public void unboard(String crewMemberName);

   /**
    * Returns a String of visited planets, in order of visit. The string should be formatted like “[Earth, Mars, Venus]”
    */
   public String getPlanetsVisited();

   /**
    * Attempts to fly to a planet, which takes the given number of days.
    */
   public boolean flyTo(String planetName, int daysRequired);
}
```

The most involved public method is flyTo. This method should do the following: 
-	It should first calculate if there is enough food on board to feed all crew members for the number of days required to get to this planet. 
-	If there is enough food, it should update the amount of food onboard to reflect that the ship has traveled to this planet, and it should add this planet to its list of visited planets. The method should also return true to indicate the trip was successful. 
-	If there is not enough food, then the method should return false to indicate the trip failed. It should not modify either the onboard food or the list of visited planets.

As an example, let’s say on myShip we have onboard Nolan, who consumes 3 pounds of food daily, and Nick, who consumes 4 pounds of food daily. myShip.flyTo(“Venus”, 7) should return true because Nick and Nolan consume 7 pounds of food per day in total, which over 7 days is 7*7=49 pounds of food (and our ship has 50). We should therefore add “Venus” to our list of visited planets, and set the food onboard to now be 50-49=1. 

On the other hand, myShip.flyTo(“Mars”, 10) should return false because we require 7*10=70 pounds of food to get to Mars, but our ship only has 50. We should not add “Mars” to our list of visited planets, nor change our onboard food.

Do:
a.	Create a maven project for your spaceship
b.	Create a git repository on github for the spaceship. Add the maven project.
c.	Create an OOP model for your spaceship. Chose the best data types and structures for the problem.
d.	Implement the flyTo(…) method. 
e.	Add unit tests for your implementation. The coverage should be higher than 80%
   
## Exchange Rates

Use the api.exchangeratesapi.io (https://api.exchangeratesapi.io/latest?symbols=RON ) to retrieve the rate of exchange between EUR and RON, and trigger alerts when the rate changes, by interrogating the API each minute for the latest rates.

You should separate the concerns of your application classes by having each class handle one responsibility. Example:

- CurrencyRatesAlertingEngine – triggering the api searches and detects when notifications need to be sent.
- CurrencyRatesService – (or query service) – a service that performs the actual API call and parses the result into something meaningful.
- CurrencyChangeListener – a class where you can customize what to do when the rates changes.

Hint: For HTTP call you can use spring-web library, RestTemplate() class.

Make unit tests for all classes. Mock the dependencies (eg, CurrencyRateService and CurrencyChangeListener for the Engine).
