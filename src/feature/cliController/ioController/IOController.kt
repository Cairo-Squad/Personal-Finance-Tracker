package feature.cliController.ioController

interface IOController {
    fun read(): String?
    fun write(message: String)
}