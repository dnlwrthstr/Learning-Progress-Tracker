import java.util.function.LongUnaryOperator;

class Operator {

    public static LongUnaryOperator unaryOperator = // Write your code here
            x -> x % 2 == 0 ? x + 2 : x + 1;
            //x -> {
            //    return  (x) % 2 == 0 ? x + 2 : x + 1;
            //};
}