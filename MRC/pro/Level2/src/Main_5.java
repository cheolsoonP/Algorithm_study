import java.util.Scanner;

public class Main_5 {

	public static void main(String[] args) {
		int a=0, b=0;
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		a = sc.nextInt();
		b = sc.nextInt();
		sb.append("두 숫자의 차는 ").append(a-b).append(" 입니다.");
		System.out.println(sb);
	}

}
