import java.util.*; 
import java.io.*; 

class Solution {
    public int[] solution(int[] prices) {
        int[] arr = new int[prices.length];
        Stack<int[]> stack = new Stack<>(); // price, index 
        for (int i=0;i<prices.length; i++){
            int price = prices[i];
            
            while(true) {
                if (stack.empty() || stack.peek()[0] <= price) {
                    break; 
                }
                int[] temp = stack.pop();
                arr[temp[1]] = i - temp[1]; 
            }
            stack.push(new int[]{prices[i], i}); 
        }
        
        while (!stack.empty()) {
            int[] temp = stack.pop();
            arr[temp[1]] = prices.length-1 - temp[1];
        }
        
        return arr; 
    }
}