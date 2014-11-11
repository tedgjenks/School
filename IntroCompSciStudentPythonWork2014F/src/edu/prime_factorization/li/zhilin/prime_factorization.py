while 1==1:
    number = int(input("Enter a number"))
    first_prime = 2
    factors = []
    
    def is_prime(number):
        prime = True
        divisor = first_prime
        while prime and divisor < number:
            if number % divisor == 0:
                prime = False
            divisor = divisor + 1
        return prime
    
    def factor(number,factors):
        loop_index = 2
        if is_prime(number) == True:
            factors.append(int(number))
            return None
        else:
            while number % loop_index != 0:
                loop_index += 1
            factor(number/loop_index,factors)
            factors.append(loop_index)
        factors.sort()
    
    
    def display_factors(number,factors):
        if is_prime(number) == True:
            return str(number) + " is a prime number!"
        for x in range(len(factors)):
            x 


