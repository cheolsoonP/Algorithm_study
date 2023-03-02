"""


"""

n = int(input())
INF = 1e9
# 0 - 시작, 1~9 - 동전, 10 - 최종 지점
# INF - 도달X
distance_graph = [[INF for _ in range(11)] for _ in range(11)]

# 자기자신으로 가는 거리 0
for i in range(11):
    distance_graph[i][i] = 0

graph = []
for i in range(n):
    graph.append(list(input()))

print(graph)

dx = [0, 0, -1, 1]
dy = [1, -1, 0, 0]

# place = [[(0-10, x, y)] ~ ]
place = [[] for _ in range(11)]
# 시작점, 동전, 끝점 위치 넣기
for i in range(n):
    for j in range(n):
        if graph[i][j] == 'S':
            place[0].append(i, j)
        if graph[i][j] == 'E':
            place[10].append(i, j)
        if graph[i][j] != '.' and graph[i][j] != '#':
            place[int(graph[i][j])].append(i, j)

for i in range(11):
    for j in range(11):
        setting_distance(graph[i], graph[j])

# a to b
def setting_distance(a, b):
    ax, ay = a[0], a[1]
    bx, by = b[0], b[1]

    q = deque()
    q.append((ax, ay, 0))
    while q:
        x, y, cost = q.popleft()
        if x == bx and y == by:
            return cost

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if nx < 0 or nx >= n or ny < 0 or ny >= n:
                continue
            if graph[nx][ny] == '#':
                continue
            q.append((nx, ny, cost+1))


find_distance(graph[])
# 거리 구하기
def find_distance(a, b):


