class Solution {
    // Sort List
    //
    // Step 1: Split the list into two sub lists.
    // Step 2: Sort the individual sub lists.
    // Step 3: Merge them into one list.
    //
    // Example
    //
    // Input:
    // 4->2->1->3
    //
    // Process:
    // 4->2->null                              1->3->null
    //   4->null   2->null           1->null  3->null
    //           2->4->null      1->3->null
    //                  1->2->3->4->null
    //
    // Output:
    // 1->2->3->4
    //
    // Time Complexity : O(nlogn), n is the number of nodes in the list.
    // Space Complexity : O(1), no additional space needed.
    //
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode leftHead = head;
        ListNode rightHead = splitInMiddle(leftHead);

        leftHead = sortList(leftHead);
        rightHead = sortList(rightHead);

        return mergeTwoLists(leftHead, rightHead);
    }

    private static ListNode mergeTwoLists(ListNode leftHead, ListNode rightHead) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;

        while (leftHead != null && rightHead != null) {
            if (leftHead.val < rightHead.val) {
                current.next = leftHead;
                leftHead = leftHead.next;
            } else {
                current.next = rightHead;
                rightHead = rightHead.next;
            }
            current = current.next;
        }

        if (leftHead != null) {
            current.next = leftHead;
        }

        if (rightHead != null) {
            current.next = rightHead;
        }

        return dummyHead.next;
    }

    private static ListNode splitInMiddle(ListNode head) {
        ListNode slowHead = head;
        ListNode fastHead = head.next;

        while (fastHead != null && fastHead.next != null) {
            slowHead = slowHead.next;
            fastHead = fastHead.next.next;
        }

        ListNode newHead = slowHead.next;
        // Break the connection between the two consecutive nodes
        slowHead.next = null;
        return newHead;
    }
}
