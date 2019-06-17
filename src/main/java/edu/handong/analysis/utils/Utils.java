package edu.handong.analysis.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
	public static ArrayList<String> getLines(String file, boolean removeHeader){//, boolean removeHeader){
		BufferedReader br = null;
		ArrayList<String> getLines = new ArrayList<String>();
		try {
			br = Files.newBufferedReader(Paths.get(file));
			String line = "";
			String line2="";
			while((line = br.readLine())!=null) {
				if(removeHeader) {
					line2 = line;
					removeHeader=false;
				}
				else
					getLines.add(line);
			}
				/*String[] array = line.split(",");
				for(int i=0; i<array.length; i++) {
					if(!(array[i]==null)|| !(array[i].length()==0)) */
			
		
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(br!=null){
					br.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		} 
		return getLines;
	
	}
	
	public static void writeAFile(ArrayList<String> allLines, String targetFileName, int menu) {
		
		try {
			File tmp = new File("logs/error.log");
			tmp.getParentFile().mkdirs();
			PrintWriter pw = new PrintWriter(new FileOutputStream(targetFileName, true));
			
			if(menu ==1) {
				pw.println("StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester");
				
			}
			else 	
				pw.println("Year,Semester,CourseCode,CourseName,TotalStudents,StudentsTaken,Rate");
			
			for(int i=0; i<allLines.size(); i++) {
				pw.println(allLines.get(i));
			}
			pw.close();
			
		}catch(FileNotFoundException e) {
			System.out.println("The file path does not exist. Please check your CLI argument");
			System.exit(0);
		}catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
			
	
	}
	

}