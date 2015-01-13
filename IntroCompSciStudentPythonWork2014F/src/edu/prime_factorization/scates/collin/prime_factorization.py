'''
Created on Oct 13, 2014

@author: SCATCO26
'''

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

def helper_function(num,factors,incr):
    div = None
    num_limit = num ** .5
    if is_prime(num):
        return factors.append(num)
    elif incr <= num_limit:
        div = num / incr
        if div.is_integer():
            factors.append(incr)
            num //= incr
            helper_function(num,factors,2)
        elif incr == 2:
            helper_function(num,factors,3)
        else:
            incr += 2
            while not is_prime(incr):
                incr += 2
            helper_function(num, factors, incr)

def factor(num,factors):
    if is_prime(num):
        factors.append(num)
    else:
        helper_function(num,factors,2)

def format_factors(factors):
    used_nums = []
    amount = []
    for value in factors:
        if value in used_nums:
            amount[used_nums.index(value)] += 1
        else:
            used_nums.append(value)
            amount.append(1)
    count = 0
    factors.clear()
    while count < len(used_nums):
        if amount[count] == 1:
            factors.append(used_nums[count])
        else:
            factors.append(str(used_nums[count]) + "^" + str(amount[count]))
        count += 1

def display_factors(num,factors):
    final_return = ''
    if is_prime(num):
        final_return = str(num) + ' is prime!'
    else:
        final_return += str(num) + ' = '
        count = 0
        for value in factors:
            if count < len(factors) - 1:
                final_return += str(value) + ' * '
            else:
                final_return += str(factors[len(factors) - 1])
            count += 1
    return final_return

def factor_loop(num, factors):
    if is_prime(num):
        factors.append(1)
        factors.append(num)
    else:
        divisor = None
        incr = 2
        num_limit = num ** .5
        while incr < num_limit:
            divisor = num / incr
            if divisor.is_integer():
                factors.append(incr)
                num //= incr
                num_limit = num ** .5
                increment = 2
            else:
                increment += 1
            if increment == int(num_limit):
                factors.append(num)