'''
Created on Oct 8, 2014

@author: JenksT
'''
import math
import edu.prime_factorization.li.zhilin.prime_factorization as student_prime_factorization

from jenks.math.prime_factorization import is_prime as expected_is_prime
from jenks.math.prime_factorization import factor as expected_factor
from jenks.math.prime_factorization import format_factors as expected_format_factors
from jenks.math.prime_factorization import display_factors as expected_display_factors

import time
current_milli_time = lambda: int(round(time.time() * 1000))
basic_test_numbers = range(2, 1000)
performance_test_numbers = [1001, 10007, 100001, 100003, 1000000001,
                            1000000007, 10000000001, 10000000019]
total_points = 0
continue_testing = True

def mult_elements(list_arg):
    product = 1
    for number in list_arg:
        product *= number
    return product

def test_is_prime():
    all_pass = True
    for number in basic_test_numbers:
        if expected_is_prime(number) != student_prime_factorization.is_prime(number):
            all_pass = False
            print('is_prime failed on', number)
            break
    if all_pass:
        global total_points
        total_points += 20
        print('test_is_prime all passed')

def test_factor_product():
    all_pass = True
    for number in basic_test_numbers:
        e_factors = []
        expected_factor(number, e_factors)
        e_product = mult_elements(e_factors)
        a_factors = []
        student_prime_factorization.factor(number, a_factors)
        a_product = mult_elements(a_factors)
        if e_product != a_product:
            all_pass = False
            print('factor failed on product:', number, a_factors)
    if all_pass:
        global total_points
        total_points += 20
        print('test_factor_product all passed')
        
def test_factor_order():
    all_pass = True
    for number in basic_test_numbers:
        e_factors = []
        expected_factor(number, e_factors)
        a_factors = []
        student_prime_factorization.factor(number, a_factors)
        if e_factors != a_factors:
            all_pass = False
            print('factor failed on order:', number, a_factors)
    if all_pass:
        global total_points
        total_points += 20
        print('test_factor_order all passed')

def test_format_factors():
    all_pass = True
    for number in basic_test_numbers:
        e_factors = []
        expected_factor(number, e_factors)
        expected_format_factors(e_factors)
        a_factors = []
        student_prime_factorization.factor(number, a_factors)
        student_prime_factorization.format_factors(a_factors)
        if e_factors != a_factors:
            all_pass = False
            print('format_factors failed:', number, a_factors)
            continue_testing = False
            break
    if all_pass:
        global total_points
        total_points += 20
        print('test_format_factors all passed')

def test_display_factors():
    all_pass = True
    for number in basic_test_numbers:
        e_factors = []
        expected_factor(number, e_factors)
        expected_format_factors(e_factors)
        expected_value = expected_display_factors(number, e_factors)
        a_factors = []
        student_prime_factorization.factor(number, a_factors)
        student_prime_factorization.format_factors(a_factors)
        actual_value = student_prime_factorization.display_factors(number, a_factors)
        if expected_value != actual_value:
            all_pass = False
            print('display_factors failed:', number, actual_value)
            print('expected:', expected_value)
            break
    if all_pass:
        global total_points
        total_points += 10
        print('test_display_factors all passed')
        
def test_performance():
    all_pass = True
    points = 0
    e_total_running_time = 0
    a_total_running_time = 0
    for number in performance_test_numbers:
        magnitude = int(math.log10(number))
        e_factors = []
        e_start_time = current_milli_time()
        expected_factor(number, e_factors)
        e_running_time = current_milli_time() - e_start_time
        e_total_running_time += e_running_time
        a_factors = []
        a_start_time = current_milli_time()
        student_prime_factorization.factor(number, a_factors)
        a_running_time = current_milli_time() - a_start_time
        a_total_running_time += a_running_time
        time_limit = e_running_time * 2 + 5
        if e_factors != a_factors:
            all_pass = False
            print('test_performance failed to factor correctly for magnitude', magnitude)
            break
        elif a_running_time > time_limit:
            all_pass = False
            print('test_performance failed time limit of', time_limit, 'ms on magnitude', magnitude)
            print('Your runtime was', a_running_time, 'ms.')
            break
        else:
            points += 1
    global total_points    
    if all_pass:
        total_points += 10
        print('test_performance all passed')
        if a_total_running_time < e_total_running_time:
            print('You beat me!')
        else:
            print('I beat you!')
        print('Your total running time in ms:', a_total_running_time)
        print('My total running time in ms:', e_total_running_time)
    else:
        total_points += points
    
test_is_prime()
print('Current points:', total_points)
test_factor_product()
print('Current points:', total_points)
test_factor_order()
print('Current points:', total_points)
test_format_factors()
print('Current points:', total_points)
test_display_factors()
print('Current points:', total_points)
test_performance()
print('Total points:', total_points)