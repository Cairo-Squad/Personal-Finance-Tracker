package test.util

import model.Category
import util.CategoryManager.checkCategorySelection
import util.CategoryManager.checkNewCategoryName

fun main(){
    val categories = mutableListOf(
        Category(1, "food"),
        Category(2, "salary"),
        Category(3, "other")
    )

    // Test case 1: Valid category ID
    val idValid:Int =1;
    check(
        name = "given valid id when checked, then should return true (selected valid category)",
        result = checkCategorySelection(categories, idValid.toString()),
        correctResult = true
    )

    // Test case 2: ID out of range
    val idInvalid1:Int =5;
    check(
        name = "given inValid ID  which is out of range, when checked, then should return false (category ID out of range)",
        result = checkCategorySelection(categories, idInvalid1.toString()),
        correctResult = false
    )
    // Test case 3: negative number input
    val idInvalid4:Int =-1;
    check(
        name = "given inValid negative number input , when checked, then should return false (negative number input)",
        result = checkCategorySelection(categories, idInvalid4.toString()),
        correctResult = false
    )

    // Test case 3: non-numeric input
    val idInvalid2:String ="a";
    check(
        name = "given non numeric input , when checked, then should return false (invalid  non numeric input)",
        result = checkCategorySelection(categories, idInvalid2),
        correctResult = false
    )

    // Test case 4: Empty input
    val idInvalid3:String ="";
    check(
        name = "given empty input '', when checked, then should return false (empty input)",
        result = checkCategorySelection(categories, idInvalid3),
        correctResult = false
    )

// i tell abdo he is ask user when choose other
//    // Test case 5: 'other' with exact match
//    val invalid2:Int =3;
//    check(
//        name = "given input id of other he new category , when checked, then should checking of name new_category",
//        result =checkCategorySelection(categories, invalid2.toString()),
//        correctResult = true
//    )

    // Test case 6: 'other' with partial match
    val isValid2:String ="fo";
    check(
        name = "given input 'other' and new category 'fo ', when checked, then should show what match with it  and he decision",
        result = checkNewCategoryName(categories, isValid2),
        correctResult = true
    )

    // Test case 7: 'other' with no match
    val isValid3:String ="play";
    check(
        name = "given input 'other' and new category  not match with any list of category , when checked, then should add new category,New category 'play' added",
        result = checkNewCategoryName(categories, isValid3) ,
        correctResult = true
    )

    // Test case 8: 'other' with empty new category
    val isValid4:String ="";

    check(
        name = "given input 'other' and empty new category, when checked, then should return false  new category name is empty",
        result = checkNewCategoryName(categories, isValid4),
        correctResult = false
    )

}


fun check(name: String, result:Boolean , correctResult:Boolean) {
    if (result == correctResult) {
        println("Success - $name")
    } else {
        println("Failed - $name")
    }
}
