'''
Created on Aug 25, 2014

@author: JenksT
'''
locked_message = "Program locked.  Enter password: "
password = "open sesame"
locked = 1
sentinel_value = "shutdown"
lock_command = "lock"
command = ""

while command != sentinel_value:
    if locked:
        while input(locked_message) != password:
            None
        locked = 0
    command = input("Type text to echo: ");
    if command == lock_command:
        locked = 1
    elif command != sentinel_value:
        print("Echo: " + command)
print("Program terminated")