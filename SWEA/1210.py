T = 10

for test_case in range(1, T+1):
    n = 100
    case = int(input())
    graph = []
    for i in range(n):
        graph.append(list(map(int, input().split())))

    j = 0
    for i in range(n):
        if graph[99][i] == 2:
            j = i
    i = 98
    print((i, j))

    while i != 0:
        if j-1 >= 0 and graph[i][j-1] == 1:
            # 왼쪽 사다리
            while j-1 >= 0 and graph[i][j-1] == 1:
                j -= 1
        elif j+1 < n and graph[i][j+1] == 1:
            while j+1 < n and graph[i][j+1] == 1:
                j += 1
        i -= 1
    print("#"+str(test_case), j)
