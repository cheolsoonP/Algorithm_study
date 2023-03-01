"""
A와 B의 최대 공약수 (A > B)
A%B = R
A, B 최대공약수 = B, R 최대공약수

"""

A, B = map(int, input().split())

result = -1


def dfs(n, m):
    global result
    print(n, m)
    if n % m == 0:
        result = m
        return
    dfs(m, n % m)


if A > B:
    dfs(A, B)
else:
    dfs(B, A)

print(result)