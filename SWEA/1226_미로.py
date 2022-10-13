"""
미로
0은 길
1은 벽
0,0,을 기준
1,1을 시작점 - 2 도착점은 13,13 - 3
출발부터 도착까지 갈 수 있는 길이 있는지 판단하는 프로그램을 작성해라.

2에서 시작 방문 노드에 추가, 상하좌우 0이 있으면 방문, 이미 방문했거나 벽이라면 return

"""

from collections import deque

n = 16

graph = []
for i in range(n):
    graph.append(list(map(int, input())))

# 상하좌우
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
visit = [[[0] for _ in range(n)] for _ in range(n)]
result = 0

def dfs(x, y):
    global result
    visit[x][y] = 1

    if graph[x][y] == 3:
        result = 1
        return

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        # 다음 이동할 곳이 길이고, 방문하지 않았다면
        if graph[nx][ny] == 0 and visit[nx][ny] == 0:
            dfs(nx, ny)
    return



dfs(1, 1)
print("visit : ", visit)
print("result: ", result)