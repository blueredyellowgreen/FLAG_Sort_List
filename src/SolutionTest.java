import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SolutionTest {
    @Test
    public void testSortList() {
        int[][] inputs = {
                {},
                {1},
                {4, 5},
                {4, 4},
                {1, 2, 1},
                {1, 2, 3},
                {1, 1, 1},
                {1, 2, 3, 1},
                {1, 2, 2, 1},
        };
        int[][] expects = {
                {},
                {1},
                {4, 5},
                {4, 4},
                {1, 1, 2},
                {1, 2, 3},
                {1, 1, 1},
                {1, 1, 2, 3},
                {1, 1, 2, 2},
        };

        List<TestCase> testCases = getTestCases(inputs, expects);

        for (int i = 0; i < testCases.size(); i++) {
            System.out.printf("case %d\n", i);

            TestCase testCase = testCases.get(i);
            assertArrayEquals(testCase.expect, listToArray(Solution.sortList(testCase.head)));
        }
    }

    private List<TestCase> getTestCases(int[][] inputs, int[][] expects) {
        List<TestCase> testCases = new ArrayList<>();
        for (int i = 0; i < inputs.length; i++) {
            TestCase testCase = new TestCase(arrayToList(inputs[i]), expects[i]);
            testCases.add(testCase);
        }
        return testCases;
    }

    private ListNode arrayToList(int[] input) {
        ListNode head = null;
        ListNode ptr = null;
        for (int val : input) {
            ListNode newNode = new ListNode(val);
            if (head == null) {
                head = newNode;
                ptr = head;
                continue;
            }
            ptr.next = newNode;
            ptr = newNode;
        }
        return head;
    }

    private int[] listToArray(ListNode list) {
        List<Integer> result = new ArrayList<>();
        while (list != null) {
            result.add(list.val);
            list = list.next;
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
}
