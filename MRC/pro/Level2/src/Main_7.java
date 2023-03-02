import java.util.Scanner;

public class Main_7 {

	public static void main(String[] args) {
		int a=0, b=0;
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		a = sc.nextInt();
		b = sc.nextInt();
		
		if (a > b) {
			sb.append("a가b보다크다");
		}else {
			sb.append("b가a보다같거나크다");
		}
		
		System.out.println(sb);
	}

}
