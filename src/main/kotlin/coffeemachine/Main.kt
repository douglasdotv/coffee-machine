package coffeemachine

import kotlin.system.exitProcess

// arrayOf(water, milk, coffeeBeans)
var availableResources = arrayOf(400, 540, 120)
var money = 550
var disposableCups = 9

// arrayOf(neededWater, neededMilk, neededCoffeeBeans, cost)
val coffeeTypeToCoffeeTypeInfo = mapOf(
    1 to arrayOf(250, 0, 16, 4),
    2 to arrayOf(350, 75, 20, 7),
    3 to arrayOf(200, 100, 12, 6)
)

fun main() {
    runMachine()
}

private fun runMachine() {
    do {
        when (getAction()) {
            "buy" -> executeAction(::buyCoffee)
            "fill" -> executeAction(::fillMachine)
            "take" -> executeAction(::takeMoney)
            "remaining" -> executeAction { showResources(availableResources, money, disposableCups) }
            "exit" -> exitProcess(0)
        }
    } while (true)
}

private fun getAction(): String {
    println("Write action (buy, fill, take, remaining, exit): ")
    return readln()
}

fun executeAction(action: () -> Unit) {
    println()
    action()
    println()
}

private fun buyCoffee() {
    val coffeeType = getCoffeeType()
    if (coffeeType <= 0) {
        return
    }
    val coffeeTypeInfo = coffeeTypeToCoffeeTypeInfo.getValue(coffeeType)

    var isPossibleToMakeCoffee = true
    if (disposableCups == 0) {
        isPossibleToMakeCoffee = false
        println("Sorry, not enough cups!")
    }
    for (i in 0..2) {
        if (availableResources[i] < coffeeTypeInfo[i]) {
            isPossibleToMakeCoffee = false
            when (i) {
                0 -> println("Sorry, not enough water!")
                1 -> println("Sorry, not enough milk!")
                2 -> println("Sorry, not enough coffee beans!")
            }
            break
        }
    }

    if (isPossibleToMakeCoffee) {
        println("I have enough resources, making you a coffee!")
        for (i in 0..2) {
            availableResources[i] -= coffeeTypeInfo[i]
        }
        money += coffeeTypeInfo[3]
        disposableCups--
    }
}

private fun getCoffeeType(): Int {
    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino")
    return when (val input = readln()) {
        "1" -> input.toInt()
        "2" -> input.toInt()
        "3" -> input.toInt()
        "back" -> -1
        else -> {
            println("Invalid input!")
            getCoffeeType()
        }
    }
}

private fun fillMachine() {
    println("Write how many ml of water you want to add: ")
    availableResources[0] += readln().toInt()
    println("Write how many ml of milk you want to add: ")
    availableResources[1] += readln().toInt()
    println("Write how many grams of coffee beans you want to add: ")
    availableResources[2] += readln().toInt()
    println("Write how many disposable cups you want to add: ")
    disposableCups += readln().toInt()
}

private fun takeMoney() {
    println("I gave you $$money")
    money = 0
}

private fun showResources(resources: Array<Int>, money: Int, disposableCups: Int) {
    println(
        """
        The coffee machine has:
        ${resources[0]} ml of water
        ${resources[1]} ml of milk
        ${resources[2]} g of coffee beans
        $disposableCups disposable cups
        $$money of money
    """.trimIndent()
    )
}
