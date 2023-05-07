import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static long arr[];
	static long tree[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
			
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new long[N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine());
			arr[i] = Long.parseLong(st.nextToken());
		}
		tree = new long[N*4];
		init(0, N-1, 1);
		
		int a,b;
		long c;
		for(int i=0;i<M+K;i++) {
			st = new StringTokenizer(in.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Long.parseLong(st.nextToken());
			if(a == 1) {
				update(0, N-1, 1, b-1, c); // a-1번째 수를 b로 변경				
			}else {
				// b-1~c-1까지 합을 구해라.
				bw.write(query(0, N-1, 1, b-1,c-1)+"\n");
			}
		}
		bw.close();
		in.close();
	}

	private static long update(int start, int end, int node, int index, long value) {
		if(index < start || end < index) {//index가 현재 구간에 포함되지 않으면
			return tree[node];
		}
		if(start == end) { // 하나의 수만 포함할 때
			return tree[node] = value;
		}
		int mid = (start + end) / 2;
		return tree[node] = update(start, mid, node*2, index, value) + update(mid+1, end, node*2+1, index, value);
	}

	private static long query(int start, int end, int node, int left, long right) {
		if(right < start || end < left) { // 구간을 벗어난 곳이라면
			return 0; // 결과가 변하지 않는 0 반환
		}
		if(left <= start && end <= right) {// 원하는 구간이 현재 구간 전체를 포함하면 
			return tree[node];
		}
		int mid = (start+end) / 2;
		return query(start, mid, node*2, left, right) + query(mid+1, end, node*2+1, left, right);
	}

	// 트리 초기화, tree[node]는 start부터 end까지 수의 합
	private static long init(int start, int end, int node) {
		if(start == end) { // 구간이 하나의 숫자만 있을 때
			return tree[node] = arr[start];// 해당 숫자를 tree[node]에 대입
		}
		int mid = (start + end) / 2;// 현재 구간을 다른 두 구간으로 나눔
		return tree[node] = init(start, mid, node*2) + init(mid+1, end, node*2 + 1);
		// 왼쪽 구간, 오른쪽 구간으로 분할, 각각 값을 더한 값을 현재로 한다.
	}
	
	
}
