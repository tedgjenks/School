'''
Created on Sep 25, 2014

@author: JenksT
'''
import turtle
import random

turtle.title('Concentric Circles')
outer_radius = int(input('Enter the outer radius: '))
num_circles = int(input('Enter the number of circles: '))
colors = ['red', 'orange', 'yellow', 'green', 'blue', 'indigo', 'violet']

color_index = 0
counter = num_circles
while counter > 0:
    radius = round(outer_radius * counter / num_circles) 
    turtle.up()
    turtle.goto(0, -1 * radius)
    turtle.down()
    turtle.color(colors[color_index])
    color_index += 1
    if color_index >= len(colors):
        color_index = 0
    turtle.begin_fill()
    turtle.circle(radius)
    turtle.end_fill()
    counter -= 1

turtle.done()