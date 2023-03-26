import java.util.*;
import java.io.*;


public class Main {
	static int arr[], cnt, N;
	public static void main(String[] args) {
		// 2로 나눈 몫  a
		// 4로 나눈 몫  b
		// 6으로 나눈 몫 c
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[31];
		arr[2] = 3;		
		for(int i=4; i<=30; i+=2) {
			arr[i] = arr[i-2] * 3 + 2;
			for(int j=2;j<=i-4;j+=2) {
				arr[i] += arr[j] * 2;
			}
		}
		System.out.println(arr[N]);		
	}
}
