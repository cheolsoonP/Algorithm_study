import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int N = sc.nextInt();
		int M = sc.nextInt();
		int arr[] = new int[N+1];
		for(int i=1;i<=N;i++) {
			arr[i] = i;
		}
		for(int i=1;i<=M;i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int temp = arr[a];
			arr[a] = arr[b];
			arr[b] = temp;
		}
		
		for(int i=1;i<=N;i++) {
			sb.append(arr[i] + " ");
		}
		
		System.out.println(sb);
	}
}
