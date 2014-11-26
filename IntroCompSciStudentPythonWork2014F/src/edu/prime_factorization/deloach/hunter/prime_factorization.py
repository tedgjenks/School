def is_prime(number):
    prime=None
    counter=2
    while 1==1:
        if number % counter==0:
            prime=False
            return False
        else:
            if counter>(number**.5):
                prime=True
                return prime
            else:
                counter+=1
                prime=True
                continue
        if number==2:
            return True   

def helper_method(number, factors, counter):
    if is_prime(number)==True:
        return factors.append(number)
    elif counter<= number**.5:
        if number%counter==0:
            factors.append(counter)
            number=number//counter
            helper_method(number, factors, 2)
        elif counter==2:
            helper_method(number, factors, 3)
        else:
            counter+=2
            while not is_prime==True:
                counter+=2
            helper_method(number, factors, counter)
        
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
    final = ''
    if is_prime(number)==True:
        final = str(number) + ' is prime!'
    else:
        final += str(number) + ' = '
        count = 0
        while count < len(factors) - 1:
            final += str(factors[count]) + ' * '
            count+=1
        final += str(factors[len(factors) - 1])
    return final