def check_view(index):
    curr = building[index]
    side_building = [building[index-2], building[index-1],
                    building[index+1], building[index+2]]
    min_count = 1e9
    for side in side_building:
        cnt = curr - side
        if side >= curr:
            return 0
        else:
            if cnt < min_count:
                min_count = cnt
    return min_count

T = 10
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    n = int(input())
    building = list(map(int, input().split()))
    count = 0

    for i in range(2, n-2):
        count += check_view(i)
    print("#"+str(test_case), count)


