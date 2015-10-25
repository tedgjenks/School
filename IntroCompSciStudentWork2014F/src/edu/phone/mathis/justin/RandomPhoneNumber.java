package edu.phone.mathis.justin;

import edu.jenks.dist.phone.AbstractRandomPhoneNumber;

public class RandomPhoneNumber extends AbstractRandomPhoneNumber {

	@Override
	public String generateRandomPhoneNumber() {
		int num1 = (int)(Math.abs(Math.random()*10-2));
		int num2 = (int)(Math.abs(Math.random()*10-2));
		int num3 = (int)(Math.abs(Math.random()*10-2));
		int num456 = (int)(Math.abs(Math.random()*1000-258));
		int num78910 = (int)(Math.abs(Math.random()*10000));
		String num321 = ""+num1+num2+num3;
		String num654 = ""+num456;
		String num01987 = ""+num78910;
		if (num321.length()==2){
			num321 = "0"+num321;
		}
		if (num321.length()==1){
			num321 = "0"+"0"+num321;
		}
		if (num321.length()==0){
			num321 = "000"+num321;
		}
		if (num654.length()==2){
			num654 = "0"+num456;	
		}
		if (num654.length()==1){
			num654 = "0"+"0"+num456;
		}
		if (num654.length()==0){
			num654 = "000"+num456;
		}
		if (num01987.length()==3){
			num01987 = "0"+num78910;
		}
		if (num01987.length()==2){
			num01987 = "0"+"0"+num78910;
		}
		if (num01987.length()==1){
			num01987 = "0"+"0"+"0"+num78910;
		}
		if (num01987.length()==0){
			num01987 = "0000"+num78910;
		}
		String swag = ""+num321+"-"+num654+"-"+num01987;
		System.out.print(swag);
		return swag;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num1 = (int)(Math.abs(Math.random()*10-2));
		int num2 = (int)(Math.abs(Math.random()*10-2));
		int num3 = (int)(Math.abs(Math.random()*10-2));
		int num456 = (int)(Math.abs(Math.random()*1000-258));
		int num78910 = (int)(Math.abs(Math.random()*10000));
		String num321 = ""+num1+num2+num3;
		String num654 = ""+num456;
		String num01987 = ""+num78910;
		if (num321.length()==2){
			num321 = "0"+num321;
		}
		if (num321.length()==1){
			num321 = "0"+"0"+num321;
		}
		if (num321.length()==0){
			num321 = "000"+num321;
		}
		if (num654.length()==2){
			num654 = "0"+num456;	
		}
		if (num654.length()==1){
			num654 = "0"+"0"+num456;
		}
		if (num654.length()==0){
			num654 = "000"+num456;
		}
		if (num01987.length()==3){
			num01987 = "0"+num78910;
		}
		if (num01987.length()==2){
			num01987 = "0"+"0"+num78910;
		}
		if (num01987.length()==1){
			num01987 = "0"+"0"+"0"+num78910;
		}
		if (num01987.length()==0){
			num01987 = "0000"+num78910;
		}
		String swag = ""+num321+"-"+num654+"-"+num01987;
		System.out.print(swag);
	
	}

}
