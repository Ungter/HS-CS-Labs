import java.util.ArrayList;

public class stu {
    private String name;
    private double gpa;

    public double getGpa() {
        return gpa;
    }

    public String getName() {
        return name;
    }



public ArrayList<String> findHonorRollStudents(Student[] students, double goodGPA) {
    // Store names of students in stu whose pga is greater than or equal to goodGPA in an ArrayList and return it.
    ArrayList<String> honorRollStudents = new ArrayList<String>();
    for (Student student : students) {
        if (getGpa() >= goodGPA) {
            honorRollStudents.add(student.getName());
        }
    }
    return honorRollStudents;
}
    
    // Write a method that accepts an array of Strings and removes consecutive duplicate values from an ArrayList.
    public static ArrayList<String> removeDuplicates(String[] strings) {
        ArrayList<String> newArray = new ArrayList<String>();
        for (String string : strings) {
            if (!newArray.contains(string)) {
                newArray.add(string);
            }
        }
        return newArray;
    }

    public static void main(String[] args) {
        String[] tes = {"A", "A", "A", "B", "C", "C"};
        System.out.println(removeDuplicates(tes));
    }
}




