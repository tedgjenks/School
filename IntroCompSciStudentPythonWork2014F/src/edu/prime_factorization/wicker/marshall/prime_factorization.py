def is_prime(number):
    if number == 2:
        return True
    elif not number & 1:
        return False
    end_val = number ** .5
    if end_val.is_integer():
        return False
    end_val = int(end_val + 1)
    incr = 3
    while incr < end_val:
        if number % incr == 0:
            return False
        else:
            incr += 2
    return True


def factor_helper(number, factors, incr):
    append = factors.append
    div = None
    limit = number ** .5
    if is_prime(number):
        return append(number)
    elif incr <= limit:
        div = number / incr
        if div.is_integer():
            append(incr)
            number //= incr
            factor_helper(number, factors, 2)
        elif incr == 2:
            factor_helper(number, factors, 3)
        else:
            incr += 2
            while not is_prime(incr):
                incr += 2
            factor_helper(number, factors, incr)


def factor(number, factors):
    append = factors.append
    if is_prime(number):
        append(number)
    else:
        factor_helper(number, factors, 2)


def format_factors(factors):
    used_chars = []
    amt_chars = []
    for value in factors:
        if value in used_chars:
            amt_chars[used_chars.index(value)] += 1
        else:
            used_chars.append(value)
            amt_chars.append(1)
    count = 0
    factors.clear()
    while count < len(used_chars):
        if amt_chars[count] != 1:
            factors.append(str(used_chars[count]) + "^" + str(amt_chars[count]))
        else:
            factors.append(str(used_chars[count]))
        count += 1


def display_factors(number, factors):
    factor_list = []
    for value in factors:
        factor_list.append(str(value).split('^'))
    return_str = ''
    if is_prime(number):
        return_str = str(number) + ' is prime!'
    else:
        return_str += str(number) + '='
        x = 0
        length = len(factors) - 1
        for value in factor_list:
            if len(factor_list) > 1:
                if len(value) == 2:
                    if x < length:
                        return_str += (value[0] + '*') * int(value[1])
                    else:
                        return_str += value[len(value) - 1][0]
                else:
                    if x < length:
                        return_str += (value[0] + '*')
                    else:
                        return_str += value[len(value) - 1][0]
            else:
                if len(value) == 2:
                    if x <= length:
                        return_str += ((value[0] + '*') * (int(value[1]) - 1)) + value[0]
                    else:
                        return_str += value[0] * int(value[1])
                else:
                    if x < length:
                        return_str += (value[0] + '*')
                    else:
                        return_str += value[len(value) - 1][0]
            x += 1
    return return_str

'''
def factor_loop(number, factors):
    append = factors.append
    if is_prime(number):
        append(1)
        append(number)
    else:
        div = None
        incr = 2
        limit = number ** .5
        while incr < limit:
            div = number / incr
            if div.is_integer():
                append(incr)
                number //= incr
                limit = number ** .5
                incr = 2
            else:
                incr += 1
            if incr == int(limit):
                append(number)
'''
