import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_SWEA_4008_숫자만들기 {
	static int n;
	static int[] operation;
	static int[] nums;
	static int minValue;
	static int maxValue;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			minValue = Integer.MAX_VALUE;
			maxValue = Integer.MIN_VALUE;
			operation = new int[4];
			n = Integer.parseInt(in.readLine());
			nums = new int[n];
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < 4; i++) {
				operation[i] = Integer.parseInt(st.nextToken());
			} // 각 연산자의 개수 입력 받기
			
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			} // 숫자 입력 받기
			
			// 연산자 조합 구하기
			chooseOperation(0, new int[n-1]);
			
			sb.append("#").append(testCase).append(" ");
			sb.append(maxValue-minValue).append('\n');
		}
		System.out.println(sb);
		
	}
	
	private static void chooseOperation(int idx, int[] operSelec) {
		if (idx == n-1) {
			int result = calc(operSelec);
			minValue = Math.min(result, minValue);
			maxValue = Math.max(result, maxValue);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if (operation[i] > 0) {// 연산자가 있을때
				operSelec[idx] = i; // 이번 순서의 연산자 넣어주기
				operation[i] -= 1;//사용하기
				chooseOperation(idx+1, operSelec);
				operation[i] += 1;//사용취소
				// 연산자를 사용하지 않고 수식이 완성되는 경우 X 
			}
		}
	}
	
	private static int calc(int[] operSelec) {
		int num = nums[0];
		for (int n = 0; n < operSelec.length; n++) {
			int oper = operSelec[n];
			if (oper == 0) {
				num+=nums[n+1];
			}else if(oper==1) {
				num-=nums[n+1];
			}else if(oper==2) {
				num*=nums[n+1];
			}else if(oper==3) {
				num/=nums[n+1];
			}
		}
		return num;
	}
}
