def top(isTop):
    if isTop:
        for i in range(width):
            if i == 0 or i == width-1:
                graph[0].append(' ')
                continue
            graph[0].append('-')
    else:
        for i in range(width):
            graph[0].append(' ')


def top_lr(dir):
    for i in range(1, mid-1):
        for j in range(width):
            if dir == 'right':
                if j == width-1:
                    graph[i].append('|')
                else:
                    graph[i].append(' ')
            elif dir == 'left':
                if j == 0:
                    graph[i].append('|')
                else:
                    graph[i].append(' ')
            elif dir == 'both':
                if j == width-1 or j == 0:
                    graph[i].append('|')
                else:
                    graph[i].append(' ')
            else:
                graph[i].append(' ')


def middle(isMid):
    if isMid:
        for i in range(width):
            if i == 0 or i == width-1:
                graph[mid-1].append(' ')
            else:
                graph[mid-1].append('-')
    else:
        for i in range(width):
            graph[mid-1].append(' ')


def bottom_lr(dir):
    for i in range(mid, height-1):
        for j in range(width):
            if dir == 'right':
                if j == width-1:
                    graph[i].append('|')
                else:
                    graph[i].append(' ')
            elif dir == 'left':
                if j == 0:
                    graph[i].append('|')
                else:
                    graph[i].append(' ')
            elif dir == 'both':
                if j == width-1 or j == 0:
                    graph[i].append('|')
                else:
                    graph[i].append(' ')
            else:
                graph[i].append(' ')


def bottom(isBottom):
    if isBottom:
        for i in range(width):
            if i == 0 or i == width-1:
                graph[height-1].append(' ')
            else:
                graph[height-1].append('-')
    else:
        for i in range(width):
            graph[height-1].append(' ')

def blank():
    for i in range(height):
        graph[i].append(' ')

s, n = input().split()
s = int(s)
n = list(map(int,n))

width = s+2
height = 2*s+3
mid = height // 2 + 1

graph = [[] for _ in range(height)]

for i in range(len(n)):

    if n[i] == 1:
        top(False)
        top_lr("right")
        middle(False)
        bottom_lr("right")
        bottom(False)
    elif n[i] == 2:
        top(True)
        top_lr("right")
        middle(True)
        bottom_lr("left")
        bottom(True)
    elif n[i] == 3:
        top(True)
        top_lr("right")
        middle(True)
        bottom_lr("right")
        bottom(True)
    elif n[i] == 4:
        top(False)
        top_lr("both")
        middle(True)
        bottom_lr("right")
        bottom(False)
    elif n[i] == 5:
        top(True)
        top_lr("left")
        middle(True)
        bottom_lr("right")
        bottom(True)
    elif n[i] == 6:
        top(True)
        top_lr("left")
        middle(True)
        bottom_lr("both")
        bottom(True)
    elif n[i] == 7:
        top(True)
        top_lr("right")
        middle(False)
        bottom_lr("right")
        bottom(False)
    elif n[i] == 8:
        top(True)
        top_lr("both")
        middle(True)
        bottom_lr("both")
        bottom(True)
    elif n[i] == 9:
        top(True)
        top_lr("both")
        middle(True)
        bottom_lr("right")
        bottom(True)
    elif n[i] == 0:
        top(True)
        top_lr("both")
        middle(False)
        bottom_lr("both")
        bottom(True)
    blank()

# for i in range(height):
#     print(graph[i])

for i in range(height):
    for j in range(len(graph[0])):
        print(graph[i][j], end="")
    print()