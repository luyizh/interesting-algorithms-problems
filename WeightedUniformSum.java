/*A weighted string is a string of lowercase English letters 
where each letter has a weight in the inclusive range from 1 to 26, defined below:
	a:1, b:2, ..., z:26


The weight of a string is the sum of the weights of all the string's characters.

A uniform string is a string consisting of a single character repeated zero or more times. 
For example, ccc and a are uniform strings, but bcb and cd are not 
(i.e., they consist of more than one distinct character).
Given a string, s, let U be the set of weights for all possible uniform substrings (contiguous) 
of string s. You have to answer n queries, where each query i consists of a single integer, x_i. 
For each query, print Yes on a new line if x_i is in U; otherwise, print No instead.

Input Format

The first line contains a string denoting s (the original string). 
The second line contains an integer denoting n (the number of queries). 
Each line i of the n subsequent lines contains an integer denoting x_i 
(the weight of a uniform substring of s that may or may not exist).

Constraints

1<=|s|, n<=10^5
1<=x_i<=10^7
s will only contain lowercase English letters.
Output Format

Print n lines. For each query, print Yes on a new line if x_i is in U; 
otherwise, print No instead.*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class WeightedUniformSum {
    
    // generate length of longest substring made exclusively of each character
    public static Map<Character, Integer> getLongestSubstringLengths(String s) {
        // create hashmap of characters and the longest length of substring corresponding to each character
        
        // if currLetter is NOT prevLetter,
        //    result hashmap.add(currBlockStartLet, currBlockLen)
        //    currBlockLen - 1
        //    reset currBlockStartLet
        // if currLetter IS prevLet,
        //    increment currBlockLen
        
        Map<Character, Integer> longestSubstringLengths = new HashMap<Character, Integer>();
        // initialize values given first character
        char currLetter = s.charAt(0);
        char prevLetter;
        char currBlockStartLetter = s.charAt(0);
        int currBlockLen = 1;
        
        if (s.length() == 1) {
            longestSubstringLengths.put(currLetter, currBlockLen);
            return longestSubstringLengths;
        }
        
        // update values given second character
        currLetter = s.charAt(1);
        prevLetter = s.charAt(0);
        if (currLetter == prevLetter) {
            currBlockLen++;
        } else {
            List<Integer> blockLens = new ArrayList<Integer>();
            longestSubstringLengths.put(currBlockStartLetter, currBlockLen);
            currBlockLen = 1;
            currBlockStartLetter = currLetter;
        }
        
        // loop for third character and rest
        for (int i = 2; i < s.length(); i++) {

            currLetter = s.charAt(i);
            prevLetter = s.charAt(i-1);
            
            // incrementing current block length
            if (currLetter == prevLetter) {
                currBlockLen++;
            // putting current block letter and length
            } else {
                if (!longestSubstringLengths.containsKey(currBlockStartLetter)) {
                    longestSubstringLengths.put(currBlockStartLetter, currBlockLen);
                } else {
                    int currMaxLen = longestSubstringLengths.get(currBlockStartLetter);
                    if (currBlockLen > currMaxLen) {
                        longestSubstringLengths.put(currBlockStartLetter, currBlockLen);
                    }
                }
                // reset current block length and start letter afterward
                currBlockLen = 1;
                currBlockStartLetter = currLetter;
            }

        }
        
        // add final length
        if (!longestSubstringLengths.containsKey(currBlockStartLetter)) {
            longestSubstringLengths.put(currBlockStartLetter, currBlockLen);
        } else {
            int currMaxLen = longestSubstringLengths.get(currBlockStartLetter);
            if (currBlockLen > currMaxLen) {
                longestSubstringLengths.put(currBlockStartLetter, currBlockLen);
            }
        }
        
        return longestSubstringLengths;
    }
    
    // generate set of weights for a string
    public static Set<Integer> generateWeights(String s) {
        Set<Integer> weights = new HashSet<Integer>();
        
        Map<Character, Integer> GIVEN_WEIGHTS = new HashMap<Character, Integer>();
        GIVEN_WEIGHTS.put('a', 1);
        GIVEN_WEIGHTS.put('b', 2);
        GIVEN_WEIGHTS.put('c', 3);
        GIVEN_WEIGHTS.put('d', 4);
        GIVEN_WEIGHTS.put('e', 5);
        GIVEN_WEIGHTS.put('f', 6);
        GIVEN_WEIGHTS.put('g', 7);
        GIVEN_WEIGHTS.put('h', 8);
        GIVEN_WEIGHTS.put('i', 9);
        GIVEN_WEIGHTS.put('j', 10);
        GIVEN_WEIGHTS.put('k', 11);
        GIVEN_WEIGHTS.put('l', 12);
        GIVEN_WEIGHTS.put('m', 13);
        GIVEN_WEIGHTS.put('n', 14);
        GIVEN_WEIGHTS.put('o', 15);
        GIVEN_WEIGHTS.put('p', 16);
        GIVEN_WEIGHTS.put('q', 17);
        GIVEN_WEIGHTS.put('r', 18);
        GIVEN_WEIGHTS.put('s', 19);
        GIVEN_WEIGHTS.put('t', 20);
        GIVEN_WEIGHTS.put('u', 21);
        GIVEN_WEIGHTS.put('v', 22);
        GIVEN_WEIGHTS.put('w', 23);
        GIVEN_WEIGHTS.put('x', 24);
        GIVEN_WEIGHTS.put('y', 25);
        GIVEN_WEIGHTS.put('z', 26);
       
        // generate hashmap
        Map<Character, Integer> longestSubstringLengths = new HashMap<Character, Integer>();
        longestSubstringLengths = getLongestSubstringLengths(s);
        
        Set<Character> chars = longestSubstringLengths.keySet();
        for (char c : chars) {
            for (int numReps = 1; numReps <= longestSubstringLengths.get(c); numReps++) {
                weights.add(numReps * GIVEN_WEIGHTS.get(c));
            }
        }
        
        return weights;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int n = in.nextInt();
        Set<Integer> possibleWeights = generateWeights(s);
        for(int a0 = 0; a0 < n; a0++){
            int x = in.nextInt();
            if (possibleWeights.contains(x)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }

    }
}
