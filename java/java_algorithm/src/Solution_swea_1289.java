import java.util.Scanner;

public class Solution_swea_1289 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		int T = sc.nextInt();
		
		for (int tc = 1; tc < T+1; tc++) {
			String arr = new String(sc.next());
			char curr = '0';
			int count = 0;

			for (int i = 0; i < arr.length(); i++) {
				if (arr.charAt(i) != curr) {
					if (curr == '0')
						curr = '1';	
					else
						curr = '0';
					count += 1;
				};			
			}
			System.out.println("#"+tc+" "+count);
		}
	}
}

