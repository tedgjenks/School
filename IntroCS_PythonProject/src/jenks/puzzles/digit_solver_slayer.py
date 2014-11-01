'''
Created on Sep 5, 2014

@author: JenksT
'''

def test_number(number):
    works = False
    digits = number // 100000
    digitl = (number % 100000) // 10000
    digita = (number % 10000) // 1000
    digity = (number % 1000) // 100
    digite = (number % 100) // 10
    digitr = number % 10
    if 3 * number == digitl * 10 ** 5 + digita * 10 ** 4 + digity * 10 ** 3 + digite * 10 ** 2 + digitr * 10 + digits:
        works = True
    return works

number = 10 ** 5
while number < 10 ** 6:
    if test_number(number):
        print(number)
    number = number + 1