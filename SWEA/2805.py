

T = int(input())

for test_case in range(1, T+1):
    n = int(input())
    result = []
    graph = []
    for i in range(n):
        graph.append(list(map(int, input())))

    # 다이아 그리기
    center = n//2
    for i in range(n//2):
        result.append(sum(graph[i][center-i:center+i+1]))
    result.append(sum(graph[n//2][:]))
    for i in range(n//2+1, n):
        result.append(sum(graph[i][center-(n-i-1):center+(n-i-1)+1]))

    print("#"+str(test_case), sum(result))
