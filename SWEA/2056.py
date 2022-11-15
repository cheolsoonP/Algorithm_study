T = int(input())

for test_case in range(1, T+1):
    result = "-1"
    date = list(input())
    days30 = [4, 6, 9, 11]
    days31 = [1, 3, 5, 7, 8, 10, 12]
    days28 = [2]
    if len(date) != 8:
        print("#"+str(test_case), result)
    else:
        year = date[0:4]
        year = date[0]+date[1]+date[2]+date[3]
        month = date[4]+date[5]
        day = date[6]+date[7]

        if int(month) in days30 and int(day) <= 30:
            result = year + '/' + month + '/' + day
        elif int(month) in days31 and int(day) <= 31:
            result = year + '/' + month + '/' + day
        elif int(month) in days28 and int(day) <= 28:
            result = year + '/' + month + '/' + day
        print("#"+str(test_case), result)