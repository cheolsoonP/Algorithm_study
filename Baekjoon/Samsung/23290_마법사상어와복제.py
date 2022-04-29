"""
1,1 4,4
물고기 M마리 있다.
방향을 가지고 있다
물고기는 같은 칸에 둘 이상 있을 수 있다 .
상어도 물고기랑 같이 있을 수 있다

마법연습은 순차적으로 한다
1. 물고기 복제마법, 5번에서 복제가 된다
2. 물고기가 이동한다, 상어가 있는 곳, 물고기 냄새가 있는곳 못간다
범위를 벗어날 수 없다, 이동할 수 있는 곳까지 45도 반시계회전
3. 상어가 연속 3칸 이동한다 , 상하좌우로, 격자를 벗어나면 안된다
이동 중에 있는 모든 물고기는 먹히고, 냄새를 남긴다
이동 방법 중 가장 많은 물고기를 먹을 수 있게 이동한다
사전 순으로 가장 앞에 있는 방법으로 간다
4. 두번 전 연습에서 생긴 물고기 냄새가 사라진다
5. 복제마법이 된다. 물고기는 방향과 위치를 그대로 갖는다

격자에 있는 물고기 수를 구해라.
"""

m, s = map(int, input().split())

fish = [[[] for _ in range(4)] for _ in range(4)]
for i in range(m):
    x, y, d = map(int, input().split())
    fish[x - 1][y - 1].append(d - 1)

# 방향 ←, ↖, ↑, ↗, →, ↘, ↓, ↙
dx = [0, -1, -1, -1, 0, 1, 1, 1]
dy = [-1, -1, 0, 1, 1, 1, 0, -1]

# 상어 초기 좌표
now_x, now_y = map(int, input().split())
now_x -= 1
now_y -= 1


# 1. 복제 마법
def prepare_copy_fish(fish):
    temp = []
    temp = fish
    return temp


# 방향 반시계 회전
def rotate_counterclockwise(direction):
    if direction == 0:
        return 7
    else:
        return direction - 1


# 2. 물고기 이동
# 상어가 있는 곳, 물고기 냄새가 있는곳 못간다
# 범위를 벗어날 수 없다, 이동할 수 있는 곳까지 45도 반시계회전
# 방향 ←, ↖, ↑, ↗, →, ↘, ↓, ↙
def move_fish(fish):
    temp = [[[] * 4 for _ in range(4)] for _ in range(4)]
    for i in range(4):
        for j in range(4):
            if fish[i][j]:
                for k in range(len(fish[i][j])):
                    x, y = i, j
                    direction = fish[i][j][k]
                    for k in range(8):
                        nx, ny = x + dx[direction], y + dy[direction]
                        if 0 <= nx < 4 and 0 <= ny < 4:
                            if smell[nx][ny] < 1 and nx != now_x and ny != now_y:
                                temp[nx][ny].append(direction)
                                break
                        direction = rotate_counterclockwise(direction)

    return temp


# 3. 상어가 연속 3칸 이동한다 , 상하좌우로, 격자를 벗어나면 안된다
# 이동 중에 있는 모든 물고기는 먹히고, 냄새를 남긴다
# 이동 방법 중 가장 많은 물고기를 먹을 수 있게 이동한다
# 사전 순으로 가장 앞에 있는 방법으로 간다
def dfs_find_position(x, y, count, eat, path):
    global total_eat, total_path
    if count >= 3:
        if eat > total_eat:
            total_eat = eat
            total_path = path[:]
        return

    # 상 좌 하 우 - 사전순서
    sx = [-1, 0, 1, 0]
    sy = [0, -1, 0, 1]

    for i in range(4):
        nx, ny = x + sx[i], y + sy[i]
        if 0 <= nx < 4 and 0 <= ny < 4:
            if visit[nx][ny] == 0:
                if fish[nx][ny]:
                    eat += len(fish[nx][ny])
                visit[nx][ny] = 1
                path.append([nx, ny])
                dfs_find_position(nx, ny, count + 1, eat, path)
                path.pop()
                visit[nx][ny] = 0



def move_shark(path):
    global now_x, now_y
    for i in range(len(path)):
        x, y = path[i][0], path[i][1]
        if fish[x][y]:
            fish[x][y] = []
            smell[x][y] = 3
    now_x, now_y = x, y
    return


def remove_smell():
    for i in range(4):
        for j in range(4):
            if smell[i][j] > 0:
                smell[i][j] -= 1
    return


# 물고기 복제
def copy_fish(before_fish):
    for i in range(4):
        for j in range(4):
            if before_fish[i][j]:
                for k in before_fish[i][j]:
                    fish[i][j].append(k)
    return


# main
smell = [[0] * 4 for _ in range(4)]

for i in range(s):
    # 1. 물고기 복사 준비
    before_fish = []
    before_fish = prepare_copy_fish(fish)
    # 2. 물고기 이동
    fish = move_fish(fish)
    visit = [[0] * 4 for _ in range(4)]
    total_eat = 0
    total_path = [[] for _ in range(3)]

    # 3. 상어 이동, 4. 물고기 냄새 감소
    dfs_find_position(now_x, now_y, 0, 0, [])
    move_shark(total_path)
    remove_smell()
    # 5. 물고기 복제
    copy_fish(before_fish)

result = 0
for i in range(4):
    for j in range(4):
        if fish[i][j]:
            for k in fish[i][j]:
                result += 1

print(result)