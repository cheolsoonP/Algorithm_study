
public class Main_1 {

	public static void main(String[] args) {
		int cnt = 1;
		int spaceCnt = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < spaceCnt; j++) {
				sb.append(" ");
			}
			for (int j = 0; j < 4-i; j++) {
				sb.append(cnt++);
			}
			if(cnt==10) cnt = 0;
			spaceCnt++;
			sb.append('\n');
		}
		System.out.println(sb);
	}

}
