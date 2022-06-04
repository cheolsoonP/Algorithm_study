"""
19237 어른 상어

"""

# n x n 격자, m - 상어 수, 1~M까지 번호, k는 향이 없어지는데 걸리는 시간
n, m, k = map(int, input().split())

# 상어의 위치를 담는다.
graph = []
for i in range(n):
    graph.append(list(map(int, input().split())))

# 상어의 냄새를 담는다.
smell = [[None] * n for _ in range(n)]
for i in range(n):
    for j in range(n):
        if graph[i][j]:
            smell[i][j] = [graph[i][j], k]

# 상어의 방향을 담는 배열 (0~m-1)
direction = list(map(int, input().split()))

# 상 하 좌 우
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

# 우선순위를 담는다.
# pr[0][0][0] - 1번 상어, 상일때, 이동 우선순위
pr = [[] for _ in range(m)]

for i in range(m):
    for j in range(4):
        pr[i].append(list(map(int, input().split())))


# 1. 상어 각각 이동할 수 있는지 확인
# 상하좌우 중 냄새가 없는 칸,
# 냄새가 없는 칸이 여러개, 우선순위에 따라 움직인다.
# 모두 냄새가 있으면 자신의 냄새가 있는 칸으로 이동
# 여러칸인 경우 자신의 우선순위를 따른다.
# 각자 상어가 위치를 반환 받음

def find_shark(num):
    for i in range(n):
        for j in range(n):
            if graph[i][j] == num:
                return i, j
    return None

def get_moving_point(num):
    now_x, now_y = find_shark(num)
    temp = []
    # 상어가 보고 있는 곳
    direct = direction[num - 1]

    # 우선순위로 돌면서
    for i in pr[num-1][direct - 1]:
        nx, ny = now_x + dx[i - 1], now_y + dy[i - 1]

        if 0 <= nx < n and 0 <= ny < n:
            if smell[nx][ny] == None:
                direction[num - 1] = i
                return nx, ny
            # 자신의 냄새가 있는 칸으로 이동
            elif smell[nx][ny][0] == num:
                temp.append([nx, ny, i])
    # 모두 냄새가 있을 경우
    direction[num - 1] = temp[0][2]
    return temp[0][0], temp[0][1]


# 2. 상어는 그 위치로 이동
def move_all():
    new_array = [[0] * n for _ in range(n)]
    for i in range(n):
        for j in range(n):
            if graph[i][j] > 0:
                nx, ny = get_moving_point(graph[i][j])
                if new_array[nx][ny] == 0:
                    new_array[nx][ny] = graph[i][j]
                else:  # 안에 값이 있으면 값이 작은 상어가 들어간다
                    new_array[nx][ny] = min(new_array[nx][ny], graph[i][j])

    return new_array


# 3. 이전 냄새들 -1씩 하기, 새로운 위치 냄새 뿌리기
def smell_control():
    # 이전 냄새 -1
    for i in range(n):
        for j in range(n):
            if smell[i][j] != None:
                smell[i][j][1] -= 1
                # 냄새가 사라지면 none으로 초기화
                if smell[i][j][1] == 0:
                    smell[i][j] = None

    # 새로운 냄새 생성
    for i in range(n):
        for j in range(n):
            if graph[i][j] > 0:
                smell[i][j] = [graph[i][j], k]


result = 0
while True:
    count = 0
    check = False
    new_array = move_all()
    graph = new_array
    result += 1
    smell_control()

    for i in range(n):
        for j in range(n):
            if graph[i][j] != 0:
                count += 1
                if graph[i][j] == 1:
                    check = True

    if count == 1:
        if check == True:
            print(result)
            break
        print(-1)
        break

    if result >= 1000:
        print(-1)
        break

# 종료 지점을 제대로 잘 설정해주어야 한다!