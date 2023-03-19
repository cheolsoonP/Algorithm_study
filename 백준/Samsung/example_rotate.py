graph = [[i+j for i in range(3)] for j in range(3)]
n = 3
for i in range(3):
    print(graph[i])

# 90도 시계 방향 회전
def rotate_90():
    new_graph = [[0 for _ in range(3)] for _ in range(3)]
    for i in range(n):
        for j in range(n):
            new_graph[j][n-1-i] = graph[i][j]
    for i in range(3):
        print(new_graph[i])
    return new_graph

def rotate_180():
    new_graph = [[0 for _ in range(3)] for _ in range(3)]
    for i in range(n):
        for j in range(n):
            new_graph[n-1-i][n-1-j] = graph[i][j]
    for i in range(3):
        print(new_graph[i])
    return new_graph

def rotate_reverse_90():
    new_graph = [[0 for _ in range(3)] for _ in range(3)]

    for i in range(n):
        for j in range(n):
            new_graph[n-1-j][i] = graph[i][j]
    for i in range(3):
        print(new_graph[i])
    return new_graph


rotate_reverse_90()