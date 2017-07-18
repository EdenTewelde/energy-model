package org.codefx.demo.bingen.bank

/*
 * TODO #3: Use the bank
 *
 * Create a bank, some customers, and accounts and transfer some money back and forth.
 */
fun main(args : Array<String>) {

    val bestbank : Bank = Bank()
    val eden = bestbank.newCustomer("Eden")
    val john = bestbank.newCustomer("John")
    val account = bestbank.openAccount(eden,Money(50),Balance(-100))
    account.deposit(Money(100))
    account.withdraw(Money(300))
    val accj = bestbank.openAccount(john,Money(100),limit = Balance(-100))
    println(bestbank.withdraw(accj,Money(150)))
    println(bestbank.closeAccount(john,accj))


}