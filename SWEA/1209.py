

T = 10

for test_case in range(1, T+1):
    t = int(input())
    graph = []

    for i in range(100):
        graph.append(list(map(int, input().split())))

    result = []
    # 행의 합
    for i in range(100):
        row_sum = 0
        for j in range(100):
            row_sum += graph[i][j]
        result.append(row_sum)

    # 열의 합
    for i in range(100):
        col_sum = 0
        for j in range(100):
            col_sum += graph[j][i]
        result.append(col_sum)
    # 대각선 합
    cross1, cross2 = 0, 0
    for i in range(100):
        cross1 += graph[i][i]
        cross2 += graph[100-1-i][i]
    result.append(cross1)
    result.append(cross2)

    print("#"+str(test_case), max(result))