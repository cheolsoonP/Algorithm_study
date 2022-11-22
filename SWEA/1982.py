li = []
for i in range(ord('A'), ord('Z')+1):
    li.append(chr(i))
for i in range(ord('a'), ord('z')+1):
    li.append(chr(i))
for i in range(ord('0'), ord('9')+1):
    li.append(chr(i))
li.append('+')
li.append('/')

def binary(num):
    bin_num = []
    while True:
        a = num // 2
        b = num % 2
        bin_num.insert(0, b)
        if a != 0:
            num = a
        else:
            break
    while len(bin_num) < 6:
        bin_num.insert(0, 0)

    return bin_num

def find_num(letter):
    num = -1
    for i in range(len(li)):
        if li[i] == letter:
            num = i
            break
    return map(str, binary(num))


T = int(input())

for test_case in range(1, T+1):
    enc_letter = list(input())
    dec_num = []
    result = []


    for l in enc_letter:
        dec_num+=(find_num(l))

    print(dec_num)
    for i in range(0, len(dec_num)//8):
        print(map(, (dec_num[i * i:8 * (i + 1)])))
        data = int(dec_num[i*i:8*(i+1)], 2)
        result += chr(data)
        print(data)
    print(result)




