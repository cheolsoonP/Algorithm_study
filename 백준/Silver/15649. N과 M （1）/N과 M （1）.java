import java.io.InputStream;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int n;
	static int m;
	static int [] isChecked;
	static int [] arr;
	
	public static void main(String[] args) throws Exception {
        InputStream in = System.in;
		n = in.read() - '0';
		in.read();
		m = in.read() - '0';
		
		isChecked = new int[n+1];
		arr = new int[m];
		
		dfs(0);
		
		System.out.println(sb.toString());
	}
	public static void dfs(int cnt) {
		// 종료 조건
		if (cnt == m) {
			for (int i : arr)
				sb.append(i+" ");
			sb.append('\n');
			return;
		}
		
		for (int i = 1; i < n+1; i++) {
			if (isChecked[i] == 1)
				continue;
			isChecked[i] = 1;
			arr[cnt] = i;
			
			dfs(cnt+1);
			
			isChecked[i] = 0;
		}
	}
}
