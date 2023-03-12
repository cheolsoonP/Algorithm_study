import java.io.*;
import java.util.*;

public class Main_BOJ_1541_잃어버린괄호 {
	public static void main(String[] args) throws Exception{
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		  String str = br.readLine();

		  StringTokenizer minusTokens = new StringTokenizer(str,"-");

		  int sum=0;
		  boolean checked = true;
		  
		  while(minusTokens.hasMoreTokens()) {
			  
			 String subStr = minusTokens.nextToken();
			 StringTokenizer plusTokens = new StringTokenizer(subStr,"+");
			 
			 int plusValue=0;
			 
			 while(plusTokens.hasMoreTokens()) {
				 plusValue += Integer.parseInt(plusTokens.nextToken());
			 }
			 
			 if(checked) {
				 sum = plusValue;
				 checked = false;
			 }else {
				 sum -= plusValue;
			 }
		  }
		  
		  System.out.println(sum);
		  
		  br.close();
	}//main

}
