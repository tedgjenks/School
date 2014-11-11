'''
Created on Oct 13, 2014

@author: TRANDU65
'''
import math

def is_prime(number):
    Prime = True
    prime_count = 2
    for x in range(round(math.sqrt(number))):
        if number == 2:
            return Prime
        if number % prime_count == 0 and prime_count != number:
            Prime = False
        prime_count += 1
    return Prime

def factor(number, factors):
    first_prime = 2
    if is_prime(number) == True:
        factors.append(int(number))
    else:
        while number % first_prime != 0:
            first_prime += 1
        factor(number/first_prime, factors)
        factors.append(int(first_prime))
    factors.sort()


def format_factors(factors):
    factors.sort(reverse = True)
    newlist = []
    for x in range(len(factors)):
        newlist.append(factors.pop())
    length = 1
    a = newlist[0]
    for x in range(len(newlist)):
        if newlist[x] != a:
            a = newlist[x]
            length += 1
    end = length
    for b in range(length):
        count_two = 0
        a = newlist[0]
        for y in range(len(newlist)):
            if newlist[y] == a:
                count_two += 1
        for z in range(count_two):
            newlist.remove(a)
        if count_two > 1:
            factors.append((str(a)+'^'+str(count_two)))
        else:
            factors.append(a)
          
def display_factors(number, factors):
    if is_prime(number):
        return ((str(factors[0]) + ' is prime!'))
    else:
        display = (str(number) + ' = ')
        for x in range(len(factors)):
            if x == len(factors) - 1:
                display = display + str(factors[x])
            else:
                display = display + str(factors[x]) + ' * '
        return display