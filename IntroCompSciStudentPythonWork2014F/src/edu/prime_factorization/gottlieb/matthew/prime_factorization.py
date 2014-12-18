'''
Created on Oct 28, 2014

@author: Matthew
'''
import math
def is_prime(number):
    if number == 2:
        return True
    elif not number & 1:
        return False
    end_value = number ** .5
    if end_value.is_integer():
        return False
    end_value = int(end_value + 1)
    for increment in range(3,end_value,2):
        if number % increment == 0:
            return False
    return True

def helper_function(number,factors,incr):
    div = None
    number_limit = number ** .5
    if is_prime(number):
        return factors.append(number)
    elif incr <= number_limit:
        div = number / incr
        if div.is_integer():
            factors.append(incr)
            number //= incr
            helper_function(number,factors,2)
        elif incr == 2:
            helper_function(number,factors,3)
        else:
            incr += 2
            while not is_prime(incr):
                incr += 2
            helper_function(number, factors, incr)

def factor(number,factors):
    if is_prime(number):
        factors.append(number)
    else:
        helper_function(number,factors,2)

def format_factors(factors):
    used_numbers = []
    amount = []
    for value in factors:
        if value in used_numbers:
            amount[used_numbers.index(value)] += 1
        else:
            used_numbers.append(value)
            amount.append(1)
    count = 0
    factors.clear()
    while count < len(used_numbers):
        if amount[count] == 1:
            factors.append(used_numbers[count])
        else:
            factors.append(str(used_numbers[count]) + "^" + str(amount[count]))
        count += 1

def display_factors(number,factors):
    final_return = ''
    if is_prime(number):
        final_return = str(number) + ' is prime!'
    else:
        final_return += str(number) + ' = '
        count = 0
        for value in factors:
            if count < len(factors) - 1:
                final_return += str(value) + ' * '
            else:
                final_return += str(factors[len(factors) - 1])
            count += 1
    return final_return

def factor_loop(number, factors):
    if is_prime(number):
        factors.append(1)
        factors.append(number)
    else:
        divisor = None
        incr = 2
        number_limit = number ** .5
        while incr < number_limit:
            divisor = number / incr
            if divisor.is_integer():
                factors.append(incr)
                number //= incr
                number_limit = number ** .5
                increment = 2
            else:
                increment += 1
            if increment == int(number_limit):
                factors.append(number)
