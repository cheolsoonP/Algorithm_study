import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class Main_BOJ_1244 {
	static int [] arrSwitch;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(in.readLine());
		
		arrSwitch = new int[n+1];
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		for (int i = 1; i < arrSwitch.length; i++)
			arrSwitch[i] = Integer.parseInt(st.nextToken());
		
		int sn = Integer.parseInt(in.readLine());
		
		for (int i = 0; i < sn; i++) {
			st = new StringTokenizer(in.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int idx = Integer.parseInt(st.nextToken());
			
			changeSwitch(gender, idx);
		}
		
		for (int i = 1; i < arrSwitch.length; i++) {
			System.out.print(arrSwitch[i]+" ");
			if(i % 20 == 0)
				System.out.println();
		}
	}

	private static void changeSwitch(int gender, int idx) {
		// 1-남, 2-여
		if (gender == 1) {
			for (int i = idx; i < arrSwitch.length; i+=idx) {
				if (arrSwitch[i] == 0) 
					arrSwitch[i] = 1;
				else 
					arrSwitch[i] = 0;
			}
		}
		if (gender == 2) {
			int cnt = 1;
			
			if (arrSwitch[idx] == 1) { 
				arrSwitch[idx] = 0;
			} else { 
				arrSwitch[idx] = 1; 
			}
					
			while ((idx-cnt) > 0 && (idx+cnt) < arrSwitch.length) {
				if (arrSwitch[idx-cnt] == arrSwitch[idx+cnt]) {
					if (arrSwitch[idx-cnt] == 1) {
						arrSwitch[idx-cnt] = 0;
						arrSwitch[idx+cnt] = 0;
					}
					else {
						arrSwitch[idx-cnt] = 1;
						arrSwitch[idx+cnt] = 1;
					}
					cnt++;
				}
				else {
					break;
				}
			}
		}
	}

}
