import java.util.*; 
import java.io.*; 

class Solution {
    public int solution(String[][] clothes) {
        /*
        매일 다른 옷 입는거 좋아함 
        각 종류별 1가지 의상만 착용 가능 
        의상을 하루에 최소 한개 입는다 
        각 종류별로 0 or 1 개 입기 가능 
        서로 다른 옷의 조합의 수 
        [의상 이름, 의상 종류]
        
        */
        Map<String, Integer> map = new HashMap<>();
        for (String[] cl : clothes) {
            String name = cl[0];
            String type = cl[1]; 
            map.put(type, map.getOrDefault(type, 0)+1);
        }
        
        int result = 1;
        for (String type : map.keySet()) {
            result *= (map.get(type)+1); 
        }
        return result - 1;
    }
}