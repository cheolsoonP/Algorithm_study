import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_1759_암호만들기 {
	static int L, C;
	static char arr[];
	static Set<Character> aeiou;
	static boolean visited[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		visited = new boolean[C];		
		st = new StringTokenizer(in.readLine());
		arr = new char[C];
		aeiou = new HashSet<>();
		aeiou.add('a');aeiou.add('e');aeiou.add('i');
		aeiou.add('o');aeiou.add('u');
				
		for (int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(arr);

		dfs(0, 0);
		System.out.println(sb);
	}

	private static void dfs(int start, int len) {
		if (len == L) {
			char []res = new char[L];
			int idx=0;
			int cnt = 0;
			int cnt2 = 0;
			for(int i=0; i<arr.length;i++) {
				// 최소 한개 모음, 최소 두개 자음
				if(visited[i] == true) {
					res[idx++]=arr[i];
					if(aeiou.contains(arr[i])) cnt++;
					else cnt2++;
				}
			}
			if(cnt>=1 && cnt2>=2) {
				sb.append(res).append('\n');
			}
			return;
		}		
		for (int i = start; i < arr.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(i+1, len+1);
				visited[i] = false;
			}
		}
	}
	
	
	

}
