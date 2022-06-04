"""
21610 마법사 상어와 비바라기 

1과 n은 연결 됨.  (0~n) n+a / n
비바라기 (n, 1) (n,2) (n-1,1) (n-1, 2)에 비구름 생성
구름 이동 M번 명령
이동명령은 방향 di 거리 si
방향은 8가지 방향 1~8
←, ↖, ↑, ↗, →, ↘, ↓, ↙

1. 모든 구름 di방향으로 si 만큼 이동
2. 각구름에서 비 내림 - 구름 칸에 있는 바구니 물의 양 + 1
3. 구름 사라진다.
4. 물이 증가한 칸에 물복사 버그 시전,
대각선으로 거리가 1인 물이 있는 바구니수(A) 만큼 r,c에 바구니의 물 양 증가
이동과 다르게 경계를 넘어가서 대각선을 확인하지 않음
5. 바구니 물의양이 2 이상인 모든칸에 구름 생기고, 물의 양 2감소,
3에서 구름이 사라진 칸이 아니어야 한다.

M번 이동 끝나고 바구니의 물의 양 합 출력
"""

n, m = map(int, input().split())

# 바구니 지도
graph = []
for i in range(n):
    graph.append(list(map(int, input().split())))

# 방향, 거리 리스트
move = []
for i in range(m):
    move.append(list(map(int, input().split())))


# 1. 모든 구름 di방향으로 si 만큼 이동
def move_cloud(direction, distance):
    temp = [[0] * n for _ in range(n)]
    for i in range(n):
        for j in range(n):
            if cloud[i][j] == 1:
                x, y = i, j
                nx = x + (dx[direction] * distance)
                ny = y + (dy[direction] * distance)
                if nx < 0:
                    nx = (n - (abs(nx) % n)) % n
                else:
                    nx %= n
                if ny < 0:
                    ny = (n - (abs(ny) % n)) % n
                else:
                    ny %= n
                temp[nx][ny] = 1
    return temp


# 2. 각구름에서 비 내림 - 구름 칸에 있는 바구니 물의 양 + 1
def raindrop():
    copy_position = []
    for i in range(n):
        for j in range(n):
            if cloud[i][j] == 1:
                graph[i][j] += 1
                copy_position.append([i, j])
    water_copy(copy_position)
    return


def water_copy(copy_position):
    # 대각선
    cx = [-1, -1, 1, 1]
    cy = [-1, 1, -1, 1]
    for i in range(len(copy_position)):
        count = 0
        x, y = copy_position[i][0], copy_position[i][1]
        for j in range(4):
            nx, ny = x + cx[j], y + cy[j]
            if 0 <= nx < n and 0 <= ny < n:
                if graph[nx][ny] > 0:
                    count += 1
        graph[x][y] += count

    return


def make_cloud():
    temp = [[0] * n for _ in range(n)]
    for i in range(n):
        for j in range(n):
            if graph[i][j] >= 2 and cloud[i][j] == 0:
                temp[i][j] = 1
                graph[i][j] -= 2
    return temp


# main
cloud = [[0] * n for _ in range(n)]
cloud[n-1][0] = 1
cloud[n-1][1] = 1
cloud[n-2][0] = 1
cloud[n-2][1] = 1

# 방향은 8가지 방향 1~8
# ←, ↖, ↑, ↗, →, ↘, ↓, ↙
dx = [0, -1, -1, -1, 0, 1, 1, 1]
dy = [-1, -1, 0, 1, 1, 1, 0, -1]

for i in range(m):
    direction, distance = move[i][0]-1, move[i][1]
    cloud = move_cloud(direction, distance)
    raindrop()
    cloud = make_cloud()

result = 0
for i in range(n):
    for j in range(n):
        result += graph[i][j]

print(result)

