# 회의실 배정

n = int(input())  # 회의 개수

a = []

for i in range(0, n):
    first, second = map(int, input().split(' '))
    a.append([first, second])

a = sorted(a, key=lambda a:a[0])

a = sorted(a, key=lambda a:a[1])


last = 0
cnt = 0

for i,j in a:
    if i >= last:
        cnt += 1
        last = j
print(cnt)