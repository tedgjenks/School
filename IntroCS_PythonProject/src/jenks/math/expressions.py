'''
Created on Aug 18, 2014

@author: JenksT
'''
import math

def calcMiles(rateMinutes, timeHours):
    #walk 1 mile per rateMinutes
    return 60 * timeHours / rateMinutes

#print(calcMiles(15, 10))

def calcAds(minCommPerHour, secPerAd, hoursProg):
    return 60 * minCommPerHour * hoursProg / secPerAd

print(calcAds(20, 30, 21))












def calcMilesPerHour(rotPerMinute, radiusFeet):
    return rotPerMinute * 60 * 2 * math.pi * radiusFeet / 5280



#print(calcMilesPerHour(180, 1.2))