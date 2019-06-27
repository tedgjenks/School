package edu.math.gabriel.mitchell;
import edu.jenks.dist.math.*;
import java.util.*;

public class BasicCalculator implements Calculates{
	
    private final String OPERANDS = "^*/+-";
    private final int[] PREC = {3,2,2,1,1};
    private String equation = "";
    
    public BasicCalculator(){
    }
    
    public static void main(String[] args){
        BasicCalculator calc = new BasicCalculator();
        assert calc.performCalculation("3 + 8 ").equals("11.0") : "Wrong 3 + 8";
        assert calc.performCalculation("3-8").equals("-5.0") : "Wrong -5.0";
        assert calc.performCalculation("3^4").equals("81.0") : "Wrong 81.0";
        assert calc.performCalculation("3^ 4+ 8").equals("89.0") : "Wrong 89.0";
        assert calc.performCalculation("8 + 3^ 4").equals("89.0") : "op Wrong 89.0";
        assert calc.performCalculation("3^ 4+ 8/16").equals("81.5") : "Wrong 81.5: " + calc.performCalculation("3^ 4+ 8/16");
    }
    
    public String performCalculation(String eqIn){
        equation = eqIn;
        cleanUpWhites();
        equation = equation.replaceAll("\\+\\+","+");
        equation = equation.replaceAll("-\\+","-");
        equation = equation.replaceAll("\\+-","-");
        equation = equation.replaceAll("--","+");
        if (isSolo(eqIn)){
            return eqIn;
        } else{
        	ArrayList<String> shunted = shunt(equation);
        	String answer = postShunt(shunted);
            return answer;
        }
    }
    private ArrayList<String> shunt(String oIn) {
    	String In = oIn.substring(0);
    	ArrayList<String> ops = new ArrayList<>();
    	ArrayList<String> out = new ArrayList<>();
    	for(int active = 0; active < In.length(); active++) {
    		if(OPERANDS.indexOf(In.substring(active,active+1)) != -1) { // If active char is Operand
    			if(active > 0) {
    				out.add(In.substring(0,active));
    			}
    			int opPrec = PREC[OPERANDS.indexOf(In.substring(active,active+1))];
    			if(ops.size() == 0 || opPrec >= PREC[OPERANDS.indexOf(ops.get(ops.size()-1))]) { // If active has has higher prec
    				ops.add(In.substring(active,active+1));
    			} else {
    				while(!ops.isEmpty()) {
    					out.add(ops.remove(0));
    				}
    				ops.add(In.substring(active,active+1));
    			}
    			In = In.substring(active+1);
    			active = -1;
    		}
    	}
    	out.add(In.substring(0));
    	while(!ops.isEmpty()) {
			out.add(ops.remove(ops.size()-1));
	}
    	return out;
    }
    
    private String postShunt(ArrayList<String> In){
        System.out.println(In);
    	ArrayList<String> bag = new ArrayList<>();
    	while(!In.isEmpty()) {
    		if(OPERANDS.indexOf(In.get(0)) == -1) {
    			bag.add(In.remove(0));
    		} else {
    			double total;
    			double a = Double.parseDouble(bag.remove(bag.size()-1));
    			double b = Double.parseDouble(bag.remove(bag.size()-1));
    			if(In.get(0).equals("+")) {
    				total = add(a, b);
    				bag.add(Double.toString(total));
    				In.remove(0);
    			} else if(In.get(0).equals("-")) {
    				total = subtract(a, b);
    				bag.add(Double.toString(total));
    				In.remove(0);
    			} else if(In.get(0).equals("*")) {
    				total = multiply(a, b);
    				bag.add(Double.toString(total));
    				In.remove(0);
    			} else if(In.get(0).equals("/")) {
    				total = divide(a, b);
    				bag.add(Double.toString(total));
    				In.remove(0);
    			} else if(In.get(0).equals("^")) {
    				total = pow(a, b);
    				bag.add(Double.toString(total));
    				In.remove(0);
    			}
    		}
    	}
    	return bag.remove(0);
    }
    
     private void cleanUpWhites(){
        equation = equation.replaceAll("\\s+","");
    }
    
    private boolean isSolo(String eqIn){
        boolean found = false;
        for (int i = 0; i < eqIn.length(); i++){
            if (OPERANDS.indexOf(eqIn.substring(i,i+1)) != -1){
                found = true;
            }
        }
        return !found;
    }
    
    private double add(double a, double b){
        return b + a;
    }
    
    private double subtract(double a, double b){
        return b - a;
    }
    
    private double multiply(double a, double b){
        return b*a;
    }
    
    private double divide(double a, double b){
        return b/a;
    }
    
    private double pow(double a, double b){
        return Math.pow(b,a);
    }
    
}
