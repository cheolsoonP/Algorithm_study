
n, m = map(int, input().split())
graph = []
for i in range(n):
    graph.append(list(map(int, input())))
print(graph)

# 상하좌우
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
count = 0


def dfs(x, y):
    if graph[x][y] == 1:
        return
    # 방문
    graph[x][y] = 1
    for i in range(4):
        if 0 <= x+dx[i] < n and 0 <= y+dy[i] < m:
            dfs(x+dx[i], y+dy[i])


for i in range(n):
    for j in range(m):
        if graph[i][j] == 0:
            count+=1
            dfs(i, j)

print(count)