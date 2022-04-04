# 문제
# 세계적인 도둑 상덕이는 보석점을 털기로 결심했다.
#
# 상덕이가 털 보석점에는 보석이 총 N개 있다.
# 각 보석은 무게 Mi와 가격 Vi를 가지고 있다.
# 상덕이는 가방을 K개 가지고 있고, 각 가방에 담을 수 있는 최대 무게는 Ci이다.
# 가방에는 최대 한 개의 보석만 넣을 수 있다.
#
# 상덕이가 훔칠 수 있는 보석의 최대 가격을 구하는 프로그램을 작성하시오.
#
# 입력
# 첫째 줄에 N과 K가 주어진다. (1 ≤ N, K ≤ 300,000)
#
# 다음 N개 줄에는 각 보석의 정보 Mi와 Vi가 주어진다. (0 ≤ Mi, Vi ≤ 1,000,000)
#
# 다음 K개 줄에는 가방에 담을 수 있는 최대 무게 Ci가 주어진다. (1 ≤ Ci ≤ 100,000,000)
#
# 모든 숫자는 양의 정수이다.
#
# 출력
# 첫째 줄에 상덕이가 훔칠 수 있는 보석 가격의 합의 최댓값을 출력한다.

import sys
import heapq

N, K = map(int, sys.stdin.readline().split())
# N개의 보석, K개의 가방

# 각 보석의 무게, 가격 vi
# 각 가방의 최대 무게 ci
# 최대 가격을 구하는 것이다.
vi = []
ci = []
for i in range(0, N):
    heapq.heappush(vi, list(map(int, input().split())))
for i in range(0, K):
    ci.append(int(sys.stdin.readline()))

# 최대 가격으로 해야한다.
# 가방의 무게를 오름차순으로 정렬
ci.sort()

cost = 0
tmp = []
# 비싼 보석부터 작은가방부터 들어가는지 확인
# 들어가는 가방이 있으면 채워준다. cost += 해당보석의가격
for i in ci:
    while vi and i >= vi[0][0]:
        heapq.heappush(tmp, -heapq.heappop(vi)[1])
    if tmp:
        cost -= heapq.heappop(tmp)
    elif not vi:
        break


print(cost)

######## 시간초과로 실패
# 우선순위 큐를 이용해라.
#