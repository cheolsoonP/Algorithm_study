import java.io.*; 
import java.util.*; 

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Comparator.reverseOrder()); 
        PriorityQueue<Integer> minQueue = new PriorityQueue<>(); 
        
        StringTokenizer st; 
        for (String str : operations) {
            int num = Integer.parseInt(str.substring(2));
            if (str.startsWith("I")) {
                // 값 추가 
                maxQueue.add(num);
                minQueue.add(num);
            } else if (str.startsWith("D 1") && maxQueue.size() > 0) {
                // 최대값 삭제
                minQueue.remove(maxQueue.poll());
            } else if (str.startsWith("D -1") && minQueue.size() > 0) {
                // 최솟값 삭제 
                maxQueue.remove(minQueue.poll());
            }
        }
        if (maxQueue.size() == 0 && minQueue.size() == 0) {
            return new int[]{0,0};
        } else {
            return new int[]{maxQueue.peek(), minQueue.peek()};
        }
    }
}
