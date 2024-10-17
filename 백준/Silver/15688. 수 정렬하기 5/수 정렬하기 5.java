import java.util.*;
import java.io.*;

public class Main {
	static int N; 
	static int[] count = new int [2000001]; 
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		for (int i=0;i<N;i++) {
			int x = Integer.parseInt(in.readLine());
			count[x+1000000]++;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<2000001;i++) {
			if (count[i] == 0) continue;
			else {
				for (int num=0;num<count[i];num++) {
					sb.append(i-1000000+"\n");		
				}
			}
		}
		System.out.println(sb.toString());
	}
}
