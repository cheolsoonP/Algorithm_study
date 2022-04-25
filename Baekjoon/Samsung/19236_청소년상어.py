"""
청소년 상어
4x4 크기 공간
각 칸은 x, y로 표현 x-행, y-열
물고기는 번호와 방향을 가진다.
1 <= 번호 <= 16

두 물고기는 서로 다른 번호를 가진다
방향은 8가지 상하좌우 대각선 중 하나
[1]
청소년 상어는 0,0에 물고기를 먹고 0,0에 들어간다
상어의 방향은 0,0에 있던 물고기 방향과 같다
이후 물고기가 이동

[2]
(1~16번까지)
물고기는 번호가 작은 물고기부터 순서대로 이동한다.
물고기는 한칸을 이동할 수 있고,
이동할 수 있는 칸은 빈칸과 다른 물고기가 있는 칸,

이동할 수 없는 칸은 상어가 있거나, 공간의 경계를 넘는 칸이다.
각 물고기는 방향이 이동할 수 있는 칸을 향할 때까지
방향을 45도 반시계 화전
만약 이동할 수 없는 칸이 없으면 이동을 하지 않음
그외의 경우 그 칸으로 이동
물고기가 다른 물고기가 있는 칸으로 이동할 때는 서로 위치를 바꿈

물고기 이동이 모두 끝나면 상어 이동
상어는 방향에 있는 칸으로 이동 가능, 한번에 여러 칸 이동 가능
상어가 물고기가 있는 칸으로 이동하면 그 칸의 물고기를 먹고
그 물고기의 방향을 가진다.
이동중에 지나가는 칸에 물고기는 먹지 않음
물고기가 없는 칸으로 이동할 수 없다.

상어가 이동할 수 있는 칸이 없다면 집으로 간다.

물고기 이동 -> 상어 이동 (반복)

1. 상어 0,0으로 이동,
2. 물고기 이동
    물고기 번호가 낮은 것부터 이동
    2-1 이동 시 해당 방향으로 이동 그 방향이 막혀있을시 반시계 회전, 이동
    2-2 이동 시 범위가 넘어간다면 반시계로 방향 회전
    2-3 이동 시 상어가 있다면 반시계로 방향 회전
    2-4 이동 시 물고기가 있다면 위치 교환
    2-5 이동 시 빈칸이라면 그곳으로 이동
    (모든 물고기를 반복)
3. 상어 이동
    3-1 물고기의 방향으로 이동가능, 여러칸 가능
    3-2 BFS로 DFS로 모든 곳의 경우를 따짐.
    3-3 위치를 초과해야하거나, 해당 방향에 먹을 물고기가 없다면 종료

"""

import copy

graph = [[] * 4 for _ in range(4)]
for i in range(4):
    data = list(map(int, input().split()))
    for j in range(4):
        graph[i].append([data[j*2], data[j*2+1]])

# 1~8 위 왼위 왼 왼아 아 오아 오 오위
# 반시계 순서
dr = [0, -1, -1, 0, 1, 1, 1, 0, -1]
dc = [0, 0, -1, -1, -1, 0, 1, 1, 1]

# 초기 상어 위치, 방향 정의
cur_dir = graph[0][0][1]

# 초기 물고기(1~16)
fishes = [1 for _ in range(17)]
fishes[0] = -1
result = 0


def move_fish(graph, cur_r, cur_c):
    # 번호 순서대로 물고기 이동(먹힌 물고기는 제외)
    for fish_num in range(1, 17):
        if fishes[fish_num] == 1:
            r, c = 0, 0
            fdir = 0

            for i in range(4):
                for j in range(4):
                    if graph[i][j][0] == fish_num:
                        r, c = i, j
                        fdir = graph[i][j][1]

            for i in range(1, 9):
                nr, nc = r + dr[fdir], c + dc[fdir]

                # 이동 시 범위안에 있고, 상어가 아닐 때
                if 0 <= nr < 4 and 0 <= nc < 4:
                    # 이동 시 물고기가 있거나, 0이면 switch
                    if graph[nr][nc][0] >= 0:
                        graph[nr][nc], graph[r][c] = graph[r][c], graph[nr][nc]
                        break
                # 이동 시 범위가 넘어가거나 상어가 있다면 반시계로 회전
                fdir = (fdir + i) % 8
                graph[r][c][1] = fdir


# 상어가 먹을 수 있는 물고기들 위치 반환
def get_fish_list(graph, cur_r, cur_c):
    global cur_dir
    positions = []
    for i in range(4):
        nr, nc = cur_r + (i * dr[cur_dir]), cur_c + (i * dc[cur_dir])
        if 0 <= nr < 4 and 0 <= nc < 4:
            if graph[nr][nc][0] > 0:
                positions.append([nr, nc])

    return positions


# 모든 경우의 수 탐색
def dfs(graph, cur_r, cur_c, total):
    global result, fishes, cur_dir
    graph = copy.deepcopy(graph)

    # 현재 위치 물고기 먹기
    total += graph[cur_r][cur_c][0]
    fishes[graph[cur_r][cur_c][0]] = 0
    cur_dir = graph[cur_r][cur_c][1]

    # 상어 위치 -1로 바꾸기
    graph[cur_r][cur_c][0] = -1
    graph[cur_r][cur_c][1] = 0

    # 물고기 이동
    move_fish(graph, cur_r, cur_c)

    # 상어 이동할 수 있는 위치 확인
    positions = get_fish_list(graph, cur_r, cur_c)

    # 상어가 이동할 수 없다면
    if len(positions) == 0:
        result = max(result, total)
        return
    # 모든 위치로 이동
    for next_r, next_c in positions:
        graph[cur_r][cur_c][0] = 0
        dfs(graph, next_r, next_c, total)


dfs(graph, 0, 0, 0)
# 먹을 수 있는 물고기 번호의 합 출력
print(result)