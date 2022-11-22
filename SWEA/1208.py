def find_min_max():
    max_box = [-1, -1e9]
    min_box = [-1, 1e9]
    for i in range(len(box)):
        # 가장 높은 박스 찾기
        if box[i] > max_box[1]:
            max_box = [i, box[i]]
        # 가장 낮은 박스 찾기
        if box[i] < min_box[1]:
            min_box = [i, box[i]]
    return max_box, min_box


def play_dump():
    max_b, min_b = find_min_max()
    box[max_b[0]] -= 1
    box[min_b[0]] += 1

    max_b, min_b = find_min_max()
    return box[max_b[0]] - box[min_b[0]]


T = 10
for test_case in range(1, T+1):
    n = int(input())
    box = list(map(int, input().split()))
    distance = 1e9

    # 덤프 작업 시행 n번 만큼
    for i in range(n):
        distance = play_dump()
        if distance <= 1:
            break
    print("#"+str(test_case), distance)

