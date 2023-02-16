/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Package for the view class and for other supporting classes used for
 * viewing data inside the application in Model View Controller architecture .
 */
package pl.polsl.view;


import javax.swing.*;
import pl.polsl.model.Model;

/**
 * Represents the visualization of the data that model contains.
 *
 * @author Bartosz Jalowiecki
 * @version 3.1-SNAPSHOT
 */
public class View {

    Model model = new Model();

    /**
     * Default view constructor.
     */
    public View(Model m) {
        model = m;
    }
//    /** stala dla komend zdarzen */
//    protected final static String JOB_TITLE = "view_job_title";
//    /** stala dla komend zdarzen */
//    protected final static String SORT_CURRENCIES = "sort_curr";
//    /** stala dla komend zdarzen */
//    protected final static String CURRENCY_MOST_SALARIES = "curr_most_sal";
//    /** stala dla komend zdarzen */
//    protected final static String PEARSON_CORRELATION = "pearson_corr";
//    /** stala dla komend zdarzen */
//    protected final static String PERCENTAGE_RANKING = "perc_rank";
//    /** stala dla komend zdarzen */
//    protected final static String CREATE_WINDOW = "new_win";
//
////    protected final static String FILE_ICON = "file_icon";
////    /** stala dla komend zdarzen */
////    protected final static String PAINT_ICON = "paint_icon";

    /**
     * Displays the string supplied as a parameter.
     *
     * @param temp strings to print
     */
    public void updateView(String... temp) {
        for (String S : temp) {
            System.out.print(S);
        }
    }

    /**
     * Displays the double supplied as a parameter.
     *
     * @param d doubles to print
     */
    public void updateView(double... d) {
        for (var x : d) {
            System.out.print(x);
        }
    }

    /**
     * Displays the integer supplied as a parameter.
     *
     * @param i integers to print
     */
    public void updateView(int... i) {
        for (var x : i) {
            System.out.print(x);
        }
    }

    public JFrame generateWindowView() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //400 width and 500 height
        frame.setSize(400, 500);
        frame.setVisible(true);
        return frame;
    }

    
    public void showNewWindow(String result, int type) {
        JOptionPane.showMessageDialog(null, result, "Result", type);
    }

    public void showNewWindow(JScrollPane result, int type) {
        JOptionPane.showMessageDialog(null, result, "Result", type);
    }

}
