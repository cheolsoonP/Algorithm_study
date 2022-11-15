"""
소문자 => 대문자로 변경
대문자 -> 소문자 아스키 변경 + 32
소문자 -> 대문자 아스키 변경 - 32
chr : 숫자 -> 문자로 변경 아스키
ord : 문자 -> 숫자로 변경 아스키

"""
headline = list(input())

for i in range(len(headline)):
    if ord(headline[i]) >= 97 and ord(headline[i]) <= 122: # 소문자
        headline[i] = chr(ord(headline[i]) - 32) # 대문자로 변경
for h in headline:
    print(h, end="")
