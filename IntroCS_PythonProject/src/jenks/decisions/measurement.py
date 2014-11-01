'''
Created on Aug 26, 2014

@author: JenksT
'''
giga = "Gm"
mega = "Mm"
kilo = "km"
hecto = "hm"
deca = "dam"
meter = "m"
deci = "dm"
centi = "cm"
milli = "mm"
micro = "um"
nano = "nm"
prefix_values = {}
prefix_values[giga] = 10 ** 9
prefix_values[mega] = 10 ** 6
prefix_values[kilo] = 10 ** 3 
prefix_values[hecto] = 10 ** 2 
prefix_values[deca] = 10 ** 1 
prefix_values[meter] = 10 ** 0
prefix_values[deci] = 10 ** -1
prefix_values[centi] = 10 ** -2
prefix_values[milli] = 10 ** -3
prefix_values[micro] = 10 ** -6
prefix_values[nano] = 10 ** -9

def convert_to_meters(number, unit):
    return number * prefix_values[unit]

def convert_from_meters(number, unit):
    return number / prefix_values[unit]

def convert(number, current_unit, desired_unit):
    meters = convert_to_meters(number, current_unit)
    return convert_from_meters(meters, desired_unit)

end = False
while not end:
    number_string = input("Enter the number of units you want to convert (zero will exit): ")
    try:
        number = float(number_string)
        if(number == 0):
            end = True
            print("Program terminated")
        else:
            current_unit = input("Enter the unit you have: ")
            desired_unit = input("Enter the unit you want: ")
            if current_unit not in prefix_values or desired_unit not in prefix_values:
                print("Unrecognized unit!")
            else:
                print(number, current_unit, "=", convert(number, current_unit, desired_unit), desired_unit)
    except ValueError:
        print("'" + number_string + "'", "is not a number!")