import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2961_도영이가만든맛있는음식2 {
	
	static int N;
	static int[][] taste;
	static boolean[] isSelected;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		taste = new int[N][2];
		isSelected = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			taste[i][0] = Integer.parseInt(st.nextToken());
			taste[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// n개 재료 중에 r개의 재료를 선택
		choice(0);
		System.out.println(answer);
	}

	
	private static void choice(int idx) {
		if (idx == N) {
			// 선택된 재료들의 신맛, 쓴맛 차 구하기
			int sourSum = 1;
			int bitterSum = 0;
			for (int i = 0; i < isSelected.length; i++) {
				if (isSelected[i] == true) {
					sourSum *= taste[i][0]; // 신맛의 곱
					bitterSum += taste[i][1]; // 쓴맛의 합
				}
			}
			if (sourSum*bitterSum == 0) {
				return;
			}
			
			answer = Math.min(answer, Math.abs(sourSum-bitterSum));
			return;
		}
		
		// 현재 재료 선택하고 다음 재료 선택
		isSelected[idx] = true;
		choice(idx+1);
		// 현재 재료 선택하지 않고 다음 재료 선택
		isSelected[idx] = false;
		choice(idx+1);
	}
	
}
