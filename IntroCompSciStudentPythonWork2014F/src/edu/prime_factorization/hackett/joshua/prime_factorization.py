def is_prime(number):
    prime = True
    fact = 2
    while fact <= number:
        if number % fact == 0 or number != 2:
            prime = False
        elif number % fact != 0:
            prime = True
            fact += 1
        elif number % 3 == 0:
            prime = False
        return prime

factors = []

def factor(number, factors):
    first_prime = 2
    if is_prime(number) == True:
        factors.append(int(number))
    else:
        while number % first_prime != 0:
            first_prime += 1
            factors.append(first_prime)
            factor((number / first_prime), factors)




def format_factors(factors):
    index = 1
    while (index < len(factors)):
        factor = factors[index]
        factor_count = 1
        start_index = index
        while (index + 1 < len(factors) and factors[index] == factors[index - 1]):
            factor_count += 1
            
            del factors[index]
            print(factors)
        if factor_count > 1:
            exp_factor = str(factor) + '^' + str(factor_count)
            print(exp_factor)
        index = start_index + 1

factors = [2, 3]
format_factors(factors)
print(factors)
factors = [2, 3, 5, 5, 5, 7, 11]
format_factors(factors)
print(factors)