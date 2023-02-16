/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Package for the controller class and for other supporting classes used
 * for data flow between Model and View inside the applicationin in MVC architecture.
 */
package pl.polsl.controller;

import java.io.FileNotFoundException;
import java.util.*;
import pl.polsl.view.View;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableCellRenderer;
import pl.polsl.model.CyberSecuritySalariesException;
import pl.polsl.model.*;

/**
 * Controls the flow of data between the Model class and the View class.
 *
 * @author Bartosz Jalowiecki
 * @version 3.1-SNAPSHOT
 */
public class Controller implements ActionListener {

    /**
     * Field representing model class in Model-View-Controller architecture.
     */
    private Model model;
    /**
     * Field representing view class in Model-View-Controller architecture.
     */
    private View view;

    private StringBuilder[] result;
    //private Vector<Vector<String>> result;

    private Vector<String> columnNames;

    /**
     * Default controller constructor, that initializes the model and view field
     * with passed arguments.
     *
     * @param m model that will be used in application
     * @param v view that will be used in application
     */
    public Controller(Model m, View v) {
        this.model = m;
        this.view = v;
        result = new StringBuilder[1];
        result[0] = new StringBuilder("Nothing has been pressed");
        //    result = new Vector<Vector<String>>();
        //    result.add(new Vector<String>("Cos tam"));
        columnNames = new Vector<>();
        columnNames.add("Result");
    }

    /**
     * Checks the parameters passed by the user to the program.
     *
     * @param parameters array of strings representing parameters passed by the
     * user to the program.
     * @param filePath if the parameters are valid, updated path of the file -
     * the one passed by user.
     * @return boolean value representing validity of parameters passed by the
     * user to the program.
     */
    public boolean validateConsoleParameters(String[] parameters, StringBuilder filePath) {
        if (parameters.length >= 2 && parameters.length < 5) {
            for (int i = 0; i < parameters.length; i++) {
                String temp = parameters[i];
                if (temp.equals("-i")) {
                    filePath.append(parameters[i + 1]);
                } else if (temp.equals("-h")) {
                    return false;
                }
            }
            return true;
        }
        view.updateView("You enetered wrong parameters. You should enter a path to the file aftter \"-i\" switch. \nSample command: \napp.exe -i C:/users/file.csv.\n");
        return false;
    }

    /**
     * Adds Person objects loaded from the given file whose name is a parameter.
     *
     * @param filePath string representing the path of file or the name of file
     * present in the project directory.
     * @throws FileNotFoundException
     */
    private void loadDataFromFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        if (f.isFile() && f.exists()) {
            Scanner csvScanner = new Scanner(new File(filePath));
            csvScanner.nextLine();
            csvScanner.useDelimiter(",");   //sets the delimiter pattern
            while (csvScanner.hasNext()) //returns a boolean value
            {
                int workYear = Integer.parseInt(csvScanner.next());
                String experienceLevel = csvScanner.next();
                String employentType = csvScanner.next();
                String jobTitle = csvScanner.next();
                int salary = csvScanner.nextInt();
                String salaryCurrency = csvScanner.next();
                int salaryInUSD = Integer.parseInt(csvScanner.next());
                String employeeResidence = csvScanner.next();
                int remoteRatio = Integer.parseInt(csvScanner.next());
                String companyLocation = csvScanner.next();
                String companySize = csvScanner.findInLine(Pattern.compile("[SML]"));
                model.addPerson(new Person(workYear, experienceLevel, employentType, jobTitle, salary, salaryCurrency, salaryInUSD, employeeResidence, remoteRatio, companyLocation, companySize));
                if (csvScanner.hasNext()) {
                    csvScanner.nextLine();
                }
            }
            //closes the scanner 
            csvScanner.close();
        } else {
            throw new FileNotFoundException("Path you entered doesn't exist.");
        }
    }

    /**
     * Calls the LoadFile method on the model field specifying the file path.
     *
     * @param Path
     * @return success of operation
     */
    public boolean addPersonsFromFileToModel(String Path) {
        try {
            loadDataFromFile(Path);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage() + " Wrong file path: " + Path);
            return false;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Calls the currencyWithMostSalaries method on the model field, and then
     * with updateView method lists the items contained in the result array.
     */
    public void viewCurrencyWithMostSalaries() {
        for (int i = 0; i < model.currencyWithMostSalaries().size(); i++) {
            view.updateView(model.currencyWithMostSalaries().get(i), "\n");
        }
    }

    /**
     * Calls the JobTitleWithhighestSalaries method on the model field, and then
     * with updateView method lists the items contained in the result array.
     */
    public void viewJobTitleWithHighestSalaries() {
        for (int i = 0; i < model.jobTitleWithHighestSalaries().size(); i++) {
            view.updateView(model.jobTitleWithHighestSalaries().get(i) + '\n');
        }
    }

    /**
     * Calls the getCorrelation method on the model field, and then calls the
     * updateView method, which shows the returned by previous method value in
     * the result .
     */
    public void viewCorrelation() {
        try {
            view.updateView(model.calculateCorrelation() + "" + '\n');
        } catch (CyberSecuritySalariesException ex) {
            //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Calls the sortCurrenciesOnTypeOfEmployment method on the model field, and
     * then calls the updateView method, which shows the returned by previous
     * method values in the result .
     */
    public void viewSortedCurrencies() {
        boolean evenOrOdd = false;
        for (var tempString : model.sortCurrenciesOnTypeOfEmployment()) {
            if (evenOrOdd) {
                evenOrOdd = !evenOrOdd;
                view.updateView(tempString + " ");
            } else {
                evenOrOdd = !evenOrOdd;
                view.updateView(tempString, "\\n");
            }
        }
    }

    /**
     * Calls the companyLocationPercentageRanking() method on the model field
     * and copies returned values to the entrySet. Next calls the updateView
     * method, which shows the copied values in the result .
     */
    public void viewRankingOfCompLoc() {
        for (Map.Entry<String, Double> entry : model.companyLocationPercentageRanking().entrySet()) {
            view.updateView(entry.getValue() + "%" + '\t' + entry.getKey() + '\n');
        }
    }

    /**
     * Generates the UI window
     */
    public void generateUI() {
        // Displaying an empty window
        JFrame frame = view.generateWindowView();
        Container contentPane = frame.getContentPane();
        JLabel label = new JLabel("Options for app:");
        ButtonGroup bg = new ButtonGroup();

        JRadioButton rb1 = new JRadioButton();
        rb1.setText("Wyświetl stanowisko, na którym pracujące osoby zarabiają najwięcej");
        rb1.setActionCommand("view_jobtitle");
        rb1.addActionListener(this);
        bg.add(rb1);

        JRadioButton rb2 = new JRadioButton();
        rb2.setText("Sortuj waluty według rodzaju zatrudnienia");
        rb2.setActionCommand("sort_curr");
        rb2.addActionListener(this);
        bg.add(rb2);
        //
        JRadioButton rb3 = new JRadioButton();
        rb3.setText("Wyświetl walutę, w której najczęsciej wypłacana jest pensja");
        rb3.setActionCommand("view_curr");
        rb3.addActionListener(this);
        bg.add(rb3);

        JRadioButton rb4 = new JRadioButton();
        rb4.setText("Oblicz korelację Pearsona pomiędzy krajem, a pensją brutto");
        rb4.setActionCommand("pearson_corr");
        rb4.addActionListener(this);
        bg.add(rb4);

        JRadioButton rb5 = new JRadioButton();
        rb5.setText("Wyświetl procentowy ranking siedziby firm, których pracownicy najczęsciej pracowali zdalnie");
        rb5.setActionCommand("perc_rank");
        rb5.addActionListener(this);
        bg.add(rb5);

        Box box = Box.createVerticalBox();
        box.add(label);
        //free space beetween label and first radioButton
        box.add(Box.createVerticalStrut(5));
        box.add(rb1);
        box.add(rb2);
        box.add(rb3);
        box.add(rb4);
        box.add(rb5);
        //adding an empty space between box elements and border
        box.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(box, BorderLayout.CENTER);
        JButton button = new JButton("Do");
        button.setActionCommand("new_win");
        button.addActionListener(this);
        JPanel pane = new JPanel();
        pane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pane.add(button);
        contentPane.add(pane, BorderLayout.PAGE_END);
        frame.getRootPane().setDefaultButton(button);
        frame.pack();
        frame.setLocationRelativeTo(null); //center it
        frame.setVisible(true);
        // Adding all application elements to empty window  
    }

    /**
     * Controller method responsible for calling appropriate model methods
     *
     * @param e The ActionEvent that caused the call of the method.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (null != command) {
            switch (command) {
                case "new_win" -> {
                    Vector data = new Vector<Vector<String>>();
                    for (var p : result) {
                        data.add(new Vector<String>(Arrays.asList(p.toString().split("\\r?\\n|\t"))));
                    }   //            data.add(Arrays.asList(Arrays.toString(result).split("\\r?\\n|(\t)")));
                    JTable table = new JTable(data, columnNames) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }

                        @Override
                        public Class<?> getColumnClass(int column) {
                            return getValueAt(0, column).getClass();
                        }
                    };
                    //Vector data = new Vector<String>(Arrays.asList(result.toString().split("\\r?[\\n\t]")));
                    //JList textList = new JList(data);
                    //Font font = new Font("Arial",Font.PLAIN,20);
                    //textList.setFont(font);
                    DefaultTableCellRenderer centerRenderer = (DefaultTableCellRenderer) table.getDefaultRenderer(table.getColumnClass(0));
                    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                    JScrollPane scrollPane = new JScrollPane(table);//textList);
                    //DefaultListCellRenderer renderer = (DefaultListCellRenderer)textList.getCellRenderer();
                    //renderer.setHorizontalAlignment(SwingConstants.CENTER);
                    //scrollPane.setMaximumSize(new Dimension(500, 200));
                    view.showNewWindow(scrollPane, JOptionPane.PLAIN_MESSAGE);
                }
                case "view_jobtitle" -> {
                    //Handler of "Do" button
                    result = new StringBuilder[1];
                    result[0] = new StringBuilder();
                    columnNames.clear();
                    columnNames.add("Job title");
                    for (var string : model.jobTitleWithHighestSalaries()) {
                        result[0].append(string).append('\n');
                    }
                }
                case "view_curr" -> {
                    result = new StringBuilder[1];
                    result[0] = new StringBuilder();
                    columnNames.clear();
                    columnNames.add("Currency");
                    for (var string : model.currencyWithMostSalaries()) {
                        result[0].append(string).append('\n');
                    }
                }
                case "sort_curr" -> {
                    result = new StringBuilder[model.sortCurrenciesOnTypeOfEmployment().size() / 2];
                    for (int i = 0; i < result.length; i++) {
                        result[i] = new StringBuilder();
                    }
                    columnNames.clear();
                    columnNames.add("Type of employment");
                    columnNames.add("Currency");
                    int iterator = 0;
                    for (var tempString : model.sortCurrenciesOnTypeOfEmployment()) {
                        if (iterator % 2 == 0) {
                            result[iterator / 2].append(tempString).append("\t");
                        } else {
                            result[(iterator - 1) / 2].append(tempString).append('\n');
                        }
                        iterator++;
                    }
                }
                case "pearson_corr" -> {
                    result = new StringBuilder[1];
                    result[0] = new StringBuilder();
                    columnNames.clear();
                    columnNames.add("Correlation");
                    try {
                        result[0].append(model.calculateCorrelation().toString());
                    } catch (CyberSecuritySalariesException ex) {
                        view.showNewWindow(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                    }
                }
                case "perc_rank" -> {
                    result = new StringBuilder[model.companyLocationPercentageRanking().size()];
                    for (int i = 0; i < result.length; i++) {
                        result[i] = new StringBuilder();
                    }
                    columnNames.clear();
                    columnNames.add("Percentage");
                    columnNames.add("Company locaiton");
                    int i = 0;
                    for (var entry : model.companyLocationPercentageRanking().entrySet()) {
                        result[i].append(entry.getValue().toString()).append("%").append("\t");
                        result[i].append(entry.getKey()).append('\n');
                        i++;
                    }
                }
                default -> {
                }
            }
        }
    }
}
