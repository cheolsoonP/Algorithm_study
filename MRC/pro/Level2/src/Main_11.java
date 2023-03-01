import java.util.Scanner;

public class Main_11 {

	public static void main(String[] args) {
		int a=0;
		int b=0;
		int c=0;
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		a = sc.nextInt();
		b = sc.nextInt();
		c = sc.nextInt();
		
		if(a+b+c >= a*b*c) {
			System.out.println("행운의 수");
		}else {
			System.out.println("소소한 수");
		}
	}

}
