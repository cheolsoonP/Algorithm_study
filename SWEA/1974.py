
T = int(input())

for test_case in range(1, T + 1):
    graph = []

    for i in range(9):
        graph.append(list(map(int, input().split())))

    # 9x9 스도쿠 확인,

    # 1. 행 확인
    for i in range(9):
        check = set()
        for j in range(9):
            if graph[i][j] in check:
                answer = 0
            else:
                check.add(graph[i][j])

    # 2. 열 확인
    for i in range(9):
        check = set()
        for j in range(9):
            if graph[j][i] in check:
                answer = 0
            else:
                check.add(graph[j][i])

    # 3. 3x3 확인
    for i in range(0, 9, 3):
        for j in range(0, 9, 3):

            print(i, j)
            # print(graph[i:i+2][j:j+2])

