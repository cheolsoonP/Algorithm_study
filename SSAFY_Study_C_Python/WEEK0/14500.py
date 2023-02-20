from collections import deque

# 상하좌우
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
max_score = 0
max_val = 0


def dfs(x, y, length, score):
    global max_score, max_val

    if score + max_val * (4 - length) <= max_score:
        return

    if length == 4:
        max_score = max(score, max_score)
        return
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if 0 <= nx < n and 0 <= ny < m:
            if visited[nx][ny] == 0:
                if length == 2:
                    visited[nx][ny] = 1
                    dfs(x, y, length + 1, score+graph[nx][ny])
                    visited[nx][ny] = 0
                visited[nx][ny] = 1
                dfs(nx, ny, length + 1, score + graph[nx][ny])
                visited[nx][ny] = 0

#
# def bfs(x, y):
#     global max_score
#     score = graph[x][y]
#     side = []
#     bfs_score = 0
#     for i in range(4):
#         nx, ny = x + dx[i], y + dy[i]
#         if 0 <= nx < n and 0 <= ny < m:
#             side.append(graph[nx][ny])
#             score += graph[nx][ny]
#     for i in range(len(side)):
#         bfs_score = max(bfs_score, score - side[i])
#     max_score = max(max_score, bfs_score)
#     return


def find():
    for i in range(n):
        for j in range(m):
            temp = []
            visited[i][j] = 1
            dfs(i, j, 1, graph[i][j])
            visited[i][j] = 0
            # bfs(i, j)


n, m = map(int, input().split())
graph = []
for i in range(n):
    graph.append(list(map(int, input().split())))
max_val = max(map(max, graph))
visited = [[0 for _ in range(m)] for _ in range(n)]
find()
print(max_score)
