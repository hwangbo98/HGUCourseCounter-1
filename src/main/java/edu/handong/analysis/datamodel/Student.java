package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.utils.Utils;

public class Student {
	private String studentId;
	private ArrayList<Course> coursesTaken;
	private HashMap<String,Integer> semestersByYearAndSemester=new HashMap<String,Integer>();
	private HashMap<String,String> semestersAndSubject= new HashMap<String, String>();
	private HashMap<String,Integer> semeInstance = new HashMap<String,Integer>();
	
	
		public Student(String studentId) {
			this.studentId = studentId;
			this.coursesTaken = new ArrayList<Course>();
			this.semestersByYearAndSemester= new HashMap<String,Integer>();
		}
		
		public void addCourse(Course newRecord){
				coursesTaken.add(newRecord);
		}
			
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return coursesTaken.toString();
		}  
		
		public HashMap<String,Integer> getSemestersByYearAndSemester(){
			
			int count =0;
			String toYear="";
			String toSemester="";
			
			for(Course coursesTakens : coursesTaken) {
				toYear = Integer.toString(coursesTakens.getYearTaken()); 
				toSemester = Integer.toString(coursesTakens.getSemesterCourseTaken());
				String toYearAndtoSemester = toYear + "-" + toSemester ;
				if(!semeInstance.containsKey(toYearAndtoSemester)){
				count++;
				semeInstance.put(toYearAndtoSemester,count);
				//System.out.println(toYearAndtoSemester+count);
				}
				else ;
				
			}
			return semeInstance;
		} 
		
		public int getNumCourseInNthSementer(int semester) {
			int count =0;
			String toYear1 ="";
			String toSemester1 ="";
			for(Course courTk : coursesTaken) {
				toYear1 = Integer.toString(courTk.getYearTaken()); 
				toSemester1 = Integer.toString(courTk.getSemesterCourseTaken());
				String toYearAndtoSemester1 = toYear1 + "-" + toSemester1 ;
				if(semeInstance.get(toYearAndtoSemester1)==semester) {
					count++;
					
				}
				else ;
				
					//semesterByYearAndSemester
			}
			return count; 
		} 
}