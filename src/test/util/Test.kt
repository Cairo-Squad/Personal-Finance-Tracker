package test.util

fun test(name: String, result: Any, correctResult: Any) {
    if(result == correctResult){
        println("Success: $name")
    } else{
        println("Failed: $name")
    }
}