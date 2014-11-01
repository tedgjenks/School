'''
Created on Sep 22, 2014

@author: JenksT
'''
n = "n"
d = "d"
q = "q"
dc = "dc"
coin_values_cents = {n:5, d:10, q:25, dc:100}
stock = {n:50, d:50, q:50, dc:20}
user_price_cents = None
while user_price_cents == None:
    user_price_cents = int(input("Enter a price in the form xx.xx or 'q' to quit: ").replace('.', ''))
    if user_price_cents % 5 != 0:
        print("Enter a multiple of 5 cents!")
        user_price_cents = None
print("Enter your deposits. Enter 'c' at any time to cancel.")
print("'n' = nickel, 'd' = dime, 'q' = quarter, 'dc' = dollar coins")
total_amt_entered = 0
while total_amt_entered < user_price_cents:
    deposit = input("Deposit")
    
          
print("Program terminated.")