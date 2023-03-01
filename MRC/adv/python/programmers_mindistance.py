from collections import deque


def bfs(x, y):
    q = deque()
    q.append([x, y])
    map[x][y] = 1

    while q:
        cx, cy = q.popleft()
        for i in range(4):
            nx = cx + dx[i]
            ny = cy + dy[i]
            # 범위를 초과
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue
            # 벽일 때
            if map[nx][ny] == 0:
                continue
            q.append([nx, ny])
            map[nx][ny] = map[cx][cy] + 1


def solution(maps):
    n = len(maps[:][0])  # 행 길이
    m = len(maps[0])  # 열 길이
    print(maps[:][1])

    # 상하좌우
    dx = [0, 0, -1, 1]
    dy = [1, -1, 0, 0]

    bfs(0, 0)

    answer = map[n - 1][m - 1]
    if answer == 0:
        return -1
    else:
        return answer

    return answer