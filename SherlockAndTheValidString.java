/*Sherlock considers a string, s, to be valid if either of the following conditions are satisfied:

All characters in  have the same exact frequency (i.e., occur the same number of times). 
For example, "aabbcc" is valid, but "baacdd" is not valid.
Deleting exactly 1 character from s will result in all its characters having the same frequency. 
For example, "aabbccc" and "aabbc" are valid because 
all their letters will have the same frequency if we remove 1 occurrence of c, 
but "abcccc" is not valid because we'd need to remove 3 characters.
Given s, can you determine if it's valid or not? 
If it's valid, print YES on a new line; otherwise, print NO instead.

Input Format

A single string denoting .

Constraints

1 <= |s| <= 10^5
String s consists of lowercase letters only (i.e., [a-z]).
Output Format

Print YES if string  is valid; otherwise, print NO instead.*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class SherlockAndTheValidString  {

    static String isValid(String s){
        // create hashmap of letters to their frequencies
        Map<Character, Integer> letterFreqs = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char currLetter = s.charAt(i);
            if (letterFreqs.containsKey(currLetter)) {
                letterFreqs.put(currLetter, letterFreqs.get(currLetter)+1);
            } else {
                letterFreqs.put(currLetter, 1);
            }
        }
        Integer[] frequencies = letterFreqs.values().toArray(new Integer[letterFreqs.size()]);
        
        // create hashmap of letter frequencies to their frequencies
        Map<Integer, Integer> freqFreqs = new HashMap<Integer, Integer>();
        for (int i = 0; i < frequencies.length; i++) {
            int currFreq = frequencies[i];
            if (freqFreqs.containsKey(currFreq)) {
                freqFreqs.put(currFreq, freqFreqs.get(currFreq)+1);
            } else {
                freqFreqs.put(currFreq, 1);
            }
        }
        
        // success possibilities:
        // {x:y}, {x:y, x+1:1}, {1:1, x:y}
        
        // Case 1 {x:y} - all letters occur with same frequencies
        if (freqFreqs.keySet().size() == 1) {
            return "YES";
        }
        
        else if (freqFreqs.keySet().size() == 2) {
            Integer[] freqs = freqFreqs.keySet().toArray(new Integer[2]);
            int value0 = freqFreqs.get(freqs[0]);
            int value1 = freqFreqs.get(freqs[1]);
            // Case 2 {1:1, x:y} - one letter occurs exactly once and all others occur the same
            if (freqs[0] == 1 && value0 == 1) {
                return "YES";
            }
            if (freqs[1] == 1 && value1 == 1) {
                return "YES";
            }
            // Case 3 {x:y, x+1:1} - one letter occurs once more than all others
            if (freqs[0] - freqs[1] == 1) {
                if (value0 == 1) {
                    return "YES";
                }
            } 
            if (freqs[1] - freqs[0] == 1) {
                if (value1 == 1) {
                    return "YES";
                }
            } 

        }

        return "NO";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        String result = isValid(s);
        System.out.println(result);
    }
}
