package coffeemachine

fun main() {
    println("Write how many cups of coffee you will need: ")
    val cupsOfCoffee = readln().toInt()

    calculateIngredients(cupsOfCoffee)
}

private fun calculateIngredients(cupsOfCoffee: Int) {
    println(
        """
    For 125 cups of coffee you will need: 
    ${cupsOfCoffee * 200} ml of water
    ${cupsOfCoffee * 50} ml of milk
    ${cupsOfCoffee * 15} g of coffee beans
    """.trimIndent()
    )
}
