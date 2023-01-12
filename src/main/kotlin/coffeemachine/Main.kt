package coffeemachine

fun main() {
    val ingredients = getIngredients()
    val neededCupsOfCoffee = getNeededCoffee()
    val maxCupsOfCoffee = calculateMaxCupsOfCoffee(ingredients)
    runCoffeeMachine(neededCupsOfCoffee, maxCupsOfCoffee)
}

private fun getIngredients(): Array<Int> {
    val ingredientNames = arrayOf("water", "milk", "coffee")
    val ingredients = Array(3) { 0 }

    for (i in ingredientNames.indices) {
        println("Write how many ml of ${ingredientNames[i]} the coffee machine has: ")
        ingredients[i] = readln().toInt()
    }

    return ingredients
}

private fun getNeededCoffee(): Int {
    println("Write how many cups of coffee you will need: ")
    return readln().toInt()
}

fun calculateMaxCupsOfCoffee(ingredients: Array<Int>): Int {
    val requiredIngredients = arrayOf(200, 50, 15)

    if (ingredients[0] < requiredIngredients[0]
        || ingredients[1] < requiredIngredients[1]
        || ingredients[2] < requiredIngredients[2]
    ) {
        return 0
    }

    return minOf(
        ingredients[0] / requiredIngredients[0],
        ingredients[1] / requiredIngredients[1],
        ingredients[2] / requiredIngredients[2]
    )
}

private fun runCoffeeMachine(needed: Int, max: Int) {
    val diff = max - needed
    when {
        diff < 0 -> println("No, I can make only $max cups of coffee")
        diff > 0 -> println("Yes, I can make that amount of coffee (and even $diff more than that)")
        else -> println("Yes, I can make that amount of coffee")
    }
}
