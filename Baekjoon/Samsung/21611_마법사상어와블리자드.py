"""
블리자드
nxn - 홀수
1,1 ~ n,n

n+1 / 2, n+1 / 2 위치에 있다. (가운데 있다. )

일부칸과 칸사이는 벽이있다.
칸에는 구슬 번호가 있다
같은 번호가 연속하면 연속하는 구슬이라고 한다
블리자드 시전하려면 di si 정해야 한다
4가지 방향
↑, ↓, ←, →
1 2 3 4

상어는 di 방향, si이라인 모든 칸에 얼음을 던져 그 구슬을 모두 파괴
그칸은 빈칸이 된다

구슬은 빈칸으로 이동한다. 구슬이 더이상 이동하지 않을때까지 반복
그다음 구슬이 폭발한다 - 4개 이상 연속하는 같은 값이 있을때
다시 구슬이 이동하고
다시 폭발한다
폭발이 없을때까지 한다.

구슬이 변화한다
그룹으로 이루어진다. 각 그룹은 연속된 값을 기준으로 한다.
각 그룹은 한 그룹은 구슬 A와 B로 변한다. A는 구슬의 개수, B는 그룹의 구슬의 번호이다.

1* 폭발한 1번 구슬의 개수 + 2 * 폭발한 2번 구슬 개수 + 3 * 폭발한 3번 구슬 개수 출력

첫째 줄에 N, M이 주어진다.
둘째 줄부터 N개의 줄에는 격자에 들어있는 구슬의 정보가 주어진다.
r번째 행의 c번째 정수는 (r, c)에 들어있는 구슬의 번호를 의미한다.
어떤 칸에 구슬이 없으면 0이 주어진다. 상어가 있는 칸도 항상 0이 주어진다.

다음 M개의 줄에는 블리자드 마법의 방향 di와 거리 si가 한 줄에 하나씩 마법을 시전한 순서대로 주어진다.
"""

n, m = map(int, input().split())

graph = []
for i in range(n):
    graph.append(list(map(int, input().split())))

direction = 0
for i in range(n):
    for j in range(n):
        

# ↑, ↓, ←, →
# 0 1 2 3
mdx = [-1, 1, 0, 1]
mdy = [0, 0, -1, 1]
magic = []
for i in range(m):
    d, s = map(int, input().split())
    magic.append([d-1, s])

# 구슬 확인 - 맨위부터,
# 오른쪽, 아래, 왼쪽, 위쪽
cdx = [0, 1, 0, -1]
cdy = [1, 0, -1, 0]

shark_x, shark_y = (n - 1) / 2, (n - 1) / 2


# 구슬을 파괴한다.
def magic(direction, distance):

    for i in range(1, distance+1):
        nx = shark_x + (mdx[direction] * i)
        ny = shark_y + (mdy[direction] * i)
        if 0 <= nx < n and 0 <= ny < n:
            graph[nx][ny] = 0


# 구슬 빈칸 이동
def move(x, y):
    while True:
        if x
    for i in range(shark_x-1, ):
