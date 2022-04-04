# A - 5분 B-1분, C-10초
# 횟수가 최소, 합이 T가 되도록

# A B C 순서로 횟수 출력
# 만약에 불가능하면 -1출력




def microwave(T):
    A = int(5 * 60)  # 300
    B = int(1 * 60)  # 60
    C = int(10)  # 10

    result = []

    if((T/A) > 0):
        a = int(T/A)
        T = int(T%A)
        result.append(a)
    else:
        result.append(0)

    if(T/B > 0):
        b = int(T/B)
        T = int(T%B)
        result.append(b)
    else:
        result.append(0)

    if(T/C > 0):
        c = int(T/C)
        T = int(T%C)
        result.append(c)
    else:
        result.append(0)

    if(T>0):
        result = []
        result.append(-1)

    return result

T = int(input())

res = microwave(T)

for i in res:
    print(i, end=' ')