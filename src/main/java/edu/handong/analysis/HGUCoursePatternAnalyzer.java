package edu.handong.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.lang.Iterable;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysis.utils.NotEnoughArgumentException;
import edu.handong.analysis.utils.Utils;
import edu.handong.analysis.utils.csvParser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;


public class HGUCoursePatternAnalyzer {

	private HashMap<String,Student> students = new HashMap<String,Student>();
	private HashMap<String, Integer> semeAndYear = new HashMap<String, Integer>();
	private ArrayList<Course> saveCourse ;
	private String studentId;
	private String yearMonthGraduated;
	private String firstMajor;
	private String secondMajor;
	private String courseCode;
	private String courseName;
	private String courseCredit;
	private int yearTaken;
	private int semesterCourseTaken; 
	
			
	private String[] values;
	private String[] sortOfOption = {"i", "o", "a", "c", "s", "e", "h"};
	private boolean hasHelp;
	private Course course;
	/**
	 * This method runs our analysis logic to save the number courses taken by each student per semester in a result file.
	 * Run method must not be changed!!
	 * @param args
	 */
	public void run(String[] args) {
		
		try {
			// when there are not enough arguments from CLI, it throws the NotEnoughArgmentException which must be defined by you.
			if(args.length<2)
				throw new NotEnoughArgumentException();
		} catch (NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		Options options = createOptions();
		HelpFormatter formatter = new HelpFormatter();
		
		if(!parseOptions(options, args)){
			System.exit(0);
		}
		else if(!includeOptions()) {
			formatter.printHelp("HGUCourseCounter", "HGU Course Analyzer", options, "", true);
			System.exit(0);
		}
		else {
			if(hasHelp){
				formatter.printHelp("HGUCourseCounter", "HGU Course Analyzer", options, "", true);
			}
			String dataPath = values[0];
			String resultPath = values[1];
			int startYear = Integer.parseInt(values[4]);
			int endYear = Integer.parseInt(values[5]);
			
			if(values[2].contentEquals("1")) {
				ArrayList<String> lines = Utils.getLines(dataPath,true);
				students = loadStudentCourseRecords(lines);
				/*for(String key : students.keySet()) {
					Student value = students.get(key);
					System.out.println("key:" +key + "value: " + value);
				}  */
				// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
				Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
				
				// Generate result lines to be saved.
				
				
				
				//Student stu = new Student(students.keySet());
				//Map<String, Integer> just = new TreeMap<String,Integer>());
				ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents,startYear,endYear); 
				
				//Write a file (named like the value of resultPath) with linesTobeSaved.
				Utils.writeAFile(linesToBeSaved, resultPath, Integer.parseInt(values[2]));
			}
			else {
				List<CSVRecord> allLines = csvParser.listOfAll(dataPath,true);
				course = subjectRecords(allLines, values[3]);
				Utils.writeAFile(resultData(allLines,startYear,endYear),resultPath, Integer.parseInt(values[2]));
			}
			
		}
		
		
		
		
		
		
		 
		 
		 
		
		
		
	}
	
	/**
	 * This method create HashMap<String,Student> from the data csv file. Key is a student id and the corresponding object is an instance of Student.
	 * The Student instance have all the Course instances taken by the student.
	 * @param lines
	 * @return
	 */
	private HashMap<String,Student> loadStudentCourseRecords(ArrayList<String> lines) {
		//HashMap<String,Student> loadStudentCourseRecords = new HashMap<String,Student>();
		students = new HashMap<String,Student>();
	
		for(String line: lines) {
			//System.out.println(line);
			Course courses = new Course(line);
			//System.out.println(courses);
			String identity = courses.getStudentId();
			if(students.containsKey(identity)){
				students.get(identity).addCourse(courses);
			} else {
				Student student = new Student(identity);
				student.addCourse(courses);
				students.put(identity,student);
			}
			
			
			
		}
		
		
		return students; // do not forget to return a proper variable.
	}


	/**
	 * This method generate the number of courses taken by a student in each semester. The result file look like this:
	 * StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester
	 * 0001,14,1,9
     * 0001,14,2,8
	 * ....
	 * 
	 * 0001,14,1,9 => this means, 0001 student registered 14 semeters in total. In the first semeter (1), the student took 9 courses.
	 * 
	 * 
	 * @param sortedStudents
	 * @return
	 */
	private ArrayList<String> countNumberOfCoursesTakenInEachSemester(Map<String, Student> sortedStudents, int startYear, int endYear) {
		ArrayList<String> countNumOfCourses = new ArrayList<String>();
		Collection<Student> collection = sortedStudents.values();
		Iterator<Student> iter = collection.iterator();
		int num =0;
		while(iter.hasNext()) {
			Student student = iter.next();
			num = student.getSemestersByYearAndSemester().size();
			
			for(int i=1; i<=num; i++) {
				if(student.getNumCourseInNthSemester(i, startYear, endYear)!=0) {
					countNumOfCourses.add(student.getStudentId() + "," + num + "," + i +"," + student.getNumCourseInNthSemester(num, startYear, endYear));
				}
			}
		}
		
		countNumOfCourses.add("Student ID " + " TotalNumberOfSemestersRegistered " + " Semester " + " NumCoursesTakenInTheSemester ");
		countNumOfCourses.add("\n");
		
		// TODO: Implement this method
		
		return countNumOfCourses; // do not forget to return a proper variable.
	} 
	
	private Options createOptions() {
		Options options = new Options();
		
		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set an input file path")
				.hasArg()
				.argName("Input path")
				.required()
				.build());
		options.addOption(Option.builder("o").longOpt(("output"))
				.desc("Set an output file path")
				.hasArg()
				.argName("Output path")
				.required()
				.build());
		options.addOption(Option.builder("a").longOpt("analysis")
				.desc("1: Count courses per semester, "
						+ "2: Count per course name and year")
				.hasArg()
				.argName("Analysis option")
				.required()
				.build());
		
		options.addOption(Option.builder("c").longOpt("coursecode")
				.desc("Course code for '-a 2' option")
				.hasArg()
				.argName("course code")
				.required()
				.build());
		options.addOption(Option.builder("s").longOpt("startyear")
				.desc("Set the start year for analysis e.g., -s 2002")
				.hasArg()
				.argName("Start year for analysis")
				.required()
				.build());
		options.addOption(Option.builder("e").longOpt("endyear")
				.desc("Set the end year for analysis e.g., -e 2005")
				.hasArg()
				.argName("End year for analysis")
				.required()
				.build());
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Show a Help page")
				.hasArg()
				.argName("Help")
				.required()
				.build());
		return options;
	}
	
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		
		try {
			CommandLine cmd = parser.parse(options, args);
			values = new String[sortOfOption.length];
			
			for(int i =0; i<values.length; i++) {
				values[i] = cmd.getOptionValue(sortOfOption[i]);
				
				hasHelp = cmd.hasOption("h");
			} 
		}catch(Exception e) {
				formatter.printHelp("HGUCourseCounter", "HGU Course Analyzer", options, "", true);
				return false;
			}
			return true;
		}
	
	private boolean includeOptions() {
		for(int i=0; i<values.length -1; i++ ) {
			if(i==3) {
				if(values[i-1].equals("2") && values[i]==null) {
					return false;
				}
				else if(values[i] == null) {
					return false;
				}
			}
		}
		return true;
	}
	
	private Course subjectRecords(List<CSVRecord> allLines, String codeOfSubject) {
		
		String subjectName =null;
		
		for(int i =0; i<allLines.size(); i++) {
			CSVRecord record = allLines.get(i);
			
			if(record.get(4).trim().equals(codeOfSubject)) {
				subjectName = record.get(4).trim();
				break;
			}
		}
		Course course = new Course(subjectName, codeOfSubject);
		
		for(int j =0; j<allLines.size(); j++) {
			CSVRecord record = allLines.get(j);
			
			if(record.get(5).trim().contentEquals(subjectName)) {
				course.addRecord(record);
			}
			
		}
		return course;
	}
	
	private int totalStudent(List<CSVRecord> allLines, int year, int semester ) {
		ArrayList<String> studentName = new ArrayList<String>();
		int count =0;
		
		for(int i=0; i<allLines.size(); i++) {
			CSVRecord record = allLines.get(i);
			
			if(!studentName.contains(record.get(0))){
				if(Integer.parseInt(record.get(7).trim()) ==year && Integer.parseInt(record.get(8).trim()) == semester){
					count++;
					
					studentName.add(record.get(0));
				}
			}
		}
		return count;
	}
	
	private ArrayList<String> resultData(List<CSVRecord> allLines, int startYear, int endYear){
		Map<String, Integer> sortedStudentsTaken = new TreeMap<String,Integer>(course.getStudentsTake(startYear, endYear));
		/*Collection<Integer> collection = sortedStudentsTaken.values();
		Set<String> set = sortedStudentsTaken.keySet();
		Iterator<Integer> iterCol = collection.iterator();
		Iterator<String> iterSet = set.iterator(); */
		ArrayList<String> finalData = new ArrayList<String>(); 
		
		for(Integer value : sortedStudentsTaken.values()) {
			for(String key : sortedStudentsTaken.keySet()) {
				int year = Integer.parseInt(key.substring(0,4));
				int semester = Integer.parseInt(key.substring(5));
				int total = totalStudent(allLines,year,semester);
				
				finalData.add(year + "," + semester + "," + course.getCourseCode() + "," + total +"," + value + "," + String.format("%.2f", (float) value/total) + "%" );
			}
		}
		return finalData;
	}
	
} 