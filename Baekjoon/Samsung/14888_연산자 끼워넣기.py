
n = int(input())

nums = list(map(int, input().split()))

# 연산자 + - * /
operations = list(map(int, input().split()))

min_value = 1e9
max_value = -1e9


def dfs(total, i, add, sub, mul, div):
    global min_value, max_value

    if i == n:
        max_value = max(max_value, total)
        min_value = min(min_value, total)
        return

    if add > 0:
        dfs(total + nums[i], i + 1, add - 1, sub, mul, div)
    if sub > 0:
        dfs(total - nums[i], i + 1, add, sub - 1, mul, div)
    if mul > 0:
        dfs(total * nums[i], i + 1, add, sub, mul - 1, div)
    if div > 0:
        dfs(int(total / nums[i]), i + 1, add, sub, mul, div - 1)


dfs(nums[0], 1, operations[0], operations[1], operations[2], operations[3])

print(max_value)
print(min_value)