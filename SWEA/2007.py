"""
패턴에 값을 계속 추가, 1 2 3 4 5 1 2 3 4 5
앞에서부터 패턴 길이 추가
패턴 길이만큼 해당 배열~길이만큼 패턴이랑 비교
같은 곳 -> 패턴 발견
"""

T = int(input())

for test_case in range(1, T+1):
    letters = list(input())
    pattern = []
    confirm = []
    for i in range(11):
        pattern.append(letters[i])
        confirm = letters[i:i+len(pattern)]
        if i > 0 and pattern == confirm:
            print("#"+str(test_case), len(pattern)-1)
            break