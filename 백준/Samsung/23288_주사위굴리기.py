"""
23288 주사위 굴리기 2
N x M
오른쪽은 동쪽, 위쪽은 북쪽
r, c
1,1 ~ n,m

전개도

제일 처음 주사위는 동쪽으로 이동
1,1에서 시작

지도에도 정수가 있다.
1. 주사위가 이동방향으로 굴러간다. 이동방향에 칸이 없으면 반대로하고 굴러간다
2. 도착한 칸에 대한 점수를 획득한다.
3. 주사위 아랫면과 주사위가 있는 칸의 정수를 비교, 방향을 결정한다
(주사위 바닥 > 칸 값 - 90도 시계방향 회전
주사위 바닥 < 칸 값 - 90도 반시계 회전
바닥 == 칸 값 - 이동방향 그대로

칸에 대한 점수는 x, y 정수가 b 일때 동서남북으로 이동할 수 있는 칸의 수 C를 구한다
이동할 수 있는 칸에 모두 B가 있어야 한다.
(칸의 값과 연결된 값을 계속 찾는다.)
B와 C를 곱한 값이 점수이다.
각 이동에서 획득하는 점수의 합을 구해라

"""
from collections import deque

## 1. 주사위가 해당 방향으로 굴러간다.
## 2. 점수를 계산한다.
## 3. 방향을 정한다.

n, m, k = map(int, input().split())

graph = []
for i in range(n):
    graph.append(list(map(int, input().split())))

# 주사위- 위, 뒤, 오른쪽, 왼쪽, 아래, 앞
dice = [1, 2, 3, 4, 5, 6]
cur_x, cur_y = 0, 0

# 상 우 하 좌
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

# 방향, 처음에는 동쪽, 우측으로 향함(0~3)
direction = 1


def move_top():
    dice[0], dice[1], dice[4], dice[5] = dice[4], dice[0], dice[5], dice[1]


def move_down():
    dice[0], dice[1], dice[4], dice[5] = dice[1], dice[5], dice[0], dice[4]


def move_left():
    dice[0], dice[2], dice[5], dice[3] = dice[2], dice[5], dice[3], dice[0]


def move_right():
    dice[0], dice[3], dice[5], dice[2] = dice[3], dice[5], dice[2], dice[0]


def turn_clockwise(move):
    return (move + 1) % 4


def turn_counterclockwise(move):
    return (move - 1) % 4


# 주사위가 굴러간다, 상 우 하 좌
def move_dice(move):
    global direction
    nx = cur_x + dx[move]
    ny = cur_y + dy[move]

    if nx < 0 or nx >= n or ny < 0 or ny >= m:
        move = (move + 2) % 4

    if move == 0:
        move_top()
    if move == 1:
        move_right()
    if move == 2:
        move_down()
    if move == 3:
        move_left()

    nx = cur_x + dx[move]
    ny = cur_y + dy[move]
    direction = move

    return nx, ny


def bfs(x, y, before):
    visit[x][y] = 1
    q.append([x, y])
    count = 1
    while q:
        x, y = q.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < n and 0 <= ny < m:
                if graph[nx][ny] == before and visit[nx][ny] == 0:
                    q.append([nx, ny])
                    visit[nx][ny] = 1
                    count += 1
    return before * count


# direction
# 위 오른쪽 아래 왼쪽
def get_direction(direction):

    # 주사위 아래면과 비교
    if dice[5] > graph[cur_x][cur_y]:
        direction = (direction + 1) % 4
    if dice[5] < graph[cur_x][cur_y]:
        direction = (direction - 1) % 4

    return direction


# main
direction = 1  # 초기 방향 오른쪽
total = 0
cur_x, cur_y = 0, 0
q = deque()
for _ in range(k):
    visit = [[0] * m for _ in range(n)]
    cur_x, cur_y = move_dice(direction)
    result = bfs(cur_x, cur_y, graph[cur_x][cur_y])
    direction = get_direction(direction)
    total += result

print(total)
