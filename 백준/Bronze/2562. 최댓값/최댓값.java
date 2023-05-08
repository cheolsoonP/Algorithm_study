import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int arr[] = new int[9];
		int n = 0;
		int maxValue = 0;
		for (int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(in.readLine());
			if(maxValue < arr[i]) {
				n = i+1;
				maxValue = arr[i];
			}
		}
		
		System.out.println(maxValue);
		System.out.println(n);
		
		
	}
}
