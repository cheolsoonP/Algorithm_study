import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int hour = sc.nextInt();
		int m = sc.nextInt();
		int need = sc.nextInt();
		
		int addHour = need / 60;
		int addM = need % 60;
		
		m += addM;
		hour += addHour;
		if(m >= 60) {
			hour += 1;
			m -= 60;
		}
		if(hour >= 24) {
			hour -= 24;
		}
		
		System.out.println(hour + " " + m);
	}

}
