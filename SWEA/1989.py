"""
내이름은 우영우 똑바로 읽어도 거꾸로 읽어도 우영ㅇ우
== 팰린드롬
찾아라!


"""

T = int(input())

for test_case in range(1, T+1):
    letter = list(input())
    if letter[::-1] == letter:
        print("#"+str(test_case), 1)
    else:
        print("#"+str(test_case), 0)
