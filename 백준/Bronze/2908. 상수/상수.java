import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		// 
		int aa = ((a%10) * 100);
		a = a/10;
		aa += (a%10) * 10;
		a = a/10;
		aa += (a%10);
		
		int bb = ((b%10) * 100);
		b = b/10;
		bb += (b%10) * 10;
		b = b/10;
		bb += (b%10);

		System.out.println(Math.max(aa, bb));
		
	}
}
