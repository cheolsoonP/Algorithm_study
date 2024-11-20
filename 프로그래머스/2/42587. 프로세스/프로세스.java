import java.util.*; 
import java.io.*; 

class Solution {
    public int solution(int[] priorities, int location) {
        Deque<int[]> queue = new ArrayDeque<>(); 
        
        for (int i=0;i<priorities.length; i++) {
            queue.add(new int[] {i, priorities[i]}); // index, priority 
        }
        
        Arrays.sort(priorities); 
        int targetIdx = priorities.length-1; 
        int targetPR = priorities[targetIdx]; 
        int cnt = 0; 
        while(!queue.isEmpty()) {
            int[] temp = queue.poll();
            int idx = temp[0];
            int pr = temp[1]; 
            
            if (pr == targetPR) {
                cnt++; 
                if (idx == location) {
                    break; 
                }
                targetIdx--; 
                targetPR = priorities[targetIdx]; 
            } else {
                queue.offer(temp);                
            }
            
        }
        
        return cnt;
    }
}