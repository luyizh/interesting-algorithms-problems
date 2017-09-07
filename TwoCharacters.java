/*String s always consists of two distinct alternating characters. 
 * For example, if string s's two distinct characters are x and y, 
 * then t could be xyxyx or yxyxy but not xxyy or xyyx.
 */

/*You can convert some string s to string t by deleting characters from s. 
 * When you delete a character from s, you must delete all occurrences of it in s. 
 * For example, if s= abaacdabd and you delete the character a, then the string becomes bcdbd.
 */

/* Given s, convert it to the longest possible string t. Then print the length of string t on a new line; 
 * if no string t can be formed from s, print 0 instead.
 */

/*Input Format

The first line contains a single integer denoting the length of s. 
The second line contains string s.

Constraints

1 <= |s| <= 1000
s only contains lowercase English alphabetic letters (i.e., a to z).
Output Format

Print a single integer denoting the maximum length of t for the given s; 
if it is not possible to form string t, print 0 instead.*/
		
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class TwoCharacters {
        
    // given input string s, create string containing all unique letters in s
    public static String uniqueLetters(String s) {
        String uniqueLettersString = "";
        Set<String> uniqueLetters = new HashSet<String>();
        for (int i = 0; i <= s.length()-1; i++) {
            uniqueLetters.add(s.substring(i, i+1));
        }
        for (String letter : uniqueLetters) {
            uniqueLettersString += letter;
        }
        return uniqueLettersString;
    }
    
    // given input string s of unique letters, create array of 2-element substrings (order doesn't matter)
    public static List<String> generatePairs(String s) {
        
        List<String> allPairs = new ArrayList<String>();
        for (int i = 0; i <= s.length()-2; i++) {
            String letter1 = s.substring(i, i+1);
            for (int j = i+1; j <= s.length()-1; j++) {
                String letter2 = s.substring(j, j+1);
                String pair = letter1+letter2;
                allPairs.add(pair);
            }
        }
            
        return allPairs;
         
    }
    
    // given input string s and 2-letter string, return s with only those letters
    public static String filterString(String s, String options) {
        String filteredString = "";

        char option1 = options.charAt(0);
        char option2 = options.charAt(1);
        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);
            if (currChar == option1 || currChar == option2) {
                filteredString += currChar;
            }
        }
        return filteredString;
        
    }
    
    // given input string s, see if s is made up of alternating letters
    public static boolean alternatingLetters(String s) {

        if (s.length() == 2) {
            return (s.charAt(0) != s.charAt(1));
        }
        char char1 = s.charAt(0);
        char char2 = s.charAt(1);

        // all odd indices must be equal to char2
        // all even indices must be equal to char1
        for (int i = 2; i <= s.length()-1; i++) {
            char currChar = s.charAt(i);
            if (i % 2 == 1) {
                if (currChar != char2) {
                    return false;
                }
            } else {
                if (currChar != char1) {
                    return false;
                }
            }
        }
        return true;
        
    }
    
    public static int getAns(String s) {
        // get all unique letters in s
        String uniqueLetters = uniqueLetters(s);
        if (uniqueLetters.length() == 1) {
            return 0;
        }
        // get all pairs of unique letters
        List<String> allPairs = generatePairs(uniqueLetters);
        // for each pair of unique letters, write s with only those letters
        // and check if the result has alternating letters
          // if it does, keep track of the max length
        int maxLength = 0;
        for (int i = 0; i < allPairs.size(); i++) {
            String currentPair = allPairs.get(i);
            String filteredString = filterString(s, currentPair);
            if (alternatingLetters(filteredString)) {
                if (filteredString.length() > maxLength) {
                    maxLength = filteredString.length();
                }
            }
        }
        return maxLength;
        
        
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        String s = in.next();
        System.out.println(getAns(s));

    }
}
