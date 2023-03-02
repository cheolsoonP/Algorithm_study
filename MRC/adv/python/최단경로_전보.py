
"""n, m, start_city = map(int, input().split())
INF = 1e9

# 시작, 도착, 비용
graph = [[INF for _ in range(n+1)] for _ in range(n+1)]
for i in range(m):
    start, end, cost = map(int, input().split())
    graph[start][end] = cost

# 초기 거리 그래프 생성
print(graph)

second_cities = set()


# 시작위치 -> 도착지점 확인
def find_dest(start_ct):
    cities = set()
    for i in range(1, n+1):
        if graph[start_ct][i] < INF:
            cities.add(i)
    return cities

# 시작위치 -> A -> dest가 존재하는지 찾기, 비용 작은 값으로 변경
def find_new_course(start_ct, cities):
    print(cities)
    isChange = 0
    for city in cities:
        for i in range(1, n+1):
            if graph[city][i] < INF:
                new_cost = graph[start_ct][city] + graph[city][i]
                now_cost = graph[start_ct][i]
                if new_cost < now_cost:
                    graph[start_ct][i] = new_cost
                    isChange = 1
    return isChange


change = 1
while change:
    cities = find_dest(start_city)
    change = find_new_course(start_city, cities)

max_cost = -INF
for i in range(1, n+1):
    if max_cost < graph[start_city][i] < INF:
        max_cost = graph[start_city][i]

print(len(cities), max_cost)
"""

"""
시간 초과가 날 수 있다. -> 우선순위 큐를 활용한 다익스트라 알고리즘을 구현한다.
"""
"""
2번째 풀이 - 다익스트라 알고리즘 활용
"""

import heapq
import sys
inout = sys.stdin.readline
INF = int(1e9) # 무한을 의미하는 값으로 10억을 설정

def dijkstra(start):
    q = []
    heapq.heappush(q, (0, start))
    distance[start] = 0
    while q:
        dist, now = heapq.heappop(q)
        if distance[now] < dist:
            continue
        for i in graph[now]:
            cost = dist + i[1]
            if cost < distance[i[0]]:
                distance[i[0]] = cost
                heapq.heappush(q, (cost, i[0]))

n, m, start = map(int, input().split())
graph = [[] for _ in range(n+1)]
distance = [INF] * (n+1)

for _ in range(m):
    x, y, z = map(int, input().split())
    graph[x].append((y, z))

dijkstra(start)

count = 0

max_distance = 0
for d in distance:
    if d != 1e9:
        count += 1
        max_distance = max(max_distance, d)

print(count-1, max_distance)
