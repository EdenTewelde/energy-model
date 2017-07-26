package org.codefx.demo.bingen.bank

import java.util.*

class Bank {

    /*
     * TODO #4: improve [Bank]
     *
     * Currently the bank hands out accounts (e.g. [openAccount] returns an [Account]).
     * That's weird because it allows customers to interact directly with the account
     * without going through the bank.
     *
     * Instead of acting on [Account] entities all methods should receive and return
     * [AccountNumber] values (i.e. data classes). Before starting to write any code
     * consider carefully:
     *
     *  - what should the [AccountNumber] value class have as fields
     *    (think about what your account number looks like but keep it simple)
     *  - who needs to know about the account numbers? the account? the customer? the bank?
     *  - given an account number how does the bank get the corresponding account
     *    (two ideas: collect all accounts in a map[1] or put them all in the list
     *    and search through that)
     *  - if you have any problems, open a PR with what you have
     *
     * [1] https://www.youtube.com/watch?v=FUqD6srpuPY
     */

    val customers: MutableList<Customer> = mutableListOf()
    val allAccounts : MutableMap<AccountNumber,Account> = mutableMapOf()
    var newAccountNumber : AccountNumber = AccountNumber(0)
    val report : MutableList<String> = mutableListOf()

    fun getAccountNumber() : AccountNumber {
        newAccountNumber = newAccountNumber.next()
        return newAccountNumber
    }

    fun openAccount(customer: Customer, openingDeposit: Money = Money(0), limit: Balance = Balance(0)): AccountNumber {
        if (!customers.contains(customer)) {
            println("!! CUSTOMER DOES NOT BELONG TO THIS BANK")
        }
        val newAccount = Account(this,openingDeposit,limit)
        val newAccountNumber = this.getAccountNumber()
        customer.accounts.add(newAccountNumber)
        allAccounts.put(newAccountNumber,newAccount)
        addReport("Open account for " + customer.name + "(#" + newAccountNumber.accountNumber +")" )
        return newAccountNumber
    }

    fun closeAccount(customer: Customer, accountNumber: AccountNumber): Money {
        val account : Account? = allAccounts[accountNumber]
        if (account == null) {
            println("!! ACCOUNT NUMBER DOES NOT EXIST IN THIS BANK")
            return Money(0)
        }

        if (!customers.contains(customer)) {
            println("!! CUSTOMER DOES NOT BELONG TO THIS BANK")
            return Money(0)
        }
        if (account.balance.isOverdrawn()) {
            println("!! OVERDRAWN ACCOUNT CAN NOT BE CLOSED")
            return Money(0)
        }

        customer.accounts.remove(accountNumber)
        allAccounts.remove(accountNumber)

        if (customer.accounts.isEmpty()) {
            customers.remove(customer)
            allAccounts.remove(accountNumber)
        }
        addReport("Close account #" + accountNumber.accountNumber)
        return account.withdrawRemaining()

    }

    fun newCustomer(name: String): Customer {
        val newAccount = Account(this)
        val newAccountNumber = this.getAccountNumber()
        val newCustomer = Customer(name, newAccountNumber)
        customers.add(newCustomer)
        allAccounts.put(newAccountNumber,newAccount)
        addReport("New Customer: " + newCustomer.name)
        return newCustomer
    }

    fun balance(accountNumber: AccountNumber): Balance {
        val account = allAccounts[accountNumber]
        if (account == null) {
            println("!! ACCOUNT NUMBER DOES NOT BELONG TO THIS BANK")
            return Balance(0)
        }
        return account.balance
    }

    fun deposit(accountNumber: AccountNumber, amount: Money): Money {
        val account = allAccounts[accountNumber]
        if (account == null) {
            println("!! ACCOUNT NUMBER DOES NOT BELONG TO THIS BANK")
            return Money(0)
        }
        addReport("Deposit " + amount.centAmount/100.0 + " " + amount.currency + " to account #"
                + accountNumber.accountNumber)
        return account.deposit(amount)
    }

    fun withdraw(accountNumber: AccountNumber, amount: Money): Money {
        val account = allAccounts[accountNumber]
        if (account == null) {
            println("!! ACCOUNT NUMBER DOES NOT BELONG TO THIS BANK")
            return Money(0)
        }
        val withdrawnAmount = account.withdraw(amount)
        if (withdrawnAmount.centAmount > 0) {
            addReport("Withdraw " + amount.centAmount / 100.0 + " " + amount.currency + " from account #"
                    + accountNumber.accountNumber)
        }
        return withdrawnAmount
    }

    fun transferBetweenAccounts(from: AccountNumber, to: AccountNumber, amount: Money): Money {
        val fromAccount = allAccounts[from]
        if (fromAccount == null) {
            println("!! ACCOUNT NUMBER (SENDER) DOES NOT BELONG TO THIS BANK")
            return Money(0)
        }
        val toAccount = allAccounts[to]
        if (toAccount == null) {
            println("!! ACCOUNT NUMBER (RECEIVER) DOES NOT BELONG TO THIS BANK")
            return Money(0)
        }
        val transferredAmount = fromAccount.withdraw(amount)
        toAccount.deposit(transferredAmount)
        if (transferredAmount.centAmount > 0) {
            addReport("Transfer " + amount.centAmount / 100.0 + " " + amount.currency + " from account #"
                    + from.accountNumber + " to account #" + to.accountNumber)
        }
        return transferredAmount
    }

    fun transferBetweenCustomers(from: Customer, to: Customer, amount: Money): Money {
        val fromAccountNumber = from.defaultAccountNumber
        val toAccountNumber = to.defaultAccountNumber
        val fromAccount = allAccounts[fromAccountNumber]
        if (fromAccount == null) {
            println("!! ACCOUNT NUMBER (SENDER) DOES NOT BELONG TO THIS BANK")
            return Money(0)
        }
        val toAccount = allAccounts[toAccountNumber]
        if (toAccount == null) {
            println("!! ACCOUNT NUMBER (RECEIVER) DOES NOT BELONG TO THIS BANK")
            return Money(0)
        }
        val transferredAmount = fromAccount.withdraw(amount)
        toAccount.deposit(transferredAmount)
        if (transferredAmount.centAmount > 0) {
            addReport("Transfer " + amount.centAmount / 100.0 + " " + amount.currency + " from: " + from.name + " to: "
                    + to.name)
        }
        return transferredAmount
    }

    fun addReport(action : String){
        this.report.add(action)
    }

    fun getBankReports() {
        for (item in this.report) {
            println(item)
        }
    }
}
