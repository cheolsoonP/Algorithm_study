from collections import deque

n, m = map(int, input().split())
# 초기 맵 리스트
graph = []
for i in range(n):
    graph.append(list(map(int, input().split())))
# 벽 설치 후 맵 리스트
temp = [[0] * m for _ in range(n)]

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]




# 바이러스 확산 후 안전영역 계산
def bfs():
    # 초기 바이러스 위치 확인
    virus_queue = deque()
    for i in range(n):
        for j in range(m):
            if graph[i][j] == 2:
                virus_queue.append((i, j))

    while virus_queue:
        r, c = virus_queue.popleft()

        # 상하좌우로 퍼질 수 있는지 확인
        for i in range(4):
            nr, nc = r+dy[i], c+dx[i]
            # graph 범위 안에
            if 0 <= nr < n and 0 <= nc < m:
                if temp[nr][nc] == 0:
                    temp[nr][nc] = 2
                    virus_queue.append((nr, nc))

    # 안전지대 계산
    safe = 0
    for i in range(n):
        for j in range(m):
            if temp[i][j] == 0:
                safe += 1
    return safe


# 벽을 세울 수 있는 모든 조합 구하기
def dfs(count):
    global result
    if count == 3:
        for i in range(n):
            for j in range(m):
                temp[i][j] = graph[i][j]
        result = max(bfs(), result)
        return

    for i in range(n):
        for j in range(m):
            if graph[i][j] == 0:
                graph[i][j] = 1
                count += 1
                dfs(count)
                graph[i][j] = 0
                count -= 1


result = -1e9
dfs(0)
print(result)
