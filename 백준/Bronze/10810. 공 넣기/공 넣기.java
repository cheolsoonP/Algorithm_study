import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int arr[] = new int[n+1];
		int from, to, k;
		for (int i = 0; i < m; i++) {
			st  = new StringTokenizer(in.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			for(int j=from;j<=to;j++) {
				arr[j] = k;
			}
		}
		for(int i=1;i<=n;i++) {
			System.out.print(arr[i]+" ");
		}
	}

}
