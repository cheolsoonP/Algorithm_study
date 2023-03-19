# ATM

n = int(input())  # 사람의 수
p = input().split(' ')  # 각 사람이 인출에 걸리는 시간

sum = 0

for i in range(0, n):
    p[i] = int(p[i])

p.sort()

for i in range(0, n):
    sum += p[i]*(n-i)

print(sum)