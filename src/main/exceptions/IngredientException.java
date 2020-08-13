package exceptions;

public class IngredientException extends java.lang.Exception {
    public String getMessage() {
        return "Something went wrong when adding the ingredient";
    }

}
