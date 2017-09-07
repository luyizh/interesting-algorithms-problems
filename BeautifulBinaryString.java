import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BeautifulBinaryString {
    
    // get array of lengths of continuous blocks of 2
    static List<Integer> findTwos(List<Integer> arr) {
        List<Integer> startIndices = new ArrayList<Integer>();
        if (arr.get(0) == 2) {
            startIndices.add(0);
        }
        for (int index = 1; index <= arr.size() - 1; index++) {
            if (arr.get(index-1) != 2 && arr.get(index) == 2) {
                startIndices.add(index);
            }
        }

        List<Integer> endIndices = new ArrayList<Integer>();

        for (int index1 = 0; index1 <= arr.size() - 2; index1++) {
            if (arr.get(index1) == 2 && arr.get(index1+1) != 2) {
                endIndices.add(index1);
            }
        }
        if (arr.get(arr.size()-1) == 2) {
            endIndices.add(arr.size()-1);
        }
        
        List<Integer> lengths = new ArrayList<Integer>();
        for (int k = 0; k < startIndices.size(); k++) {
            lengths.add(endIndices.get(k) - startIndices.get(k) + 1);
        }
        return lengths;
    }

    static int minSteps(int n, String B){
        if (B.length() < 3) {
            return 0;
        }
        
        int num010count = 0;
        
        // get start indices of when "010" occurs as substring
        List<Integer> startIndices = new ArrayList<Integer>();  
        for (int i = 0; i <= n-3; i++) {
            if (B.substring(i, i+3).equals("010")) {
                num010count++;
                startIndices.add(i);
            }
        }

        if (num010count == 0) {
            return 0;
        }
        
        // get gaps between consecutive start indices
        List<Integer> startIndexGaps = new ArrayList<Integer>();
        for (int i = 1; i < startIndices.size(); i++) {
            startIndexGaps.add(startIndices.get(i) - startIndices.get(i-1)); 
        }
       
        List<Integer> twos = findTwos(startIndexGaps);
        
        for (int i = 0; i < twos.size(); i++) {
            int length = twos.get(i);
            int diff = (length+1)/2;
            num010count -= diff;
        }

        return num010count;
        
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String B = in.next();
        int result = minSteps(n, B);
        System.out.println(result);

    }
}