def is_prime(number):
    if number > 1:
        prime = True
        first_prime = 2
        divisor = first_prime
        while prime and divisor < number:
            if number % divisor == 0:
                return False
            divisor = divisor + 1 
        else:
            return True

def factor(number, factors):
    divisor = 2
    while number%divisor !=0:
        divisor += 1
    if is_prime(divisor) == True:
        factors.append(divisor)
        factors.sort()
    if number > divisor:
        number = number//divisor
        factor(number, factors)
    return factors

def format_factors(factors):
    factors2=[]
    factors.sort()
    for x in range(len(factors)):
        factors2.append(factors.pop())
    factors2.sort()
    count1=1
    a=factors2[0]
    for x in range(len(factors2)):
        if factors2[x] !=a:
            a= factors2[x]
            count1 +=1
    end = count1
    for a in range(count1):
        count1=1
        count2=0
        a= factors2[0]
        for x in range(len(factors2)):
            if factors2[x] !=a:
                a= factors2[x]
                count1 +=1
        a=factors2[0]
        for y in range(len(factors2)):
            if factors2[y]==a:
                count2+=1
        for z in range(count2):
            factors2.remove(a)
        if count2 >1:
            if len(factors) == end -1:
                factors.append((str(a)+"^"+str(count2)))
            else:
                factors.append((str(a)+"^"+str(count2)))
        else:
            if len(factors) == end -1:
                factors.append(int(a))
            else:
                factors.append(int(a))
        
def display_factors(number, factors):
    if is_prime(number) == True:
        return(str(number) + " is prime!")
    else:
        display = (str(number)+' = ')
        for x in range(len(factors)):
            if x==len(factors)-1:
                display=display + str(factors[x])
            else:
                display=display + str(factors[x])+" * "
        return display