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
	
	public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		BufferedWriter bufWriter = null;
		try {
			File tmp = new File("logs/error.log");
			tmp.getParentFile().mkdirs();
			bufWriter = Files.newBufferedWriter(Paths.get(targetFileName));
			
			for(String data : lines) {
				bufWriter.write(data);
				bufWriter.write(",");
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(bufWriter!=null) {
					bufWriter.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		
		}
		
	}
}