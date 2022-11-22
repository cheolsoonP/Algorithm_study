
T = int(input())

for test_case in range(1, T+1):
    n, k = map(int, input().split())

    count = n // 10

    # 중간 35 기말 45 과제 20
    students = []

    for i in range(1, n+1):
        mid, final, subject = map(int, input().split())
        total = mid * 35 + final * 45 + subject * 20
        students.append((i,total))

    scores = sorted(students, reverse=True, key=lambda x:x[1])

    # 1 2 3 4 5 6 7 8 9 10
    # 12 34 56 78 910 1112 1314 1516 1718 1920
    for i in range(len(scores)):
        s_id, score = scores[i]
        if s_id == k:
            rate = (i + 1) / n * 10
            print("#"+str(test_case), end=" ")
            if rate > 9:
                print("D0")
            elif rate > 8:
                print("C-")
            elif rate > 7:
                print("C0")
            elif rate > 6:
                print("C+")
            elif rate > 5:
                print("B-")
            elif rate > 4:
                print("B0")
            elif rate > 3:
                print("B+")
            elif rate > 2:
                print("A-")
            elif rate > 1:
                print("A0")
            elif rate >= 0:
                print("A+")