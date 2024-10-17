import java.io.*;
import java.util.*;

public class Main {
	static int N; 
	static long currVal; // 현재 보고 있는 수 
	static int currCnt; 	//현재 보고 있는 수의 횟수 
	static long maxVal;	//가장 많이 등장한 값 
	static int maxCnt; 	// 가장 많이 등장한 횟수 
	static long[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		arr = new long[N];
		for (int i=0;i<N;i++) {
			arr[i] = Long.parseLong(in.readLine());
		}

//		System.out.println("before: \n"+Arrays.toString(arr));
		Arrays.sort(arr);
//		System.out.println("after: \n"+Arrays.toString(arr));
		
		currVal = arr[0];
		currCnt = 0;
		maxVal = -1;
		maxCnt = -1; 
		for (int i=0;i<N;i++) {
			if (currVal != arr[i]) {
				// 변경된 수로 currVal,cullCnt 초기화 
				currVal = arr[i];
				currCnt = 1;				
			} else {
				// 동일한 수 
				currCnt++;
				
				// 지금까지 동일한 수 Count 비교 
				if (maxCnt < currCnt) {
					maxCnt = currCnt;
					maxVal = currVal;
				}
			}
		}
		
		System.out.println(maxVal);
	}

}
