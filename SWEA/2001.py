"""
mxm 파리채
최대한 많은 파리 죽여야한다.

각 포인트 -> x,y ~ x+m, y+m

5 5
2 2

"""

def check_sum(x, y, size):
    cur_score = 0
    for i in range(size):
        for j in range(size):
            nx = x+i
            ny = y+j
            if nx >= n or ny >= n:
                continue
            cur_score += graph[nx][ny]
    return cur_score

T = int(input())

for test_case in range(1, T+1):
    n, m = map(int, input().split())
    graph = []
    max_score = -1e9
    for i in range(n):
        graph.append(list(map(int, input().split())))

    for i in range(n):
        for j in range(n):
            score = check_sum(i, j, m)
            if score > max_score:
                max_score = score
    print("#"+str(test_case), max_score)