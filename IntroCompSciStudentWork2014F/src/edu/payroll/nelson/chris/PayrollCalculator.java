/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.payroll.nelson.chris;
import edu.jenks.dist.payroll.*;
/**
 *
 * @author chris
 */
public class PayrollCalculator extends AbstractPayrollCalculator{

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        PayrollCalculator pay = new PayrollCalculator();
    }
    public double convertToHours(int hours, int minutes, int seconds){
        double doubleMin = minutes;
        double decimalMin = (doubleMin/60);
        
        double doubleSec = seconds;
        double decimalSec = doubleSec/3600; 
        
        double hoursWorked = hours + decimalMin + decimalSec;
        return hoursWorked;
    }
    public double calculatePay(double hoursWorked){
        double overtimeHours = hoursWorked - 40; 
        double overtimePay = 0;
        double pay = 0;
        
        if(overtimeHours>0){
            overtimePay += 15.75*overtimeHours;
            hoursWorked-=overtimeHours;
        }
        pay += (hoursWorked*10.50) + overtimePay;  
        return pay;
    }
    public String formatPay(double pay){
        double dollars = Math.floor(pay);
        double roundedCents = Math.round((pay - dollars)*100);
        String dollarString = Integer.toString((int) dollars);
        String centString = Integer.toString((int) roundedCents);
       
        if(((int) roundedCents)<10){
            centString = "0" + centString; 
        }
        if(((int) roundedCents>=100)){
            dollars += 1;
            dollarString = Integer.toString((int) dollars);
            centString = "00";
        }
        
        return "$" + dollarString + "." + centString;
    }
}
