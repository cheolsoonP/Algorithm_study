import java.util.*;
import java.io.*;

public class Main {
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(in.readLine());
		int space = n-1;
		int star = 1;
		for(int i=0;i<2*n-1;i++) {
			for(int j=0;j<space;j++) {
				sb.append(" ");
			}
			for(int j=0;j<star;j++) {
				sb.append("*");
			}
			if(i<n-1) {
				space--;
				star+=2;
			}else {
				space++;
				star-=2;
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
}
