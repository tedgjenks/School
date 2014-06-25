package edu.barrett.joshua.calculator;



import java.util.*;




import edu.jenks.calculator.dist.*;






public class BasicCalculator implements Calculates  {

	private List <String> n1;




	@Override

	public double performCalculation(String arg0) {

		n1 = MathematicalExpressionParser.tokenizeExpression(arg0);

		if (n1.size()==1) {

			return Double.parseDouble(n1.get(0));

		}

		else if (n1.size()==3) {

			List<String> n4 = Helper(n1);

			return Double.parseDouble(n4.get(0));

		}

		else {

			int a = n1.indexOf(")");

			int b = -1;

			for (int index=a; index>=0; index--) {

				if (n1.get(index).equals("(")) {

					b=(index);

					

				}

			}

			if (a==-1 && b==-1) {

				List<String> n4 = Helper(n1);

				return Double.parseDouble(n4.get(0)); 

			}

			else if (a!=-1 && b!=-1) {

				List <String> n4 = new ArrayList<String>(n1.subList(0, b));

				List <String> n5 = new ArrayList<String>(n1.subList(b, a+1));

				List <String> n6 = new ArrayList<String>(n1.subList(a+1, n1.size()));

				n1.clear();

				List <String> n7 = Helper(n5);

				n1.addAll(n4);

				n1.addAll(n7);

				n1.addAll(n6);

				//printn1();

				String s1 = "";

				for (int index=0; index<n1.size(); index++) {

					s1+=n1.get(index);

					}

				return performCalculation(s1);

				}

			return 0;

		}

		

		

	

		

		}

	

	public List<String> Helper(List<String> n2) {

		if (n2.get(0).equals("(")) {

			n2.remove(0);

		}

		if (n2.get(n2.size()-1).equals(")")) {

			n2.remove(n2.size()-1);

		}

			

		for (int index =1; index<n2.size()-1; index++) {

			if (n2.get(index).equals ("*")) {

				double n3 = Double.parseDouble(n2.get(index-1))*Double.parseDouble(n2.get(index+1));

				n2.set(index-1, Double.toString(n3));

				n2.remove(index+1);

				n2.remove(index);

				

			}

			index=0;

			

		}

		

		for (int index =1; index<n2.size()-1; index++) {

			if (n2.get(index).equals ("/")) {

				double n3 = Double.parseDouble(n2.get(index-1))/Double.parseDouble(n2.get(index+1));

				n2.set(index-1, Double.toString(n3));

				n2.remove(index+1);

				n2.remove(index);

				

			}

			index=0;

			

		}

		for (int index =1; index<n2.size()-1; index++) {

			if (n2.get(index).equals ("+")) {

				double n3 = Double.parseDouble(n2.get(index-1))+Double.parseDouble(n2.get(index+1));

				n2.set(index-1, Double.toString(n3));

				n2.remove(index+1);

				n2.remove(index);

				

			}

			index=0;

			

		}

		for (int index =1; index<n2.size()-1; index++) {

			if (n2.get(index).equals ("-")) {

				double n3 = Double.parseDouble(n2.get(index-1))-Double.parseDouble(n2.get(index+1));

				n2.set(index-1, Double.toString(n3));

				n2.remove(index+1);

				n2.remove(index);

				

			}

			index=0;

			

		}

		return n2;

	}

	public void printn1(){

		for(int index=0;index<n1.size();index++){

			System.out.print(n1.get(index)+"\t");

		}

		System.out.println();

	}




	}

	

	














