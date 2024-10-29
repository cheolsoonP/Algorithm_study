import java.util.*;
import java.io.*; 

/*
Ax + By + E = 0
Cx + Dy + F = 0
x = B*F - E*D / (A*D - B*C) 
y = E*C - A*
*/

class Solution {
    public String[] solution(int[][] line) {
        long minX = Long.MAX_VALUE;
        long minY = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long maxY = Long.MIN_VALUE;

        Map<Long, ArrayList<Long>> intersections = new HashMap<>();
        for(int i=0; i<line.length-1; i++) {
            for(int j=i+1; j<line.length; j++) {
                long[] inter = getIntersection(line[i], line[j]);

                if(inter == null) {
                    continue;
                }

                long x = inter[0];
                long y = inter[1];

                minX = Math.min(minX, x);
                minY = Math.min(minY, y);
                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);

                if(!intersections.containsKey(y)) {
                    intersections.put(y, new ArrayList<>());
                }

                intersections.get(y).add(x);
            }
        }

        Map<Long, String> result = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        ArrayList<Long> xs;
        for(long i=minY; i<=maxY; i++) {
            sb.setLength(0);
            xs = intersections.getOrDefault(i, null);

            for(long j=minX; j<=maxX; j++) {
                if(xs != null && xs.contains(j)) {
                    sb.append("*");
                }else {
                    sb.append(".");
                }
            }

            result.put(i, sb.toString());
        }

        String[] answer = new String[(int)(maxY - minY + 1)];
        int idx = 0;
        for(long i=maxY; i>=minY; i--) {
            answer[idx++] = result.get(i);
        }

        return answer;
    }

     public static long[] getIntersection(int[] f1, int[] f2) {
        long denominator = ((long) f1[0] * (long) f2[1]) - ((long) f2[0] * (long) f1[1]);

        if(denominator == 0) {
            return null;
        }

        long xNumerator = ((long) f1[1] * (long) f2[2]) - ((long) f1[2] * (long) f2[1]);

        if(xNumerator % denominator != 0) {
            return null;
        }

        long yNumerator = ((long) f1[2] * (long) f2[0]) - ((long) f1[0] * (long) f2[2]);

        if(yNumerator % denominator != 0) {
            return null;
        }


        return new long[] {(xNumerator/denominator), (yNumerator/denominator)};
    }
}