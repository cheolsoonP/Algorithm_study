# 1818 ~ 1846

# N개 도시, 수평방향으로 제일 왼쪽 > 제일 조른쪽 이동
# 도로길이 km 기름을 넣고 출발 1km마다 1L 각 도시마다 리터당 가격이 다르다
# 원안 해당 도시의 리터당 가격, 도로 길이 주어짐
# 최소 비용으로 가야한다.

# 도시 개수 N
# 도로 길이 주어짐
# 주유소 리터당 가격 주어짐

import sys

n = int(sys.stdin.readline())
distance = list(map(int, sys.stdin.readline().split(' ')))
oilCost = list(map(int, sys.stdin.readline().split(' ')))
del oilCost[n-1]
cost = 0
# 5 (2) 2 (3) 4 (1) 1

# 비용이 비싼 곳에서는 최소한으로 주유하고, 비용이 싼 곳에서는 최대한 많이 주유 한다.

sortCost = sorted(oilCost)
limCost = sortCost[0]
remDistance = sum(distance)

i = 0
while(remDistance > 0):

    if (remDistance <= 0):
        break

    if(oilCost[i] <= limCost):
        # 해당 도시가 최저가 이면, 해당 도시의 비용으로 남은 거리를 충전하고 감
        cost += oilCost[i] * remDistance
        remDistance = 0
    else:
        # 최저가가 아니라면 다음까지 가야할 거리만큼만 충전하고 감
        cost += oilCost[i] * distance[i]
        remDistance -= distance[i]
    i += 1

print(cost)