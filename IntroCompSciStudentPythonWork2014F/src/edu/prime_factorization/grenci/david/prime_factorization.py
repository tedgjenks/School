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
  
def format_factors(factors):
    index = 0
    while index < len(factor):
        factor_count = 0
        start_index = index
        factor = factors[0]
        while(index +1 < len(factors) and factors[index] == factors[index-1]):
            factor_count += 1
            del factor[index + 1]
        if factor_count > 1:
            exp_factor = str(factor) + "^" + str(factor_count)
            print(exp_factor)
        index = start_index +1
    
    

#factor = [2, 2, 2]
#format_factors(factors)
    
    
    