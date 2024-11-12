import java.io.*;
import java.util.*;

public class Main {

	static int M;
	static int N; 
    private static List<List<Integer>> Spaces = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; 
        StringBuilder sb = new StringBuilder(); 
        
        st = new StringTokenizer(in.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        for(int i=0; i<M; i++){
            st = new StringTokenizer(in.readLine(), " ");
            List<Integer> space = new ArrayList<>();
            for(int j=0; j<N; j++){
                int planet = Integer.parseInt(st.nextToken());
                space.add(planet);
            }

            List<Integer> clone = new ArrayList<>(space);
            Collections.sort(clone);

            for(int j=0; j<N; j++){
                int idx = Collections.binarySearch(clone, space.get(j));
                space.set(j, idx);
            }
            Spaces.add(space);
        }
        int cnt = 0;
        for (int i=0;i<M-1;i++) {
        	for (int j=i+1;j<M;j++) {
        		if (Arrays.equals(Spaces.get(i).toArray(), Spaces.get(j).toArray())) {
        			cnt++;
        		}
        	}
        }
        
        System.out.println(cnt);
    }

}