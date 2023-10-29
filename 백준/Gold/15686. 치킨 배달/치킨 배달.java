import java.io.*;
import java.util.*;
 
public class Main {
	static int N, M ;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int map[][];
    static List<int[]> person;
	static List<int[]> chicken;
	static int result;
	static boolean isOpen[];
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		result = Integer.MAX_VALUE;
		chicken = new ArrayList<>();
		person = new ArrayList<>();
		
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					chicken.add(new int[] {i, j});
				}
				if(map[i][j] == 1) {
					person.add(new int[] {i, j});
				}
			}
		}
		
		isOpen = new boolean[chicken.size()];

		
		dfs(0,0);
		
		System.out.println(result);
		
	}


	private static void dfs(int start, int cnt) {
		
		if(cnt == M) {
			int res = 0;
			
			for(int i=0;i<person.size();i++) {
				int temp = Integer.MAX_VALUE;
				int px = person.get(i)[0];
				int py = person.get(i)[1];
				
				for(int j=0;j<chicken.size();j++) {
					if(isOpen[j] == false) continue;
					int cx = chicken.get(j)[0];
					int cy = chicken.get(j)[1];
					
					temp = Math.min(Math.abs(cx - px) + Math.abs(cy - py),temp);
				}
				
				res += temp;
			}			
			
			result = Math.min(result, res);
            return;
		}
		
		for(int i=start;i<chicken.size();i++) {
			isOpen[i] = true;
			dfs(i+1, cnt+1);
			isOpen[i] = false;
		}
	}
}
