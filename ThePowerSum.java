/*Find the number of ways that a given integer, X, can be expressed 
 * as the sum of the Nth power of unique, natural numbers.

Input Format

The first line contains an integer X. 
The second line contains an integer N.

Constraints
1 <= X <= 1000
2 <= N <= 10

Output Format

Output a single integer, the answer to the problem explained above.*/

import java.io.*;
import java.util.*;

public class ThePowerSum {
    
    public static List<List<Integer>> createAllSubsets(List<Integer> arr) { 
        List<List<Integer>> allSubsets = new ArrayList<List<Integer>>();

        // base case
        if (arr.size() == 1) {
            List<Integer> emptyList = new ArrayList<Integer>();
            allSubsets.add(emptyList);
            allSubsets.add(arr);
            return allSubsets;
        }
        
        // recursive step
        // first add all of prev to new array
        // then add all of prev concatenated with last element
        List<List<Integer>> prev = createAllSubsets(arr.subList(0, arr.size() - 1));
        allSubsets = createAllSubsets(arr.subList(0, arr.size() - 1));
        Integer lastElement = arr.get(arr.size() - 1);
        
        for (int i = 0; i < prev.size(); i++) {
            List<Integer> joined = new ArrayList<Integer>();
            joined.addAll(prev.get(i));
            joined.add(lastElement);
            allSubsets.add(joined);
        }

        return allSubsets;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int sum = Integer.parseInt(scan.nextLine());
        int exp = Integer.parseInt(scan.nextLine());
        
        int maxBase = 1;
        while ((int)Math.pow(maxBase, exp) <= sum) {
            maxBase++;
        }
        if (maxBase > 1) {
            maxBase--;
        }
        
        // create all subsets of integers from 1 to maxBase inclusive
        List<Integer> nums = new ArrayList<Integer>();
        for (int i = 1; i <= maxBase; i++) {
            nums.add(i);
        }
        List<List<Integer>> allSubsets = createAllSubsets(nums);

        // iterate through all subsets to see if sum is reached
        int ans = 0;
        for (int i = 0; i < allSubsets.size(); i++) {
            List<Integer> currentList = allSubsets.get(i);
            int count = 0;
            for (int j = 0; j < currentList.size(); j++) {
                count += (int)Math.pow(currentList.get(j), exp);
            }
            if (count == sum) {
                ans++;
            }
        }
        
        System.out.println(ans);

    }
}
