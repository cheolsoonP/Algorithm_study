# 1734 ~ 1801

# 서류 면접 적어도 떨어지지않는 참가자 최대 인원수를 구하는 프로그램
# 적어도 하나가 다른 지우너자보다 떨어지지 않는자만 선별한다.
# 첫째줄에 테스트 케이스 개수, 각 테스트 케이스의 첫째줄에 지원자의 수
# 둘째 줄부터 N개 줄에는 지원자의 서류 성적, 면접 성적이 주어진다.
# 성적과 순위를 헷갈리지 말아라.
# 순위가 주어진다.

# 서류 기준으로 정렬, 면접 기준은 서류 점수 첫번째 사람을 기준으로 더 높은 등수이면
# 뽑음 뽑으면 또 그 사람의 면접 순위가 기준이 됨.
# 1 4 v
# 2 3
# 3 2
# 4 1 v
# 5 5
#
# 1 4 v
# 2 5
# 3 6
# 4 2 v
# 5 7
# 6 1 v
# 7 3

import sys

t = int(input())


result = []

for i in range(0,t):
    n = int(input())
    rank = []
    for j in range(0,n):
        a, b = sys.stdin.readline().split(' ')
        rank.append([int(a), int(b)])

    rank.sort(key=lambda x:x[0])

    stand = int(rank[0][1])
    count = 1
    for k in range(1, len(rank)):
        if(rank[k][1] < stand):
            count += 1
            stand = rank[k][1]

    result.append(count)

for i in result:
    print(i)


# 시간 제한 2초 > 지원자 수가 10만명까지 이므로
# input보다는 sys 모듈을 사용하여 받는다.