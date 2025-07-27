/**
 * LeetCode #1: Two Sum
 * 
 * Problem: Given an array of integers nums and an integer target, 
 * return indices of the two numbers such that they add up to target.
 * 
 * Pattern: Hash Table + Array
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 * 
 * This is a foundational problem that demonstrates the power of hash tables
 * to optimize brute force O(n²) solutions to O(n) time complexity.
 */

import java.util.*;

public class TwoSum {
    
    /**
     * Optimized Solution using HashMap
     * 
     * Key Insight: Instead of checking if target-nums[i] exists in the remaining array,
     * we can store seen numbers in a hash table and check for complement in O(1) time.
     * 
     * Algorithm:
     * 1. Initialize a HashMap to store value -> index mapping
     * 2. For each number, calculate its complement (target - current number)
     * 3. If complement exists in map, we found our answer
     * 4. Otherwise, store current number and its index in map
     * 
     * @param nums array of integers
     * @param target target sum to find
     * @return array containing indices of two numbers that sum to target
     */
    public int[] twoSum(int[] nums, int target) {
        // HashMap to store number -> index mapping
        Map<Integer, Integer> numToIndex = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            int complement = target - current;
            
            // Check if complement exists in our seen numbers
            if (numToIndex.containsKey(complement)) {
                return new int[]{numToIndex.get(complement), i};
            }
            
            // Store current number and its index
            numToIndex.put(current, i);
        }
        
        // Problem guarantees exactly one solution exists
        throw new IllegalArgumentException("No two sum solution found");
    }
    
    /**
     * Brute Force Solution (for comparison)
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     */
    public int[] twoSumBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution found");
    }
    
    /**
     * Test cases and main method for verification
     */
    public static void main(String[] args) {
        TwoSum solution = new TwoSum();
        
        // Test Case 1: Example from problem description
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = solution.twoSum(nums1, target1);
        System.out.println("Test 1: " + Arrays.toString(result1)); // Expected: [0, 1]
        
        // Test Case 2: Numbers not in ascending order
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] result2 = solution.twoSum(nums2, target2);
        System.out.println("Test 2: " + Arrays.toString(result2)); // Expected: [1, 2]
        
        // Test Case 3: Duplicate numbers
        int[] nums3 = {3, 3};
        int target3 = 6;
        int[] result3 = solution.twoSum(nums3, target3);
        System.out.println("Test 3: " + Arrays.toString(result3)); // Expected: [0, 1]
        
        // Test Case 4: Negative numbers
        int[] nums4 = {-1, -2, -3, -4, -5};
        int target4 = -8;
        int[] result4 = solution.twoSum(nums4, target4);
        System.out.println("Test 4: " + Arrays.toString(result4)); // Expected: [2, 4]
    }
}

/**
 * Key Learning Points:
 * 
 * 1. Hash Table Optimization: Converting O(n²) to O(n) by trading space for time
 * 2. Complement Logic: For target sum, if we have number x, we need (target - x)
 * 3. Single Pass: We can find the solution in one iteration through the array
 * 4. Index Preservation: HashMap stores the original indices even as we process
 * 
 * Interview Tips:
 * - Always ask about edge cases (empty array, no solution, duplicates)
 * - Discuss both brute force and optimized approaches
 * - Explain the time/space tradeoff clearly
 * - Consider follow-up: what if array is sorted? (Two pointers approach)
 * 
 * Pattern Application:
 * This hash table pattern appears in many other problems:
 * - 3Sum (with additional sorting)
 * - 4Sum
 * - Two Sum II (sorted array - use two pointers instead)
 * - Subarray Sum Equals K
 */