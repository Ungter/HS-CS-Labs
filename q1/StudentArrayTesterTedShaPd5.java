/*************************************
 * Name: Qianhe Sha; Pd. 5
 * Lab: 1-D Array of References
 * Purpose of the Program: Review how to declare and initalize an array of references.
 *                         Review the sequenial search algorithm.
 * 
 ************************************/

import java.util.Scanner;
public class StudentArrayTesterTedShaPd5 {
    public static void main(String[] args) {
        StudentBody students = new StudentBody();
        students.init();

        Scanner keyboard = new Scanner (System.in);
        System.out.println ("Enter the id of the student you are looking for: "); 
        int studentID = keyboard.nextInt();

        Student	target = students.search(studentID); 

        if	(target != null)
            System.out.println(target); 
        else
            System.out.println ("Student not found … ");

        System.out.println("Student Body Information:\n" + students);

        Student[] honorsStudents = students.aboveAvgStudents();

        /*
            System.out.println("The honors students are "); 
            for (Student s: honorsStudents) {
                System.out.println (s);              
            }
        */

    }
} // StudentArrayTesterTedShaPd5

class StudentBody {
    private Student[] students;

    public StudentBody() {
        students = new Student[3];
    }

    // Prompts the user to enter the Student's data into the array using a scanner object.
    public void init() {
        for (int i = 0; i < students.length; i++) {
            System.out.println("Enter student " + (i + 1) + "'s data:");
            System.out.print("Enter student's id: ");
            int id = new Scanner(System.in).nextInt();
            System.out.print("Enter student's name: ");
            String name = new Scanner(System.in).next();
            System.out.print("Enter student's gpa: ");
            double gpa = new Scanner(System.in).nextDouble();
            students[i] = new Student(name, id, gpa);
        }
    }

    // search(int id): find if a Student object with "id" 
    //                 exists in the array and return the
    //                 index of the Student object, if the
    //                 object doesnt exist, return null.
    public Student search(int id) {
        for (int i = 0; i < students.length; i++) {
            if (students[i].getStudentID() == id) {
                return students[i];
            }
        }
        return null;
    }
    
    // calcAvgGpa: returns the average GPA of all the Student objects in StudentBody.
    public double calcAvgGpa() {
        double sum = 0;
        for (int i = 0; i < students.length; i++) {
            sum += students[i].getGPA();
        }
        return sum / students.length;
    }

    // aboveAvgStudents: returns an array of Student objects
    //                   that have a GPA greater than the
    //                   average GPA of all the Student objects 
    //                   in StudentBody.
    public Student[] aboveAvgStudents() {
        Student[] aboveAvgStudents = new Student[students.length];
        int count = 0;
        for (int i = 0; i < students.length; i++) {
            if (students[i].getGPA() > calcAvgGpa()) {
                aboveAvgStudents[count] = students[i];
                count++;
            }
        }
        return aboveAvgStudents;
    }

    // toString: Prints the current StudentBody object.
    public String toString() {
        String str = "";
        for (int i = 0; i < students.length; i++) {
            str += students[i].toString() + "\n";
        }
        return str;
    }
} // StudentBody

class Student {
    private String name;
    private int studentID;
    private double gpa;

     /* precondition: none
      * postcondition: create a Student object with default instance variable values */
    public Student() {
        name = "unknown";
        studentID = 0;
        gpa = 0.0;
    }

    /* precondition: method must be called with String representing name, 
     * int representing student ID, and double from 0.0-5.0 representing GPA
     * postcondition: create a Student object with specified instance variable values */
    public Student(String name, int studentID, double gpa) {
        this.name = name;
        this.studentID = studentID;
        this.gpa = gpa;
    }

    /* precondition: must be called with valid Student object
     * postcondition: returns the name, student ID, and GPA of a student in the form of a string */
    public String toString() {
        return "Name: " + name + "\nStudent ID: " + studentID + "\nGPA: " + gpa;
    }

    /* precondition: must be called with valid Student object, must be called with a double argument from 0.0-5.0
     * postcondition: reassign the value of the argument to a Student’s GPA*/ 
    public void setGPA(double gpa) {
        this.gpa = gpa;
    }

    /* precondition: must be called with valid Student object
     * postcondition: returns the name of a student */
    public String getName() {
        return name;
    }

    /* precondition: must be called with the valid Student object
     * postcondition: returns the student ID of a student */
    public int getStudentID() {
        return studentID;
    }

    /* precondition: must be called with the valid Student object
     * postcondition: returns the GPA of a student */
    public double getGPA() {
        return gpa;
    }
} // Student
