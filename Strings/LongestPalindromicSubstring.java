/**
 * LeetCode #5: Longest Palindromic Substring
 * 
 * Problem: Given a string s, return the longest palindromic substring in s.
 * 
 * Pattern: Expand Around Center
 * Time Complexity: O(n²)
 * Space Complexity: O(1)
 * 
 * This problem demonstrates the elegant "expand around center" technique
 * and is fundamental for understanding palindrome detection algorithms.
 */

public class LongestPalindromicSubstring {
    
    /**
     * Expand Around Center Approach (Optimal for this problem)
     * 
     * Key Insight: A palindrome mirrors around its center.
     * We can check all possible centers and expand outward.
     * 
     * There are 2n-1 possible centers:
     * - n centers for odd-length palindromes (single character centers)
     * - n-1 centers for even-length palindromes (between two characters)
     * 
     * @param s input string
     * @return longest palindromic substring
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        
        int start = 0; // Start index of longest palindrome found
        int maxLen = 1; // Length of longest palindrome found
        
        for (int i = 0; i < s.length(); i++) {
            // Check for odd-length palindromes (center at i)
            int len1 = expandAroundCenter(s, i, i);
            
            // Check for even-length palindromes (center between i and i+1)
            int len2 = expandAroundCenter(s, i, i + 1);
            
            // Take the maximum length from both cases
            int currentMaxLen = Math.max(len1, len2);
            
            // Update global maximum if we found a longer palindrome
            if (currentMaxLen > maxLen) {
                maxLen = currentMaxLen;
                // Calculate start index: center - (length-1)/2
                start = i - (currentMaxLen - 1) / 2;
            }
        }
        
        return s.substring(start, start + maxLen);
    }
    
    /**
     * Helper method to expand around center and return palindrome length
     * 
     * @param s input string
     * @param left left boundary of expansion
     * @param right right boundary of expansion
     * @return length of palindrome centered between left and right
     */
    private int expandAroundCenter(String s, int left, int right) {
        // Expand while characters match and boundaries are valid
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        
        // Return length: right - left - 1
        // (We went one step too far in the while loop)
        return right - left - 1;
    }
    
    /**
     * Dynamic Programming Approach (Alternative solution)
     * Time Complexity: O(n²)
     * Space Complexity: O(n²)
     * 
     * This approach builds a table dp[i][j] = true if substring s[i..j] is palindrome
     */
    public String longestPalindromeDP(String s) {
        int n = s.length();
        if (n == 0) return "";
        
        boolean[][] dp = new boolean[n][n];
        String result = "";
        
        // Every single character is a palindrome
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            result = s.substring(i, i + 1);
        }
        
        // Check for palindromes of length 2
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                result = s.substring(i, i + 2);
            }
        }
        
        // Check for palindromes of length 3 and more
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                
                // Check if s[i] == s[j] and s[i+1..j-1] is palindrome
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    result = s.substring(i, j + 1);
                }
            }
        }
        
        return result;
    }
    
    /**
     * Brute Force Approach (for educational purposes)
     * Time Complexity: O(n³)
     * Space Complexity: O(1)
     */
    public String longestPalindromeBruteForce(String s) {
        String longest = "";
        
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String substring = s.substring(i, j + 1);
                if (isPalindrome(substring) && substring.length() > longest.length()) {
                    longest = substring;
                }
            }
        }
        
        return longest;
    }
    
    /**
     * Helper method to check if a string is palindrome
     */
    private boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    /**
     * Test cases and main method for verification
     */
    public static void main(String[] args) {
        LongestPalindromicSubstring solution = new LongestPalindromicSubstring();
        
        // Test Case 1: Odd-length palindrome
        String test1 = "babad";
        System.out.println("Test 1: '" + test1 + "' -> '" + 
                          solution.longestPalindrome(test1) + "'"); // Expected: "bab" or "aba"
        
        // Test Case 2: Even-length palindrome
        String test2 = "cbbd";
        System.out.println("Test 2: '" + test2 + "' -> '" + 
                          solution.longestPalindrome(test2) + "'"); // Expected: "bb"
        
        // Test Case 3: Single character
        String test3 = "a";
        System.out.println("Test 3: '" + test3 + "' -> '" + 
                          solution.longestPalindrome(test3) + "'"); // Expected: "a"
        
        // Test Case 4: No palindrome longer than 1
        String test4 = "abcdef";
        System.out.println("Test 4: '" + test4 + "' -> '" + 
                          solution.longestPalindrome(test4) + "'"); // Expected: any single char
        
        // Test Case 5: Entire string is palindrome
        String test5 = "racecar";
        System.out.println("Test 5: '" + test5 + "' -> '" + 
                          solution.longestPalindrome(test5) + "'"); // Expected: "racecar"
        
        // Test Case 6: Empty string
        String test6 = "";
        System.out.println("Test 6: '" + test6 + "' -> '" + 
                          solution.longestPalindrome(test6) + "'"); // Expected: ""
        
        // Compare with DP approach
        System.out.println("\nDP approach results:");
        System.out.println("Test 1 DP: '" + solution.longestPalindromeDP(test1) + "'");
        System.out.println("Test 2 DP: '" + solution.longestPalindromeDP(test2) + "'");
    }
}

/**
 * Key Learning Points:
 * 
 * 1. Expand Around Center: Elegant O(n²) time, O(1) space solution
 * 2. Palindrome Properties: Mirrors around center, odd/even length considerations
 * 3. Center Enumeration: 2n-1 possible centers (n + n-1)
 * 4. String Manipulation: Efficient substring operations
 * 
 * Algorithm Intuition:
 * - For each possible center, expand outward as long as characters match
 * - Track the longest palindrome found so far
 * - Handle both odd-length (single center) and even-length (dual center) cases
 * 
 * Interview Tips:
 * - Start with brute force approach, then optimize
 * - Explain the center expansion concept clearly
 * - Discuss time/space tradeoffs between different approaches
 * - Handle edge cases: empty string, single character
 * - Mention alternative approaches: Manacher's algorithm O(n), DP O(n²)
 * 
 * Follow-up Questions:
 * - How would you find ALL palindromic substrings?
 * - Can you solve this in O(n) time? (Manacher's algorithm)
 * - What if you need to find longest palindrome after one character deletion?
 * 
 * Pattern Applications:
 * This expand-around-center technique appears in:
 * - Palindromic Substrings (count all)
 * - Longest Palindromic Subsequence (with modifications)
 * - Valid Palindrome variations
 * - String pattern matching problems
 * 
 * Optimization Notes:
 * - Manacher's algorithm can solve this in O(n) time
 * - For multiple queries, consider preprocessing
 * - Early termination possible if remaining string length < current max
 */