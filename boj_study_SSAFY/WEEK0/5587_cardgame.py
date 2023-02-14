
n = int(input())

a = []
b = []
for i in range(n):
    a.append(int(input()))

for i in range(1,2*n+1):
    if i not in a:
        b.append(i)

a.sort()
b.sort()


last = 0

while True:
    for i in range(len(a)):
        if a[i] > last:
            last = a[i]
            a.pop(i)
            break
        if i == len(a) - 1:
            last = 0

    if len(a) <= 0:
        print(len(b))
        print(len(a))
        break

    for i in range(len(b)):
        if b[i] > last:
            last = b[i]
            b.pop(i)
            break
        if i == len(b)-1:
            last = 0

    if len(b) <= 0:
        print(len(b))
        print(len(a))
        break
