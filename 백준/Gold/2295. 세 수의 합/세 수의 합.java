import java.io.*; 
import java.util.*;

public class Main {
	static int N; 
	static int[] arr; 
	static Set<Integer> two;
	static int[] temp = new int[2];
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		arr = new int[N];
		two = new HashSet<>();
		for (int i=0; i<N;i++) {
			// a[x] + a[y] + a[z] = a[k] 
			// 
			arr[i] = Integer.parseInt(in.readLine());
		}
		
		// make two list 
		dfs(0);
		
		Arrays.sort(arr);
		
		// find 
		for (int i=N-1;i>=0;i--) {
			for (int j=N-1;j>=0;j--) {
				if(two.contains(arr[i] - arr[j])) {
					System.out.println(arr[i]);
					return;
				}
			}
			
		}	
	}

	private static void dfs(int cnt) {
		if (cnt == 2) {
			two.add(temp[0]+temp[1]);
			//System.out.println(temp[0]+", "+temp[1]);
			return; 
		}
		
		for (int i=0;i<N;i++) {
			temp[cnt] = arr[i];
			dfs(cnt+1);
		}
	}
}
