import java.io.*;
import java.util.*;

/*
 * 노트북 위쪽부터 채워나간다. 
 * 왼쪽부터 채운다. 
 * 붙일 수 있는 위치가 없으면 시계방향 90도 회전, 하고 다시 위치 찾는다. 
 * 4번 회전 가능함. (그래도 없으면 버린다.) 
 * 차례로 붙였을 때 스티커가 붙은 칸 출력 
 * */
public class Main {
	
	static int N, M, K; 
		
	static int[][] notebook;
	static int cnt; 

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		notebook = new int[N][M];
		
		for (int i=0;i<K;i++) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int sticker[][] = new int[x][y];
			
			//System.out.println("## STICKER "+(i+1));
			for (int a=0;a<x;a++) {
				st = new StringTokenizer(in.readLine());
				for (int b=0;b<y;b++) {
					sticker[a][b] = Integer.parseInt(st.nextToken());
				}
			}
			
			// play 
			play(sticker, x,y);
		}
		
		checkSpace();
		System.out.println(cnt);
		
	}

	private static void checkSpace() {
		for (int i=0;i<N;i++) {
			for (int j=0;j<M;j++) {
				if (notebook[i][j] == 1) cnt++;
			}
		}
	}

	private static void play(int[][] sticker, int x, int y) {
		int[][] rotSticker = new int[x][y];
		for (int i=0;i<x;i++) {
			rotSticker[i] = sticker[i].clone();
		}// copy 
		
		// check x,y
		for (int rot=0;rot<4;rot++) {
			rotSticker = rotation(rotSticker, rot, rotSticker.length, rotSticker[0].length);
			//printSticker(rotSticker, rot);
			for (int i=0;i<N;i++) {
				for (int j=0;j<M;j++) {
					// 시작점 
					if (checkPossible(i,j,rotSticker.length,rotSticker[0].length,rotSticker)) {
						//printNotebook();
						return; // 붙이고 종료
					}
				}
			}
		}
	}

	private static void printNotebook() {
		System.out.println("**PUT!! NOTEBOOK");
		for (int i=0;i<N;i++) {
			System.out.println(Arrays.toString(notebook[i]));
		}
	}

	private static void printSticker(int[][] sticker, int rot) {
		System.out.println("******"+rot);
		for (int i=0;i<sticker.length;i++) {
			System.out.println(Arrays.toString(sticker[i]));
		}
	}

	private static int[][] rotation(int[][] sticker, int rot, int x, int y) {
		if (rot == 0) {
			return sticker;
		}
		
		int[][] tempSticker;
		tempSticker = new int[y][x];
		for (int i=0;i<x;i++) {
			for (int j=0;j<y;j++) {
				tempSticker[j][x-i-1] = sticker[i][j];
			}
		}
		return tempSticker;
	}

	private static boolean checkPossible(int i, int j, int x, int y, int[][] sticker) {
		// TODO Auto-generated method stub
		for (int a=0;a<x;a++) {
			for (int b=0;b<y;b++) {
				if (a+i >= N || b+j >= M) return false;
				if (sticker[a][b] == 1 && notebook[a+i][b+j] == 1) {
					// 붙일 수 없음 
					return false;
				}
			}
		}
		
		
		for (int a=0;a<x;a++) {
			for (int b=0;b<y;b++) {
				if (sticker[a][b]==1 && notebook[a+i][b+j]==0) {
					notebook[a+i][b+j] = 1;
				}
			}
		}
		
		return true;
	}
	
	
}