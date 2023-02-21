import java.util.Scanner;

public class Main_3040_백설공주와일곱난쟁이 {
	static boolean isUsed[] = new boolean[9];
	static int nums[] = new int[9];
	public static void main(String[] args) {
		/*
		 * 의자 7 접시 7 나이프 7개
		 * 모자에 100보다 작은 양의 정수
		 * 모자 합이 100이 되도록
		 * 
		 * */
		Scanner sc =  new Scanner(System.in);
		
		for (int i = 0; i < 9; i++) {
			nums[i] = sc.nextInt();
		}
		
		dfs(0, 0, 0);
	}
	private static void dfs(int idx, int cnt, int curr) {
		if (cnt == 7) {
			if (curr == 100) {
				for (int i = 0; i < 9; i++) {
					if (isUsed[i] == true) {
						System.out.println(nums[i]);
					}
				}
			}
			return;
		}
		
		if (idx < 9) {
			isUsed[idx] = true;
			dfs(idx+1, cnt+1, curr+nums[idx]);
			isUsed[idx] = false;
			dfs(idx+1, cnt, curr);			
		}
	}

}
