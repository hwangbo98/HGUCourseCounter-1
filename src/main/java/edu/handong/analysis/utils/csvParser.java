package edu.handong.analysis.utils;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;
import java.io.InputStreamReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import edu.handong.analysis.datamodel.Student;
public class csvParser {
	private String studentID;
    private String yearMonthGraduated;
    private String firstMajor ;
    private String secondMajor;
    private String courseCode ;
    private String courseName;
    private String courseCredit ;
    private String yearTaken ;
    private String semesterTaken ; 
	
    
	public ArrayList<String> listOfAll() {
		ArrayList<String> recordList = new ArrayList<String>();
		try {
			Reader in = new FileReader("/Users/yeon/Downloads/hw5data.csv");
			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
			
			for(CSVRecord record : records) {
				studentID = record.get(0);
				yearMonthGraduated = record.get(1);
				firstMajor = record.get(2);
				secondMajor = record.get(3);
				courseCode = record.get(4);
				courseName = record.get(5);
				courseCredit = record.get(6);
				yearTaken = record.get(7);
				semesterTaken = record.get(8); 
				recordList.add(studentID + yearMonthGraduated + firstMajor + secondMajor + courseCode + courseName + courseCredit + yearTaken + semesterTaken);
				//recordList.add("\n");
			}
			/*for(int i =0; i<recordList.size(); i++) {
				System.out.println(recordList);
			} */
			
			
			
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return recordList;
	}
	public String getStudentID() {
		return studentID;
	}
	public String getSecondMajor() {
		return secondMajor;
	}
}
		
		
			
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/* String documentPath = System.getProperty("user.home") +"/Downloads";
			
			System.out.println(documentPath);
			Path documentsDirectory = Paths.get(documentPath);
			
			String filename = "/hw5data.csv";
			String csvFile = documentPath + filename;
	
			
			Path csvPath = documentsDirectory.resolve(filename);
			
			//System.out.println(csvPath); 
			//Reader reader = Files.newBufferedReader(Paths.get(args[0]));
			try
			{
				Reader reader = Files.newBufferedReader(Paths.get(csvFile));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
				
				for (CSVRecord csvRecord : csvParser) {
	                // Accessing Values by Column Index
	                String StudentID = csvRecord.get(0);
	                String YearMonthGraduated = csvRecord.get(1);
	                String FirstMajor = csvRecord.get(2);
	                String SecondMajor = csvRecord.get(3);
	                String CourseCode = csvRecord.get(4);
	                String CourseName = csvRecord.get(5);
	                String CourseCredit = csvRecord.get(6);
	                String YearTaken = csvRecord.get(7);
	                String SemesterTaken = csvRecord.get(8);
	                	                
	                csvReader.add(StudentID + YearMonthGraduated  + FirstMajor + SecondMajor +
	                		 CourseCode  + CourseName  + CourseCredit + YearTaken  + SemesterTaken);
	                //System.out.println("Record No - " + csvRecord.getRecordNumber());
	                //System.out.println("Name : " + StudentID + "Email : " + YearMonthGraduated + "Phone : " + FirstMajor + "Country : " + SecondMajor +
	                //		"Country : " + CourseCode + "Country : " + CourseName +"Country : " + CourseCredit + "Country : " + YearTaken + "Country : " + SemesterTaken);
	                
				}
				for(String copyCsv : csvReader) {
					System.out.println(copyCsv);
					csvDown.add(copyCsv);
				} 
				
				
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return csvDown;
			
		}
	public String getStudentID(){
		return StudentID;
	}
	
	
}
	
	public void write(String outputFilePath);
	}  */
	

			