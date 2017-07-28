package org.codefx.demo.bingen.energy_model

data class Energy(val amount : Int) {
    init {
        if (amount < 0) {
            println("Negative energy is not possible!")
        }
    }
}

data class EnergyOrder(val amount : Int) {
    init {
        if (amount < 0) {
            println("Negative energy is not possible!")
        }
    }
}