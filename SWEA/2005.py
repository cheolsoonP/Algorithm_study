
"""
파스칼 삼각형
1배열 - 1
2배열 - 1 1
3배열 - 1 1+1 1
4배열 - 1 1+2 2+1 1
5배열 - 1 1+3 3+3 3+1 1
...

"""

T = int(input())

for test_case in range(1, T+1):
    n = int(input())

    graph = []

    # make 파스칼
    for i in range(n+1):
        list = []
        for j in range(i):
            if j == 0 or j == i-1:
                list.append(1)
            else:
                list.append(graph[i-1][j-1]+graph[i-1][j])
        graph.append(list)

    print("#"+str(test_case))
    for i in range(1, n+1):
        for j in range(len(graph[i])):
            print(graph[i][j], end=" ")
        print()
