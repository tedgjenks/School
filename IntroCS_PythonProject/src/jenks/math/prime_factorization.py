'''
Created on Aug 29, 2014

@author: JenksT
'''
import time
current_milli_time = lambda: int(round(time.time() * 1000))

import math

prime_factors = [2, 3, 5]

def is_prime(number):
    divisor = 2
    keep_testing = True
    is_prime = True
    if number in prime_factors:
        keep_testing = False
    elif number < prime_factors[len(prime_factors) - 1]:
        is_prime = False
    while is_prime and keep_testing:
        if divisor ** 2 > number:
            keep_testing = False
        elif number % divisor == 0:
            is_prime = False
        elif divisor == 2:
            divisor = divisor + 1
        else:
            divisor = divisor + 2
    return is_prime
        
# pre-condition: number >= 2
def find_first_factor(number):
    first_factor = 1
    index = 0
    max_factor = math.sqrt(number)
    while first_factor == 1 and index < len(prime_factors) and prime_factors[index] <= max_factor:
        if number % prime_factors[index] == 0:
            first_factor = prime_factors[index]
        index += 1
    last_known_prime = prime_factors[len(prime_factors) - 1]
    test_factor = last_known_prime + 2
    #order_of_magnitude = 1
    loop_counter = 0
    #five_counter = 4 #distance from 5
    #str_test_factor = str(test_factor)
    #last_digit_test_factor = str_test_factor[len(str_test_factor) - 1:]
    #five_found = last_digit_test_factor == '5'
    #print(last_digit_test_factor)
    while first_factor == 1 and test_factor <= max_factor:
        if is_prime(test_factor):
            prime_factors.append(test_factor)
            if number % test_factor == 0:
                first_factor = test_factor
        test_factor += 2
        str_test_factor = str(test_factor)
        last_digit_test_factor = str_test_factor[len(str_test_factor) - 1:]
        if last_digit_test_factor == '5':
            test_factor += 2
        loop_counter += 1
        #if math.log10(loop_counter) >= order_of_magnitude:
            #order_of_magnitude += 1
            #print('loop counter in search for first factor:', loop_counter)
    return first_factor
        
def format_factors(factors):
    index = 0
    repeats = 1
    prev_num = None
    while index < len(factors):
        cur_num = factors[index]
        if cur_num == prev_num:
            repeats = repeats + 1
            factors.pop(index)
            index = index - 1
        prev_num = cur_num
        index = index + 1
        if repeats > 1 and (index >= len(factors) or cur_num != factors[index]):
            factors[index - 1] = str(prev_num) + "^" + str(repeats)
            repeats = 1
        
# pre-condition: number >= 2
def factor(number, factors):
    first_factor = find_first_factor(number)
    if first_factor == 1:
        factors.append(number)
    else:
        factors.append(first_factor)
        factor(number // first_factor, factors)
        
def display_factors(number, factors):
    return_value = None
    if len(factors) == 1 and type(factors[0]) is int:
        return_value = str(number) + " is prime!"
    else:
        format_factors(factors)
        return_value = str(number) + " = "
        index = 0
        factors_length = len(factors)
        while index < factors_length:
            return_value += str(factors[index])
            index = index + 1
            if index < factors_length:
                return_value += " * "
    return return_value

'''
print(is_prime(4))
factors = []
factor(4, factors)
print(factors)
format_factors(factors)
print(factors)
print(display_factors(4, factors))


error_message = "You must enter an integer >= 2"
exit_cmd = "exit"
user_input = str()
while user_input.lower() != exit_cmd:
    user_input = input("Enter an integer to factor.  Type 'exit' to exit. ")
    if user_input.lower() != exit_cmd:
        try:
            input_int = int(user_input)
            if input_int == 1:
                print("1 cannot be factored")
            elif input_int < 1:
                print(error_message)
            else:
                factors = []
                start_time = current_milli_time()    
                factor(input_int, factors)
                end_time = current_milli_time()
                print(display_factors(input_int, factors))
                print((end_time - start_time), 'ms')
                #print("Known prime factors:", prime_factors)
        except ValueError:
            print(error_message)
print("Program terminated.")
'''