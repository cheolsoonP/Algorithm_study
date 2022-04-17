"""
N x M 미로에 갇혀 있다.
괴물을 탈출해야 한다.
위치는1,1에서 시작
출구는 N,M

괴물이 있는 곳 0
괴물이 없는 곳 1
1로만 가야한다.
반드시 탈출할 수 있는 형태로 제시된다.
움직여야 하는 칸의 개수는??

시작 칸, 마지막 칸 포함 한다.
첫째칸과 마지막 칸은 항상 1

1. 상하좌우 1이 있는 곳을 확인 후 방문 0으로 표기, 계속 방문하여 현재위치가 n, m이라면 True 종료, count 반환
2. 상하좌우 0이라면 리턴 false,
1,1에서 시작

BFS문제

입력
5 6
101010
111111
000001
111111
111111

출력
10

"""

from collections import deque

n, m = map(int, input().split())

graph = []
for i in range(n):
    graph.append(list(map(int, input())))

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]


def bfs(x, y):

    queue = deque()
    queue.append((x, y))

    while queue:
        x, y = queue.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if nx < 0 or ny < 0 or nx >= n or ny >= m:
                continue
            if graph[nx][ny] == 0:
                continue

            if graph[nx][ny] == 1:
                graph[nx][ny] = graph[x][y] + 1
                queue.append((nx, ny))

    return graph[n-1][m-1]


print(bfs(0, 0))



















from collections import deque

n, m = map(int, input().split())

graph = []
for i in range(n):
    graph.append(list(map(int, input())))

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def bfs(x, y):
    queue = deque()
    queue.append((x, y))

    while queue:
        x, y = queue.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if nx < 0 or ny < 0 or nx >= n or ny >= m:
                continue
            if graph[nx][ny] == 0:
                continue

            if graph[nx][ny] == 1:
                graph[nx][ny] = graph[x][y] + 1
                queue.append((nx, ny))

    return graph[n - 1][m - 1]

print(bfs(0, 0))

