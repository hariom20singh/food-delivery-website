# Python code
# To print even length words in string

# input string
n = "This is a python language"
# splitting the words in a given string
s = n.split(" ")
for i in s:
    # checking the length of words
    if len(i) % 2 == 0:
        print(i)

# this code is contributed by gangarajula laxmi
