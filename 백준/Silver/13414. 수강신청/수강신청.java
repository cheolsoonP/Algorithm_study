import java.util.*;
import java.io.*;

public class Main {
	static int K, L; 
	static List<String> waitingList; 
	static Map<String, Integer> studentMap;	
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb = new StringBuilder(); 

		st = new StringTokenizer(in.readLine());
		K = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		waitingList = new ArrayList<>(); 
		studentMap = new HashMap<>(); 
		
		for (int i=0;i<L;i++) {
			String student = in.readLine();
			waitingList.add(student);
			if (studentMap.containsKey(student)) {
				studentMap.put(student, studentMap.get(student)+1);
			} else {
				studentMap.put(student, 1);
			}
		}
		
		int cnt = 0;
		for (String student : waitingList) {
			if (studentMap.containsKey(student)) {
				
			}
			if (studentMap.get(student) == 1) {
				System.out.println(student);
				cnt++;
			} else {
				studentMap.put(student, studentMap.get(student)-1);
			}
			
			if (cnt == K) break;
		}
		
		
	}

}
