package org.codefx.demo.bingen.energy_model

class Account(var balance: Int, var limit: Int) {

    fun deposit(amount: Int): Int {
        if (amount < 0) {
            println("Please enter a positive amount.")
            return 0
        } else {
            balance += amount
            return amount
        }

    }

    fun withdraw(amount: Int): Int {
        if (balance-limit >= amount) {
            if (amount < 0) {
                println("Please enter a positive amount.")
                return 0
            } else {
                // balance suffices, pay out the money
                balance -= amount
                return amount
            }
        } else {
            // insufficient balance; pay out 0
            return balance
        }
    }

    fun currentBalance(): Int {
        return balance
    }

}
