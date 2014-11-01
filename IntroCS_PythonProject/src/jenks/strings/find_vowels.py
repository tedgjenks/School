'''
Created on Oct 3, 2014

@author: JenksT
'''

vowels = ('A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u')
def locate_vowels(word):
    vowel_indices = []
    for index in range(0, len(word)):
        if word[index:index + 1] in vowels:
            vowel_indices.append(index)
    return vowel_indices

def print_test_locate_vowels(expected_value, actual_value, word):
    pass_test = True
    if expected_value != actual_value:
        print('Fail on ', '"' + word + '"', expected_value, actual_value)
        pass_test = False
    return pass_test

def test_locate_vowels():
    all_pass = True
    word = 'test'
    if not print_test_locate_vowels([1], locate_vowels(word), word):
        all_pass = False
    word = 'barbecue'
    if not print_test_locate_vowels([1, 4, 6, 7], locate_vowels(word), word):
        all_pass = False
    word = 'BEEBOP'
    if not print_test_locate_vowels([1, 2, 4], locate_vowels(word), word):
        all_pass = False
    word = 'bopIlong'
    if not print_test_locate_vowels([1, 3, 5], locate_vowels(word), word):
        all_pass = False
    if all_pass:
        print("locate_vowels passed")
        
test_locate_vowels()