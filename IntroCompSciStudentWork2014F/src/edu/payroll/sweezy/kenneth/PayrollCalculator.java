package edu.payroll.sweezy.kenneth;

import edu.jenks.dist.payroll.*;

public class PayrollCalculator extends AbstractPayrollCalculator {
    private static boolean doublesEqual(double d1, double d2) {
        return Math.abs(d1 - d2) < (1.0 / 3600 / 10);
    }

    public static void main(String[] args) {
        System.out.println("Begin Tests");
        PayrollCalculator pc = new PayrollCalculator();
        double actual = pc.convertToHours(0, 9, 0);
        double payAmount = pc.calculatePay(actual);
        pc.formatPay(payAmount);
        System.out.println("Tests Completed Without Error");
    }

   public double convertToHours(int hours, int minutes, int seconds) {
       assert hours >= 0 : "Hours value is negative, expected positive";
       assert minutes >= 0 : "Minutes value is negative, expected positive";
       assert seconds >= 0 : "Seconds value is negative, expected positive";
       double total = 0;
       total += hours;
       total += (double)minutes / 60;
       total += (double)seconds / 3600;
       return (Math.floor(total * 100) / 100.0);
    }

   public double calculatePay(double hoursWorked) {
       assert hoursWorked >= 0 : "Hours worked value is negative, expected positive";
       double tempPay = 0.0;
       if(hoursWorked > 40) {
           tempPay += 40 * 10.5;
           tempPay += ((hoursWorked - 40) * 15.75);
       } else {
           tempPay += hoursWorked * 10.5;
       }
       return tempPay;
    }

    public String formatPay(double pay) {
        double mathThing = (Math.round(pay * 100.0) / 100.0);
        String stringthing = String.valueOf(mathThing);
        int decimalLocation = stringthing.indexOf('.');
        if(stringthing.charAt(stringthing.length() - 2) == '.') {
            System.out.println("$" + stringthing + "0");
            return "$" + stringthing + "0";
        } else {
            System.out.println("$" + stringthing);
            return "$" + stringthing;
        }
    }
    /*
    public String decimalCount(String enteredString) {
        int tempCount = 0;
        String tempString = "";
        boolean passedDecimal = false;
       for(int i = 0; i < enteredString.length(); i++) {
            if(enteredString.charAt(i) == '.'){
                passedDecimal = true;
            }
            if(passedDecimal) {
                tempCount++;
            }
        }
        if(tempCount == 1) {
            tempString += "0";
        } else if(tempCount < 1) {
            tempString += "00";
        }
        return tempString;
    }
    */
}

/*
assert pay >= 0.0 : "Pay value is negative, expected positive";
        double mathThing = (Math.round(pay * 100.0) / 100.0);
        String stringthing = String.valueOf(mathThing);
        String completedStringThing = "";
        boolean passedDecimal = false;
        int tempCount = 0;
        int tempCount2 = 0;
        String temporaryString = decimalCount(stringthing);
        boolean passedDecimalCheck = false;
        int charsPassed = 0;
        while(currentChar != '.') {
            charsPassed++;
        }
        if(charsPassed < 2) {
            temporaryString += "0";
        } else if(charsPassed )
        completedStringThing = "$" + stringthing;
        System.out.println("Completed String: " + completedStringThing);
        return completedStringThing;
*/