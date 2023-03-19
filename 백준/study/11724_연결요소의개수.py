"""
문제
방향 없는 그래프가 주어졌을 때, 연결 요소 (Connected Component)의 개수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 정점의 개수 N과 간선의 개수 M이 주어진다.
(1 ≤ N ≤ 1,000, 0 ≤ M ≤ N×(N-1)/2)
둘째 줄부터 M개의 줄에 간선의 양 끝점 u와 v가 주어진다.
(1 ≤ u, v ≤ N, u ≠ v) 같은 간선은 한 번만 주어진다.
"""

"""
인접 리스트 방식 사용
방문 노드 체크하는 방식,
사이클을 체크하는 방식 진행
Python3로 채점시 런타임 에러
PyPy3로 채점 시 성공
"""

n, m = map(int, input().split())

graph = [[] for _ in range(n)]
# 인접 리스트 방식 그래프 생성, 무방향 그래프
for _ in range(m):
    u, v = map(int, input().split())
    graph[u-1].append(v-1)
    graph[v-1].append(u-1)


# 그래프 순회, 방문 노드 체크
def dfs(x):
    visited[x] = True

    for i in graph[x]:
        if visited[i] is False:
            dfs(i)
    return


# main
# 그래프 각 노드별로 방문, 이전 방문 노드는 제외
# 한 사이클 -> 연결 요소
visited = [False for _ in range(n)]
result = 0
for i in range(n):
    if visited[i] is False:
        dfs(i)
        result += 1

print(result)
