import java.util.Scanner;

public class Main_10 {

	public static void main(String[] args) {
		int a=0;
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		a = sc.nextInt();
		
		if(a>0) {
			System.out.println("###");
			System.out.println("###");
			System.out.println("###");
		}else if(a<0) {
			System.out.println("$$$");
			System.out.println("$$$");
		}else {
			System.out.println();
		}
	}

}
