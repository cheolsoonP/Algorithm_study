
"""
배추 재배
해충 방지 지렁이

지렁이 - 인접 배추로 이동
배추 상하좌우 인접

인접해있는 배추 몇군데 퍼져있는지 조사
지렁이 몇마리 필요한지 조사
"""
from collections import deque

def bfs(x, y):
    q = deque()
    q.append((x, y))

    while q:
        x, y = q.popleft()
        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue
            if visited[nx][ny] > 0:
                continue
            if graph[nx][ny] > 0:
                q.append((nx, ny))
                visited[nx][ny] = 1


T = int(input())
visited = []

# 상 하 좌 우
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

for test_case in range(1, T+1):
    m, n, k = map(int, input().split())

    graph = [[0 for _ in range(m)] for _ in range(n)]
    visited = [[0 for _ in range(m)] for _ in range(n)]
    count = 0

    for i in range(k):
        y, x = map(int, input().split())
        graph[x][y] = 1

    for i in range(n):
        for j in range(m):
            if visited[i][j] == 0 and graph[i][j] > 0:
                bfs(i,j)
                count += 1

    print(count)