# 삽입 정렬
# 보통의 경우 O(n^2)의 시간 복잡도를 가지지만
# 거의 정렬이 되어 있는 경우에는 매우 빠르게 동작한다.
# 입력 데이터가 거의 정렬이 되어 있다면 다른 정렬 알고리즘을 사용하는 것보다
# 삽입 정렬을 사용하는 것이 정답률을 높인다.

arr = [7, 5, 9, 0, 3, 1, 6, 2, 4, 8]

for i in range(1, len(arr)):
    # 한칸씩 앞으로 가면서 자신이 작은 값이면 변경,
    # 자신보다 작은 값 만나면 스톱
    for j in range(i, 0, -1):
        if arr[j] < arr[j-1]:
            arr[j-1], arr[j] = arr[j], arr[j - 1]
        else:
            break

print(arr)