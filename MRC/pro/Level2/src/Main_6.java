import java.util.Scanner;

public class Main_6 {

	public static void main(String[] args) {
		int a=0, b=0;
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		a = sc.nextInt();
		b = sc.nextInt();
		sb.append(a).append("+").append(b).append("=").append(a+b).append('\n');
		sb.append(a).append("*").append(b).append("=").append(a*b).append('\n');
		sb.append(a).append("/").append(b).append("=").append(a/b).append('\n');
		
		System.out.println(sb);
	}

}
