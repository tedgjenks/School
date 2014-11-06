def is_prime(number):
    prime = True
    fact = 2
    while fact <= number:
        if number % fact == 0 and number != 2:
            prime = False
        elif number % fact != 0 or number == fact:
            prime = True
            fact += 1
        return prime

factors = []

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


def test_factor():
    all_pass = None
    exp_result = ['3', '2']
    act_result = factor(6, factors)
    if act_result == exp_result:
        all_pass = True
    else:
        all_pass = False
    if all_pass == True:
        print("worked")
    else:
        print("not worked")

