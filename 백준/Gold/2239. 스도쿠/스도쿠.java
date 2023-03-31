
import java.io.*;
import java.util.*;

public class Main {
	  static int[][]board;
	  static int N=9,size;
	  static List<int[]> list;
	  static StringBuilder sb;
	  
	  
	public static void main(String[] args) throws Exception{
		  board = new int[N][N];
		  list = new ArrayList<>();
		  sb = new StringBuilder();

		  BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	      
		  String s;
		  //보드에 숫자입력
		  for(int i=0; i<N; i++) {
			s = in.readLine();
			for(int j=0; j<N; j++) {
			   board[i][j]=s.charAt(j)-48;
			   if(board[i][j]==0) {
				   list.add(new int[] {i,j});
			   }
			}
		  }
		  size=list.size();
		  recursive(0);
	}
	
	  static void recursive(int idx) {
			if(idx==size) {
		 	  printBoard();
			  System.exit(0);
			}
			
			int[] p = list.get(idx);
			for(int i=1; i<10; i++) {//1~9까지의 수를 한번씩 대입하여 본다
			  if(checkBoard(p[0], p[1], i)) {//대입이 가능한 숫자라고 한다면
			    board[p[0]][p[1]]=i; //빈칸에 실제 숫자대입
			    recursive(idx+1);//그 다음 빈칸 찾기
			    board[p[0]][p[1]]=0; //대입했던 숫자빼기
			  }
			}
			
	}
	  
	  static boolean checkBoard(int x, int y, int num) {
		  
			 //행열중복검사
			  for(int i=0; i<9; i++) {
				if(board[x][i]==num) return false;//(열을 늘려가며) 행의 중복 찾기 
				if(board[i][y]==num) return false;//(행을 늘려가며) 열의 중복 찾기		
			  }
			  
			  for(int i=x/3*3; i<x/3*3+3; i++) {
				  for(int j=y/3*3; j<y/3*3+3; j++) {
				    if(board[i][j]==num)return false;
				  }
			  }
			  return true;
		  }//checkBoard	  

	  static void printBoard() {
		  for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
			  sb.append(board[i][j]);
			}
			sb.append('\n');
		  }
		  System.out.print(sb.toString());
	  }//printBoard
	

}