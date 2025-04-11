package test.util

import model.Category
import util.CategoryManager.addCategoryDecision
import util.CategoryManager.validateCategorySelection
import util.CategoryManager.validateNewCategoryName

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

// i tell abdo he is ask user when choose other
//    // Test case 5: 'other' with exact match
//    val invalid2:Int =3;
//    check(
//        name = "given input id of other he new category , when checked, then should checking of name new_category",
//        result =checkCategorySelection(categories, invalid2.toString()),
//        correctResult = true
//    )

    // Test case 6: 'other' with partial match
    val isValid2:String ="Sa";
    check(
        name = "given input 'other' and new category $isValid2, when checked, then should show what match with it  and he decision",
        result = addCategoryDecision(categories, isValid2,2),
        correctResult = true
    )

    // Test case 7: 'other' with no match
    val isValid3:String ="play";
    check(
        name = "given input 'other' and new category $isValid3 not match with any list of category , when checked, then should add new category",
        result = validateNewCategoryName(categories,isValid3) ,
        correctResult = 3
    )
    // Test case 7: 'other' match(copy) with one list of category
    val isValid4:String ="Business";
    check(
        name = "given input 'other' and new category  match(copy) with one list of category , when checked, then $isValid4 already exit",
        result = validateNewCategoryName(categories,isValid4) ,
        correctResult = 1
    )

    // Test case 8: 'other' with empty new category
    val isValid5:String ="";

    check(
        name = "given input 'other' and empty new category, when checked, then should return false  new category name is empty",
        result = addCategoryDecision(categories, isValid5,3),
        correctResult =false
    )

}


fun check(name: String, result:Any? , correctResult:Any?) {
    if (result == correctResult) {
        println("Success - $name")
    } else {
        println("Failed - $name")
    }
}
