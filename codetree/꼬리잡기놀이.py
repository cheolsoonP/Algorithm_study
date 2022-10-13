from collections import deque

# 상 하 좌 우
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def find_team(index, x, y):
    visited = [[0 for _ in range(n)] for _ in range(n)]

    q = deque()
    q.append((x, y))
    while q:
        x, y = q.popleft()
        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]
            if nx < 0 or nx >= n or ny < 0 or ny >= n:
                continue
            if visited[nx][ny] == 1:
                continue
            if graph[nx][ny] == 2:
                team[index].append((nx, ny))
                q.append((nx, ny))
                visited[nx][ny] = 1
                continue
            if graph[nx][ny] == 3:
                team[index].append((nx, ny))
                q.append((nx, ny))
                visited[nx][ny] = 1
                break


def find_course(x, y):
    for i in range(4):
        nx = x+dx[i]
        ny = y+dy[i]
        if nx < 0 or nx >= n or ny < 0 or ny >= n:
            continue
        if graph[nx][ny] == 4:
            return nx, ny

def move_team(member):
    # 1번은 가장 가까운 4번으로 이동
    # 나머지 한칸 씩 이동
    x, y = member[0][0], member[0][1]
    for i in range(4):
        nx = x+dx[i]
        ny = y+dy[i]
        if nx < 0 or nx >= n or ny < 0 or ny >= n:
            continue
        if graph[nx][ny] == 4:
            member[1:] = member[0:len(member) - 1]
            member[0] = (nx, ny)

    return member


def move_graph(team):
    for i in range(n):
        for j in range(n):
            if graph[i][j] > 0:
                graph[i][j] = 4

    for one_team in team:
        for i in range(len(one_team)):
            x, y = one_team[i]
            if i <= 0:
                graph[x][y] = 1
            elif i == len(one_team)-1:
                graph[x][y] = 3
            else:
                graph[x][y] = 2


def switch_direction(x, y):
    for i in range(len(team)):
        if (x, y) in team[i]:
            team[i] = team[i][::-1]


def throw_ball(game_round, n):
    main_round = game_round % (4 * n)
    inner_round = main_round % n

    sx, sy = -1, -1
    switch_x, switch_y = -1, -1

    score = 0
    # 0~11
    # 1. 왼쪽 -> 오른쪽 - [행 고정][열 0~n-1]
    if 0 <= main_round < n:
        for i in range(n):
            if 0 < graph[inner_round][i] < 4:
                sx, sy = inner_round, i
                switch_x, switch_y = inner_round, i
                break
    # 2. 아래 -> 위 왼 -> 오
    if n <= main_round < 2*n:
        for i in range(n-1, -1, -1):
            if 0 < graph[i][inner_round] < 4:
                sx, sy = i, inner_round
                switch_x, switch_y = i, inner_round
                break
    # 3. 오른 -> 왼, 아래 - 위
    if 2*n <= main_round < 3*n:
        for i in range(n-1, -1, -1):
            if 0 < graph[n-1-inner_round][i] < 4:
                sx, sy = n-1-inner_round, i
                switch_x, switch_y = n-1-inner_round, i
                break
    # 4. 위 -> 아래, 오른 -> 왼
    if 3*n <= main_round < 4*n:
        for i in range(0, n-1):
            if 0 < graph[i][n-1 - inner_round] < 4:
                sx, sy = i, n-1-inner_round
                switch_x, switch_y = i, n-1-inner_round
                break

    if sx != -1 and sy != -1:
        for one_team in team:
            for i in range(len(one_team)):
                if one_team[i][0] == sx and one_team[i][1] == sy:
                    score = i+1
                    switch_direction(switch_x, switch_y)

    return score * score

####

n, m, k = map(int, input().split())

team = []
graph = []

for i in range(n):
    # 0 - 벽, 1- 머리 2-나머지사람 3-꼬리, 4-코스,경로
    graph.append(list(map(int, input().split())))

# 1번 위치 찾아 팀 리더로 추가
for i in range(n):
    for j in range(n):
        if graph[i][j] == 1:
            team.append([(i, j)])

# 리더로 팀 찾아 매칭
for i in range(len(team)):
    x, y = team[i][0]
    find_team(i, x, y)

total_score = 0
# 라운드 진행
for game_round in range(k):
    for i in range(len(team)):
        team[i] = move_team(team[i])

    move_graph(team)

    total_score += throw_ball(game_round, n)

print(total_score)