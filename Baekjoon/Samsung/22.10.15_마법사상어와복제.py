# 방향 0 ~ 7
# 방향 ←, ↖, ↑, ↗, →, ↘, ↓, ↙
dx = [0, -1, -1, -1, 0, 1, 1, 1]
dy = [-1, -1, 0, 1, 1, 1, 0, -1]

MAX_SCORE = 0
DICTIONARY = 1e9
SHARK_MOVING = []

def copy_fish_position():
    return fishes[:]


def move_fish():
    for i in range(len(fishes)):
        x, y, direction = fishes[i][0], fishes[i][1], fishes[i][2]
        for j in range(0, 8):
            direction = direction % 8
            nx, ny = x+dx[direction], y+dy[direction]
            if nx < 0 or nx >= 4 or ny < 0 or ny >= 4:
                direction += j
                continue
            if nx == sx and ny == sy:
                direction += j
                continue
            if smell[nx][ny] > 0:
                direction += j
                continue
            fishes[i][0], fishes[i][1], fishes[i][2] = nx, ny, direction
            break

def convert_to_graph():
    for i in range(len(fishes)):
        x, y = fishes[i][0], fishes[i][1]
        graph[x][y] += 1


def dfs(x, y, count, score, position, directions, visited):
    # 상하좌우
    global DICTIONARY
    global MAX_SCORE
    global SHARK_MOVING
    if count >= 3:
        if MAX_SCORE <= score:
            direc = ""
            for i in range(3):
                direc += str(directions[i])
            if int(direc) < DICTIONARY:
                DICTIONARY = int(direc)
                MAX_SCORE = score
                SHARK_MOVING = position
        return

    ddx = [-1, 1, 0, 0]
    ddy = [0, 0, -1, 1]
    for i in range(4):
        nx = x+ddx[i]
        ny = y+ddy[i]
        if nx < 0 or nx >= 4 or ny < 0 or ny >= 4:
            continue
        if visited[nx][ny] == 1:
            continue
        visited[nx][ny] = 1
        position.append((nx, ny))
        directions.append(i+1)
        dfs(nx, ny, count+1, score+graph[nx][ny], position, directions, visited)
        visited[nx][ny] = 0


def make_smell(x, y):
    smell[x][y] = 3

def kill_fish():
    new_fishes = []
    for i in range(3):
        x, y = SHARK_MOVING[i]
        if graph[x][y] > 0:
            graph[x][y] = 0
            make_smell(x, y)

    for i in range(len(fishes)):
        if (fishes[i][0], fishes[i][1]) not in SHARK_MOVING:
            new_fishes.append([fishes[i][0], fishes[i][1], fishes[i][2]])
    return new_fishes


def move_shark():
    global MAX_SCORE
    global DICTIONARY
    global SHARK_MOVING

    MAX_SCORE = -1e9
    DICTIONARY = 1e9
    SHARK_MOVING = []
    visited = [[0 for _ in range(4)] for _ in range(4)]
    dfs(sx, sy, 0, 0, [], [], visited) # 상어 움직임 좌표, 물고기 잡은 수, 상어 움직임 좌표의 초기값
    new_fishes = kill_fish()
    return new_fishes


def down_smell():
    for i in range(4):
        for j in range(4):
            if smell[i][j] > 0:
                smell[i][j] -= 1


def duplicate_fish():
    for i in range(fish_position):
        fishes.append([fish_position[i]])


m, s = map(int, input().split())

fishes = []
for i in range(m):
    x, y, dire = map(int, input().split())
    fishes.append([x, y, dire-1])

sx, sy = map(int, input().split())

smell = [[0 for _ in range(4)] for _ in range(4)]
graph = [[0 for _ in range(4)] for _ in range(4)]

for _ in range(s):
    fish_position = copy_fish_position()
    move_fish()
    convert_to_graph()
    fishes = move_shark()
    sx, sy = SHARK_MOVING[2][0], SHARK_MOVING[2][1]
    down_smell()
    duplicate_fish()

answer = len(fishes)

print(anser)


"""
5 1
4 3 5
1 3 5
2 4 2
2 1 6
3 4 4
4 2

"""
# 9