# 0과 1로만 이루어진 행렬 A와 행렬 B가 있다.
# 이때, 행렬 A를 행렬 B로 바꾸는데 필요한
# 연산의 횟수의 최솟값을 구하는 프로그램을 작성하시오.
#
# 행렬을 변환하는 연산은 어떤 3×3크기의 부분 행렬에 있는
# 모든 원소를 뒤집는 것이다. (0 → 1, 1 → 0)

# 입력
# 첫째 줄에 행렬의 크기 N M이 주어진다.
# N과 M은 50보다 작거나 같은 자연수이다.
# 둘째 줄부터 N개의 줄에는 행렬 A가 주어지고,
# 그 다음줄부터 N개의 줄에는 행렬 B가 주어진다.
#
# 출력
# 첫째 줄에 문제의 정답을 출력한다.
# 만약 A를 B로 바꿀 수 없다면 -1을 출력한다.

# 행렬을 더한다. 0인 부분은 고칠 필요가 없는 부분이다.
# 1인 행렬들의 위치를 바꿔야한다.
# 3x3행렬로 전부 바꿔가며 같아질 때까지 한다.
# 0,0 값이 다르면 바꾼다.


import sys
N, M = map(int, sys.stdin.readline().split())
count = 0
A = [list(map(int, input())) for _ in range(N)]
B = [list(map(int, input())) for _ in range(N)]

def convert3x3(x,y,arr):
    for i in range(x, x+3):
        for j in range(y, y+3):
            A[i][j] = 1 - A[i][j]

for i in range(0, N-2):
    for j in range(0, M-2):
        if A[i][j] != B[i][j]:
            convert3x3(i, j, A)
            count += 1


result = 1
for i in range(0, N):
    for j in range(0, M):
        if A[i][j] != B[i][j]:
            result = 0

if result == 0:
    print(-1)
else:
    print(count)
