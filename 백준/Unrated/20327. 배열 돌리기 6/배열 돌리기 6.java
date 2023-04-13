import java.util.*;
import java.io.*;

public class Main {
    static int N, R, graph[][], newGraph[][];
    
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        N = (int) Math.pow(2, N);

        graph = new int[N][N];
        newGraph = new int[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(in.readLine());
            for(int j=0;j<N;j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                newGraph[i][j] = graph[i][j];
            }
        }
        
        
        for(int i=0;i<R;i++) {
            st = new StringTokenizer(in.readLine());
            int command = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            int size = (int)Math.pow(2, level);
            switch (command) {
            // 1~4번은 각 부분배열을 움직인다.
            case 1:
                flipPartTopBottom(size);
                break;
            case 2:
                flipPartLeftRight(size);
                break;
            case 3:
                rotatePartRight(size);
                break;
            case 4:
                rotatePartLeft(size);
                break;
            // 5~8번은 부분 배열을 한칸으로 생각하고 적용시킨다. 
            case 5:
                flipTopBottom(size);
                break;
            case 6:
                flipLeftRight(size);
                break;
            case 7:
                rotateRight(size);
                break;
            case 8:
                rotateLeft(size);
                break;
            default:
                break;
            }
        }
        
        for(int i=0;i<N;i++) {
        	for(int j=0;j<N;j++)
        		sb.append(graph[i][j]+" ");
        	sb.append("\n");
        }
        System.out.println(sb);
    }
    
    private static void printGraph() {
        for(int i=0;i<N;i++) {
            System.out.println(Arrays.toString(graph[i]));
        }
    }

    private static void flipPartTopBottom(int size) {
        for(int i=0;i<N;i+=size) {
            for(int j=0;j<N;j+=size) {
                for(int x=0;x<size;x++) {
                    newGraph[x+i] = Arrays.copyOf(graph[i+size-1-x], graph[i].length);
                }
            }
        }
        copy();
    }

    private static void copy() {
        for(int i=0;i<N;i++) {
            graph[i] = Arrays.copyOf(newGraph[i], newGraph[i].length);
        }
    }

    private static void flipPartLeftRight(int size) {
        for(int i=0;i<N;i+=size) {
            for(int j=0;j<N;j+=size) {
                for(int x=0;x<size;x++) {
                    for(int y=0;y<size;y++) {
                        newGraph[x+i][y+j] = graph[x+i][j+size-1-y];
                    }
                }
            }
        }
        copy();
    }

    private static void rotatePartRight(int size) {
        for(int i=0;i<N;i+=size) {
            for(int j=0;j<N;j+=size) {
                for(int x=0;x<size;x++) {
                    for(int y=0;y<size;y++) {
                        newGraph[x+i][j+size-1-y] = graph[y+i][x+j];
                    }
                }
            }
        }
        copy();
    }

    private static void rotatePartLeft(int size) {
        for(int i=0;i<N;i+=size) {
            for(int j=0;j<N;j+=size) {
                for(int x=0;x<size;x++) {
                    for(int y=0;y<size;y++) {
                        newGraph[x+i][y+j] = graph[y+i][j+size-1-x];
                    }
                }
            }
        }
        copy();
    }

    private static void flipTopBottom(int size) {
        for(int i=0;i<N;i+=size) {
            for(int x=0;x<size;x++) {
            	newGraph[i+x] = Arrays.copyOf(graph[N-(size)-i+x], graph[i].length);
            }
        }
        copy();
    }

    private static void flipLeftRight(int size) {
    	for(int i=0;i<N;i+=size) {
    		for(int j=0;j<N;j+=size) {
                for(int x=0;x<size;x++) {
                	for(int y=0;y<size;y++) {
                		newGraph[i+x][j+y] = graph[i+x][N-size-j+y];
                	}
                }    			
    		}
        }
        copy();
    }

    private static void rotateRight(int size) {
        // 전체를 시계로 90도 -> 부분 배열을 반시계로 90도 돌리면 된다. 
    	rotatePartRight(N);
    	rotatePartLeft(size);
    }

    private static void rotateLeft(int size) {
        // 전체를 반시계로 90도 -> 부분 배열을 시계로 90도 돌리면 된다. 
    	rotatePartLeft(N);
    	rotatePartRight(size);
    }
}
