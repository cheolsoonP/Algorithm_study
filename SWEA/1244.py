for tc in range(1, int(input()) + 1):
    num, cnt = input().split()
    cnt = int(cnt)
    data = set([num])
    sub = set()
    for _ in range(cnt):
        while data:
            s = data.pop()
            s = list(s)
            for i in range(len(num)):
                for j in range(i + 1, len(num)):
                    s[i], s[j] = s[j], s[i]
                    sub.add(''.join(s))
                    s[i], s[j] = s[j], s[i]

        data, sub = sub, data

    print("#"+str(tc), max(map(int, data)))
