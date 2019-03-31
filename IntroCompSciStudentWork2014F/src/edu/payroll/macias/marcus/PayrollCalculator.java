package edu.payroll.macias.marcus;

import edu.jenks.dist.payroll.*;
public class PayrollCalculator extends AbstractPayrollCalculator
{
    private static boolean doublesEqual(double d1,double d2){
        return Math.abs(d1 - d2) < (1.0 / 3600 / 10);
    }
    public static void main(String[] args){
        System.out.println("Begin");
        PayrollCalculator payRoll = new PayrollCalculator();
        double act = payRoll.convertToHours(5,45,15);
        double exp = 5.754166666;
        assert doublesEqual(act,exp): "Convert to hours:" + exp + ";" + act;
        System.out.println(payRoll.calculatePay(50));
        System.out.println(payRoll.formatPay(264.5688881390873));
        System.out.println("End without Error");
    }
    
    public double convertToHours(int hours, int minutes, int seconds){
        double totalHours = (double)hours;
        double minToHours = (double)minutes / 60;
        double secToHours = (double)seconds / 3600;
        totalHours += minToHours + secToHours;
        return totalHours;
    }
    
    
    public double calculatePay(double hoursWorked){
        double pay = 10.50;
        double overTime = hoursWorked - 40;
        if(hoursWorked > 40){
            double totalPay = (pay * 40) + ((overTime * 1.5) * pay);
            return totalPay;
        }else{
            double totalPay = pay * hoursWorked;
            return totalPay;
        }
    }
    
    
    
    public String formatPay(double pay){
        String format = "$";
        pay *= 100;
        pay = Math.round(pay);
        pay /= 100;
        format += pay;
        
        int index = format.indexOf('.');
        int dot = format.length() - 3;
        if(index != dot){
            format += 0;
        }
        return format;
    }
    
    
}



                                 
                                 
                               