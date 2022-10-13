from collections import deque

n, k = map(int, input().split())
direction = [(-1, 0), (1, 0), (0, -1), (0, 1)]
board = list()
boaed.append(deque(list(map(int, input().split()))))


def push_fish_min_room(graph):
    min_count = min(graph[0])
    for i in range(len(graph[0])):
        if graph[0][i] == min_count:
            graph[0][i] += 1


def popleft_and_stack(graph):
    pop = graph[0].popleft()
    graph.append(deque([pop]))


def rotate_90_clockwise(bowls):
    # len(bowls) - 높이, len(bowls[0]) - 너비
    new_bowls = [[0] * len(bowls) for _ in range(len(bowls[0]))]
    for i in range(len(bowls[0])):
        for j in range(len(bowls)):
            # 시계로 회전
            new_bowls[i][j] = bowls[j][len(bowls[0])-1-i]

    return new_bowls


def fly_blocks(graph):
    while True:
        # 전체 높이 > 바닥 길이 - 분리될 길이
        if len(graph) > len(graph[0]) - len(graph[-1]):
            break

        will_fly_blocks = []
        will_fly_blocks_row = len(graph)
        will_fly_blocks_col = len(graph[-1])

        for i in range()