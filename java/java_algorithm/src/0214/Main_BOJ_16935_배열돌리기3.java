import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_16935_배열돌리기3 {
	static int n, m, r;
	static int[][] graph, newGraph;
	static int [][][]subGraph;
	static int[] opers;
	static StringBuilder sb = new StringBuilder();

	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		graph = new int[n][m];
		opers = new int[r];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < m; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}		
		st = new StringTokenizer(in.readLine());		
		for (int i = 0; i < r; i++) {
			opers[i] = Integer.parseInt(st.nextToken());
		}
		//------ input -------------

		for (int i = 0; i < r; i++) {
			switch (opers[i]) {
			case 1:
				reverseTopDown();
				break;
			case 2:
				reverseLeftRight();
				break;
			case 3:
				rotate90();
				break;
			case 4:
				rotateRev90();
				break;
			case 5:
				moveLeft();
				break;
			case 6:
				moveRight();
				break;
			}
		}
		
		printGraph();
		
	}//main
	
	private static void swap() {
		int temp = n;
		n = m;
		m = temp;
	}
	
	private static void printGraph() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(graph[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	private static void reverseTopDown() {
		newGraph = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				newGraph[i][j] = graph[n-1-i][j];
			}
		}
		graph = newGraph;
	}
	
	private static void reverseLeftRight() {
		newGraph = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				newGraph[i][j] = graph[i][m-1-j];
			}
		}
		graph = newGraph;
	}
	
	private static void rotate90() {
		newGraph = new int[m][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				newGraph[j][n-1-i] = graph[i][j];
			}
		}
		swap();
		graph = newGraph;
	}
	
	private static void rotateRev90() {
		newGraph = new int[m][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				newGraph[m-1-j][i] = graph[i][j];
			}
		}
		swap();
		graph = newGraph;
	}
	
	private static void divGroup() {
		subGraph = new int[4][n/2][m/2];
		int dx = 0;
		int dy = 0;

		for (int g = 0; g < 4; g++) {
			if (g==1) {
				dy = m/2;
			} else if (g==2) {
				dx = n/2;
			} else if (g==3) {
				dy = 0;
			}
			for (int i = 0; i < n/2; i++) {
				for (int j = 0; j < m/2; j++) {
					subGraph[g][i][j] = graph[i+dx][j+dy];
				}
			}		
		}
	}
	
	private static void moveLeft() {
		newGraph = new int[n][m];
		int dx = 0;
		int dy = m/2;

		divGroup();
		
		for (int g = 0; g < 4; g++) {
			if (g==1) {
				dx = n/2;
			} else if (g==2) {
				dy = 0;
			} else if (g==3) {
				dx = 0;
			}
			for (int i = 0; i < n/2; i++) {
				for (int j = 0; j < m/2; j++) {
					newGraph[i+dx][j+dy] = subGraph[g][i][j];
				}
			}		
		}
		
		graph = newGraph;
	}
	
	private static void moveRight() {
		newGraph = new int[n][m];
		int dx = n/2;
		int dy = 0;

		divGroup();
		
		for (int g = 0; g < 4; g++) {
			if (g==1) {
				dx = 0;
			} else if (g==2) {
				dy = m/2;
			} else if (g==3) {
				dx = n/2;
			}
			for (int i = 0; i < n/2; i++) {
				for (int j = 0; j < m/2; j++) {
					newGraph[i+dx][j+dy] = subGraph[g][i][j];
				}
			}		
		}
		graph = newGraph;
	}
}
