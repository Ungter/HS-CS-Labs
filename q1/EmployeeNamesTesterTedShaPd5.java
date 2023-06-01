/********************************************
 * Name: Qianhe Sha; Pd. 5
 * Lab: 1-D Array of References
 * Purpose of the Program: review an array of names, 
 *                         and return the people that
 *                         are eligible for hire.
 *********************************************/
public class EmployeeNamesTesterTedShaPd5 {
    public static void main(String[] args) {
        String[] names = {"Confold", "Tamer", "Chessy", "Jade", "Neon", "Cypher", "Nova"};
        String[] formattedNames = new String[names.length];

        formattedNames = EmployeeNames.convertName(names);
        
        for (int i = 0; i < formattedNames.length; i++) {
            System.out.println(formattedNames[i]);
        }

    } // main
} // EmployeeNamesTesterTedShaPd5

class EmployeeNames {

    // Precondition: names is an array of strings
    // Postcondition: return an array of strings that returns the compatible names
    public static String[] convertName(String[] names) {
        String[] result = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            String firstLetter = name.substring(name.length() - 1) + ". ";
            String secondLetter = name.substring(name.length() - 2, name.length() - 1) + ".";
            result[i] = firstLetter + secondLetter  + " " + name;
        }
        return result;
    }
} // EmployeeNames
