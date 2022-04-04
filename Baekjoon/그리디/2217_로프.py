k = int(input())

w = []
total = []

for i in range(0, k):
    w.append(int(input()))

w.sort(reverse=True)
for i in range(1, k+1):
    total.append(int(i * w[i-1]))

print(max(total))
