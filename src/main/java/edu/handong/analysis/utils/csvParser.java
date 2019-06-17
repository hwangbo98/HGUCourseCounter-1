package edu.handong.analysis.utils;


import java.util.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;


public class csvParser {
	
	
    
	public static List<CSVRecord> listOfAll(String file, boolean removeHeader) {
		List<CSVRecord> records = null;
		
		try {
			Reader in = new FileReader(file);
			CSVParser save = new CSVParser(in, CSVFormat.EXCEL);
			records = save.getRecords();
			
			save.close();
		} catch(FileNotFoundException e) {
			System.out.println("The file path does not exist. Please check your CLI argument!");
			System.exit(0);
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		if(removeHeader) {
			records.remove(0);
			
		}
			/*for(CSVRecord record : records) {
				studentID = record.get(0);
				yearMonthGraduated = record.get(1);
				firstMajor = record.get(2);
				secondMajor = record.get(3);
				courseCode = record.get(4);
				courseName = record.get(5);
				courseCredit = record.get(6);
				yearTaken = record.get(7);
				semesterTaken = record.get(8); 
				recordList.add(studentID +"," + yearMonthGraduated +"," + firstMajor +"," + secondMajor +"," + courseCode +"," + courseName +"," + courseCredit +"," + yearTaken +"," + semesterTaken +",");
				recordList.add("\n");
			} */
			/*for(int i =0; i<recordList.size(); i++) {
				System.out.println(recordList);
			} */
			
			return records;
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
	

			