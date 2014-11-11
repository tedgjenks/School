'''
Created on Oct 13, 2014

@author: SMITRO47
'''
def is_prime(number):
    prime = True
    divisor = 2 
    while prime and divisor < number:
        if number % divisor == 0:
            prime = False  
        divisor += 1
    return prime
 
def factor(number, factors):
    numtest = number
    while numtest != 1:
        primefound = False
        if numtest % 2 == 0:
            primecounter = 2
        else:
            primecounter = 3
            while primefound == False:
                if (numtest % primecounter) == 0:
                    primefound = True
                else:
                    primecounter += 2 
        numtest = numtest / primecounter
        factors.append(primecounter)
    return factors
   
 

        
    
    
    