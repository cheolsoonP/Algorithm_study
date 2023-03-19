result = 0
fish = [None, [1, 2, 3], None, [1,2,2], None]
for i in range(len(fish)):
    if fish[i]:
        result += 1

print(result)