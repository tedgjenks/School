'''
Created on Oct 28, 2014

@author: Matthew
'''
import math
def is_prime(number):
    if number == 2:
        return True
    elif not number:
        return False
    elif not number==1:
        return False
    last= math.sqrt(number)
    if last.is_integer():
        return False
    last = int(last)
    for incr in range(3, last, 2):
        if number % incr == 0:
            return False
    return True
def helper_method(number, factors, count):
    if is_prime(number)==True:
        return factors.append(number)
    elif count<= math.sqrt(number):
        if number%count==0:
            factors.append(count)
            number=number//count
            helper_method(number, factors, 2)
        elif count==2:
            helper_method(number, factors, 3)
        else:
            count+=2
            while not is_prime==True:
                count+=2
            helper_method(number, factors, count)
def factor(number, factors):
    if is_prime(number)==True:
        factors.append(1)
        factors.append(number)
    else:
        helper_method(number, factors, 2)
def format_factors(factors):
    used_numbers = []
    frequency = []
    for value in factors:
        if value in used_numbers:
            frequency[used_numbers.index(value)] += 1
        else:
            used_numbers.append(value)
            frequency.append(1)
    count = 0
    factors.clear()
    while count < len(used_numbers):
        if frequency[count]==1:
            factors.append(used_numbers[count])
        else:
            factors.append(str(used_numbers[count]) + "^" + str(frequency[count]))
        count += 1
def display_factors(number, factors):
    factor(number, factors)
    format_factors(factors)
    final_return = ''
    if is_prime(number)==True:
        final_return = str(number) + ' is prime!'
    else:
        final_return += str(number) + ' = '
        count = 0
        while count < len(factors) - 1:
            final_return += str(factors[count]) + ' * '
            count+=1
        final_return += str(factors[len(factors) - 1])
    return final_return
print(display_factors(1456,[]))