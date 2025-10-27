import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	
	public static void main(String[] args) {
		
        // Problem 1 Test Cases Passed
        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println(Arrays.deepToString(merge(intervals1))); // Output: [[1, 6], [8, 10], [15, 18]]
        
        int[][] intervals2 = {{1, 4}, {4, 5}};
        System.out.println(Arrays.deepToString(merge(intervals2))); // Output: [[1, 5]]
        
        int[][] intervals3 = {{1, 3}, {5, 7}};
        System.out.println(Arrays.deepToString(merge(intervals3))); // Output: [[1, 3], [5, 7]]
        
     
       // Problem 2 Test Cases Passed 
        System.out.println(countAnagramSubstrings("cbaebabacd", "abc")); // Output: 2
        System.out.println(countAnagramSubstrings("aaaaa", "aa"));       // Output: 4
        System.out.println(countAnagramSubstrings("abab", "ab"));        // Output: 3
    }
		
	public static int[][] merge(int[][] intervals) {
		
        // Edge case: If the input list is empty, return an empty array
        if (intervals.length == 0) {
            return new int[0][0];
        }

        // Sort the intervals by the start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Initialize a list to store the merged intervals
        List<int[]> mergeList = new ArrayList<>();

        // Iterate through the sorted intervals and merge them
        for (int[] interval : intervals) {
            // If merged is empty or the current interval does not overlap with the last merged one
            if (mergeList.isEmpty() || mergeList.get(mergeList.size() - 1)[1] < interval[0]) {
                // No overlap, simply add the interval
            	mergeList.add(interval);
            	
            } else {
            	
                // Overlap, so merge the intervals
            	mergeList.get(mergeList.size() - 1)[1] = Math.max(mergeList.get(mergeList.size() - 1)[1], interval[1]);
            }
        }

        // Convert the list of merged intervals into 2D array
        return mergeList.toArray(new int[mergeList.size()][]);
    }
	
	public static int countAnagramSubstrings(String s, String p) {
        int lenS = s.length(), lenP = p.length();
        
        // return 0 if string p length is greater than string s 
        if (lenP > lenS) return 0;

        int[] freqP = new int[26];
        int[] freqS = new int[26];

        //Count frequency of p
        for (char c : p.toCharArray()) {
            freqP[c - 'a']++;
        }

        //Initialize the first window of s
        for (int i = 0; i < lenP; i++) {
            freqS[s.charAt(i) - 'a']++;
        }

        int count = 0;
        //Check the first window
        if (Arrays.equals(freqP, freqS)) {
            count++;
        }

        //Slide the window across s
        for (int i = lenP; i < lenS; i++) {
            // Remove left char of window
            freqS[s.charAt(i - lenP) - 'a']--;
            // Add new right char
            freqS[s.charAt(i) - 'a']++;

            // Check if current window matches
            if (Arrays.equals(freqP, freqS)) {
                count++;
            }
        }

        return count;
    }


}
