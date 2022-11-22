T = int(input())

for test_case in range(1, 1 + T):
    a, b, c, d = map(int, input().split())
    on = 0
    off = 0
    if a <= c:
        on = c
    else:
        on = a

    if b <= d:
        off = b
    else:
        off = d

    if off - on > 0:
        print("#" + str(test_case), off - on)
    else:
        print("#" + str(test_case), 0)
