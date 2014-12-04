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
    
    def format_factors(factors):
        index = 0
        while(index < len(factors) - 1):
            factor = factors[index]
            factor_count = 1
            start_index = index
            while(index + 1 < len(factors) and factors[index] == factors[index + 1]):
                factor_count += 1
                index += 1
                del factors[index - 1]
            if(factor_count > 1):
                exp_factor = str(factor) + "^" + str(factor_count)
                factors[start_index] = exp_factor
                factor_count += 1
            index = start_index + 1
        return factors
            
    def display_factors(number,factors):
        if is_prime(number) == True:
            return str(number) + " is a prime number!"
        if is_prime(number) == False:
            return factors
    