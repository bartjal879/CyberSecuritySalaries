/*
 * This is the package for all of the pl.polsl.model package classes tests.
 */
package pl.polsl.model;

import java.io.*;
import java.util.*;
//import org.apache.commons.math.stat.descriptive.summary.Sum;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.AfterAll;
//import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
//import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.function.Executable;
//import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * This class contains methods that tests class methods contained in Model.java
 * class.
 *
 * @author Bartosz Jałowiecki
 * @version 2.0
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ModelTest {
// @Before

    /**
     * Fields that represent the data used for testing
     */
    private Model model = new Model();
    private Person person = new Person(1, "JU", "FT", "JakisTytul", 2351, "EUR", 20202, "PL", 50, "US", "L");
    private Person person2 = new Person(1, "MI", "CT", "JakisTytul2", 5351, "USD", 202020, "PL", 33, "US", "L");

    /**
     * Method used for loading data for the tests
     */
    @BeforeEach
    public void loadData() {
        model = new Model();
        model.addPerson(person);
        model.addPerson(person2);
    }

    /**
     * Test responsible for checking the adiition of object of Person class to
     * collection of objects in stored in the model.
     */
    @Test
    @Order(1)
    public void addPersonTest() {

        assertSame(model.getPersonsArray().get(0), person, "Method does not correctly add or get objects from Collection");
        assertEquals(model.getPersonsArray().get(1), person2, "Method does not correctly add or get objects from Collection");
        //assertSame(model.persons.get(1), person2);
        assertNotSame(model.getPersonsArray().get(0), model.getPersonsArray().get(1), "Method does not correctly add or get objects from Collection");
    }

    /**
     * Test responsible for checking the returned result of the method
     * jobTitleWithHighestSalaries().
     */
    @Test
    @Order(2)
    public void jobTitleWithHighestSalariesTest() {
        ////for(var p: model.jobTitleWithHighestSalaries()
        assertEquals(this.model.jobTitleWithHighestSalaries().get(0), "JakisTytul2", "The returned by method result is not valid");
    }

    /**
     * Test responsible for checking the returned result of the method
     * sortCurrenciesOnTypeOfEmployemnt().
     */
    @Test
    @Order(3)
    public void sortCurrenciesOnTypeOfEmploymentTest() {
        ArrayList<String> sortedCurrencies = new ArrayList<String>();
        sortedCurrencies.add("CT");
        sortedCurrencies.add("USD");
        sortedCurrencies.add("FT");
        sortedCurrencies.add("EUR");
        assertEquals(model.sortCurrenciesOnTypeOfEmployment(), sortedCurrencies, "The returned by method result is not valid.");
        //sortedCurrencies
    }

    /**
     * Test responsible for checking the returned result of the method
     * currencyWithMostSalaries().
     */
    @Test
    @Order(4)
    public void currencyWithMostSalariesTest() {
        assertEquals(model.currencyWithMostSalaries().get(0), ("EUR"), "The returned by method result is not valid.");
    }

    /**
     * Test responsible for checking the exception throwing of the method
     * currencyWithMostSalaries().
     */
    @Test
    @Order(5)
    public void currencyWithMostSalariesExceptionTest() {
        IndexOutOfBoundsException assertThrows = assertThrows(IndexOutOfBoundsException.class, () -> model.currencyWithMostSalaries().get(1), "Method currencyWithMostSalaries doesn't throw an exception.");
    }

    /**
     * Test responsible for checking the returned result of the method
     * calculateCorrelation().
     */
    @Test
    @Order(6)
    public void calculateCorrelationTest() {
        CyberSecuritySalariesException exception = assertThrows(CyberSecuritySalariesException.class, () -> model.calculateCorrelation());
       try{
           assertNotEquals(model.calculateCorrelation(), 0, "Method calculateCorrelation doesn't properly calculate correlation.");
       }
       catch(CyberSecuritySalariesException ex){}
    }

    /**
     * Test responsible for checking the returned result of the method
     * companyLocationPercentageRanking().
     */
    @Test
    @Order(7)
    public void companyLocationPercentageRankingValuesTest() {
        assertFalse(model.companyLocationPercentageRanking().isEmpty(), "Method companyLocationPercentageRanking returns empty result.");
    }

    /**
     * Test responsible for checking the returned result of the method
     * companyLocationPercentageRanking().
     */
    @Test
    @Order(8)
    public void companyLocationPercentageRankingTest() {
        Map<String, Double> percRank = new HashMap<>();
        int Sum = 0;
        // Traverses through array elements and sums their values by the keys. 
        for (var p : this.model.getPersonsArray()) {
            Sum = Sum + p.getRemoteRatio();
            if (percRank.containsKey(p.getCompLoc())) {
                percRank.put(p.getCompLoc(), percRank.get(p.getCompLoc()) + (double) p.getRemoteRatio());
            } else {
                percRank.put(p.getCompLoc(), (double) p.getRemoteRatio());
            }
        }
        for (Map.Entry<String, Double> entry : percRank.entrySet()) {
            percRank.put(entry.getKey(), Math.round(((double) (entry.getValue()) / Sum) * 100000) / 1000.0);
        }
        List<Map.Entry<String, Double>> list = new LinkedList<>(percRank.entrySet());

        //Sort the list using own comparator
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> map1, Map.Entry<String, Double> map2) {
                return (map2.getValue()).compareTo(map1.getValue());
            }
        });
        percRank = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : list) {
            percRank.put(entry.getKey(), entry.getValue());
        }
        assertEquals(model.companyLocationPercentageRanking(), percRank, "Method companyLocationPercentageRanking does not calculate ranking properly.");
    }
}
