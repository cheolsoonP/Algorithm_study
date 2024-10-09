import java.io.*; 
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine()); 
		int a = -1;
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<N; i++) {
			a = Integer.parseInt(st.nextToken());
			if (X > a) sb.append(a).append(" ");
		}
		
		System.out.println(sb.toString());
	}

}