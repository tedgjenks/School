'''
Created on Sep 24, 2014

@author: JenksT
'''
import math
import turtle
import sys
from jenks.math.utils import calculate_slope
from jenks.math.utils import create_slope_intercept

def prompt_2_points(points_list):
    points_list.append(int(input("Enter your first x-coordinate: ")))
    points_list.append(int(input("Enter your first y-coordinate: ")))
    points_list.append(int(input("Enter your second x-coordinate: ")))
    points_list.append(int(input("Enter your second y-coordinate: ")))
    
def pop_points_cl(point1, point2):
    for arg in range(1, 5):
        point1.append(int(sys.argv[arg]))
    for arg in range(5, 9):
        point2.append(int(sys.argv[arg]))
    
def draw_line(x1, y1, x2, y2):
    slope = calculate_slope(x1, y1, x2, y2)
    turtle.up()
    turtle.goto(x1, y1)
    turtle.down()
    turtle.goto(x2, y2)
    if slope == None:
        turtle.write("x = " + str(x1))
    else:
        turtle.write(create_slope_intercept(x1, y1, slope))
        
def calc_angle_incl(slope):
    radians = math.atan(abs(slope))
    return round(radians * 180 / math.pi)
        
def calc_angle(slope1, slope2):
    radians = math.atan(abs((slope1 - slope2) / (1 + slope1 * slope2)))
    return round(radians * 180 / math.pi)

point1 = []
point2 = []
#print("Enter data for line 1")
#prompt_2_points(point1)
#print("Enter data for line 2")
#prompt_2_points(point2)
pop_points_cl(point1, point2)
draw_line(point1[0], point1[1], point1[2], point1[3])
draw_line(point2[0], point2[1], point2[2], point2[3])
slope1 = calculate_slope(point1[0], point1[1], point1[2], point1[3])
slope2 = calculate_slope(point2[0], point2[1], point2[2], point2[3])
turtle.up()
turtle.goto(250, -275)
if slope1 == slope2:
    turtle.write("Parallel lines")
elif slope1 == None or slope2 == None:
    if slope1 != None:
        turtle.write(str(90 - calc_angle_incl(slope1)) + ' degrees')
    else:
        turtle.write(str(90 - calc_angle_incl(slope2)) + ' degrees')
elif slope1 * slope2 == -1:
    turtle.write('90 degrees')
else:
    turtle.write(str(calc_angle(slope1, slope2)) + ' degrees')
turtle.done()