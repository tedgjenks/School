'''
Created on Oct 27, 2014

@author: HASTNI31
'''
number = 300
exit_cmd = "exiting"
while number != "exiting" :
    
    def is_prime(number):
        first_prime = 2
        prime= True
        divisor = first_prime
        while prime and divisor < number:
            if number% divisor ==0:
                prime = False
            divisor= divisor+1
        return prime
    print(is_prime(number))
        

    factors = []
    def factor(number, factors):
        if is_prime(number) == True: 
            None
        else:
            numtest = number
            done = False
            while done == False:
                first_prime = 2
                divisor = first_prime
                primefound = False
                while divisor < numtest and primefound == False:
                    if numtest % divisor == 0:
                        numtest = numtest / divisor
                        factors.append(divisor)
                        primefound = True
                    elif is_prime(numtest) ==  True:
                        factors.append(int(numtest))
                        done = True
                        primefound = True
                    divisor += 1
    factor(number, factors)
    
    def format_factors(factors):
        print(factors)
        
    format_factors(factors)
        
    def display_factors(number, factors):
        print("The number is ", number, "has", (len(factors)), "factors")
    display_factors(number, factors)