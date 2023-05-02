import java.io.*;
import java.util.*;

public class Main {
	/*
	 * N개 바구니
	 * 1번 ~ N번
	 * M번 바구니 순서를 역순으로 바꾼다
	 * 
	 * */
	static int N,M, arr[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N+1];
		for(int i=0;i<=N;i++) {
			arr[i] = i;
		}
		int start, end;

		for(int i=0;i<M;i++) {
			int temp[] = Arrays.copyOf(arr, arr.length);
			st = new StringTokenizer(in.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			
			for(int j=start;j<=end;j++) {
				arr[j] = temp[end-j+start];
			}
		}
		for(int i=1;i<=N;i++) {
			sb.append(arr[i]+" ");
		}
		System.out.println(sb);
	}
}
