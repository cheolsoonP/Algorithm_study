import java.util.*;
import java.io.*;

public class Main {
	/*
	 * 코딩실력 -10000~10000
	 * 세 팀원 코딩실력 합 0 이되는 팀을 만든다
	 * 합이 0이되는 팀을 몇개 만들 수 있는가 
	 * 
	 * */
	static int N, student[];
    static long count;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(in.readLine());
		student = new int[N];
		st = new StringTokenizer(in.readLine());
		for(int i=0;i<N;i++) {
			int score = Integer.parseInt(st.nextToken());
			student[i] = score;
		}
		
		Arrays.sort(student);
		for(int curr=0;curr<N;curr++) {
			if (student[curr] > 0) break;
			int left = curr+1;
			int right = N-1;
			while(left < right) {
				int temp = student[left] + student[right] + student[curr];
				if(temp == 0) {
					if(student[left] == student[right]) {
						int K = right-left+1;
						count+=(K*(K-1))/2;
						break;
					}					
					int l = 1;
					int r = 1;
					while((left+1<right) && (student[left] == student[left+1])) {
						left++;
						l++;					
					}
					while((right-1>left) && (student[right] == student[right-1])) {
						right--;
						r++;
					}
					count+=l*r;
				}
				if(temp > 0) right--;
				else left++;
			}
		}
		
		System.out.println(count);
	}
}

/*
 * 정렬 후
 * 1. 처음에는 양끝에를 포함하고 목표로하는 값이 있는지 이분탐색으로 찾도록 했다.
 * 양끝을 번갈아 하나씩 줄이면서 찾도록 했다. 
 * 2. 시작 0번 인덱스를 포함하고 1~N-1를 끝으로 하며, 셋의 합이 0보다 크면 right--, 합이 0보다 작으면 left++ 
 * -> -8, -2, -2, -2, 10, 10, 10, 10 같은 값이 있을 경우 방법을 생각해보면
 * -2위치 3가지 * 10의 위치 4가지 -> 12가지 존재
 * 
 * */