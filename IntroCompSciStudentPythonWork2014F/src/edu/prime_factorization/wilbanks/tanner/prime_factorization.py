def is_prime(number):
    prime = True
    divisor = 2
    while prime and divisor < number:
        if number % divisor == 0:
            prime = False
        divisor = divisor + 1
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
    factors.sort()
def format_factors(factors):
    LeNumber = len(factors)
    FormatedList = factors
    primecounter = 3
    factorsl = []
    startnewprime = 0
    counter = 0
    if FormatedList[0] == 2:
        try:   
            while FormatedList[counter] == 2:
                counter += 1
            spaces = counter
        except IndexError:
            spaces = counter
        if spaces == 1:
            factorsl.append(2)
        else:
            factorsl.append("2^" + str(spaces))
        startnewprime = spaces
    while LeNumber > counter:
        if FormatedList[counter] == primecounter:
            try:
                while FormatedList[counter] == primecounter:
                    counter +=  1
                    spaces = counter + 1
            except IndexError:
                spaces = counter + 2
            numnumber = spaces-startnewprime - 1
            if numnumber == 1:
                factorsl.append(primecounter)
            else:
                factorsl.append(repr(primecounter) + "^" + repr(numnumber))
        else:
            primecounter += 2
        startnewprime = spaces
    for x in range(len(factors)):
        a = factors[0]
        factors.remove(a)
    newlist = []
    for y in range(len(factorsl)):
        newlist.append(factorsl.pop())
    for y in range(len(newlist)):
        factors.append(newlist.pop())    
def display_factors(number, factors):
    if is_prime(number):
        return(number, "is prime!")
    else:
        javanot = (str(number) + " = ")
        for x in range(len(factors)):
            if x != len(factors) - 1: 
                javanot += (str(factors[x]) + " * ")
            else:
                javanot += str(factors[x])
        return javanot