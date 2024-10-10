import java.io.*;
import java.util.*;


public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] word = new int['z'-'a'+1];
		char[] words = in.readLine().toCharArray();
		for (char w : words) {
			word[w - 'a']++;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int cnt : word) {
			sb.append(cnt+" ");
		}
		System.out.println(sb.toString());
	}
}
