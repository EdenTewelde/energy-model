package org.codefx.demo.bingen.bank

/*
 * TODO #3: Use the bank
 *
 * Create a bank, some customers, and accounts and transfer some money back and forth.
 */
fun main(args : Array<String>) {

    val bestbank : Bank = Bank()
    val eden = bestbank.newCustomer("Eden")
    val fifty : Money = Money(5000)
    val balance100 : Balance = Balance(10000)
    val account = bestbank.openAccount(eden,fifty,balance100)
    account.deposit(fifty)
    account.withdraw(Money(3000))
    val john = bestbank.newCustomer("John")
    val jaccount = bestbank.openAccount(john,openingDeposit = Money(100),limit = Balance(50))
    bestbank.deposit(jaccount,Money(100))
    println(bestbank.balance(jaccount))






}