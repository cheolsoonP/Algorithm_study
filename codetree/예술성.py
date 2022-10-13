from collections import deque

# 상하좌우
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def find_group(init_x, init_y):
    visited = [[0 for _ in range(n)] for _ in range(n)]
    count = 0
    q = deque()
    q.append((init_x, init_y))
    while q:
        x, y = q.popleft()
        visited[x][y] = 1
        for i in range(4):
            nx, ny = x+dx[i], y+dy[i]
            if nx < 0 or nx >= n or ny < 0 or ny >= n:
                continue
            if graph[nx][ny] != graph[x][y]:
                # 맞닿은 변의 수
                continue
            visited[nx][ny] = 1
            q.append((nx, ny))
            count += 1

    return init_x, init_y, count

    for i in range(n):
        for j in range(n):
            dfs(i, j, graph[i][j], visited)

n = int(input())  # 홀수
graph = []
for i in range(n):
    graph.append(list(map(int, input().split())))


for i in range(3):
    find_group()
    #
    # computing_score()
    #
    # rotation_art()
