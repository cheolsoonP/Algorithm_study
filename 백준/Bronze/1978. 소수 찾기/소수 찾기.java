import java.util.*;
import java.io.*;
 
public class Main {

	static int MAX_INDEX = 1001;
	static Set<Integer> set;
	static boolean[] isPrimary;
	public static void main(String[] args) throws Exception {
	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb = new StringBuilder();
		isPrimary = new boolean[MAX_INDEX+1];
		Arrays.fill(isPrimary, true);
		
		isPrimary[1] = false; 
		isPrimary[0] = false; 
	    // n의 제곱근까지 나눈다.
        for(int i = 2; i <= Math.sqrt(MAX_INDEX); i++){ // 2부터 n의 제곱근까지의 모든 수를 확인
            if(isPrimary[i]){ // 해당수가 소수라면, 해당수를 제외한 배수들을 모두 false 처리하기
                for(int j = i*i; j<= MAX_INDEX; j += i){//그 이하의 수는 모두 검사했으므로 i*i부터 시작
                	isPrimary[j] = false;
                }
            }
        }	
		
		int N = Integer.parseInt(in.readLine());
		st = new StringTokenizer(in.readLine());
		int cnt = 0;
		for (int i=0;i<N;i++) {
			int num = Integer.parseInt(st.nextToken());
			if(isPrimary[num]) {
				cnt++;
			}
		}
		System.out.println(cnt);		
	}

}
