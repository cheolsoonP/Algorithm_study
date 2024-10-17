import java.util.*;
import java.io.*;


public class Main {
	static int n;
	static int[] arr;
	static int[] tmp; // merge 함수에서 리스트 2개를 합친 결과를 임시로 저장하고 있을 변수
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(in.readLine());
		arr = new int[n];
		tmp = new int[n];
		for (int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(in.readLine());
		}
		
		merge_sort(0, n);
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<n;i++) {
			sb.append(arr[i]+"\n");
		}
		System.out.println(sb.toString());
	}
	
	// mid = (st+en)/2라고 할 때 arr[st:mid], arr[mid:en]은 이미 정렬이 되어있는 상태일 때 arr[st:mid]와 arr[mid:en]을 합친다.
	private static void merge(int st, int en){
		int mid = (st+en)/2;
		
		int idx1, idx2;
		idx1 = st;
		idx2 = mid;

		for (int i=st;i<en;i++) {
			if (idx2 == en) tmp[i] = arr[idx1++];
			else if (idx1 == mid) tmp[i] = arr[idx2++];
			else if (arr[idx1] <= arr[idx2]) tmp[i] = arr[idx1++];
			else tmp[i] = arr[idx2++];
		}
		for (int i=st;i<en;i++) {
			arr[i] = tmp[i];
		}
	}

	// arr[st:en]을 정렬하고 싶다.
	private static void merge_sort(int st, int en){
	  if(en-st <= 1) {
		  return; // 길이 1인 경우
	  }
	  int mid = (st+en)/2;
	  merge_sort(st, mid); // arr[st:mid]을 정렬한다.
	  merge_sort(mid, en); // arr[mid:en]을 정렬한다.
	  merge(st, en); // arr[st:mid]와 arr[mid:en]을 합친다.
	}
}
