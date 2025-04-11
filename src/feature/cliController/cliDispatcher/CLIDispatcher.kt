package feature.cliController.cliDispatcher

interface CLIDispatcher {
    fun dispatch(userInput: Int)
    fun validateOption(option: Int): Boolean
}