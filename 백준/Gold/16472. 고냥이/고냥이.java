import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] arr;
	static int[] check;
	// 투포인터
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());
		
		char[] temp = in.readLine().toCharArray();
		arr = new int[temp.length];
		check = new int[26];
		for(int i=0;i<temp.length;i++) {
			arr[i] = temp[i] - 'a';
		}
				
		int front = 0;
		int end = 0;
		int cnt = 1;
		int result = 0;
		check[arr[end]]++;
		while(true) {
			end++;
			if(end >= arr.length) break;
			
			check[arr[end]]++;
			if(check[arr[end]] == 1) {
				cnt++;
			}
			while(cnt > N) {
				check[arr[front]]--;
				if(check[arr[front]] == 0) {
					cnt--;
				}
				front++;
			}
			result = Math.max(result, end-front+1);			
		}
		
		System.out.println(result);
	}
}
