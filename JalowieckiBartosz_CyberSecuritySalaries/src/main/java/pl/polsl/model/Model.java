/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Package for the model class and for other supporting classes used
 * for data handling and manipulation inside the application in Model View Controller architecture .
 */
package pl.polsl.model;

import java.util.ArrayList;
import java.util.*;
import java.util.stream.*;

import org.apache.commons.math.stat.correlation.PearsonsCorrelation;

/**
 * Represents the visualization of the data that model contains.
 *
 * @author Bartosz Jalowiecki
 * @version 3.1-SNAPSHOT
 */
public class Model {

    /**
     * Field representing the ArrayList of person class objects
     */
    private final ArrayList<Person> persons;

    /**
     * Default model constructor.
     */
    public Model() {
        persons = new ArrayList<>();
    }

    /**
     * Accessor to ArrayList of person's objects used in model.
     *
     * @return person's objects stored in ArrayList, that are used in model .
     */
    public ArrayList<Person> getPersonsArray() {
        return persons;
    }

    /**
     * Adds an object of class Person to Persons Arraylist.
     *
     * @param pers array of Person objects to add
     */
    public void addPerson(Person... pers){
        persons.addAll(Arrays.asList(pers));
    }

    /**
     * Finds in the Persons ArrayList job or jobs with the highest average
     * salary per peroson.
     *
     * @return The job or jobs with highest salaries.
     */
    public ArrayList<String> jobTitleWithHighestSalaries() {
        Map<String, Integer> mp = new HashMap<>();
        Map<String, Integer> map = new HashMap<>();

        // Traverse through array elements, counts frequencies and sums their salaries
        for (var p : this.persons) {
            if (mp.containsKey(p.getJobTitle())) {
                mp.put(p.getJobTitle(), mp.get(p.getJobTitle()) + p.getSalaryUSD());
                map.put(p.getJobTitle(), map.get(p.getJobTitle()) + 1);
            } else {
                mp.put(p.getJobTitle(), p.getSalaryUSD());
                map.put(p.getJobTitle(), 1);
            }
        }
        int maxSalary = 0;
        ArrayList<String> maxKeys = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : mp.entrySet()) {
            String key = entry.getKey();
            int sumOfSalaries = entry.getValue();
            int numberOfSalaries = map.get(key);
            double average = (double) sumOfSalaries / numberOfSalaries;
            if (average > maxSalary) {
                maxKeys.clear();
                /* New max remove all current keys */
                maxKeys.add(entry.getKey());
                maxSalary = (int) average;
            } else if (entry.getValue() == maxSalary) {
                maxKeys.add(entry.getKey());
            }
        }
        return maxKeys;
    }

    /**
     * Sorts the currencies on type of employemt.
     *
     * @return The array of strings representing sorted table.
     */
    public ArrayList<String> sortCurrenciesOnTypeOfEmployment() {
        //char[][][] currWithTypeOfEmpl = new char[this.persons.size()][2][0];
        ArrayList<ArrayList<String>> currWithTypeOfEmpl = new ArrayList<ArrayList<String>>(this.persons.size());
        for (int i = 0; i < this.persons.size(); i++) {
            ArrayList<String> temp = new ArrayList<String>();
            temp.add(this.persons.get(i).getTypeOfEmpl());
            temp.add(this.persons.get(i).getCurrency());
            currWithTypeOfEmpl.add(temp);
        }
        Collections.sort(currWithTypeOfEmpl, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> entry1, ArrayList<String> entry2) {
                return (entry1.get(0).compareTo(entry2.get(0)) == 0) ? entry1.get(1).compareTo(entry2.get(1)) : entry1.get(0).compareTo(entry2.get(0));
            }
        });  // End of function call sort().
        ArrayList<String> sortedCollection = new ArrayList<String>();
        for (var p : currWithTypeOfEmpl) {
            sortedCollection.add(p.get(0));
            sortedCollection.add(p.get(1));
        }
        return sortedCollection;
    }

    /**
     * Finds the currencies with the most salaries paid per peroson.
     *
     * @return The currency or currencies with highest salaries.
     */
    public List<String> currencyWithMostSalaries() {
        Map<String, Integer> mp = new HashMap<>();
        // Traverse through array elements and count frequencies
        for (int i = 0; i < this.persons.size(); i++) {
            if (mp.containsKey(this.persons.get(i).getCurrency())) {
                mp.put(this.persons.get(i).getCurrency(), mp.get(this.persons.get(i).getCurrency()) + 1);
            } else {
                mp.put(this.persons.get(i).getCurrency(), 1);
            }
        }
        var maxKeys = mp.entrySet()/*values()*/.stream()
                .max(Map.Entry.comparingByValue())
                .map(e -> e.getKey())
                .stream()//.collect(Collectors.toCollection(ArrayList::new));
                .collect(Collectors.toList());

//    int maxValue = 0; 
//    for(Map.Entry<String,Integer> entry : mp.entrySet()) {
//     if(entry.getValue() > maxValue) {
//         maxKeys.clear(); // New max remove all current keys 
//         maxKeys.add(entry.getKey());
//         maxValue = entry.getValue();
//     }
//     else if(entry.getValue() == maxValue)
//     {
//       maxKeys.add(entry.getKey());
//     }
//    }
        return maxKeys;
    }

    /**
     * Calculates the Pearson correlation between the employee residence and
     * salary after tax fee.
     *
     * @return The Pearson correlation beetwen employee residence and their
     * salary after tax fee.
     * @throws pl.polsl.model.CyberSecuritySalariesException
     */
    public Double calculateCorrelation() throws CyberSecuritySalariesException {
        Map<String, Integer> mp = new HashMap<>();
        int index = 0;
        // Traverse through array elements and gives every distinct string unique index  
        for (var p : this.persons) {
            if (mp.containsKey(p.getEmplRes())) {
                mp.get(p.getEmplRes());
            } else {
                mp.put(p.getEmplRes(), index);
                index++;
            }

        }
        double[] first = new double[persons.size()];
        double[] second = new double[persons.size()];
        for (int i = 0; i < persons.size(); i++) {
            first[i] = mp.get(this.persons.get(i).getEmplRes());
            second[i] = this.persons.get(i).getSalary();
        }
        Double pearsonsCorr = new PearsonsCorrelation().correlation(first, second);
        if (pearsonsCorr.isNaN()) {
            throw new CyberSecuritySalariesException("The data stored in the model is not valid for calculating the Pearsons correlation.");
        }
        return pearsonsCorr;
    }

    /**
     * Calculates the percenrage of employees remote ratio in each company
     * location.
     *
     * @return The ranking of company locations with the biggest number of
     * remote workers.
     */
    public Map<String, Double> companyLocationPercentageRanking() {
        Map<String, Double> mp = new HashMap<>();
        int Sum = 0;
        // Traverses through array elements and sums their values by the keys. 
        for (var p : this.persons) {
            Sum = Sum + p.getRemoteRatio();
            if (mp.containsKey(p.getCompLoc())) {
                mp.put(p.getCompLoc(), mp.get(p.getCompLoc()) + (double) p.getRemoteRatio());
            } else {
                mp.put(p.getCompLoc(), (double) p.getRemoteRatio());
            }
        }
        for (Map.Entry<String, Double> entry : mp.entrySet()) {
            mp.put(entry.getKey(), Math.round(((double) (entry.getValue()) / Sum) * 100000) / 1000.0);
        }
        List<Map.Entry<String, Double>> list = new LinkedList<>(mp.entrySet());

        //Sort the list using own comparator
        Collections.sort(list, (Map.Entry<String, Double> map1, Map.Entry<String, Double> map2) -> (map2.getValue()).compareTo(map1.getValue()));

        //Putting data from sorted list to hashmap
        mp = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : list) {
            mp.put(entry.getKey(), entry.getValue());
        }
        return mp;
    }
}
