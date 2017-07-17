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

}
