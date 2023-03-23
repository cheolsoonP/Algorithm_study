import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		/*
		 * 1 - 2~7 - 8~19 
		 * 1 1+6 1+6+12 1+6+12+18+...
		 * 1
		 * 37
		 * */
		int ans = 1;
		int i = 0;
		while(true) {
			ans += i*6;
			if(ans >= n) break;
			i++;
		}
		
		System.out.println(i+1);
	}

}
