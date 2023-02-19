
"""
퇴사한다.
n+1번째 되는 날 퇴사하려고 한다,
남은 N일 동안 최대한 많은 상담 한다

비서는 하루에 하나씩 서로 다른 사람의 상담을 잡아 놓았다.
각각 상담 완료 걸리는 기간 Ti
상담 했을 때 받을 수 있는 금액 Pi
N =7 인 경우
상담을 적절히 했을 때 얻을 수 있는 최대 수익을 구해라.

해당 날짜까지 최대 이익 = 기존까지 최대 이익
i
1   2   3   4   5   6   7
0   0   10  10+
6일
3일 10 or 0
4일 10 + 10 or 10
5일 10 + 10 + 20
6일 10 + 10 + 20 or 20

"""

n = int(input())
day_cost = []
dp = [0 for _ in range(n+1)]
curr_max = 0
for i in range(n):
    day, cost = map(int, input().split())
    curr_max = max(curr_max, dp[i])
    if i+day > n:
        continue
    dp[i+day] = max(curr_max+cost, dp[i+day])

print(max(dp))