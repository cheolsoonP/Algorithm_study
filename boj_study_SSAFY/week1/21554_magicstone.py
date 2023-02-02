"""
가게정리
매장 N개 장난감

1~n 자연서
크기순서로 정렬
1,2, .... n
몇 번 조작 가능
인접한 몇개 장난감 골라서 뒤집는다
1 2 5 4 3
3~5 조작
정렬 완료
1 2 3 4 5

장난감 100회 이하 조작 -> 정렬 가능한지 판단

left, right
right 가 left보다 작은 지 확인
1 3 2 5 4

1 2 3 5 4
1 2 3 4 5

3 2 1 4 5
0 1 2 3 4

"""
n = int(input())

arr = list(map(int, input().split()))

answer = []
count = 0
while(True):
    switch_len = 0
    left = -1
    for i in range(n-1):
        if switch_len <= 0:
            if arr[i] > arr[i+1]:
                left = i
                switch_len += 1
        else:
            if arr[i] > arr[i+1]:
                switch_len += 1
            else:
                break
    if switch_len > 0:
        # switch
        answer.append((left+1, left+switch_len+1))
        arr[left:left+switch_len+1] = sorted(arr[left:left+switch_len+1])
            # arr[left+i], arr[left+switch_len-i] = arr[left+switch_len-i], arr[left+i]
    else:
        print(count)
        for (l, r) in answer:
            print(l, r)
        break
    count += 1
    if count > 100:
        print(-1)
        break

# 100번 이하 정렬 불가능 -> -1
# 가능 -> 조작횟수 출력
# 둘째줄 -> Q개 줄 -> 각 조작의 장난감 왼쪽 끝, 오른족끝 출력