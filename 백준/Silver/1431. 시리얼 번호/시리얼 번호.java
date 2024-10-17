import java.io.*;
import java.util.*;

public class Main {
	
	static class Serial implements Comparable<Serial> {
		char[] nums;
		int sum;
		/*
		 * 1. 시리얼 번호 짧은거 우선 먼저 
		 * 2. 같은 길이일 경우 모든 자리수의 합과, B의 모든 자리수의 합 작은 것이 앞 (숫자만 더함) 
		 * 3. 사전 순으로 비교 
		 * */
		public Serial(char[] nums) {
			this.nums = nums;
			for (char num : nums) {
				if (num >='0' && num <= '9') this.sum += num - '0';
			}
		}
		
		@Override
		public int compareTo(Serial o) {
			if (this.nums.length == o.nums.length) {
				if (this.sum == o.sum) {
					for (int i=0;i<this.nums.length;i++) {
						if (this.nums[i] == o.nums[i]) continue;
						return this.nums[i] - o.nums[i];
					}
				}
				return this.sum - o.sum;
			}
			return this.nums.length - o.nums.length;
		}
	}
	
	static int N;
	static List<Serial> serials;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		serials = new ArrayList<>();
		
		
		for (int i=0;i<N;i++) {
			char[] nums = in.readLine().toCharArray();
			Serial serial = new Serial(nums);
			serials.add(serial);
		}
		
		Collections.sort(serials);
		
		for (int i=0;i<N;i++) {
			System.out.println(serials.get(i).nums);
		}
	}

}
