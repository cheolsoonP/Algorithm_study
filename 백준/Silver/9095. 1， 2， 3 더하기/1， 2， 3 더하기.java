import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int D[];
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		D = new int[12];
		D[0] = 0;
		D[1] = 1;
		D[2] = 2; 
		D[3] = 4;
		for (int i=4;i<=11;i++) {
			D[i] = D[i-1]+D[i-2]+D[i-3];
		}		
		StringBuilder sb = new StringBuilder();
		
		for (int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(in.readLine());
			sb.append(D[N]+"\n");	
		}
		System.out.println(sb.toString());
	}
}
