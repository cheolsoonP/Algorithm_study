"""
백준: 쉬운 최단거리, 실버1
https://www.acmicpc.net/problem/14940
"""
"""
지도 -> 주모든 지점에 대한 목표지자ㅓㅁ까지의 거리
가로 세로로만 움직인다. 
n,m

"""
from collections import deque


def bfs(x, y):
    global visited, new_graph
    q = deque()
    q.append((x, y))
    visited[x][y] = True
    new_graph[x][y] = 0
    while q:
        x, y = q.popleft()
        visited[x][y] = True
        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue
            if visited[nx][ny]:
                continue
            if graph[nx][ny] == 1:  # 원래 갈 수 있는 땅
                new_graph[nx][ny] = new_graph[x][y]+1
                q.append((nx, ny))
            else:  # 원래 갈 수 없는 땅
                new_graph[nx][ny] = 0


# 상 하 좌 우
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
n, m = map(int, input().split())
graph = []
new_graph = [[-1 for _ in range(m)] for _ in range(n)]  # 원래 갈 수 있는 땅인데, 도달 할 수 없으면 -1
visited = [[False for _ in range(m)] for _ in range(n)]
for i in range(n):
    graph.append(list(map(int, input().split())))

i = 0
j = 0
while True:
    if graph[i][j] == 2:
        bfs(i, j)
        break
    i+=1
    j+=1

for i in range(n):
    for j in range(m):
        print(new_graph[i][j], end=" ")
    print()
