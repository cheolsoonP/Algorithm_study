import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Solution_SWEA_1873_상호의배틀필드 {
    static int H, W, N, dir, curx,cury;
    static char[][] graph;
    static char []input;
    // 상 하 좌 우
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};
     
    public static void main(String[] args) throws Exception {
         
 
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
         
        int T = Integer.parseInt(in.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(in.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            graph = new char[H][W];
             
            for (int i = 0; i < H; i++) {
                graph[i] = in.readLine().toCharArray();
                for (int j = 0; j < W; j++) {
                	switch (graph[i][j]) {
					case '^': 
						dir = 0; curx = i; cury = j;
						graph[i][j] = '.';
						break;
					case 'v': 
						dir = 1; curx = i; cury = j;
						graph[i][j] = '.';
						break;
					case '<': 
						dir = 2; curx = i; cury = j;
						graph[i][j] = '.';
						break;
					case '>': 
						dir = 3; curx = i; cury = j;
						graph[i][j] = '.';
						break;
                	}
                }
            }
             
            N = Integer.parseInt(in.readLine());
            input = new char[N];
            input = in.readLine().toCharArray();
            for (int i = 0; i < N; i++) {
                play(input[i]);
            }
            
            switch (dir) {
			case 0:
				graph[curx][cury] = '^';
				break;
			case 1:
				graph[curx][cury] = 'v';
				break;
			case 2:
				graph[curx][cury] = '<';
				break;
			case 3:
				graph[curx][cury] = '>';
				break;
			}
            
            sb.append("#"+testCase+" ");
            for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(graph[i][j]);
				}
				sb.append("\n");
			}
        }
        System.out.println(sb);
    }
 
    private static void play(char in) {
        if(in == 'S') {
        	int nx=curx, ny=cury;
            while(true) {
                nx += dx[dir];
                ny += dy[dir];
                if (nx < 0 || nx >= H || ny < 0 || ny >= W) break;
                if (graph[nx][ny] == '*'){
                	graph[nx][ny] = '.';
                    break;
                }
                if (graph[nx][ny] == '#') break;
            }
        }else{
            if (in == 'U') {
                dir = 0;
            }else if (in == 'D') {
                dir = 1;
            }else if (in == 'L') {
                dir = 2;
            }else if (in == 'R') {
                dir = 3;
            }
            int nx = curx+dx[dir];
            int ny = cury+dy[dir];
            if (nx >= 0 && nx < H && ny >= 0 && ny < W) {
                if (graph[nx][ny] == '.') {
                    curx = nx;
                    cury = ny;
                }
            }
        }
    }
         
}