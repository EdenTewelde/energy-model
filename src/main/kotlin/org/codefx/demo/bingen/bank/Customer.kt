package org.codefx.demo.bingen.bank

class Customer(val name: String, var defaultAccountNumber: AccountNumber) {

    val accounts: MutableList<AccountNumber> = mutableListOf()

    init {
        accounts.add(defaultAccountNumber)
    }

}
