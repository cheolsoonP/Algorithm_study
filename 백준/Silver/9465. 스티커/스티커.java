import java.util.*;
import java.io.*;

public class Main {
	/*
	 * 상근이 상냥이 2N개 구매 
	 * 2행 N열 배치
	 * 스티커로 책상 꾸민다
	 * 한장 떼면 변을 공유하는 스티커  상하좌우 연결된 스티커 사용할 수 없다
	 * 모든 스티커를 붙일 수 없게된 상냥이는 점수를 매기고
	 * 점수 합 최대가 되도록 스티커를 떼어낸다. 
	 * 2n개 스티커 중에 점수합이 최대, 서로 변을 공유하지 않는 스티커 집합을 구해야한다. 
	 * 
	 * */
	static int N, graph[][],dp[][], result;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(in.readLine());
		for(int testCase=1; testCase<=T; testCase++) {
			N = Integer.parseInt(in.readLine());
			graph = new int[2][N];
			dp = new int[3][N];
			for(int i=0;i<2;i++) {
				st = new StringTokenizer(in.readLine());
				for(int j=0;j<N;j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			/*
			 * 상 하 상 하
			 * 상 pass (상/하) 
			 * dp[] = dp[] + 
			 * 현재 하 -> Max(이전에 상을 선택했을 경우, 선택하지 않았을때 최대값) + 현재값
			 * 현재 상 -> Max(이전에 하를 선택했을 경우 , 선택하지 않았을때 최대값) + 현재값
			 * 현재 선택X -> 이전에 상, 하 중에 최대값
			 * */
			dp[0][0] = graph[0][0]; // 상
			dp[1][0] = graph[1][0]; // 하
			dp[2][0] = 0; 			// 선택X 
			for(int i=1;i<N;i++) {
				dp[0][i] = Math.max(dp[1][i-1],dp[2][i-1]) + graph[0][i];
				dp[1][i] = Math.max(dp[0][i-1],dp[2][i-1]) + graph[1][i];
				dp[2][i] = Math.max(dp[0][i-1],dp[1][i-1]);
			}
			
			result = Math.max(dp[2][N-1], Math.max(dp[0][N-1], dp[1][N-1]));
			System.out.println(result);
		}
	}

}
