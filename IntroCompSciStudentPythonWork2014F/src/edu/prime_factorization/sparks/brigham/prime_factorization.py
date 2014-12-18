'''
Created on Oct 28, 2014

@author: SPARBR96
'''
def is_prime(num):
    if num == 2:
        return True
    elif not num & 1:
        return False
    endvalue = num ** .5
    if endvalue.is_integer():
        return False
    endvalue = int(endvalue + 1)
    for increment in range(3,endvalue,2):
        if num % increment == 0:
            return False
    return True

def helper_function(num,factors,increasingnum):
    div = None
    numlimit = num ** .5
    if is_prime(num):
        return factors.append(num)
    elif increasingnum <= numlimit:
        div = num / increasingnum
        if div.is_integer():
            factors.append(increasingnum)
            num //= increasingnum
            helper_function(num,factors,2)
        elif increasingnum == 2:
            helper_function(num,factors,3)
        else:
            increasingnum += 2
            while not is_prime(increasingnum):
                increasingnum += 2
            helper_function(num, factors, increasingnum)

def factor(num,factors):
    if is_prime(num):
        factors.append(num)
    else:
        helper_function(num,factors,2)

def format_factors(factors):
    usednums = []
    amount = []
    for value in factors:
        if value in usednums:
            amount[usednums.index(value)] += 1
        else:
            usednums.append(value)
            amount.append(1)
    count = 0
    factors.clear()
    while count < len(usednums):
        if amount[count] == 1:
            factors.append(usednums[count])
        else:
            factors.append(str(usednums[count]) + "^" + str(amount[count]))
        count += 1

def display_factors(num,factors):
    finalreturn = ''
    if is_prime(num):
        finalreturn = str(num) + ' is prime!'
    else:
        finalreturn += str(num) + ' = '
        count = 0
        for value in factors:
            if count < len(factors) - 1:
                finalreturn += str(value) + ' * '
            else:
                finalreturn += str(factors[len(factors) - 1])
            count += 1
    return finalreturn

def factor_loop(num, factors):
    if is_prime(num):
        factors.append(1)
        factors.append(num)
    else:
        divisor = None
        increasingnum = 2
        numlimit = num ** .5
        while increasingnum < numlimit:
            divisor = num / increasingnum
            if divisor.is_integer():
                factors.append(increasingnum)
                num //= increasingnum
                numlimit = num ** .5
                increment = 2
            else:
                increment += 1
            if increment == int(numlimit):
                factors.append(num)