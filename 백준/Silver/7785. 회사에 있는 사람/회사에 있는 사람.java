import java.io.*;
import java.util.*;

public class Main {
	static Set<String> userSet;	
	static List<String> userList;
	static int N; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		userSet = new HashSet<>();
		userList = new ArrayList<>(); 
		
		StringTokenizer st;
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine());
			String name = st.nextToken();
			String action = st.nextToken();
			if (action.equals("enter")) {
				userSet.add(name);
			} else {
				userSet.remove(name);
			}
		}
		
		for (String user : userSet) {
			userList.add(user);
		}
		Collections.sort(userList, Collections.reverseOrder());
		
		for (String user : userList) {
			System.out.println(user);
		}
	}
}
