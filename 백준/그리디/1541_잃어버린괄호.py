
a = input().split('-')

total = []
for i in a:
    sum = 0
    b = i.split('+')
    for j in b:
        sum += int(j)
    total.append(int(sum))

n = total[0]

for i in range(1, len(total)):
    n -= total[i]

print(n)