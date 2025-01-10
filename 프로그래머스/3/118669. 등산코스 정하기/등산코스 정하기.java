import java.util.*;
import java.io.*; 

class Edge implements Comparable<Edge> {
    int from; 
    int to; 
    int weight; 
    
    Edge(int from, int to, int weight) {
        this.from = from; 
        this.to = to; 
        this.weight = weight; 
    }
    
    public int compareTo(Edge o) {
        if (this.weight == o.weight) {
            return this.to - o.to; 
        }
        return this.weight - o.weight; 
    }
}

class Solution {
    static List<Edge>[] graph;
    static Set<Integer> start;
    static Set<Integer> end;
    static int[] dist; 
    static int minSummit; 
    static int minIntensity; 
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        graph = new List[n+1];
        start = new HashSet<>();
        end = new HashSet<>();
        dist = new int[n+1];
        minSummit = Integer.MAX_VALUE; 
        minIntensity = Integer.MAX_VALUE; 
            
        for (int i=0;i<=n;i++){
            graph[i] = new ArrayList<>();
        }
        
        for (int gate : gates) {
            start.add(gate);
        }

        for (int summit : summits) {
            end.add(summit);
        }
        
        for (int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int weight = path[2];
            
            if (!start.contains(from) && !end.contains(to)) {
                graph[to].add(new Edge(to, from, weight));
            }
            if (!start.contains(to) && !end.contains(from)) {
                graph[from].add(new Edge(from, to, weight));
            }
        }

        findRoute(); 
        
        return new int[]{minSummit, minIntensity};
        
    }
    
    private static void findRoute() {
        Arrays.fill(dist, Integer.MAX_VALUE); 
        
        PriorityQueue<Edge> pq = new PriorityQueue<>(); 
        for (int startPoint : start) {
            for (Edge edge : graph[startPoint]) {
                pq.add(edge); 
            }
        }
        
        int max = Integer.MAX_VALUE;
		while(!pq.isEmpty()){
			Edge cur = pq.poll();
            if(cur.weight > max) break;
			dist[cur.to] = cur.weight;

			if(end.contains(cur.to)){
				for(int value : dist){
					if(value>max 
                       && value != Integer.MAX_VALUE || max == Integer.MAX_VALUE)
						max = value;
				}
				minSummit = Math.min(minSummit, cur.to);
                minIntensity = max;
			}

			for(int i = 0; i < graph[cur.to].size(); ++i){
				Edge next = graph[cur.to].get(i);

				if(dist[next.to] == Integer.MAX_VALUE) {
					pq.add(next);
				}
			}
		}
    }
}