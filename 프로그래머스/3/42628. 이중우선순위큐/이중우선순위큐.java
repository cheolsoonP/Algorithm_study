import java.io.*; 
import java.util.*; 

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Comparator.reverseOrder()); 
        PriorityQueue<Integer> minQueue = new PriorityQueue<>(); 
        Map<Integer, Integer> numCnt = new HashMap<>(); // num, cnt 
        
        StringTokenizer st; 
        for (String str : operations) {
            st = new StringTokenizer(str); 
            String cmd = st.nextToken(); 
            int num = Integer.parseInt(st.nextToken());

            if (cmd.equals("I")) {
                // 값 추가 
                maxQueue.add(num);
                minQueue.add(num);
                numCnt.put(num, numCnt.getOrDefault(num, 0)+1);
            } else if (cmd.equals("D")) {
                if (num == 1) {
                    // 최대값 삭제
                    int maxNum = -1; 
                    while (true) {
                        if (maxQueue.size() == 0) break;
                        maxNum = maxQueue.peek();
                        if (numCnt.get(maxNum) == 0) {
                            maxQueue.poll();
                        } else {
                            break; 
                        }
                    }
                    if (maxQueue.size() == 0) continue;
                    maxQueue.poll();
                    numCnt.put(maxNum, numCnt.get(maxNum)-1);
                } else {
                    // 최솟값 삭제 
                    int minNum = -1;
                    while (true) {
                        if (minQueue.size() == 0) break;
                        minNum = minQueue.peek();
                        if (numCnt.get(minNum) == 0) {
                            minQueue.poll();
                        } else {
                            break; 
                        }
                    }
                    if (minQueue.size() == 0) continue;
                    minQueue.poll();
                    numCnt.put(minNum, numCnt.get(minNum)-1);
                }
            }
        }
        
        while(true) {
            int num;
            if (minQueue.size() > 0) {
                num = minQueue.peek();
                if (numCnt.get(num) == 0) {
                    minQueue.poll();
                } else {
                    break; 
                }
            } else {
                break; 
            }
        }
        while(true) {
            int num;
            if (maxQueue.size() > 0) {
                num = maxQueue.peek();
                if (numCnt.get(num) == 0) {
                    maxQueue.poll();
                } else {
                    break; 
                }
            } else {
                break; 
            }
        }
        
        int[] res = new int[2]; 
        if (maxQueue.size() == 0) {
            res[0] = 0;
        } else {
            res[0] = maxQueue.peek(); 
        }
        if (minQueue.size() == 0) {
            res[1] = 0;
        } else {
            res[1] = minQueue.peek(); 
        }
        
        return res; 
    }
}