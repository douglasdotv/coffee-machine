package coffeemachine

fun main() {
    val coffeeMachine = CoffeeMachine()
    while (true) {
        val input = readln()
        coffeeMachine.processInput(input)
    }
}