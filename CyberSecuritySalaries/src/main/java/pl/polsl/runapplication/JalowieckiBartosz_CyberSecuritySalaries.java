/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package pl.polsl.runapplication;

import pl.polsl.controller.Controller;
import pl.polsl.model.Model;
import pl.polsl.view.View;

/**
 * Class that manages the beginning of program execution.
 *
 * @author Bartosz Jalowiecki
 * @version 3.1-SNAPSHOT
 */
public class JalowieckiBartosz_CyberSecuritySalaries {

    /**
     * Entry point of program. Checks the parameters specified when invoking the
     * program. Initializes the controller class and calls methods on it.
     *
     * @param args Call parameters of the application. Available switch "-i", after which
     * must be entered the path of the source file with data for the apllication.
     */
    public static void main(String[] args) {
        //number of parameters passed to the program
        //int numberOfParameters = args.length;
        Model model = new Model();
        View view = new View(model);
        Controller contr = new Controller(model, view);

        StringBuilder filePath = new StringBuilder();
        String[] param = args;        
        if (!contr.validateConsoleParameters(param, filePath)) {
            return;
        }
        if (!contr.addPersonsFromFileToModel(filePath.toString())) {
            return;
        }
        javax.swing.SwingUtilities.invokeLater(() -> {
            contr.generateUI();
        });
//        contr.viewCurrencyWithMostSalaries();
//        contr.viewJobTitleWithHighestSalaries();
//        contr.viewCorrelation();
//        contr.viewSortedCurrencies();
//        contr.viewRankingOfCompLoc();

    }

}
