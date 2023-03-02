import java.util.Scanner;

public class Main_9 {

	public static void main(String[] args) {
		int a=0, b=0;
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		a = sc.nextInt();
		
		if(a > 3) System.out.println(++a);
		else System.out.println(--a);
		
	}

}
