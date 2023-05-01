import java.io.*;
import java.util.*;

public class Main {
	static char[][] graph = new char[5][15];
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<5;i++)
			Arrays.fill(graph[i], '.');
		for(int i=0;i<5;i++) {
			char[] temp = in.readLine().toCharArray();
			for(int j=0;j<temp.length;j++) {
				graph[i][j] = temp[j];
			}
		}
		for(int i=0;i<15;i++) {
			for(int j=0;j<5;j++) {
				if(graph[j][i] == '.') continue;
				sb.append(graph[j][i]);
			}
		}
		
		System.out.println(sb);
	}
}
