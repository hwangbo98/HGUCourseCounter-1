package edu.handong.analysis;

import java.util.ArrayList;
import java.util.HashMap;
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
	
	private HashMap<String, Integer> countStudents = new HashMap<String, Integer>();		
		
	
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
		
		String dataPath = args[0]; // csv file to be analyzed
		String resultPath = args[1]; // the file path where the results are saved.
		ArrayList<String> lines = Utils.getLines(dataPath,true);
		
		csvParser reader =new csvParser();
		/*for(int i=0; i<reader.listOfAll().size(); i++) {
		System.out.println(reader.listOfAll());
		}
		 */ 
		ArrayList<String> lineToList = new ArrayList<String>();
		
		
		
		
		 
		 
		 
		
		
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
		ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents); 
		
		//Write a file (named like the value of resultPath) with linesTobeSaved.
		Utils.writeAFile(linesToBeSaved, resultPath);
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
	private ArrayList<String> countNumberOfCoursesTakenInEachSemester(Map<String, Student> sortedStudents) {
		ArrayList<String> countNumOfCourses = new ArrayList<String>();
		
		String countNum;
		int i;
		int num=0;
		int num2=0;
		countNumOfCourses.add("Student ID " + " TotalNumberOfSemestersRegistered " + " Semester " + " NumCoursesTakenInTheSemester ");
		countNumOfCourses.add("\n");
		for (String str : sortedStudents.keySet()) {
			int count=0;
			Student resultValue = sortedStudents.get(str);
			Map<String, Integer> sort = new TreeMap<String, Integer>(resultValue.getSemestersByYearAndSemester());
			
			for(String resultKey :sort.keySet()) {
				num= sort.get(resultKey);
				num2 = resultValue.getNumCourseInNthSementer(num);
				count++;
				
				countNumOfCourses.add(str+"        " + sort.size() +"                                " + num + "            "+num2);
				countNumOfCourses.add("\n");
			} 
		}
		// TODO: Implement this method
		
		return countNumOfCourses; // do not forget to return a proper variable.
	} 
	/*private HashMap<String,Integer> countOfAllStudent(){
		
		int count =0;
		String toYear="";
		String toSemester="";
		
		for(Course coursesTakens : saveCourse) {
			toYear = Integer.toString(coursesTakens.getYearTaken()); 
			toSemester = Integer.toString(coursesTakens.getSemesterCourseTaken());
			String toYearAndtoSemester = toYear + "-" + toSemester ;
			//if(!semeInstance.containsKey(toYearAndtoSemester)){
			count++;
			semeInstance.put(toYearAndtoSemester,count);
			//System.out.println(toYearAndtoSemester+count);
			}
			else ;
			
		}
		return countStudents;
	}  */
	
	
	//private ArrayList<String> rateOfCourse(String code){
		
		
	
	//return }
} 