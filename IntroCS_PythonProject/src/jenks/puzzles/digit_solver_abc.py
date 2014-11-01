'''
Created on Sep 5, 2014

@author: JenksT
'''

#pre-condition: 100 <= number <= 999
#post-condition: 
def test_number(number):
    works = False
    hundreds_digit = number // 100
    tens_digit = (number % 100) // 10
    ones_digit = (number % 10)
    if number == hundreds_digit * tens_digit * ones_digit * (hundreds_digit + tens_digit + ones_digit):
        works = True
    return works

number = 100
while number < 1000:
    if test_number(number):
        print(number)
    number = number + 1