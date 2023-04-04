import java.util.*;
import java.io.*;

public class Main {
	static char text[], pattern[];
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		text = in.readLine().toCharArray();
		pattern = in.readLine().toCharArray();
		
		int tLength = text.length, pLength = pattern.length;
		// 부분일치테이블 만들기 : KMP의 아이디어를 똑같이 적용, W에서 W를 찾는 듯한 행위를 해서...
		int pi[] = new int[pLength];
		for(int i=1,j=0;i<pLength;i++) {
			// j접두사와 i접미사가 일치하는 최대 길이로 계산하여 작성
			// i:접미사 포인터(i=1부터 시작: 우리는 부분일치테이블를 만드는게 목적이므로 첫글자 틀리면 0위치로 가야하므로.), j:접두사 포인터
			while(j>0 && pattern[i] != pattern[j]) j = pi[j-1];
			
			if(pattern[i] == pattern[j]) pi[i] = ++j;
			else pi[i] = 0;
		}
		
		int cnt = 0;
		ArrayList<Integer> list = new ArrayList<>();
		// i : 텍스트 포인터 , j: 패턴 포인터 
		for(int i=0, j=0;i<tLength;i++) {
			while(j > 0 && text[i] != pattern[j]) j = pi[j-1];
			if(text[i] == pattern[j]) {
				if(j == pLength-1) {
					cnt++;
					list.add(i-j+1);
					j = pi[j];
				}else {
					j++;
				}
			}
		}
		
		sb.append(cnt+"\n");
		for(int num:list) {
			sb.append(num+" ");
		}
		System.out.println(sb);
	}
}
