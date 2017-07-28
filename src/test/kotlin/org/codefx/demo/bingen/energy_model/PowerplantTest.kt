package org.codefx.demo.bingen.energy_model

import org.junit.Test
import org.junit.Assert.assertEquals

class PowerplantTest() {

    @Test
    fun produceRightAmount() {
        val plant = Powerplant(Energy(5000))
        val consumption = Household(EnergyOrder(2500)).consumption(EnergyOrder(1000))

        val produced = plant.produce(consumption)

        assertEquals(Energy(1000),produced)

    }

}