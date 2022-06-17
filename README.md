# Smart_Calculator
HyperSkill Project #20

Directory:
Smart_Calculator/Smart Calculator/task/src/calculator/

https://hyperskill.org/projects/42

Smart Calculator
This project is a command line calculator that can perform the following: Perform assignment to variables (use only latin characters uppercase or lowercase from A-Z), use parenthesis (to evaluate operations in order of precedence), Exponents, Multiplication, Division, Modulus, Addition and Subtraction. This program can also interept negative integers and all the evaluations are returned as integers (BigInteger). The program uses RPN (Reverse Polish Notation) by taking the infix notation and rearranging the user inputs and parsing the input (using Regex (Regular Expressions)) to format the data into a format that can be easily interpretted using the Shunting Yard Algorithm. This program uses Enumerators for the operations and uses a Strategy Pattern to use each of the expressions (Excluding assignment). For assignment, the variable name is saved as a String and put into a map for later reference during operation.
