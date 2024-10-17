# Accounting Ledger Application

![project license](https://img.shields.io/badge/license-MIT-blue.svg)

## Description
The Account Ledger CLI Application is a Java-based command-line tool designed to help users manage their financial transactions in a simple and efficient way. The application allows users to log deposits and payments, generate financial reports, and maintain a record of all transactions, which are stored in a CSV file for future retrieval.




## Table of Contents

- [Usage](#usage)
    - [Screenshot](#screenshot)
- [Features](#features)
- [How It Works](#How-It-Works)
- [Prerequisites](#prerequisites)
- [License](#license)

------------------

## Usage
The Account Ledger CLI Application is used to track financial transactions like deposits and payments. Users can enter transaction details through a command-line interface, and the application will store and manage these transactions in a CSV file for future reference. The application offers reporting features that allow users to generate reports on their financial activity, such as month-to-date, year-to-date, and previous month transactions.

How to Use:
Launch the application from the command line.
Navigate through the menu to add deposits, record payments, or generate reports.
View reports to see all entries or filtered transactions (by month, year, etc.).
Exit the application when finished, with all transactions saved to a CSV file.
## Screenshot




------------------

## Features
<ul>
<li> Add Deposits: Users can log financial deposits with a description, vendor, and amount.
<li> Record Payments: Payments can be logged with the same details, and are stored as negative amounts.
<li> Report Generation:
  <ul> 
<li> Month-to-Date: View all transactions from the start of the current month.</li>
<li> Previous Month: Display all transactions that occurred in the previous month.</li>
<li> Year-to-Date: View transactions from the start of the current year.</li>
  </ul>
<li> Check In a Book: Users can return a checked-out book by entering its ID, after which the system updates the book’s status to available.
</ul>

## How It Works

1. Menu System: The user interacts with the application through a simple command-line menu. Each option corresponds to a particular action, such as adding a transaction or generating a report.
2. Transaction Storage: Transactions are saved to a CSV file (transactions.csv), ensuring that all data is persistent across application sessions.
3. Report Generation: The system reads through the stored transactions and generates financial reports based on the selected criteria (month, year, etc.).

------------------

## Prerequisites
<ul>
<li> Before running the Account Ledger CLI Application, ensure you have the following:
<ul>
<li>Java Development Kit (JDK) installed on your machine.</li>
<li>A command-line interface (CLI) to run the program.</li>
<li> A text editor or IDE (optional) to modify or view the source code.</li>
<li>The transactions.csv file in the project directory, or it will be created upon the first transaction.</li>
</ul>
</ul>
------------------


## License
MIT License
