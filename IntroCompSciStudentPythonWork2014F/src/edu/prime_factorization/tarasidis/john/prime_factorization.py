__author__ = "john"


import math


def is_prime(num):
    if num == 2:
        return True
    elif not num & 1:
        return False
    end_value = num ** .5
    if end_value.is_integer():
        return False
    end_value = int(end_value + 1)
    for increment in range(3,end_value,2):
        if num % increment == 0:
            return False
    return True

def factor(num,fac):
    if is_prime(num):
        fac.append(num)
    else:
        helper_function(num,fac,2)

def format_factors(fac):
    used_nums = []
    amount = []
    for value in fac:
        if value in used_nums:
            amount[used_nums.index(value)] += 1
        else:
            used_nums.append(value)
            amount.append(1)
    count = 0
    fac.clear()
    while count < len(used_nums):
        if amount[count] == 1:
            fac.append(used_nums[count])
        else:
            fac.append(str(used_nums[count]) + "^" + str(amount[count]))
        count += 1

def display_factors(num,fac):
    final_return = ''
    if is_prime(num):
        final_return = str(num) + ' is prime!'
    else:
        final_return += str(num) + ' = '
        count = 0
        for value in fac:
            if count < len(fac) - 1:
                final_return += str(value) + ' * '
            else:
                final_return += str(fac[len(fac) - 1])
            count += 1
    return final_return

def factor_loop(num, fac):
    if is_prime(num):
        fac.append(1)
        fac.append(num)
    else:
        divisor = None
        jump= 2
        num_limit = num ** .5
        while jump< num_limit:
            divisor = num / jump
            if divisor.is_integer():
                fac.append(jump)
                num //= jump
                num_limit = num ** .5
                increment = 2
            else:
                increment += 1
            if increment == int(num_limit):
                fac.append(num)

def helper_function(num,fac,jump):
    div = None
    num_limit = num ** .5
    if is_prime(num):
        return fac.append(num)
    elif jump<= num_limit:
        div = num / jump
        if div.is_integer():
            fac.append(jump)
            num //= jump
            helper_function(num,fac,2)
        elif jump== 2:
            helper_function(num,fac,3)
        else:
            jump+= 2
            while not is_prime(jump):
                jump+= 2
            helper_function(num, fac, jump)
