


while 1 == 1:
    return_value = None
    prompt = input("Give a number with three digits or more:")
    while return_value == None:
        try:
            user_input = input(prompt)
            return_value = int(user_input)
            if return_value < 0:
                return_value = None
                print("Must be more than 0")
        except ValueError:
            return_value = None
            print("Not an integer")
        num_list = []
        num_list_len = len(num_list)
        second_return_value = None
        group_num = input("How many digits in each group:")
        while second_return_value = None:
            try:
                user_input = input(group_num)
                second_return_value = int(user_input)
                if num_list_len % second_return_value != 0:
                    return_value2 = None
                    print("Cannot group this way try again:")
                except ValueError:
                    return_value = None
                    print("Not an integer")
        counter = 0
        while counter < groups:
            first_index = counter * second_return_value
            print(num_list[first_index:first_index + second_return_value], end = " ")
            counter += 1
            if counter < groups:
                print( ",", end = "")
        
        
        

        


