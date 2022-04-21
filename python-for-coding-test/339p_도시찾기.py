"""
# 백준 18352
1-N 도시
M개의 단방향 도로
도로 거리는 1
도시x 부터 출발 도달할 수 있는 모든 도시 중에서 최단 거리가
정확히 K인 번호를 출력

첫째줄 도시 개수 n 도로 개수 m 거리정보 k 출발도시 번호 x
도시는 1부터 시작
m개의 줄에는 A도로 에서 B도로로 이동하는 단방향 노드
4 4 2 1
1 2
1 3
2 3
2 4

4
DFS -> 시간 초과 에러
BFS 로 문제 풀기
"""
from collections import deque

n, m, k, start = map(int, input().split())
graph = [[] for _ in range(n+1)]
for i in range(m):
    r, c = map(int, input().split())
    graph[r].append(c)


def bfs():
    """
        cost를 사용하여 비용들을 따로 만들어 넣어주니 메모리 초과가 되었다.
        앞으로 이런 변수를 하나하나 줄이는 과정, 불필요한 변수를 만들지 않아야겠다.
    """
    # cost = 1
    while queue:
        now = queue.popleft()
        # 이동할 수 있는 모든 도시 확인
        for next_node in graph[now]:
            if distance[next_node] == -1:
                distance[next_node] = distance[now] + 1
                queue.append(next_node)


# cost_map 만들기
distance = [-1 for _ in range(n+1)]
distance[start] = 0

queue = deque()
queue.append(start)

bfs()

check = False
for i in range(1, n+1):
    if distance[i] == k:
        check = True
        print(i)

if check == False:
    print(-1)