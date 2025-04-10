package feature.cliController.ioController

class IOControllerImpl : IOController {

    override fun read(): String? {
        return readlnOrNull()
    }

    override fun write(message: String) {
        print(message)
    }

    override fun writeWithNewLine(message: String) {
        println(message)
    }
}