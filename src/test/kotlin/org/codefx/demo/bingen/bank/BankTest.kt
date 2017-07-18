package org.codefx.demo.bingen.bank

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BankTest {

    val bank = Bank()

    // OPENING / CLOSING

    /*
     * TODO #1: fix tests
     *
     * Some tests fail - change the code (note the test code!) to make them pass.
     */

    @Test
    fun newCustomerMustHaveAccount() {
        val john = bank.newCustomer("John Doe")
        assertFalse(john.accounts.isEmpty())
    }

    @Test
    fun newAccountMustBelongToCustomer() {
        val john = bank.newCustomer("John Doe")
        val account = bank.openAccount(john)

        assertTrue(john.accounts.contains(account))
    }

    @Test
    fun newAccountMustContainOpeningDeposit() {
        val john = bank.newCustomer("John Doe")
        val account = bank.openAccount(john, openingDeposit = Money(100))
        // bank.deposit(account,Money(100)) // not necessary after fixing openingDeposit in Bank

        assertTrue(bank.balance(account) == Balance(100))
    }

    @Test
    fun closingAccountPaysOutAllMoney() {
        val john = bank.newCustomer("John Doe")
        val account = bank.openAccount(john, openingDeposit = Money(150))

        assertTrue(bank.closeAccount(john, account) == Money(150))
    }

    @Test
    fun closingOverdrawnAccountPaysOutNoMoney() {
        val john = bank.newCustomer("John Doe")
        val account = bank.openAccount(john, openingDeposit = Money(100), limit = Balance(-100))
        bank.withdraw(account, Money(150))

        assertTrue(bank.closeAccount(john, account) == Money(0))
    }

    // TRANSFERRING

    /*
     * TODO #2: write tests
     *
     * Verify that:
     *  - depositing money works
     *  - withdrawing money works
     *  - transferring money between accounts works
     *  - transferring money between customers works
     *
     *  If some new tests fail, fix the code to make them pass
     */
    @Test
    fun depositMoney() {
        val john = bank.newCustomer("John Doe")
        val account = bank.openAccount(john)
        val amount = bank.deposit(account,Money(100))
        assertTrue(amount == Money(100))
    }

    @Test
    fun withdrawMoney() {
        val john = bank.newCustomer("John Doe")
        val account = bank.openAccount(john, Money(200))
        assertTrue(bank.withdraw(account,Money(50)) == Money(50))
    }

    @Test
    fun transferringBetweenAccounts() {
        val john = bank.newCustomer("John Doe")
        val account = bank.openAccount(john, Money(500))
        val account2 = bank.openAccount(john)
        assertTrue(bank.transferBetweenAccounts(account,account2,Money(200)) == Money(200))
    }

    @Test
    fun transferringBetweenCustomers() {
        val john = bank.newCustomer("John Doe")
        val jane = bank.newCustomer("Jane Doe")
        val account = bank.openAccount(john, Money(150))
        val account2 = bank.openAccount(jane, Money(500))
        assertTrue(bank.transferBetweenCustomers(jane,john,Money(100)) == Money(100))

    }
}
