import java.util.*;
import java.io.*;

public class Main {
	static int[][] D; 
	static int N;
	static int[] score;
	
	public static void main(String[] args) throws Exception {
		/*
		 * 
		 * N O = N-1 O & N-2 X 
		 * D[i][j] = 현재까지 j개의 계단을 연속해서 밟고 i번째 계단까지 올라섰을 때 점수 합의 최댓값, 
		 * 단 i번째 계단은 반드시 밟아야 함 
		 * D[k][1] = max(D[k-2][1], D[k-2][2]) + S[k] (현재까지 1개의 계단만 밟을 때)
		 * D[k][2] = D[k-1][1] + S[k] (현재까지 연속 2개 계단 밟을때 = 이전에 1개만 밟을 때) 
		*/
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		D = new int[N+1][3];
		score = new int[N+1];
		
		for (int i=1;i<=N;i++) {
			score[i] = Integer.parseInt(in.readLine());
		}
		D[1][1] = score[1];
		D[1][2] = 0;
		if (N == 1) {
			System.out.println(score[1]);
			return;
		}		
		D[2][1] = score[2];
		D[2][2] = D[1][1]+score[2];
		if (N == 2) {
			System.out.println(D[1][1]+score[2]);
			return;
		}
		for (int i=3;i<=N;i++) {
			D[i][1] = Math.max(D[i-2][1], D[i-2][2]) + score[i];
			D[i][2] = D[i-1][1] + score[i];
		}
		
		System.out.println(Math.max(Math.max(D[N][0], D[N][1]), D[N][2]));
	}
}
