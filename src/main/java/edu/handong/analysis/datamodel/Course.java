package edu.handong.analysis.datamodel;

import java.util.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Course {
	private String studentId;
	private String yearMonthGraduated;
	private String firstMajor;
	private String secondMajor;
	private String courseCode;
	private String courseName;
	private String courseCredit;
	private int yearTaken;
	private int semesterCourseTaken;
	private ArrayList<CSVRecord> allCourse;
	private HashMap<String, Integer> studentsTake;
	
	public Course(String line) {
		
		studentId = line.split(",")[0].trim();
		yearMonthGraduated = line.split(",")[1].trim();
		firstMajor = line.split(",")[2].trim();
		secondMajor = line.split(",")[3].trim();
		courseCode = line.split(",")[4].trim();
		courseName = line.split(",")[5].trim();
		courseCredit = line.split(",")[6].trim();
		yearTaken = Integer.parseInt(line.split(",")[7].trim());		
		semesterCourseTaken = Integer.parseInt(line.split(",")[8].trim());
	}
	
	public Course(String courseName, String courseCode) {
		allCourse = new ArrayList<CSVRecord>();
		this.courseName = courseName;
		this.courseCode = courseCode;
	}
	
	public void addRecord(CSVRecord lines) {
		allCourse.add(lines);
	}
	
	public HashMap<String, Integer> getStudentsTake(int startYear, int endYear){
		HashMap<String, Integer> studentsTake = new HashMap<String, Integer>();
		
		for(int i=0; i<allCourse.size(); i++) {
			int saveYear = Integer.parseInt(allCourse.get(i).get(7).trim());
			
			if(saveYear >=startYear && saveYear<= endYear) {
				String yearAndSemester = saveYear + "-" + allCourse.get(i).get(8).trim();
				
				if(!studentsTake.containsKey(yearAndSemester)) {
					studentsTake.put(yearAndSemester, 1);
				}
				else studentsTake.put(yearAndSemester, studentsTake.get(yearAndSemester)+1);
				}
			}
		
			return studentsTake;
		}
	

	public String getStudentId() {
		return studentId;
	}
	public String getCourseName() {
		return courseName;
	}
	public int getYearTaken() {
		return yearTaken;
	}
	public int getSemesterCourseTaken() {
		return semesterCourseTaken;
	}
	public String getCourseCode() {
		return courseCode;
	}
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return courseName;
	}
	
	
	
	
}