import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main_BOJ_12891_DNA비밀번호 {
	
	static int S,P,K;
	static int Ans;
	static int[] minOccurs;
	
	public static void main(String[] args) throws Exception {
		//DNA문자열 ==> 'A', 'C', 'G', 'T' 문자로만 구성된 문자열
		
		Scanner sc = new Scanner(System.in);
		S=sc.nextInt(); //DNA문자열 길이
		P=sc.nextInt(); //비밀번호로 사용할 부분문자열 길이
		String DNAStr = sc.next(); //DNA문자열
		
		minOccurs = new int[4] ; //'A', 'C', 'G', 'T'의 최소 출현횟수 저장
		
		for (int i = 0; i < 4; i++) {
			minOccurs[i]= sc.nextInt();
		}
		
		//각 문자가 0번 이상 발생할 수 있으므로 각문자 키값에 기본값 0을 입력
		Map<Character, Integer> map = new HashMap<Character, Integer>();
			map.put('A', 0);
			map.put('C', 0);
			map.put('G', 0);
			map.put('T', 0);
			
		
		for (int i = 0; i < P; i++) {
			map.put(DNAStr.charAt(i), map.get(DNAStr.charAt(i))+1);
		}
		
		
		if(check(map)) Ans++;
		
		//슬라이딩 윈도우
		for (int i=1; i+P<=S; i++) {
			char delKey = DNAStr.charAt(i-1 );   //제거할 인덱스 찾기
			char addKey = DNAStr.charAt(i-1 +P); //추가할 인덱스 찾기
			
			map.put(delKey, map.get(delKey)-1);  //제거할 문자 카운트 1감소후 수정
			map.put(addKey, map.get(addKey)+1);  //추가할 문자 카운트 1증가후 수정
			
			if(check(map)) Ans++;
		}
		System.out.println(Ans);
		sc.close();
	}//main
	
	static String dnaAlpha="ACGT";
	//최소 등장 횟수 만족 체크
	private static boolean check(Map<Character, Integer> map) {
		for (Character key : map.keySet()) {			
		   if(map.get(key) < minOccurs[dnaAlpha.indexOf(key)]) return false;
		}
		return true;
	}

}
