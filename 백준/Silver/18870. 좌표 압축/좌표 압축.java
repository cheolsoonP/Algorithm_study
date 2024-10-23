import java.io.*;
import java.util.*;

public class Main {
	static int[] arr; 
	static int[] original; 
	static int[] arr2;
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		Set<Integer> set = new HashSet<>();
		original = new int[N];
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i=0;i<N;i++) {
			original[i] = Integer.parseInt(st.nextToken());
			set.add(original[i]);
		}
		// 1. copy 
		arr = Arrays.copyOf(original, original.length);

		// 2. sort 
		Arrays.sort(arr);
		// 3. remove duplicate 
		arr2 = new int[set.size()];
		
		int idx = 0;
		for (int i=0;i<N;i++) {
			if (i != 0 && arr[i] == arr[i-1]) continue;
			arr2[idx++] = arr[i];
		}		
		// 4. original find index 
		//System.out.println(Arrays.toString(arr2));
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<N;i++) {
			sb.append(find(original[i])+" ");
		}
		System.out.println(sb.toString());
		
		
	}
	
	private static int find(int target) {
		int start = 0;
		int end = arr2.length-1; 
		
		while (start <= end) {
			int mid = (start + end)/2;
			
			if (arr2[mid] < target) {
				start = mid + 1;
			} else if (arr2[mid] > target) {
				end = mid - 1;
			} else {
				return mid;
			}
		}
		
		return 0; 		
	}
	
	
}
