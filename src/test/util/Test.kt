package test.util

fun test(name: String, result: Boolean, correctResult: Boolean) {
    if(result == correctResult){
        println("Success: $name")
    } else{
        println("Failed: $name")
    }
}