package edu.payroll.hollingsworth.james;
import edu.jenks.dist.payroll.AbstractPayrollCalculator;

public class PayrollCalculator extends AbstractPayrollCalculator
{   
    public static void main(String args[]) {
        System.out.println("Starting...");
        PayrollCalculator calc = new PayrollCalculator();
        
        double hoursWorked = calc.convertToHours(1, 0, 0);
        double pay = calc.calculatePay(hoursWorked);
        
        System.out.println(hoursWorked);
        System.out.println(pay);
        System.out.println(calc.formatPay(pay));
        
        System.out.println("Finished without errors!");
    };
    public double calculatePay(double hoursWorked) {
        if(hoursWorked <= 40.0) return hoursWorked * 10.50;
        else return 420.0 + (((hoursWorked - 40) * 1.5) * 10.5);
    };
    public double convertToHours(int hours, int minutes, int seconds) {
        double secondsToHours = (seconds / 60.0) / 60.0;
        double minutesToHours = minutes / 60.0;
        return minutesToHours + secondsToHours + hours;
    };
    public String formatPay(double pay) {
        String temp = "$" + (Math.round(pay * 100.0) / 100.0);
        if(temp.length() < 6) return temp + "0";
        return temp;
    };
}
