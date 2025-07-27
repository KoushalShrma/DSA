/**
 * LeetCode #141: Linked List Cycle
 * 
 * Problem: Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * 
 * Pattern: Floyd's Cycle Detection Algorithm (Tortoise and Hare)
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 * 
 * This is the fundamental problem for understanding Floyd's cycle detection algorithm,
 * one of the most elegant and important algorithms in computer science.
 */

import java.util.*;

/**
 * Definition for singly-linked list node
 */
class ListNode {
    int val;
    ListNode next;
    
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class LinkedListCycle {
    
    /**
     * Floyd's Cycle Detection Algorithm (Optimal Solution)
     * 
     * Key Insight: Use two pointers moving at different speeds.
     * If there's a cycle, the fast pointer will eventually catch up to the slow pointer.
     * If there's no cycle, the fast pointer will reach the end.
     * 
     * Why it works:
     * - Slow pointer moves 1 step per iteration
     * - Fast pointer moves 2 steps per iteration
     * - In a cycle, fast pointer gains 1 step on slow pointer each iteration
     * - Eventually, fast will "lap" slow if cycle exists
     * 
     * @param head the head of the linked list
     * @return true if cycle exists, false otherwise
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false; // Can't have cycle with 0 or 1 nodes
        }
        
        ListNode slow = head;    // Tortoise: moves 1 step
        ListNode fast = head;    // Hare: moves 2 steps
        
        while (fast != null && fast.next != null) {
            slow = slow.next;           // Move slow pointer 1 step
            fast = fast.next.next;      // Move fast pointer 2 steps
            
            if (slow == fast) {         // Pointers meet = cycle detected
                return true;
            }
        }
        
        return false; // Fast pointer reached end = no cycle
    }
    
    /**
     * Hash Set Solution (Alternative approach)
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * This solution uses extra space but is easier to understand initially.
     */
    public boolean hasCycleHashSet(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        
        ListNode current = head;
        while (current != null) {
            if (visited.contains(current)) {
                return true; // We've seen this node before = cycle
            }
            visited.add(current);
            current = current.next;
        }
        
        return false; // Reached end without revisiting any node
    }
    
    /**
     * Helper method to create a cycle for testing
     */
    public static ListNode createCycleList() {
        ListNode head = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node0 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        
        head.next = node2;
        node2.next = node0;
        node0.next = node4;
        node4.next = node2; // Creates cycle back to node2
        
        return head;
    }
    
    /**
     * Helper method to create a non-cycle list for testing
     */
    public static ListNode createNonCycleList() {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        
        head.next = node2;
        node2.next = node3;
        // node3.next is null (no cycle)
        
        return head;
    }
    
    /**
     * Test cases and main method for verification
     */
    public static void main(String[] args) {
        LinkedListCycle solution = new LinkedListCycle();
        
        // Test Case 1: List with cycle
        ListNode cycleList = createCycleList();
        boolean result1 = solution.hasCycle(cycleList);
        System.out.println("Test 1 - Cycle List: " + result1); // Expected: true
        
        // Test Case 2: List without cycle
        ListNode noCycleList = createNonCycleList();
        boolean result2 = solution.hasCycle(noCycleList);
        System.out.println("Test 2 - No Cycle List: " + result2); // Expected: false
        
        // Test Case 3: Empty list
        boolean result3 = solution.hasCycle(null);
        System.out.println("Test 3 - Empty List: " + result3); // Expected: false
        
        // Test Case 4: Single node with self-cycle
        ListNode selfCycle = new ListNode(1);
        selfCycle.next = selfCycle; // Points to itself
        boolean result4 = solution.hasCycle(selfCycle);
        System.out.println("Test 4 - Self Cycle: " + result4); // Expected: true
        
        // Test Case 5: Single node without cycle
        ListNode singleNode = new ListNode(1);
        boolean result5 = solution.hasCycle(singleNode);
        System.out.println("Test 5 - Single Node: " + result5); // Expected: false
        
        // Compare with HashSet approach
        System.out.println("\nHashSet approach results:");
        System.out.println("Cycle List: " + solution.hasCycleHashSet(createCycleList()));
        System.out.println("No Cycle List: " + solution.hasCycleHashSet(createNonCycleList()));
    }
}

/**
 * Key Learning Points:
 * 
 * 1. Floyd's Algorithm: Elegant O(1) space solution using two pointers
 * 2. Pointer Speed Difference: Fast pointer eventually catches slow in cycle
 * 3. Null Checking: Always validate pointers before dereferencing
 * 4. Alternative Approaches: HashSet solution trades space for simplicity
 * 
 * Mathematical Proof (why Floyd's algorithm works):
 * - Let's say slow pointer has traveled distance 'd' when they meet
 * - Fast pointer has traveled distance '2d' (twice as fast)
 * - If cycle length is 'L', then: 2d - d = nL (where n is number of laps)
 * - Therefore: d = nL, proving they meet within the cycle
 * 
 * Interview Tips:
 * - Start with the problem understanding: "What constitutes a cycle?"
 * - Mention both approaches (Floyd's and HashSet)
 * - Explain why Floyd's is preferred (constant space)
 * - Draw the algorithm on whiteboard if possible
 * - Discuss edge cases: empty list, single node, self-cycle
 * 
 * Follow-up Problems:
 * - Linked List Cycle II: Find where the cycle begins
 * - Happy Number: Uses same Floyd's algorithm concept
 * - Find Duplicate Number: Array version of cycle detection
 * 
 * Pattern Applications:
 * This two-pointer technique appears in:
 * - Cycle detection in any sequential structure
 * - Finding middle of linked list
 * - Detecting patterns in sequences
 * - Optimizing space in graph algorithms
 */