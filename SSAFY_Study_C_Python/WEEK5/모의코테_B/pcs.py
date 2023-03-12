"""

"""
"""
우수마을 
n개 마을
1~n 번호
트리 구조
마을 마을 사이 n-1개 길
방향이 없다 

모든 마을 연결
직접 가는 길 - > 인접해있다
3가지 조건을 만족하면서 n개 마을 중 몇개의 마을을 우수 마을로 선정한다
1. 우수마을 선정된 마을 주민 수 총합 최대로 한다
2. 마을 사이 충동 방지, 두 마을 인접, 두 마을 모두 우수마을 할 수 없다
(우수마을 인접 불가)
3. 선정되지 못한 마을 경각심, 우수마을이 아닌 마을은 [적어도 하나 우수마을과 인접]

각 마을 주민수, 마을 사이의 길에 대한 정보, 우수마을을 선정하는 프로그램 작성해라. 
"""
from collections import deque
n = int(input())
# n개 마을 주민수
# 1번 마을 우수
dp = [0 for _ in range(n+1)]
town = list(map(int, input().split()))
graph = [[] for _ in range(n+1)] # 각 마을과 인접한 마을 리스트
for i in range(n-1):
    fr, to = map(int, input().split())
    graph[fr].append(to)
    graph[to].append(fr)

for i in range(n):
