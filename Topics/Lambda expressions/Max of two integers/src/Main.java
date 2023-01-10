import java.util.function.IntBinaryOperator;

class Operator {

    public static IntBinaryOperator binaryOperator = // Write your code here
            (x, y) -> {
                return Math.max(x, y);
            };  //could be replaced by Math::max
}