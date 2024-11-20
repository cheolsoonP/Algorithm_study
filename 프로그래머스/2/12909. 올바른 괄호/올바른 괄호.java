import java.util.*;
import java.io.*; 

class Solution {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>(); 
        for (char ch : s.toCharArray()) {
            if (ch == ')') {
                if (!stack.empty() && stack.peek() == '(') {
                    stack.pop(); 
                    continue; 
                }
            }
            stack.push(ch); 
        }
        if (stack.empty()) {
            return true; 
        } else {
            return false; 
        }
    }
}