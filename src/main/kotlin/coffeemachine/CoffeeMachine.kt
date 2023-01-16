package coffeemachine

import kotlin.system.exitProcess

class CoffeeMachine(
    private var availableResources: Array<Int> = arrayOf(400, 540, 120), // (water, milk, coffeeBeans)
    private var money: Int = 550,
    private var disposableCups: Int = 9,
    private val coffeeTypeToCoffeeTypeNeededResources: Map<Int, Array<Int>> = mapOf(
        1 to arrayOf(250, 0, 16, 4),
        2 to arrayOf(350, 75, 20, 7),
        3 to arrayOf(200, 100, 12, 6)
    ),
    private var currentState: String = "Choosing an action"
) {
    init {
        println("Write action (buy, fill, take, remaining, exit): ")
    }

    fun processInput(input: String) {
        when (currentState) {
            "Choosing an action" -> chooseAction(input)
            "Choosing a coffee type" -> chooseCoffee(input)
        }
        if (currentState == "Choosing an action") {
            println("\nWrite action (buy, fill, take, remaining, exit): ")
        } else if (currentState == "Choosing a coffee type") {
            println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
        }
    }

    private fun buyCoffee(coffeeType: Int) {
        val coffeeTypeNeededResources = getCoffeeTypeNeededResources(coffeeType)
        val isPossibleToMakeCoffee = checkCoffeeSituation(coffeeTypeNeededResources)

        if (isPossibleToMakeCoffee) {
            println("I have enough resources, making you a coffee!")
            for (i in 0..2) {
                availableResources[i] -= coffeeTypeNeededResources[i]
            }
            money += coffeeTypeNeededResources[3]
            disposableCups--
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
        println("I gave you ${'$'}$money")
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
        ${'$'}$money of money
    """.trimIndent()
        )
    }

    private fun getCoffeeTypeNeededResources(coffeeType: Int): Array<Int> {
        return coffeeTypeToCoffeeTypeNeededResources.getValue(coffeeType)
    }

    // checks if it's possible to make coffee with the available resources
    private fun checkCoffeeSituation(coffeeTypeInfo: Array<Int>): Boolean {
        var isPossibleToMakeCoffee = true

        if (disposableCups == 0) {
            println("Sorry, not enough cups!")
            isPossibleToMakeCoffee = false
        }

        for (i in 0..2) {
            if (availableResources[i] < coffeeTypeInfo[i]) {
                when (i) {
                    0 -> println("Sorry, not enough water!")
                    1 -> println("Sorry, not enough milk!")
                    2 -> println("Sorry, not enough coffee beans!")
                }
                isPossibleToMakeCoffee = false
                break
            }
        }

        return isPossibleToMakeCoffee
    }

    private fun chooseAction(input: String) {
        println()
        when (input) {
            "buy" -> currentState = "Choosing a coffee type"
            "fill" -> fillMachine()
            "take" -> takeMoney()
            "remaining" -> showResources(availableResources, money, disposableCups)
            "exit" -> exitProcess(0)
            else -> println("Invalid input, please enter a valid action")
        }
    }

    private fun chooseCoffee(input: String) {
        when (input) {
            "1" -> buyCoffee(input.toInt())
            "2" -> buyCoffee(input.toInt())
            "3" -> buyCoffee(input.toInt())
            "back" -> currentState = "Choosing an action"
            else -> println("Invalid input, please enter a valid coffee type")
        }
        currentState = "Choosing an action"
    }
}