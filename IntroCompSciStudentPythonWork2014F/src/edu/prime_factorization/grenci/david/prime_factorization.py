factors = []
def is_prime(number):
    prime = True
    first_prime = 2
    while number >= first_prime:
        if number % first_prime == 0 and first_prime != number:
            prime = False
        first_prime += 1
    return prime


def factor(number, factors):
    first_prime = 2
    if is_prime(number) == True:
        factors.append(int(number))
        return None
    else:
        while number % first_prime != 0:
            first_prime += 1
        factors.append(first_prime)
        factor((number / first_prime), factors)
    factors.sort()
    
    