    
package edu.payroll.tran.don;

import edu.jenks.dist.payroll.*;

public class PayrollCalculator extends AbstractPayrollCalculator
{
    private static boolean doublesEqual(double d1, double d2) {
        return Math.abs(d1 - d2) < (1.0 / 3600 / 10);
    }
    public static void main (String[] args) {
        System.out.println("Begin");
        PayrollCalculator pc = new PayrollCalculator();
        String act = pc.formatPay(313.44);
        System.out.println(act);
        System.out.println("End without error");
    }
    public double convertToHours(int hours, int minutes, int seconds) {
        double minutesToHours = (double)minutes / 60;
        double secondsToHours = (double)seconds / 3600;
        //System.out.println(hours + minutesToHours + secondsToHours);
        return (double)(hours + minutesToHours + secondsToHours);
    }
    public double calculatePay(double hoursWorked) {
        double pay = 0;
        double hours = hoursWorked;
        if(hoursWorked > 40) {
            double overTimeHours = (hours - 40);
            overTimeHours *= 1.5;
            pay = (overTimeHours + 40) * 10.5;
        } else {
            pay = (double)hoursWorked * 10.5; 
        }
        return pay;
    }
    public String formatPay(double pay) {
        String result = "$";
        double tempPay = pay * 100.0;
        int numTempPay = (int)tempPay;
        String wordTempPay = Integer.toString(numTempPay);
        int secondLastNum = Integer.parseInt(Character.toString(wordTempPay.charAt(wordTempPay.length() - 1)));
        double roundPay = Math.round(tempPay);
        double realPay = roundPay / 100;
        //System.out.println(realPay);
        if(secondLastNum == 0) {
            result = "$" + realPay + "0";
        } else {
            result = "$" + realPay; 
        }
        System.out.println(result + "fadsf" ) ;
        /*
        double tempPay = pay *= 1000;
        int tempNumPay = (int)tempPay; 
        double numPay = tempNumPay / 10;
        String roundPay = Integer.toString(tempNumPay);
        String lastNum = Character.toString(roundPay.charAt(roundPay.length() - 1));
        int lastDigit = Integer.parseInt(lastNum);
        String secondLastNum = Character.toString(roundPay.charAt(roundPay.length() - 2));
        int secondLastDigit = Integer.parseInt(secondLastNum);
        if(lastDigit >= 5) {
            numPay += 1.0;
        }
        numPay /= 100;
        if(secondLastDigit == 0) {
            result = "$" + numPay + "0"; 
        } else {
            result = "$" + numPay;
        }
        System.out.println(result + "sadf" );
        */
        return result;
    }
}
