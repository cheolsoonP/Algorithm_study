import java.util.*;
import java.io.*; 

class Solution {
    public List<Integer> solution(int[] progresses, int[] speeds) {
        /*
        뒤에 있는 기능이 앞에보다 먼저 개발될수 있다 
        뒤에 있는 기능은 앞에 기능이 배포될 때 함께 배포된다. 
        progresses - 먼저 배포되어야 하는 순서대로 작업의 진도가 적힘 
        speeds - 각 개발 속도 
        
        각 배포마다 몇개의 기능이 배포되는지? 
        
        1. 각 작업별로 걸리는 기간을 구한다. 
        2. 맨 앞에 작업 기간 A일 때, 이후 작업 중 걸린 기한이 A보다 큰거를 만날때까지 개수확인 
        3. 큰거 만나면 종료 및 개수 저장 [] 
        2를 반복 
        */
        
        int[] days = new int[progresses.length]; 
        // 1. 각 작업별로 걸리는 기간 구한다. 
        Queue<Integer> queue = new ArrayDeque<>(); 
        for (int i=0; i<progresses.length; i++){
            int speed = speeds[i];
            int remain = 100 - progresses[i];
            if (remain % speed > 0) {
                queue.offer(remain/speed + 1);
            }  else {
                queue.offer(remain/speed);                
            }
        }
        
        List<Integer> countList = new ArrayList<>(); 
        int currDay = -1; 
        int cnt = 0; 
        
        System.out.println(queue.toString()); 
        while(!queue.isEmpty()) {
            if (currDay < queue.peek()) {
                if (currDay != -1) countList.add(cnt);
                currDay = queue.poll();
                cnt = 1; 
            } else {
                queue.poll();
                cnt++; 
            }
            
            if (queue.isEmpty()) {
                countList.add(cnt);
            }
            System.out.println(currDay+","+cnt);
        }
        return countList;
    }
}