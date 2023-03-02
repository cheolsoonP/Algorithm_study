import java.util.Scanner;

public class Main_4 {

	public static void main(String[] args) {
		int a=0, b=0, c=0;
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		a = sc.nextInt();
		b = sc.nextInt();
		c = sc.nextInt();
		sb.append(a).append(a).append(a).append('\n')
			.append(b).append(b).append(b).append('\n')
			.append(c).append(c).append(c);
		System.out.println(sb);
	}

}
