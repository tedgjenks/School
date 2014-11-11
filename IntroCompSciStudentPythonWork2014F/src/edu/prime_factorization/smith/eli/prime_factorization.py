'''
Created on Oct 27, 2014

@author: SMITEL72
'''
def is_prime(number):
    prime=True
    divisor= 2
    while prime and divisor < number:
        if number%divisor ==0:
            prime = False
        divisor=divisor + 1
    return prime


def factor(number, factors):
    loop_index = 2
    while number % loop_index != 0:
        loop_index += 1
    if is_prime(loop_index) == True:
        factors.append(loop_index)
        if int(number) > loop_index:
            number = number/loop_index
            factor(number, factors)
    factors.sort()
                
def format_factors(factors):
    factors.sort(reverse=True)
    new_list = []
    for anything in range(len(factors)):
        new_list.append(factors.pop())
    num_factors = 1
    current_factor = new_list[0]
    for x in range(len(new_list)):
        if new_list[x] != current_factor:
            num_factors += 1
            current_factor = new_list[x]
    end = num_factors
    for y in range(num_factors):
        count = 0
        first_factor = new_list[0]
        for b in range(len(new_list)):
            if first_factor == new_list[b]:
                count += 1
        for a in range(count):
            new_list.remove(first_factor)
        if count > 1:
            factors.append((str(first_factor) + "^" + str(count)))
        else: 
            factors.append(first_factor)
    
def display_factors(number, factors):
    if is_prime(number) == True:
        return (str(number) + ' is prime!')
    display = (str(number) +' = ')
    for x in range(len(factors)):
        if x == len(factors) - 1:
            display = display + str(factors[x])
        else:
            display = display + str(factors[x]) + " * "
    return display
