T = int(input())
n = -1
operation = []
nums = []
isUsed = []
max_value = -1e9
min_value = 1e9

def compute(choice):
    global nums, n, min_value, max_value
    temp = nums[0]
    for i in range(0, n-1):
        if choice[i] == '+':
            temp += nums[i+1]
        elif choice[i] == '-':
            temp -= nums[i+1]
        elif choice[i] == '*':
            temp *= nums[i+1]
        elif choice[i] == '/':
            temp //= nums[i+1]
    min_value = min(min_value, temp)
    max_value = max(max_value, temp)


def choose_operation(used, choice):
    global n

    if len(choice) >= n-1:
        compute(choice)
        return

    for i in range(n-1):
        if i in used:
            continue
        used.append(i)
        choice.append(operation[i])
        choose_operation(used, choice)
        choice.pop()
        used.pop()

for test_case in range(1, T+1):
    n = int(input())
    op = list(map(int, input().split()))
    nums = list(map(int, input().split()))
    temp = []
    for i in range(4):
        for j in range(op[i]):
            if i == 0:
                temp.append('+')
            elif i == 1:
                temp.append('-')
            elif i == 2:
                temp.append('*')
            elif i == 3:
                temp.append('/')
    operation = temp
    isUsed = [0 for _ in range(n-1)]
    max_value = -1e9
    min_value = 1e9

choose_operation([], [])
print("#", max_value-min_value)