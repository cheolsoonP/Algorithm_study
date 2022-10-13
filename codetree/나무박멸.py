# 상 하 좌 우 / 왼위, 왼오, 아오, 아왼(대각)
dx = [-1, 1, 0, 0, -1, -1, 1, 1]
dy = [0, 0, -1, 1, -1, 1, 1, -1]

def growth(x, y):
    count = 0
    # 상하좌우 확인, 나무가 있다면 count + 1
    for i in range(4):
        nx = x+dx[i]
        ny = y+dy[i]
        if nx < 0 or nx >= n or ny < 0 or ny >= n:
            continue
        if killer[nx][ny] > 0:
            continue
        if graph[nx][ny] > 0:
            count += 1
    return count

def growth_tree():
    for i in range(n):
        for j in range(n):
            # 나무가 있다면, 상하좌우 확인, 증가
            if graph[i][j] > 0:
                count = growth(i, j)
                graph[i][j] += count


def check_spread_position(x, y):
    count = 0
    position = []
    for i in range(4):
        nx = x+dx[i]
        ny = y+dy[i]
        # 상하좌우에 빈칸이 있으면
        if nx < 0 or nx >= n or ny < 0 or ny >= n:
            continue
        if killer[nx][ny] > 0:
            continue
        if graph[nx][ny] == 0:
            count += 1
            position.append((nx, ny))
    return position, count

def spread_tree():
    spread_list = []
    for i in range(n):
        for j in range(n):
            if graph[i][j] > 0:  # 나무가 있을 때
                position, count = check_spread_position(i, j)
                if count > 0:
                    num = graph[i][j] // count  # 주변에 심을 나무 수
                    spread_list.append([position, num])
    for pos, tn in spread_list:
        for x, y in pos:
            graph[x][y] += tn


def check_around_tree(k, x, y):
    count = graph[x][y]
    skip = []
    for i in range(4, 8):
        for j in range(1, k+1):
            nx = x + dx[i] * j
            ny = y + dy[i] * j
            if nx < 0 or nx >= n or ny < 0 or ny >= n:
                break
            if graph[nx][ny] <= 0:
                break
            count += graph[nx][ny]

    return count

def select_killer_position(k):
    max_count = 0
    x, y = -1, -1
    for i in range(n):
        for j in range(n):
            if graph[i][j] <= 0:
                continue
            count = check_around_tree(k, i, j)
            if max_count < count:
                max_count = count
                x, y = i, j

    return x, y, max_count

def reduce_killer():
    for i in range(n):
        for j in range(n):
            if killer[i][j] > 0:
                killer[i][j] -= 1


def kill_tree(x, y, c, k):

    if graph[x][y] > 0:
        graph[x][y] = 0
        killer[x][y] = c

        for i in range(4, 8):
            for j in range(1, k+1):
                nx = x + dx[i] * j
                ny = y + dy[i] * j
                if nx < 0 or nx >= n or ny < 0 or ny >= n:
                    break
                if graph[nx][ny] < 0:
                    break
                if graph[nx][ny] == 0:
                    killer[nx][ny] = c
                    break
                graph[nx][ny] = 0
                killer[nx][ny] = c


n, m, k, c = map(int, input().split())
graph = []
# 제초제 위치, 년 수
killer = [[0 for _ in range(n)] for _ in range(n)]
for i in range(n):
    graph.append(list(map(int, input().split())))

result = 0
for i in range(m):
    growth_tree()

    spread_tree()

    reduce_killer()

    x, y, count = select_killer_position(k)
    result += count

    kill_tree(x, y, c, k)  # 이전에 남은 제초제 - 1, 새로운 제초제 위치 갱신, 제초제 위치 나무 박멸

print(result)