package edu.payroll.savelyev.denis;

import edu.jenks.dist.payroll.*;

public class PayrollCalculator extends AbstractPayrollCalculator{
    private static boolean doublesEqual(double d1, double d2){
        return Math.abs(d1 - d2) < (1.0 / 3600 / 10);
    }
    public static void main (String[] args){
        PayrollCalculator pc = new PayrollCalculator();
        String answer = pc.formatPay(106);
        System.out.println(answer);
    }
    public double convertToHours(int hours,int minutes,int seconds){
        double totalHours = 0;
        double minInHr = (double)minutes / 60;
        double secInHr = (double)seconds / 3600;
        totalHours += hours;
        totalHours += minInHr;
        totalHours += secInHr;
        double total = (Math.floor(totalHours * 100.0) / 100.0);
        return total;
    }
    public double calculatePay(double hoursWorked){
        double payFin = 0;
        if (hoursWorked <= 40){
            payFin += (hoursWorked * 10.5);
        } else {
            payFin += 420 + ((hoursWorked - 40)* 15.75);
        }
        return payFin;
    }
    public String formatPay(double pay){
        pay *= 100;
        pay = Math.round(pay);
        pay /= 100;
        String finPay = String.valueOf(pay);
        for (int i = 0; i <= finPay.length() - 1; i++){
            if(finPay.charAt(i) == '.'){
                if (finPay.charAt(i + 1) == '0'){
                    finPay += "0";
                }
            }
        }
        System.out.println(finPay);
        return "$" + finPay;
    }
}
