import java.util.*; 
import java.io.*; 

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 

		int N = Integer.parseInt(in.readLine());
		int[] A = new int[N]; 
		int[] DP = new int[N]; // 최장 길이 저장 
		int[] before = new int[N]; // 이전 index 저장 
		int maxValue = 1; // 최대 길이 갱신여부 확인용 
		int startIndex = 0; // 최대 길이를 가지는 시작 지점 
		st = new StringTokenizer(in.readLine());
		for (int i=0;i<N;i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.fill(DP, 1); // 길이 1로 초기화 
		Arrays.fill(before, -1); // 이전값 -1 로 초기화 
		
		for (int i=0;i<N;i++) {
			if (i==0) continue; 
			for (int j=0;j<i;j++) {
				if (A[j] < A[i]) {
					if (DP[i] < DP[j]+1) {
						DP[i] = DP[j]+1;
						before[i] = j;
					}
				}
			}
			
			if (DP[i] > maxValue) {
				startIndex = i;
				maxValue = DP[i];
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(maxValue+"\n");
		int next = startIndex; 
		List<Integer> arr = new ArrayList<>(); 
		while (true) {
			arr.add(A[next]);
			if (before[next] == -1) break;
			next = before[next];
		}		
		
		for (int i=arr.size()-1; i>=0; i--) {
			sb.append(arr.get(i)+" ");
		}
		System.out.println(sb.toString());
	}
}
