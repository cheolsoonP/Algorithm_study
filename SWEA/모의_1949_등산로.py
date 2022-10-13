# 1949. [모의 SW 역량테스트] 등산로 조성
# https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PoOKKAPIDFAUq&categoryId=AV5PoOKKAPIDFAUq&categoryType=CODE&problemTitle=%EB%AA%A8%EC%9D%98&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=2

"""
n x n
각 숫자는 지형의 높이,

1. 등산로는 가장 높은 봉우리에서 시작
2. 산으로 올라갈 수 있도록 높은 지형에서 낮은 지형으로 가로 또는 세로 방향으로 연결,
높이가 같은곳 이동 X

3. 긴 등산로를 만들기 위해 딱 한 곳을 정해서 최대 k깊이 만큼 지형을 깎을 수 있다.

 가장 긴 등산로를 찾아 그 길이를 출력

## 가장 높은 곳에서 시작, 가로 세로 확인,
높이가 낮은 곳으로만 이동 가능,
높은 곳이거나 같은 지형일 경우 1~k까지 깎아보고 진행





"""
from deepcopy import copy

n, k = map(int, input().split())

track = []
for _ in range(n):
    track.append(list(map(int, input().split())))

top = -999
for i in range(n):
    for j in range(n):
        if track[i][j] > top:
            top = track[i][j]


def tracking(r, c, height):
    visit = []
    visit.append([r, c])

    temp = deepcopy(track)



for i in range(n):
    for j in range(n):
        if track[i][j] >= top:
            tracking(i, j, track[i][j])

