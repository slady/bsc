This is the Package Delivery software.
Banking Software Company s.r.o. requested this piece of software as a homework for a job interview process.

Technology
==========
This software was build using the Java programming language with Maven as the build automation tool and JUnit for unit testing.

In order to build this project using maven, run:
```
mvn clean package
```

In order to run the program from, run the following command from the command line:
```
java -jar target\BSC-1.0-SNAPSHOT.jar
```

User can test input files provided for weights using the following command:
```
java -jar target\BSC-1.0-SNAPSHOT.jar src/test/resources/weights.txt
```

And test input files both for weights and fees with this command:
```
java -jar target\BSC-1.0-SNAPSHOT.jar src/test/resources/weights.txt src/test/resources/prices.txt
```

Business Requirements
=====================
The purpose of this program is to keep a record of packages processed.
Each package information consists of weight (in kg) and destination postal code.
Data are stored in memory, no database engines were introduced as per customer request.

The program:
* reads user input from console, user enters a line consisting of weight of package and destination postal code
* once per minute - writes output to console, each line consists of postal code and total weight of all packages for that postal code
* processes user command “quit”, when user enters quit to command line as input, program exits
* takes and processes command line arguments specified at program run – filename of file containing lines in same format as user can enter in command line. This is considered as initial load of package information.
* handles invalid input of the user (it is up to you how, describe implemented behaviour in readme file)

Input line format:
```
<weight: positive number, >0, maximal 3 decimal places, . (dot) as decimal separator><space><postal code: fixed 5 digits> 
```
Sample input: 
```
3.4 08801
2 90005
12.56 08801
5.5 08079
3.2 09300
```
Output line format:
```
<postal code: fixed 5 digits><space><total weight: fixed 3 decimal places, . (dot) as decimal separator>
```
Sample output (order by total weight): 
```
08801 15.960
08079 5.500
09300 3.200
90005 2.000
```
Optional bonus task
===================
The optional bonus task was implemented:

The program takes and processes second command line argument specified at program run.
It is the filename of file containing information about fees related to package weight.
Once such file is specified as argument then output written to console will also contain
total fee for packages sent to certain postal code.

Line format:
```
<weight: positive number, >0, maximal 3 decimal places, . (dot) as decimal separator><space><fee: positive number, >=0, fixed two decimals, . (dot) as decimal separator> 
```
Format of file containing fees (sample):
```
10 5.00
5 2.50
3 2.00
2 1.50
1 1.00
0.5 0.70
0.2 0.50
```
Sample output: 
```
08801 15.960 7.00
08079 5.500 2.50
09300 3.200 2.00
90005 2.000 1.50
```
Output line format:
```
<postal code: fixed 5 digits><space><total weight: fixed 3 decimal places, . (dot) as decimal separator><space><total fee: fixed 2 decimal places, . (dot) as decimal separator)
```
Code quality
============
The program is thread safe and the service code functionality is covered with unit tests.
