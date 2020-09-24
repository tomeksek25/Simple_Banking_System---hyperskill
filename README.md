# Simple_Banking_System---hyperskill
FIRST STAGE:
You should allow customers to create a new account in our banking system.

Once the program starts, you should print the menu:

1. Create an account
2. Log into account
0. Exit

If the customer chooses ‘Create an account’, you should generate a new card number which satisfies all the conditions described above. 
//Comment: Starts with 400000 and passes the Luhn algorithm.
Then you should generate a PIN code that belongs to the generated card number. A PIN code is a sequence of any 4 digits. PIN should be generated in a range from 0000 to 9999.

If the customer chooses ‘Log into account’, you should ask them to enter their card information. Your program should store all generated data until it is terminated so that a user is able to log into any of the created accounts by a card number and its pin. You can use an array to store the information.

After all information is entered correctly, you should allow the user to check the account balance; right after creating the account, the balance should be 0. It should also be possible to log out of the account and exit the program.

NEXT STAGE:

In this stage, create a database with a table titled card. It should have the following columns:

    id INTEGER
    number TEXT
    pin TEXT
    balance INTEGER DEFAULT 0

Also, in this stage, you should read the database file name from the command line argument. Filename should be passed to the program using -fileName argument, for example, -fileName db.s3db.

Pay attention: your database file should be created when the program starts, if it hasn’t yet been created. And all created cards should be stored in the database from now.

LAST STAGE:

You have created the foundation of our banking system. Now let's take the opportunity to deposit money into an account, make transfers and close an account if necessary.

Now your menu should look like this:

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit

If the user asks for Balance, you should read the balance of the account from the database and output it into the console.

Add income item should allow us to deposit money to the account.

Do transfer item should allow transferring money to another account. You should handle the following errors:

    If the user tries to transfer more money than he/she has, output: "Not enough money!"
    If the user tries to transfer money to the same account, output the following message: “You can't transfer money to the same account!”
    If the receiver's card number doesn’t pass the Luhn algorithm, you should output: “Probably you made mistake in the card number. Please try again!”
    If the receiver's card number doesn’t exist, you should output: “Such a card does not exist.”
    If there is no error, ask the user how much money they want to transfer and make the transaction.

If the user chooses the Close an account item, you should delete that account from the database.
