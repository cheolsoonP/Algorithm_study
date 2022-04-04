

n = int(input())
a = 1000 - n

l = [500, 100, 50, 10, 5, 1]

coin = 0
while a > 0:
    for i in l:
        coin += a//i
        a = a - ((a//i) * i)

print(coin)
