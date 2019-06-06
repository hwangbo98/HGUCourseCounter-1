package edu.handong.analysis.datamodel;

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
	
	public Course(CSVRecord record) {
		studentId = record.get(0);
		yearMonthGraduated = record.get(1);
		firstMajor = record.get(2);
		secondMajor = record.get(3);
		courseCode = record.get(4);
		courseName = record.get(5);
		courseCredit = record.get(6);
		yearTaken = Integer.parseInt(record.get(7));
		semesterCourseTaken = Integer.parseInt(record.get(8));
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
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return courseName;
	}
	
	
	
	
}