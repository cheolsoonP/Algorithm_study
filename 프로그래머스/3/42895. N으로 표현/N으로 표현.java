import java.util.*; 
import java.io.*; 

class Solution {
    public int solution(int N, int number) {
        int answer = 0;
        /*
        N을 최소한으로 사용해서 number를 만들자. 
        DP[i] = N을 i까지 만드는데 사용한 최소 횟수 
        숫자 i까지 만드는데 최소 횟수 
        N을 i번 사용해서 만들 수 있는 수 
        
        */
        List<Set<Integer>> DP = new ArrayList<>(); 
        for (int i=0;i<=8;i++){
            DP.add(new HashSet<Integer>());
        }

        //dp[i]의 원소 = dp[i - j]의 원소 (사칙연산) dp[j]
        for (int i=1;i<=8;i++){
            Set<Integer> set = DP.get(i); 
            
            int NNN = 0;
            for (int j=0;j<i;j++){
                NNN += N * Math.pow(10, j);
            }
            System.out.println(NNN);
            set.add(NNN);
            for (int j=1;j<=i;j++){
                for (int a : DP.get(i-j)) {
                    for (int b : DP.get(j)) {
                        int plus = a+b; 
                        
                        int mul = a*b;
                        int div; 
                        if (b != 0) {
                            div = a/b; 
                            set.add(div);
                        }
                        if (a != 0) {
                            div = b/a; 
                            set.add(div);
                        }
                        set.add(plus);
                        if (a > b) {
                            int minus = a-b;
                            set.add(minus);
                        }
                        if (b > a) {
                            int minus = b-a;
                            set.add(minus);
                        }
                        set.add(mul); 
                    }
                }

            }
            if (set.contains(number)) {
                return i;
            }
            // for (int num : DP.get(i)) {
            //     System.out.print(num+",");
            // }
            // System.out.println();
        }
        return -1; 
    }
}