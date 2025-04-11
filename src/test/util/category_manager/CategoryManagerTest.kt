package test.util.category_manager

import model.Category
import util.category_manager.CategoryManager.validateCategorySelection
import util.category_manager.CategoryManager.validateNewCategoryName
import util.category_manager.CategoryState

fun main(){
    val categories =  mutableListOf(
        Category(1, "Salary"),
        Category(2, "Investments"),
        Category(3, "Business"),
        Category(4, "Other")
    )
    // Test case 1: Valid category ID
    val idValid:Int =1;
    check(
        name = "given valid id when checked, then should return true (selected valid category)",
        result = validateCategorySelection(categories, idValid.toString()),
        correctResult = true
    )

    // Test case 2: ID out of range
    val idInvalid1:Int =5;
    check(
        name = "given inValid ID  which is out of range, when checked, then should return false (category ID out of range)",
        result = validateCategorySelection(categories, idInvalid1.toString()),
        correctResult = false
    )
    // Test case 3: negative number input
    val idInvalid4:Int =-1;
    check(
        name = "given inValid negative number input , when checked, then should return false (negative number input)",
        result = validateCategorySelection(categories, idInvalid4.toString()),
        correctResult = false
    )

    // Test case 3: non-numeric input
    val idInvalid2:String ="a";
    check(
        name = "given non numeric input , when checked, then should return false (invalid  non numeric input)",
        result = validateCategorySelection(categories, idInvalid2),
        correctResult = false
    )

    // Test case 4: Empty input
    val idInvalid3:String ="";
    check(
        name = "given empty input '', when checked, then should return false (empty input)",
        result = validateCategorySelection(categories, idInvalid3),
        correctResult = false
    )

// i ask abdo he is ask user when choose other
//    // Test case 5: 'other' with exact match
//    val invalid2:Int =3;
//    check(
//        name = "given input id of other he new category , when checked, then should checking of name new_category",
//        result =checkCategorySelection(categories, invalid2.toString()),
//        correctResult = true
//    )




    // Test case 8: 'other' match(copy) with one list of category
    val isValid4:String ="Business";
    check(
        name = "given input 'other' and new category $isValid4 already exists, when checked, then should return full match exists",
        result = validateNewCategoryName(categories, isValid4),
        correctResult = CategoryState.FullMatchExists(categories[2])
    )

    // Test case 9: 'other' with empty new category
    val isValid5:String ="";
    check(
        name = "given input 'other' and empty new category, when checked, then should return false because new category name is empty",
        result = validateNewCategoryName(categories, isValid5),
        correctResult = CategoryState.Empty
    )

}


fun check(name: String, result:Any? , correctResult:Any?) {
    if (result == correctResult) {
        println("Success - $name")
    } else {
        println("Failed - $name")
    }
}
