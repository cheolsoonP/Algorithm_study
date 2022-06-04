"""
14499 주사위 굴리기
    # if mv == 1:
    #     # 동으로 가면 (0,1), (2,1) 고정
    #     # (3,1)>(1,0), (1,0)>(1,1), (1,1)>(1,2), (1,2)>(3,1)
    #     dice[1][0], dice[1][1], dice[1][2], dice[3][1] = dice[3][1], dice[1][0], dice[1][1], dice[1][2]
    # elif mv == 2:
    #     # 서로 가면
    #     # (1,0>3,1) (3,1>0,1) (0,1>1,1) (1,1>1,0)
    #     dice[3][1], dice[0][1], dice[1][1], dice[1][0] = dice[1][0], dice[3][1], dice[0][1], dice[1][1]
    # elif mv == 3:
    #     # 북으로 가면
    #     #       (3,1>2,1) (2,1>1,1) 1,1>0,1> 0,1>3,1
    #     dice[2][1], dice[1][1], dice[0][1], dice[3][1] = dice[3][1], dice[2][1], dice[1][1], dice[0][1]
    # elif mv == 4:
    #     # 남으로 가면
    #     #       (1,1>2,1) (2,1>3,1) (3,1>0,1) (0,1>1,1)
    #     dice[2][1], dice[3][1], dice[0][1], dice[1][1] = dice[1][1], dice[2][1], dice[3][1], dice[0][1]
    # if

    x, y가 순서가 잘못 입력 되어 있었음
"""
n, m, y, x, k = map(int, input().split())

graph = []
for i in range(n):
    graph.append(list(map(int, input().split())))
mv_seq = list(map(int, input().split()))

dice = [0 for _ in range(7)]

dx = [0, 1, -1, 0, 0]
dy = [0, 0, 0, -1, 1]

for mv in mv_seq:
    if 0 <= x + dx[mv] < m and 0 <= y + dy[mv] < n:
        if mv == 1:  # 동
            dice[6], dice[3], dice[1], dice[4] = dice[3], dice[1], dice[4], dice[6]
        elif mv == 2:  # 서
            dice[6], dice[4], dice[1], dice[3] = dice[4], dice[1], dice[3], dice[6]
        elif mv == 3:  # 북
            dice[1], dice[2], dice[6], dice[5] = dice[5], dice[1], dice[2], dice[6]
        elif mv == 4:  # 남
            dice[1], dice[5], dice[6], dice[2] = dice[2], dice[1], dice[5], dice[6]

        x += dx[mv]
        y += dy[mv]
        if graph[y][x] == 0:
            graph[y][x] = dice[6]
        else:
            dice[6] = graph[y][x]
            graph[y][x] = 0

        print(dice[1])

