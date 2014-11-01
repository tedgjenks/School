'''
Created on Sep 6, 2014

@author: JenksT
'''
def validate_whole_number(whole_number):
    ret_value = None
    try:
        int_number = int(whole_number)
        if int_number > 0:
            ret_value = int_number
    except ValueError:
        None
    if ret_value == None:
        print("Input must be a whole number.  Try again.")
    return ret_value

def validate_split(split, number):
    ret_value = validate_whole_number(split)
    if ret_value != None:
        if len(str(number)) % ret_value != 0:
            ret_value = None
            print(number, "must be evenly divisible by", split)
    return ret_value

def execute_split(split, number):
    ret_value = []
    number_str = str(number)
    number_len = len(number_str)
    number_str_index = 0
    while number_str_index < number_len:
        ret_value.append(int(number_str[number_str_index:number_str_index + split]))
        number_str_index = number_str_index + split
    return ret_value

def print_split(pieces):
    pieces_len = len(pieces)
    pieces_index = 0
    while pieces_index < pieces_len:
        print(pieces[pieces_index], end="")
        pieces_index = pieces_index + 1
        if pieces_index < pieces_len:
            print(", ", end="")
        else:
            print()
            
def report_increasing(pieces):
    inc = True
    pieces_len = len(pieces)
    pieces_index = 1
    while inc and pieces_index < pieces_len:
        if pieces[pieces_index] <= pieces[pieces_index - 1]:
            inc = False
        pieces_index = pieces_index + 1
    report = "Sequence is "
    if not inc:
        report = report + "not "
    report = report + "increasing"
    return report

whole_number = None
while whole_number == None:
    whole_number = validate_whole_number(input("Input a large whole number: "))
split = None
while split == None:
    split = validate_split(input("Input the split (whole number): "), whole_number)
pieces = execute_split(split, whole_number)
print_split(pieces)
print(report_increasing(pieces))