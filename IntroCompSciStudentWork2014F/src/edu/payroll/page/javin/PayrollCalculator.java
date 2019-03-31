package edu.payroll.page.javin;

import edu.jenks.dist.payroll.*;
/**
 * Write a description of class PayrollCalculator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PayrollCalculator extends AbstractPayrollCalculator
{
    public static void main(String[] args){
        System.out.println("Begin");
        PayrollCalculator pc = new PayrollCalculator();
        //double act = pc.convertToHours(5, 45, 15);
        //double exp = 5.7541666;
        //assert doubleIsEqual(act, exp) : "Convert to hours exp: " + exp + "; act: " + act;
        for(int n = 10; n > 0; n--){
            int h = pc.random(0, 60);
            int m = pc.random(0, 60);
            int s = pc.random(0, 60);
            System.out.println((pc.calculatePay(pc.convertToHours(h, m, s))));
        }
        System.out.println("End without error");
    }    
    
    public double convertToHours(int hours, int minutes, int seconds){
        double minToHour = (double)minutes / 60;
        double secToHour = (double)seconds / 3600;
        return hours + minToHour + secToHour;
    }
    public double calculatePay(double hoursWorked){
        double pay = 0.0;
        for(int n = 0; n < 40; n++){
            pay += 10.50;
            hoursWorked--;
            if (hoursWorked < 1){
                pay += (10.50 * hoursWorked);
                break;
            }
        }
        if (hoursWorked >= 1){
            pay += (hoursWorked * (10.50 * 1.5));
            
        }
        return pay;
    }
    public String formatPay(double pay){
        double s = Math.round(pay * 100.0) / 100.0;
        String l = Double.toString(s);
        String f = null, w = null;
        for(int n = 0; n < l.length(); n++){
            if (l.substring(n, n + 1).equals(".")){
                f = l.substring(0, n + 1);
                w = l.substring(n + 1);
            }    
        } 
        if(w.length() < 2){
            w += "0";
        }    
        return "$" + f + w;
   }
    
    private static boolean doubleIsEqual(double d1, double d2){
        return Math.abs(d1 - d2) < (1.0/ 3600/10);
    }  
    private int random(int min, int max){
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;
    }
}
