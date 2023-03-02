import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_D4_1238_Contact {

	static int N, start;
	static ArrayList<ArrayList<Integer>> contact;
	static boolean visited[];

	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = 10;
		for (int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			contact = new ArrayList<ArrayList<Integer>>();
			visited = new boolean[101];
			for (int i = 0; i <= 100; i++) {
				contact.add(new ArrayList<>());
			}
			
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < N/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				// 인원 최대 100명, 
				contact.get(from).add(to);
			}//인접리스트에 추가
			
			
			// 시작 start -> 연락 모두에게
			sb.append("#").append(testCase+" ");
			sb.append(call(start)).append('\n');
		}
		
		System.out.println(sb);
	}

	private static int call(int idx) {
		Deque<Integer[]> q = new ArrayDeque<>();
		q.offer(new Integer[] {idx, 1});
		visited[idx] = true;
		int lastMember = 0;
		int cnt = 0;
		while(!q.isEmpty()) {
			Integer curr[] = q.pollFirst();
			if(curr[1] != cnt) {
				lastMember = curr[0];
			}else if(curr[1] == cnt) {
				lastMember = Math.max(curr[0], lastMember);
			}
			cnt = curr[1];
			for (int i = 0; i < contact.get(curr[0]).size(); i++) {
				int next = contact.get(curr[0]).get(i);
				if(!visited[next]) {
					visited[next] = true;
					q.offer(new Integer[] {next, curr[1]+1});
				}
			}
		}
		return lastMember;		
	}

}
