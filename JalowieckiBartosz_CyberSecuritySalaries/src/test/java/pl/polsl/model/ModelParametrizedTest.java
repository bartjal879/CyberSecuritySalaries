/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
/*
 * This is the package for all of the pl.polsl.model package classes tests.
 */
package pl.polsl.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * This class contains methods that tests class methods contained in Model.java class.
 * @author Bartosz Jałowiecki
 * @version 2.0
 */
public class ModelParametrizedTest {
    
    public ModelParametrizedTest() {}
    
    
//    @BeforeAll
//    public static void setUpClass() {
//        
//    }
      /**
     * Parametrized test responsible for checking the result of the addPerson method.
     */
   @ParameterizedTest
   @CsvSource({"2022,EN,FT,Information Security Officer,68000,EUR,72762,DE,100,DE,S\n2022,SE,FT,Security Officer,123400,USD,123400,US,0,US,M\n"})
    public void testDataFromCSV(int workY, String expLevel, String empTy, String jTitle, int sal, String salCur, int salUSD, String emplRes, int remRatio, String compLoc, String compSize) {
       Model model = new Model();
        model.addPerson(new Person(workY, expLevel, empTy, jTitle, sal, salCur, salUSD, emplRes, remRatio, compLoc, compSize));
       assertEquals(
                model.getPersonsArray().get(0),new Person(workY, expLevel, empTy, jTitle, sal, salCur, salUSD, emplRes, remRatio, compLoc, compSize),
               "Method addPerson does not add the object to ArrayList"
        );
       fail("Method borrowAlbum for invalid data doesn't throw an exception.");
    }
    
}


