/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

/**
 * Class which defines application's specific exception.
 *
 * @version 1.0
 * @author Bartosz Jałowiecki
 */
public class CyberSecuritySalariesException extends Exception {

    /**
     * Constructor of exception which lets you handle CyberSecurotySalaries
     * specific exception.
     *
     * @param errorText text of the error message.
     */
    public CyberSecuritySalariesException(String errorText) {
        super(errorText);
    }
}
