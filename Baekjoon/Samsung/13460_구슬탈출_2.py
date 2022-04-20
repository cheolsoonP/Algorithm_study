"""
직사각형 보드에 빨간구슬 파란구슬 하나씩 넣고 구멍으로 빼는 게임
N x M 크기
가장 바깥 행, 열 막혀 있다.
파란 구슬이 구멍에 들어가면 out 안됨
중력으로 굴림 왼쪽으로 기울이기, 오른쪽으로 기울이기 위쪽으로 기울이기 아래쪽으로 기울이기
왼, 오, 위, 아래
빨간구슬, 파란구슬 동시에 빠져도 실패
같은 칸에 있을 수 없다.
기울이기는 동작은 구슬이 움직이지 않을때까지
최소 몇 번만에 빨간 구슬을 구멍을 통해 뺄 수 있는가?

입력 3<= N, M <=10

 이 문자열은 '.', '#', 'O', 'R', 'B' 로 이루어져 있다.
 '.'은 빈 칸을 의미하고,
 '#'은 공이 이동할 수 없는 장애물 또는 벽을 의미하며,
 'O'는 구멍의 위치를 의미한다.
 'R'은 빨간 구슬의 위치,
 'B'는 파란 구슬의 위치이다.

입력되는 모든 보드의 가장자리에는 모두 '#'이 있다.
구멍의 개수는 한 개 이며, 빨간 구슬과 파란 구슬은 항상 1개가 주어진다.

 R -> O로 가면됨

 10번이 넘으면 -1 실패
"""

from collections import deque

n, m = map(int, input().split())
red = 'R'
blue = 'B'

graph = []
for i in range(n):
    graph.append(list(input()))

queue = deque()

# queue에 초기값 세팅 red구슬 위치, blue구슬 위치
ri, rj, bi, bj, point = 0, 0, 0, 0, 0
for i in range(n):
    for j in range(m):
        if graph[i][j] == 'R':
            ri, rj = i, j
        if graph[i][j] == 'B':
            bi, bj = i, j
queue.append([ri, rj, bi, bj, point])


def bfs():
    dx = [0, 0, -1, 1]
    dy = [1, -1, 0, 0]

    visited = [[[[0] * m for _ in range(n)] for _ in range(m)] for _ in range(n)]
    visited[ri][rj][bi][bj] = 1

    while(queue):
        cur_ri, cur_rj, cur_bi, cur_bj, cur_point = queue.popleft()
        if cur_point > 10:
            return -1
        if graph[cur_ri][cur_rj] == 'O' and graph[cur_bi][cur_bj] != 'O':
            return cur_point

        for i in range(4):
            next_ri = cur_ri
            next_rj = cur_rj
            next_bi = cur_bi
            next_bj = cur_bj

            # 빨간공 움직임
            while True:
                next_ri += dy[i]
                next_rj += dx[i]
                if graph[next_ri][next_rj] == '#':
                    next_ri -= dy[i]
                    next_rj -= dx[i]
                    break
                if graph[next_ri][next_rj] == 'O':
                    break

            # 파란공 움직임
            while True:
                next_bi += dy[i]
                next_bj += dx[i]
                if graph[next_bi][next_bj] == '#':
                    next_bi -= dy[i]
                    next_bj -= dx[i]
                    break
                if graph[next_bi][next_bj] == 'O':
                    return -1

            # 벽이 아닌곳에 빨간공과 파란공이 같이 있을 경우
            if next_ri == next_bi and next_rj == next_bj:
                if graph[next_ri][next_rj] != 'O':
                    red_dist = abs(next_ri - cur_ri) + abs(next_rj - cur_rj)
                    blue_dist = abs(next_bi - cur_bi) + abs(next_bj - cur_bj)
                    if blue_dist > red_dist:
                        next_bi -= dy[i]
                        next_bj -= dx[i]
                    else:
                        next_ri -= dy[i]
                        next_rj -= dx[i]

            if visited[next_ri][next_rj][next_bi][next_bj] == 0:
                visited[next_ri][next_rj][next_bi][next_bj] = 1
                queue.append([next_ri, next_rj, next_bi, next_bj, cur_point+1])

    return -1


result = bfs()
print(result)
