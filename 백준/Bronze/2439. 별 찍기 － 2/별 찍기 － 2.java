import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for(int i=1;i<=N;i++) {
			for(int j=N-i;j>0;j--) {
				sb.append(" ");
			}
			for(int j=1;j<=i;j++) {
				sb.append("*");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
