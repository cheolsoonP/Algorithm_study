import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_1952_수영장 {
	/*
	 * 수영장 이용권 적은 비용으로
	 * 1일 이용권 - 1일
	 * 1달 - 1달 (매달1일 시작)
	 * 3달 - 연속 3달 동안 이용 가능
	 * 3달 이용권 - 매달 1일부터 시작
	 * 1년 이용권 - 1년동안, 매년 1월 1일부터 시작
	 * 
	 * 각 달의 이용계획
	 * -> 가장 적은 비용으로 수영장을 이용할 수 있는 방법을 찾고
	 * 비용을 출력하라. 
	 * 
	 * 모든 경우를 찾아봐야 한다. 
	 * 완전 탐색
	 * flat 하게 봐라.
	 * 
	 * 
	 * */
	// 테스트 케이스마다 초기화 고려해야한다. 
	static int[] cost;
	static int[] plan;
	static int minCost;
	
	
	public static void main(String[] args) throws Exception {
		

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(in.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			cost = new int[4]; // 0:1일, 1:1달,2:3달,3:1년
			plan = new int[12]; // 12개월 이용계획
					
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < 4; i++) {//이용권 가격입력
				cost[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < 12; i++) {//12개월 이용 계획 입력
				plan[i] = Integer.parseInt(st.nextToken());
			}
			// ----------------Input END---------------
			// 1년 이용하기 위한 최소 비용
			minCost = Integer.MAX_VALUE;
			
			// 1월달, 0
			swimmingPool(0, 0);
			
			sb.append("#")
				.append(testCase).append(" ")
				.append(minCost).append("\n");
		} // testCase
		System.out.println(sb);
	} // main
	
	// 누적 비용 전달, 누적 이용 개월수
	private static void swimmingPool(int month, int totalCost) {		
		if (month >= 12) { 
			minCost = Math.min(minCost, totalCost);
			return;
		}
		// 1일 이용권 쓸 때 
		swimmingPool(month+1, totalCost+(cost[0] * plan[month]));
		// 1달 이용권 쓸 때
		swimmingPool(month+1, totalCost+cost[1]);
		// 3달 이용권 쓸 때
		swimmingPool(month+3, totalCost+cost[2]);
		// 1년 이용권 쓸 때
		swimmingPool(month+12, totalCost+cost[3]);
	}

}
