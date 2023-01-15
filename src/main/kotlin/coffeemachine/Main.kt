package coffeemachine

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
    displayInfo(availableResources, money, disposableCups)

    when (getAction()) {
        "buy" -> {
            buyCoffee()
        }

        "fill" -> {
            fillMachine()
        }

        "take" -> {
            takeMoney()
        }
    }

    displayInfo(availableResources, money, disposableCups)
}

private fun displayInfo(resources: Array<Int>, money: Int, disposableCups: Int) {
    println(
        """
        The coffee machine has:
        ${resources[0]} ml of water
        ${resources[1]} ml of milk
        ${resources[2]} g of coffee beans
        $disposableCups disposable cups
        ${'$'}$money of money
    """.trimIndent() + "\n"
    )
}

private fun getAction(): String {
    println("Write action (buy, fill, take): ")
    return readln()
}

private fun buyCoffee() {
    val coffeeType = getCoffeeType()
    val coffeeTypeInfo = coffeeTypeToCoffeeTypeInfo.getValue(coffeeType)

    if (disposableCups == 0) {
        return
    }

    for (i in 0..2) {
        if (availableResources[i] < coffeeTypeInfo[i]) {
            return
        }
    }

    for (i in 0..2) {
        availableResources[i] -= coffeeTypeInfo[i]
    }

    disposableCups--

    money += coffeeTypeInfo[3]

    print("\n")
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
    print("\n")
}

private fun takeMoney() {
    println("I gave you ${'$'}$money")
    money = 0
    print("\n")
}

private fun getCoffeeType(): Int {
    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino")
    return readln().toInt()
}
