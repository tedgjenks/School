'''
Created on Sep 23, 2014

@author: JenksT
'''
import turtle

turtle.title("Let's draw a square!")

turtle.up()
turtle.goto(-100, -100)
turtle.down()

turtle.goto(-100, 100)
turtle.goto(100, 100)
turtle.goto(100, -100)
turtle.goto(-100, -100)

turtle.up()
turtle.goto(0, -100)
turtle.down()
turtle.circle(100)

turtle.up()
turtle.home()
turtle.write("Done!")

turtle.done()