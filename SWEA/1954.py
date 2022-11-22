"""
달팽이 출력
"""
# 우 하 좌 상
dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

T = int(input())

for test_case in range(1, T+1):
    n = int(input())
    graph = [[0 for _ in range(n)] for _ in range(n)]

    dir = 0
    x, y = 0, 0
    graph[0][0] = 1
    for i in range(2, n*n+1):
        nx, ny = x + dx[dir], y + dy[dir]
        if nx < 0 or nx >= n or ny < 0 or ny >= n:
            dir = (dir + 1) % 4
            nx, ny = x + dx[dir], y + dy[dir]
        if graph[nx][ny] > 0:
            dir = (dir + 1) % 4
            nx, ny = x + dx[dir], y + dy[dir]
        graph[nx][ny] = i
        x, y = nx, ny


    print("#"+str(test_case))

    for i in range(n):
        for j in range(n):
            print(graph[i][j], end=" ")
        print()