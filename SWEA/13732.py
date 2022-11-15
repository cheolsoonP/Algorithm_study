"""
. - 비어있다
# 막혀있다.

막혀있는 칸이 정사각형을 이루는지 판단
정사각형 -> 1x1 2x2 3x3 4x4 5x5 최대 1x1~ nxn

0,0
0,0, 0,1 1,0 1,1
"""

def check_square(blocks):
    for i in range(1, n+1):
        if i * i == len(blocks):
            b_size = i
            break

    for i in range(len(blocks)):


    for i in range(len(blocks)):
        x, y = blocks[i]

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    n = int(input())
    graph = []
    block = []
    for i in range(n):
        graph.append(list(input()))

    for i in range(n):
        for j in range(n):
            if graph[i][j] == '#':
                block.append((i, j))
    print(block)

    # check_square(block, n)