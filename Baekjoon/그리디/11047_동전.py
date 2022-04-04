# 동전 0

n, k = map(int, input().split(' '))

l = 0
coin = 0

a = []


for i in range(0, n):
    a.append(int(input()))


for i in range(0, n):
    if(k//a[n-i-1] >= 1):  # 몫이 있는 부분을 구함
        l = n-i-1
        break

while k >= 0:
    coin += (k//a[l])  # 가장 큰 코인으로 나누어서 사용
    k = (k%a[l])
    l -= 1
    if(l<0):
        break

print(coin)