
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_6987_월드컵_그리디 {
	static int score[][], currScore[][], res, gameN;
	static StringBuilder sb = new StringBuilder();
	static List<Integer[]> teams = new ArrayList<>();
	static boolean isGame;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 순서 없이 2개의 팀을 구한다.
		couple();
		
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(in.readLine());
			score = new int[6][3];
			currScore = new int[6][3];
			res = 0;
			isGame = true;

			for (int j = 0; j < 6; j++) {
				gameN = 0;
				for (int k = 0; k < 3; k++) {
					score[j][k] = Integer.parseInt(st.nextToken());
					gameN += score[j][k];
				}
				if (gameN != 5) isGame = false;
			}
			// 2개의 팀의 경기 결과는 승 - 패 or 무 무 or 패 - 승
			if (isGame) match(0);
			
			sb.append(res).append(" ");
		}
		System.out.println(sb);
	}

	private static void couple() {
		for (int i = 0; i < 5; i++) {
			for (int j = i+1; j < 6; j++) {
				Integer temp[] = {i, j};
				teams.add(temp);
			}
		}
	}

	private static void match(int seq) {
		if (seq == 15) {
			res = 1;
			return;
		}
		
		int teamA = teams.get(seq)[0];
		int teamB = teams.get(seq)[1];
		if (score[teamA][0] > 0 && score[teamB][2] > 0) {
			// 승 - 패
			score[teamA][0]--;
			score[teamB][2]--;
			match(seq + 1);
			score[teamA][0]++;
			score[teamB][2]++;
		}
		if (score[teamA][1] > 0 && score[teamB][1] > 0) {
			// 무 - 무
			score[teamA][1]--;
			score[teamB][1]--;
			match(seq + 1);
			score[teamA][1]++;
			score[teamB][1]++;
		}
		if (score[teamA][2] > 0 && score[teamB][0] > 0) {				
			// 패 - 승
			score[teamA][2]--;
			score[teamB][0]--;
			match(seq + 1);
			score[teamA][2]++;
			score[teamB][0]++;
		}
		
	}

}
