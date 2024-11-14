import java.util.*; 
import java.io.*; 

class Solution {
    public boolean solution(String[] numbers) {
        Arrays.sort(numbers); 
        // System.out.println(Arrays.toString(numbers));
        Set<String> numSet = new HashSet<>(); 
        StringBuilder sb;
        for (String num : numbers) {
            sb = new StringBuilder(); 
            for (char ch : num.toCharArray()) {
                sb.append(ch);
                if (numSet.contains(sb.toString())) {
                    return false; 
                }
            }
               
            numSet.add(num);
        }
        
        return true; 
    }
}