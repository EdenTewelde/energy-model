package org.codefx.demo.bingen.bank

/*
 * TODO #3: Use the bank
 *
 * Create a bank, some customers, and accounts and transfer some money back and forth.
 */
fun main(args : Array<String>) {

    val bestbank : Bank = Bank()
    val john = bestbank.newCustomer("John Doe")
    val jane = bestbank.newCustomer("Jane Strange")
    val johnAccount1 = bestbank.openAccount(john,Money(500))
    val johnAccount2 = bestbank.openAccount(john,Money(1000))
    val janeAccount1 = bestbank.openAccount(jane,Money(20000))
    bestbank.deposit(john.defaultAccountNumber,Money(10000))
    bestbank.withdraw(janeAccount1,Money(5000))
    bestbank.deposit(jane.defaultAccountNumber,Money(12340))
    bestbank.transferBetweenCustomers(jane,john,Money(5000))
    bestbank.transferBetweenAccounts(johnAccount2,janeAccount1,Money(10000))
    bestbank.closeAccount(john,johnAccount1)
    bestbank.getBankReports()

}