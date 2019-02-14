package edu.lewis.ap.newbold.griffin;
import edu.jenks.dist.lewis.ap.*;


public class HighSchoolClass extends AbstractHighSchoolClass{

	public HighSchoolClass(Student[] students){
		super(students);
	}
	public Student[] getValedictorian(){
		double highestGPA = 0;
		int arrayPosition = 0;
		for(int i = 0; i < students.length; i++){
			if(students[i].getGpa() > highestGPA){
				highestGPA = students[i].getGpa();
			}
		}
		int numOfValedictorians = checkGPA(highestGPA);
		Student[] valedictorian = new Student[numOfValedictorians];
		for(int k = 0; k < students.length; k++){
			double GPA = students[k].getGpa();
			if(GPA == highestGPA){
				valedictorian[arrayPosition] = students[k];
				arrayPosition++; 
			}
		}
		return valedictorian;	
	}
	public double getHonorsPercent(){
		double honorsStudents = 0;
		for(int g = 0; g < students.length; g++){
			boolean honors = students[g].isHonors();
			if(honors){
				honorsStudents++;
			}
		}
		return ((honorsStudents/students.length)*100);	
	}
	public double graduateWithHonorsPercent(){
		double studentsWithHonors = 0;
		for(int i = 0; i < students.length; i++){
			double gpa = students[i].getGpa();
			if(gpa >= HONORS_GPA){
				studentsWithHonors++;
			}
		}
		double percent = ((studentsWithHonors/students.length)*100);
		return percent;
	}	
	public double graduateWithHighestHonorsPercent(){
		double studentsWithHighestHonors = 0;
		for(int i = 0; i < students.length; i++){
			double gpa = students[i].getGpa();
			if(gpa >= HIGHEST_HONORS_GPA){
				studentsWithHighestHonors++;
			}
		}
		double percent = ((studentsWithHighestHonors/students.length)*100);
		return percent;
	}
	public Student[] getHonorsStudents(){
		int arrayPosition = 0;
		int numOfHonors = 0;
		for(int a = 0; a < students.length; a++){
			boolean honors = students[a].isHonors();
			if(honors){
				numOfHonors++;
			}
		}
		Student[] honors = new Student[numOfHonors];
		for(int y = 0; y < students.length; y++){
			boolean hon = students[y].isHonors();
			if(hon){
				honors[arrayPosition] = students[y];
				arrayPosition++; 
			}
		}
		return honors;	
	}
	private int checkGPA(double gpa){
		int num = 0;
		for(int j = 0; j < students.length; j++){
			double GPA = students[j].getGpa();
			if(GPA == gpa){
				num++;
			}
		}
		return num;
	}
}