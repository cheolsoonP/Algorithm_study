def fishing(man_idx):
    global score
    del_x, del_y = -1, -1

    for i in range(n):
        if len(graph[i][man_idx]) > 0:
            del_x, del_y = i, man_idx
            speed, dir, weight = graph[i][man_idx][0]
            score += weight
            break

    if del_x >= 0 and del_y >= 0:
        graph[del_x][del_y] = []


# 3. 상어 이동
def move_shark():
    global graph
    new_graph = [[[] for _ in range(m)] for _ in range(n)]
    for i in range(n):
        for j in range(m):
            # 상어가 있으면 이동
            if len(graph[i][j]) > 0:
                speed, dir, weight = graph[i][j][0]
                # 속도만큼 이동
                nx, ny = i, j
                for s in range(speed):
                    nx += dx[dir]
                    ny += dy[dir]
                    # 범위를 초과하면 방향 전환
                    if nx < 0 or nx >= n:
                        dir = (dir + 1) % 2
                        nx += dx[dir]*2
                    if ny < 0 or ny >= m:
                        if dir == 2:
                            dir += 1
                        else:
                            dir -= 1
                        ny += dy[dir]*2
                new_graph[nx][ny].append([speed, dir, weight])

    graph = new_graph


# 4. 겹치는 상어 제거
def delete_shark():
    global graph

    for i in range(n):
        for j in range(m):
            box_in_shark = len(graph[i][j])
            if box_in_shark > 1:
                winner = -1
                win_weight = -1
                for s in range(box_in_shark):
                    if win_weight < graph[i][j][s][2]:
                        winner = s
                        win_weight = graph[i][j][s][2]
                graph[i][j] = [graph[i][j][winner]]


n, m, shark_num = map(int, input().split())
graph = [[[] for _ in range(m)] for _ in range(n)]
score = 0

# 방향 상 하 우 좌 0, 1, 2,3
dx = [-1, 1, 0, 0]
dy = [0, 0, 1, -1]

for i in range(shark_num):
    r, c, s, d, z = map(int, input().split())
    # 속력, 방향, 크기
    graph[r-1][c-1].append([s, d-1, z])

# 낚시왕은 0~M+1까지 움직임, 상어는 1~m에 있음
for man_idx in range(0, m):
    # 1. 낚시왕 이동( man_idx )
    # 2. 낚시
    fishing(man_idx)
    # # 3. 상어 이동
    move_shark()
    # # 4. 겹치는 상어 제거
    delete_shark()

print(score)
