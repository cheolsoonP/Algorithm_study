import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	
	public static void main(String[] args) {
		String str = "ASDSFFASDSSAAAA";
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] dat = new int[200];
		int max = 0;
		char maxCh = ' ';
		
		for (int i = 0; i < str.length(); i++) {
			dat[str.charAt(i)]++;
			if (dat[str.charAt(i)] > max) {
				max = dat[str.charAt(i)];
				maxCh = str.charAt(i);
			}
		}
		System.out.println(max + " " + maxCh);
	}

}
