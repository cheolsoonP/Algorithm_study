import java.util.*;
import java.io.*;

public class Main {
	static int N, arr[];
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());
		arr = new int[N];
		st = new StringTokenizer(in.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		/*
		 * 어떤 수가 다른 수 두개 합으로 나타낼 수 있다면 좋다
		 * */
		int like = 0;// 좋아
		for(int curr=0;curr<N;curr++){
			int left = 0;
			int right = N-1;
			while(left < right) {
				if(left == curr) left++;
				else if(right == curr) right--;
				else if(arr[curr] < arr[left] + arr[right]) right--;
				else if(arr[curr] > arr[left] + arr[right]) left++;
				else if(arr[curr] == arr[left]+arr[right]) {
					like++;
					break;
				}
			}
		}
		System.out.println(like);
	}

}
