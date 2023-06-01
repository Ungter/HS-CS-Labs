/********************************************
 * Lab: 
 */
public class OneDarrayfrPrac {
    // The controlled vocabulary for a Vocab object
    private String[] theVocab = {/*contents not shown*/};

    // Write a method that Searches for a string in theVocab. returns true if the string is found, false otherwise.
    public boolean search(String s) {
        for (int i = 0; i < theVocab.length; i++) {
            if (theVocab[i].equals(s)) {
                return true;
            }
        }
        return false;
    }

    // Write a method that counts how many string in wordArray and not found in theVocab.
    public int count(String[] wordArray) {
        int count = 0;
        for (int i = 0; i < wordArray.length; i++) {
            if (!search(wordArray[i])) {
                count++;
            }
        }
        return count;
    }

    // Write a method that returns a new array containing the strings in wordArray that are not found in theVocab.
    public String[] notFound(String[] wordArray) {
        String[] notFound = new String[count(wordArray)];
        int count = 0;
        for (int i = 0; i < wordArray.length; i++) {
            if (!search(wordArray[i])) {
                notFound[count] = wordArray[i];
                count++;
            }
        }
        return notFound;
    }
}
