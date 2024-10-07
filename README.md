# Gated-Community-Complaint-Redressal-System
This is a java program using jdbc connectivity and java swing UI to help apartment management add, search and close maintenance complaints

->Make sure you have mysql connector JAR file in the reference libraries, if you are using vscode

->Replace <user> and <password> with your mysql username and password

->create a database complaintdb and create a table Complaint with following description:

| Name             | varchar(30) |
| Apartmentno      | varchar(4)  | 
| Complaintcat     | varchar(20) |
| Description      | varchar(40) |
| Phone            | varchar(15) |
| ServiceExecName  | varchar(30) |
| ServiceExecPhone | varchar(15) |
| Status           | varchar(20) |

or just paste this code into your sql command client:

CREATE TABLE Complaint(Name varchar(30),Apartmentno varchar(4),Complaintcat varchar(20),Description varchar(40),Phone varchar(15),ServiceExecName varchar(30),ServiceExecPhone varchar(!5),Status varchar(20));

->dont change the file names in this repository when you are executing in your system
