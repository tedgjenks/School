'''
Created on Sep 24, 2014

@author: JenksT
'''
def calculate_slope(x1, y1, x2, y2):
    x_change = x2 - x1
    ret_value = None
    if x_change != 0:
        ret_value = (y2 - y1) / x_change
    return ret_value 

def create_slope_intercept(x1, y1, slope):
    y_int = slope * x1 * -1 + y1
    return "y = " + str(round(slope, 3)) + "x + " + str(round(y_int, 3))