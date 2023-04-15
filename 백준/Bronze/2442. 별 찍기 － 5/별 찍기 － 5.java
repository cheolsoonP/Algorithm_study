import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(in.readLine());
		
		for(int i=0;i<n;i++) {
			for(int j=n-1-i;j>0;j--) {
				sb.append(" ");				
			}
			for(int j=0;j<(i*2)+1;j++) {
				sb.append("*");
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
}
