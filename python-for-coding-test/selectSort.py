arr = [7, 5, 3, 2, 1, 0, 4, 6]

for i in range(len(arr)):
    min_idx = i
    # 가장 작은 원소 찾기 i+1~ N-1
    for j in range(i+1, len(arr)):
        if arr[min_idx] > arr[j]:
            min_idx = j
    # 가장 작은 원소와 현재 위치 변경 swap
    arr[i], arr[min_idx] = arr[min_idx], arr[i]

print(arr)
