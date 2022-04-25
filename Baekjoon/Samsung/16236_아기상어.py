"""
NxN
물고기 M 마리
처음 아기상어 크기 - 2
각 칸 별로 물고기 크기가 정해짐

상어보다 작은 물고기만 먹을 수 있다.
(상어 크기만큼 물고기 먹으면 상어 크기+1, 상어 크기가 2, 2마리 먹으면 상어 크기 3 된다.)
상어랑 같은 크기 물고기 먹을 수 없다. 지나갈 수는 있다.
상어보다 큰 물고기는 지나갈 수 없다.


더이상 먹을 물고기가 없으면 종료
물고기를 먹는데 걸리는 시간을 출력
상어가 한칸 움직일때마다 1초씩 증가
상어가 먹을 수 있는 물고기가 1마리 -> 잡아 먹으러 감
상어가 먹을 수 있는 물고기가 여러마리 -> 가까운 곳부터 감
(가장 위쪽, 가장 왼쪽부터 먹는다.)

첫째 줄에 공간의 크기 N(2 ≤ N ≤ 20)이 주어진다.

둘째 줄부터 N개의 줄에 공간의 상태가 주어진다.
공간의 상태는 0, 1, 2, 3, 4, 5, 6, 9로 이루어져 있고
0: 빈 칸
1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
9: 아기 상어의 위치
아기 상어는 공간에 한 마리 있다.

"""
from collections import deque

n = int(input())

graph = []
temp = [[0] * n for _ in range(n)]
for i in range(n):
    graph.append(list(map(int, input().split())))

# 상어 초기 위치 찾기, 그 위치에 아무도 없다고 하기
now_size = 2
eat = 0
now_r, now_c = 0, 0
times = 0
for i in range(n):
    for j in range(n):
        if graph[i][j] == 9:
            now_r, now_c = i, j
            graph[now_r][now_c] = 0

# 상, 하, 좌, 우
dr = [1, -1, 0, 0]
dc = [0, 0, -1, 1]


# 최단 거리 구하기 (현재 상어에서 각 물고기까지)
def bfs():
    # dist -1이면 갈 수 없는 위치
    dist = [[-1] * n for _ in range(n)]
    q = deque()
    q.append((now_r, now_c))
    dist[now_r][now_c] = 0

    while q:
        cur_r, cur_c = q.popleft()
        for i in range(4):
            nr, nc = cur_r + dr[i], cur_c + dc[i]

            if 0 <= nr < n and 0 <= nc < n:
                if graph[nr][nc] <= now_size and dist[nr][nc] == -1:
                    dist[nr][nc] = dist[cur_r][cur_c] + 1
                    q.append((nr, nc))

    return dist


# 거리가 같은 물고기 발견 시, 가장 위쪽(r이 작을수록), 가장 왼쪽(c가 작을수록) 먼저 방문
# 가까운 물고기로 이동 후, 먹기
# 다시 탐색, 가까운 물고기로 이동 후 먹기, 반복
# 먹을 수 있는 물고기 리스트, 거리 가까운 곳 찾기
def find_fish(dist):
    cost = 1e9
    next_r, next_c = 0, 0
    for i in range(n):
        for j in range(n):
            if dist[i][j] != -1 and 1 <= graph[i][j] < now_size:
                if dist[i][j] < cost:
                    cost = dist[i][j]
                    next_r, next_c = i, j

    if cost == 1e9:
        return None
    else:
        return next_r, next_c, cost


while True:
    value = find_fish(bfs())
    if value is None:
        print(times)
        break
    else:
        # 시간 추가
        times += value[2]
        # 현재 좌표 수정
        now_r, now_c = value[0], value[1]
        graph[now_r][now_c] = 0

        # 상어가 충분히 먹으면 성장
        eat += 1
        if eat >= now_size:
            now_size += 1
            eat = 0
