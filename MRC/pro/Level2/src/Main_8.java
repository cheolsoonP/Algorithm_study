import java.util.Scanner;

public class Main_8 {

	public static void main(String[] args) {
		int a=0, b=0;
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		a = sc.nextInt();
		
		sb.append(a+++" 입력함").append('\n');
		sb.append("a++을 수행하면 "+a+"이 됩니다");
		
		System.out.println(sb);
	}

}
