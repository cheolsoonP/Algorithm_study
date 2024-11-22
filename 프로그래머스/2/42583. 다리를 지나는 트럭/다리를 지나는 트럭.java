import java.util.*; 
import java.io.*; 

class Solution {
    public int solution(int maxLength, int maxWeight, int[] trucks) {
        int currWeight = 0; // 현재 다리 무게 
        Deque<int[]> queue = new ArrayDeque<>(); // time, weight 
        int time = 0; 
        int idx = 0; 
        while (true) {
            if (queue.isEmpty() && idx >= trucks.length) {
                break; 
            }
            if (!queue.isEmpty() && time - queue.peek()[0] >= maxLength) {
                int[] temp = queue.poll();
                currWeight -= temp[1]; 
            }
            
            if (idx < trucks.length 
                    && currWeight + trucks[idx] <= maxWeight 
                    && queue.size() < maxLength) {
                currWeight += trucks[idx];
                queue.offer(new int[]{time, trucks[idx]});
                idx++; 
            }
            time++; 
        }
        return time; 
    }
}