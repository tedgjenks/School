'''
Created on Sep 24, 2014

@author: JenksT
'''
import turtle
from jenks.math.utils import calculate_slope
from jenks.math.utils import create_slope_intercept

x1 = int(input("Enter your first x-coordinate: "))
y1 = int(input("Enter your first y-coordinate: "))
x2 = int(input("Enter your second x-coordinate: "))
y2 = int(input("Enter your second y-coordinate: "))

turtle.up()

slope = calculate_slope(x1, y1, x2, y2)

turtle.goto(x1, y1)
turtle.down()
turtle.goto(x2, y2)
if slope == None:
    turtle.write("x = " + str(x1))
else:
    turtle.write(create_slope_intercept(x1, y1, slope))

turtle.up()
turtle.home()

turtle.done()