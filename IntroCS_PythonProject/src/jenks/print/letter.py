'''
Created on Aug 22, 2014

@author: JenksT
'''

city_state_zip = "Greenwood, SC  29646"
num_stamp_rows = 4
min_width = 34
width = int(input("Enter the envelope width"))
if(width < min_width):
    quit("That width is too small!")
min_height = 12
height = int(input("Enter the envelope height"))
if(height < min_height):
    quit("That height is too small!")

def top_bottom_border():
    return fill_right("+", "-",  "+")

def empty_side_border():
    return fill_right("|", " ", "|")

def fill_right(left_text, fill_char, end_char):
    full_text = left_text + fill_char * (width - len(left_text) - 1) + end_char
    return full_text

def fill_left(left_char, fill_char, percent_position):
    position = int(width * percent_position / 100)
    left_text = left_char + fill_char * (position - 1)
    return left_text

def stamp():
    stamp_rows = ""
    for count in range(0, num_stamp_rows):
        stamp_row = fill_left("|", " ", 80)
        stamp_row += "*" * 5
        stamp_row = fill_right(stamp_row, " ", "|")
        stamp_rows += stamp_row
        if(count < num_stamp_rows - 1):
            stamp_rows += "\n"
    return stamp_rows

consumed_height = 0
print(top_bottom_border())
consumed_height += 1
buffer = int(.1 * height)
for count in range(0, buffer):
    print(empty_side_border())
consumed_height += buffer
print(stamp())
consumed_height += num_stamp_rows
buffer = int(.3 * height)
for count in range(0, buffer):
    print(empty_side_border())
consumed_height += buffer
print(fill_right(fill_left("|", " ", 40) + "My Name", " ", "|"))
print(fill_right(fill_left("|", " ", 40) + "Street Address", " ", "|"))
print(fill_right(fill_left("|", " ", 40) + city_state_zip, " ", "|"))
consumed_height += 3
remaining_height = height - consumed_height - 1
for count in range(0, remaining_height):
    print(empty_side_border())
print(top_bottom_border())