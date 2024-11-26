import java.io.*;
import java.util.*;

public class Main {
	
	static int arr[][]; 
	static boolean[] visited; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 

		arr = new int[4][8];
		for (int i=0;i<4;i++) {
			String str = in.readLine();
			for (int j=0;j<8;j++) {
				arr[i][j] = str.charAt(j) - '0';				
			}
		}
		
		int K = Integer.parseInt(in.readLine());
				
		for (int i=0;i<K;i++) {
			// 번호, 방향 (1 시계, -1 반시계)
			st = new StringTokenizer(in.readLine());
			int num = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken());
			visited = new boolean[4];
			ArrayList<int[]> rotList = new ArrayList<>();
			rotList.add(new int[] {num,dir});
			visited[num] = true;
			
			check(num, dir, rotList);
			for (int[] rot : rotList) {
				if (rot[1] == 1) {
					rotate(rot[0]);
				} else {
					rotateReverse(rot[0]);
				}
			}
		}
		
		int score = 0;
		for (int i=0;i<4;i++) {
			if (arr[i][0] == 1) {
				score += Math.pow(2, i);
			}
		}
		System.out.println(score);
	}
	private static void check(int num, int dir, ArrayList<int[]> rotList) {
		if (num == 0) {
			if (arr[num+1][6] != arr[num][2]) {
				if (!visited[num+1]) {
					visited[num+1] = true;
					rotList.add(new int[] {num+1, dir*(-1)});
					check(num+1, dir*(-1), rotList);
				}
			}
		} else if (num == 3) {
			if (arr[num-1][2] != arr[num][6]) {
				if (!visited[num-1]) {
					visited[num-1] = true;
					rotList.add(new int[] {num-1, dir*(-1)});
					check(num-1, dir*(-1), rotList);
				}
			}
		} else {
			if (arr[num-1][2] != arr[num][6]) {
				if (!visited[num-1]) {
					visited[num-1] = true;
					rotList.add(new int[] {num-1, dir*(-1)});
					check(num-1, dir*(-1), rotList);
				}
			}
			if (arr[num+1][6] != arr[num][2]) {
				if (!visited[num+1]) {
					visited[num+1] = true;
					rotList.add(new int[] {num+1, dir*(-1)});
					check(num+1, dir*(-1), rotList);
				}
			}
		}
	}
	private static void rotate(int num) {
		int[] temp = new int[8];
		for (int i=0;i<8;i++) {
			if (i == 7) {
				temp[0] = arr[num][i];
			} else {
				temp[i+1] = arr[num][i];				
			}
		}
		for (int i=0;i<8;i++) {
			arr[num][i] = temp[i];
		}
	}
	private static void rotateReverse(int num) {
		int[] temp = new int[8];
		for (int i=0;i<8;i++) {
			if (i == 0) {
				temp[7] = arr[num][i];
			} else {
				temp[i-1] = arr[num][i];				
			}
		}
		for (int i=0;i<8;i++) {
			arr[num][i] = temp[i];
		}
	}

}
