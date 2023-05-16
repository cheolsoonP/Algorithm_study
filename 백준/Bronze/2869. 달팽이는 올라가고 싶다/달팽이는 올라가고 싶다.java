import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		long A = Integer.parseInt(st.nextToken());
		long B = Integer.parseInt(st.nextToken());
		long V = Integer.parseInt(st.nextToken());
		
		long x = 0; //일수 
		if((V - B) % (A - B) > 0) {
			x = ((V - B) / (A - B)) + 1; 
		}else {
			x = ((V - B) / (A - B)); 
		}
		
		System.out.println(x);
	}
}
