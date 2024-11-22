import java.util.*;

class Solution {
    class Job implements Comparable<Job> {
        int start;
        int time;
        
        public Job(int[] temp) {
            this.start = temp[0];
            this.time = temp[1];
        }
        
        public int compareTo(Job o) {
            return this.time - o.time;
        }
    }
    
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        PriorityQueue<Job> waitQ = new PriorityQueue<>();
        
        int totalTime = 0;
        int endTime = 0;
        int jobIndex = 0;
        int count = 0;
        
        while (count < jobs.length) {
            while (jobIndex < jobs.length && jobs[jobIndex][0] <= endTime) {
                waitQ.offer(new Job(jobs[jobIndex++]));
            }
            
            if (waitQ.isEmpty()) {
                endTime = jobs[jobIndex][0];
            } else {
                Job currJob = waitQ.poll();
                totalTime += endTime - currJob.start + currJob.time;
                endTime += currJob.time;
                count++;
            }
        }
        
        return totalTime / jobs.length;
    }
}