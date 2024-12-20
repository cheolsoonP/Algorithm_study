import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : scoville) {
            pq.add(s);
        }
        int cnt = 0; 
        while(pq.peek() < K) {
            if (pq.size() < 2) {
                return -1; 
            }
            int first = pq.poll();
            int second = pq.poll(); 
            pq.add(first+second*2);
            cnt++; 
        }

        return cnt; 
    }
}