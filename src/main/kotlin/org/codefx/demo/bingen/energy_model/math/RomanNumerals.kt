package org.codefx.demo.bingen.energy_model.math

fun asRoman(n: Int): String {
    var roman : String
    val one = "I"
    val five = "V"

    when (n) {
        in 0..9 -> roman = ""
        in 10..19 -> roman = "X"
        in 20..29 -> roman = "XX"
        in 30..39 -> roman = "XXX"
        in 40..49 -> roman = "XL"
        in 50..59 -> roman = "L"
        else -> roman = ("Please enter a number between 1 and 50")
    }
        when (n%10) {
            1 -> roman += one
            2 -> roman += one + one
            3 -> roman += one + one + one
            4 -> roman += one + five
            5 -> roman += five
            6 -> roman += five + one
            7 -> roman += five + one + one
            8 -> roman += five + one + one + one
            9 -> roman += "IX"
            else -> roman += ""
        }

    return roman
}
