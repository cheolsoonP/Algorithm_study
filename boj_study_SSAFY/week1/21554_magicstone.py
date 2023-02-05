

n = int(input())
arr = list(map(int, input().split()))
count = 0
control = []

for i in range(len(arr)):
    if arr[i] == i+1:
        continue
    else:
        for j in range(i+1, len(arr)):
            if arr[j] == i+1:
                count += 1
                # arr[i], arr[j] = arr[j], arr[i]
                # arr[i:j+1] = sorted(arr[i:j+1])
                arr[i:j+1] = reversed(arr[i:j+1])
                control.append([i+1, j+1])
                break
    if count >= 100:
        break

if count >= 100:
    print(-1)
else:
    print(count)
    for i in range(len(control)):
        print(control[i][0], control[i][1])
