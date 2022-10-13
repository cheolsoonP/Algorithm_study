# 파란색, 초록색 따로 두고 구현 해보기 - 헷갈리지 않게.

from collections import deque

# 0-아래, 1-오른쪽
dx = [1, 0]
dy = [0, 1]


def move_block(t, x, y):
    if t == 1:
        for i in range(9, 5, -1):
            if graph[x][i] == 0:
                graph[x][i] = 1
                break
        for i in range(9, 5, -1):
            if graph[i][y] == 0:
                graph[i][y] = 1
                break
    if t == 2:
        for i in range(9, 5, -1):
            if graph[x][i] == 0:
                graph[x][i], graph[x][i-1] = 1, 1
                break
        for i in range(9, 5, -1):
            if graph[i][y] == 0 and graph[i][y+1] == 0:
                graph[i][y], graph[i][y+1] = 1, 1
                break
    if t == 3:
        for i in range(9, 5, -1):
            if graph[x][i] == 0 and graph[x+1][i] == 0:
                graph[x][i], graph[x+1][i] = 1, 1
                break
        for i in range(9, 5, -1):
            if graph[i][y] == 0:
                graph[i][y], graph[i-1][y] = 1, 1
                break
    return graph


def remove_block(direction, index):
    # direction - 0: 열/ 1: 행
    for i in range(index, 4, -1):
        if direction == 0:
            for j in range(4):
                graph[j][i] = graph[j][i-1]
        if direction == 1:
            graph[i][:] = graph[i-1][:]
    if direction == 0:
        for i in range(10):
            graph[i][4] = 0
    if direction == 1:
        for i in range(10):
            graph[4][i] = 0
    return graph

def check_score():
    score = 0

    # 1. 열에서 체크
    for i in range(9, 5, -1):
        if list(zip(*graph))[i][0:4] == (1, 1, 1, 1):
            remove_block(direction=0, index=i)
            score += 1

    # # 2. 행에서 체크
    for i in range(9, 5, -1):
        if graph[i][:][0:4] == [1, 1, 1, 1]:
            remove_block(direction=1, index=i)
            score += 1

    return score


def check_overblock():
    # 1. 열 체크
    for i in range(6, 4, -1):
        if 1 in graph[i][:]:
            remove_block(direction=1, index=9)
        if 1 in graph[:][i]:
            remove_block(direction=0, index=9)


def get_remain():
    count = 0
    for i in range(10):
        for j in range(10):
            if graph[i][j] == 1:
                count += 1
    return count

k = int(input())

graph = [[0 for _ in range(10)] for _ in range(10)]
game = []
for i in range(k):
    game.append(list(map(int, input().split())))

score = 0
for i in range(k):
    t, x, y = game[i]
    graph = move_block(t, x, y)
    score += check_score()
    score += check_score()
    check_overblock()


print(score)
print(get_remain())