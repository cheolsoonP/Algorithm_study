"""
14501 퇴사
백준은 퇴사를 한다
오늘부터 N+1일 째 되는 날 퇴사를 하기 위해 남은 N일 동안 최대한 많은 상담
비서 최대한 많은 상담
N = 7일때, 8일째 되는날 퇴사
1, 2, 3, 4, 5, 6, 7일 동안 최대한 많은 상담

비서 - 하루에 하나씩 서로 다른 사람의 상담
각 상담 완료하는데 걸리는 기간 Ti
상당시 받을 수 있는 금액 Pi
최대 수익을 계산.. ->
DFS, BFS 문제. 1일을 수행 -> 이후 일정 ->
2일을 수행 -> 이후 일정
...
BFS, DFS 문제를 책으로 풀어 보고 오자.
-> DP 문제

"""

# 퇴사 DP
n = int(input())
T, P = [0 for i in range(n+1)], [0 for i in range(n+1)]
for i in range(n):
    a,b = map(int, input().split())
    T[i] = a
    P[i] = b

# dp[i]는 i번째날까지 일을 했을 때, 최대값이다.
dp =[0 for i in range(n+1)]

for i in range(len(T)-2, -1, -1):      # 역순으로 진행
    if T[i]+i <= n:       # 날짜를 초과하지 않을 경우.
        dp[i] = max(P[i] + dp[i + T[i]], dp[i+1])
    else:                 # 날짜를 초과할 경우.
        dp[i] = dp[i+1]
print(dp[0])