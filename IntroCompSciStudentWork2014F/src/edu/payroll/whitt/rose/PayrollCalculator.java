package edu.payroll.whitt.rose;



import edu.jenks.dist.payroll.*;

/**
 * Write a description of class PayrollCalculator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PayrollCalculator extends AbstractPayrollCalculator
{
    private static boolean doublesEqual(double d1, double d2) {
        return Math.abs(d1 - d2) < (1.0 / 3600 / 10);
    }
    
    public static void main(String[] args) {
        System.out.println("Begin");
        PayrollCalculator pc = new PayrollCalculator();
        double act = pc.convertToHours(5, 45, 15);
        double exp = 5.7541666;
        assert doublesEqual(act, exp) : "Convert to hours exp; " + exp + "; act: " + act;
        System.out.println(act);
        
       
        double boi = pc.calculatePay(act);
        System.out.println(boi);
        
        //double th = Math.abs(boi - (int)boi);
        //System.out.println(th);
        
        String finp = pc.formatPay(boi);
        System.out.println(finp);
        
        System.out.println("End without error");
    }
    
    public double convertToHours(int hours, int minutes, int seconds) {
        double a = (double) hours;
        double b = (double) minutes;
        double c = (double) seconds;
        double one = b / (double) 60;
        double two = c / (double) 3600;
        //double sec =  one + two;
        double thing = a + one + two;
        return thing;
        
        
    }
    
    public double calculatePay(double hoursWorked) {
        double data = 0.0;
        double over = hoursWorked - (double) 40;
        if(over > 0) {
            double xtra = over * (double) 1.5;
            double less = hoursWorked - over;
            data = (less * (double) 10.5) + (xtra * (double) 10.5);
            //int value = (int)overtime; 
            //return data;
        } else if (over <= 0) {
            data = hoursWorked * (double) 10.5;
            //int value = (int)data; 
            //double dif = Math.abs(data - value);
            //return data;
        }
        return data;
        
    }
    
    public String formatPay(double pay) {
        double big = (pay * 100);
        int rnd = (int) Math.round(big);
        String far = String.valueOf(rnd);
        int len = far.length();
        String cents = "" + far.charAt(len - 2) + far.charAt(len - 1);
        String dollars = "";
        for(int i = 0; i < len - 2; i++){
            dollars += "" + far.charAt(i);
        }
        String fin = "$" + dollars + "." + cents;
        return fin;
    }
}
