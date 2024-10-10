import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[] init = in.readLine().toCharArray();
		List<Character> list = new LinkedList<>();
		for (char c : init) {
			list.add(c);
		}
		int cursor = list.size();
		
		int M = Integer.parseInt(in.readLine());
		StringTokenizer st = null;
		
		char cmd;
		
		ListIterator<Character> listIterator = list.listIterator(list.size());
		
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(in.readLine());
			cmd = st.nextToken().charAt(0);
			if (cmd == 'P') {
				listIterator.add(st.nextToken().charAt(0));
			} else if (cmd == 'L') {
				if (listIterator.hasPrevious()) listIterator.previous();
			} else if (cmd == 'D') {
				if (listIterator.hasNext()) listIterator.next();	
			} else if (cmd == 'B') {
				if (listIterator.hasPrevious()) {
					listIterator.previous();
					listIterator.remove();
				}
			}
//			System.out.println(cursor);
//			System.out.println(list.toString());
		}
		StringBuilder sb = new StringBuilder();
		for (char c : list) {
			sb.append(c);
		}
		System.out.println(sb.toString());
		
	}
}
