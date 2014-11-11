def is_prime(number):
    if number == 2:
        return True
    elif not number & 1:
        return False
    last_val = number ** .5
    if last_val.is_integer():
        return False
    last_val = int(last_val + 1)
    for increment in range(3,last_val,2):
        if number % increment == 0:
            return False
    return True

def factor_helpfunc(number,factors,increment):
    append = factors.append
    div_num = None
    limit = number ** .5
    if is_prime(number):
        return append(number)
    elif increment <= limit:
        div_num = number / increment
        if div_num.is_integer():
            append(increment)
            number //= increment
            factor_helpfunc(number,factors,2)
        elif increment == 2:
            factor_helpfunc(number,factors,3)
        else:
            increment += 2
            while not is_prime(increment):
                increment += 2
            factor_helpfunc(number, factors, increment)

def factor(number,factors):
    append = factors.append
    if is_prime(number):
        append(number)
    else:
        factor_helpfunc(number,factors,2)

def format_factors(factors):
    char_used = []
    amnt_char = []
    for value in factors:
        if value in char_used:
            amnt_char[char_used.index(value)] += 1
        else:
            char_used.append(value)
            amnt_char.append(1)
    count = 0
    factors.clear()
    while count < len(char_used):
        if amnt_char[count] == 1:
            factors.append(char_used[count])
        else:
            factors.append(str(char_used[count]) + "^" + str(amnt_char[count]))
        count += 1

def display_factors(number,factors):
    return_str = ''
    if is_prime(number):
        return_str = str(number) + ' is prime!'
    else:
        return_str += str(number) + ' = '
        x = 0
        length = len(factors)
        for value in factors:
            if x < length - 1:
                return_str += str(value) + ' * '
            else:
                return_str += str(factors[len(factors) - 1])
            x += 1
    return return_str

def factor_loop(number, factors):
    append = factors.append
    if is_prime(number):
        append(1)
        append(number)
    else:
        div_num = None
        increment = 2
        limit = number ** .5
        while increment < limit:
            div_num = number / increment
            if div_num.is_integer():
                append(increment)
                number //= increment
                limit = number ** .5
                increment = 2
            else:
                increment += 1
            if increment == int(limit):
                append(number)