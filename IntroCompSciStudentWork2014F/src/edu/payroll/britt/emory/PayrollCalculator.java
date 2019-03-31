package edu.payroll.britt.emory;
import edu.jenks.dist.payroll.*; 

/**
 * Write a description of class PayrollCalculator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PayrollCalculator extends AbstractPayrollCalculator {
    private static boolean doublesEqual(double d1, double d2){
        return Math.abs(d1 - d2) < (1.0 / 3600 / 10);
    }
    public static void main(String[] args){
        System.out.println("begin");
        PayrollCalculator pc = new PayrollCalculator();
        double act = pc.convertToHours(45, 45, 15);
        double exp = 45.7541666; 
        assert doublesEqual(act, exp) : "Convert To Hours exp: " + exp + "; act: " + act;
        double act2 = pc.calculatePay(exp);
        double exp2 = 510.628124;
        assert doublesEqual(act2, exp2) : "Calculate Pay exp: " + exp2 + "; act: " + act2;
        String act3 = pc.formatPay(1.0);
        String exp3 = "$1.00";
        assert act3 == exp3 : "Format Pay exp: " + exp3 + " ; act: " + act3; 
        System.out.println("End without error");
    }
    public double convertToHours(int hours, int minutes, int seconds){
        double minuteH = minutes/60.0;
        double secH = seconds/3600.0;
        return hours + minuteH + secH; 
    }
    public double calculatePay(double hoursWorked){
        double pay = 0.0;
        if(hoursWorked < 40){
            pay = 10.50 * hoursWorked;
        } else{
            double overTime = hoursWorked - 40.0; 
            pay = 10.50 * 40;
            double overTimePay = 15.75 * overTime; 
            pay += overTimePay; 
        }
        return pay; 
    }
    public String formatPay(double pay){
        pay = pay * 100; 
        int payI = (int)Math.round(pay);
        double payF = payI / 100.00;
        String payS = "";
        payS += payF;
        if(payS.length() == 3){
            return "$" + payS + "0";
        }
        return "$" + payS; 
    }
}
