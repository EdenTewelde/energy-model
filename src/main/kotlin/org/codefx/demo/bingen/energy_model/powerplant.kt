package org.codefx.demo.bingen.energy_model

class Powerplant(maxCapacity : Energy) {

    var capacity : Energy = maxCapacity

    fun produce(order : EnergyOrder) : Energy {
        if (capacity.amount >= order.amount) {
            val producedenergy = Energy(order.amount)
            return producedenergy
        } else {
            return capacity
        }
    }

}