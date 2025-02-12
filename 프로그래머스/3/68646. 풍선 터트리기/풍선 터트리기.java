import java.util.*; 
import java.io.*; 

class Solution {
    public int solution(int[] a) {
      
        /*
        1. 인접 두 풍선, 하나 터트림 
        2. 빈공간 -> 좌로 밀착
        번호가 더 작은 풀선을 터트리는거는 최대 1번 (이외에는 번호가 더 큰것만 가능) 
        최후까지 남기는 것이 가능한 풍선의 개수 
        
        -- 양 옆의 최소값이 현재값보다 작으면 살아남을 수 없는 숫자 
        
        */
        int N = a.length; 
        
        if (N == 1) {
            return 1; 
        } else if (N == 2) {
            return 2; 
        }
        
        int minLeft = Integer.MAX_VALUE; 
        int[] minRight = new int[N];
        Arrays.fill(minRight, Integer.MAX_VALUE); 
        minRight[N-1] = a[N-1]; 
        
        for (int i=N-2;i>0;i--) {
            minRight[i] = Math.min(minRight[i+1], a[i]);
        }
        
        int count = 0;
        for (int i=0;i<N;i++) {
            if (minLeft < a[i] && minRight[i] < a[i]) count++; 
            minLeft = Math.min(minLeft, a[i]);
        }
        
        return N-count; 
    }
}