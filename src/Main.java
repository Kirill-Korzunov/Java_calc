import java.io.IOException;
import java.util.Scanner;

public class Main {
    //Массив 100 римских чисел
    static String[] nums_rim = new String[]{
            "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII",
            "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV",
            "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV",
            "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI",
            "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII",
            "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII",
            "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI",
            "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI",
            "XCVII", "XCVIII", "XCIX", "C"};
    static String[] nums_arab = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    static String[] nums_rim_input = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    static String[] operators = new String[]{"+","-","*","/"};

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        System.out.println(calc(input));
//// TEST
//        test_check_input_data();
//        test_get_index_Array();
//        test_get_index_Array_without_elem();
//        test_get_result();
    }
    public static String calc(String input) throws IOException {
        //input_arr[0] - 1 число
        //input_arr[1] - знак
        //input_arr[2] - 2 число
        String[] input_arr = input.split(" ");
        if (!check_input_data(input_arr)){
            throw new IOException("Неверные входные данные");
        }
        byte num_1, num_2;
        String result;
        if (isInArray(input_arr[0], nums_arab)){
            num_1 = (byte) (get_index_Array(input_arr[0], nums_arab) + 1);
            num_2 = (byte) (get_index_Array(input_arr[2], nums_arab) + 1);
            result = String.valueOf(get_result(num_1, input_arr[1], num_2));
        }
        else {
            num_1 = (byte) (get_index_Array(input_arr[0], nums_rim) + 1);
            num_2 = (byte) (get_index_Array(input_arr[2], nums_rim) + 1);
            if (get_result(num_1, input_arr[1], num_2) <= 0){
                throw new IOException("В римской системе нет отрицательных чисел и нуля");
            }
            result = nums_rim[get_result(num_1, input_arr[1], num_2) - 1];
        }
        return result;

    }
    public static boolean isInArray(String elem, String[] arr) {
        for (String s : arr) {
            if (elem.equals(s))
                return true;
        }
        return false;
    }

//    Ищет индекс элемента в массиве, если нет -> -1
    public static byte get_index_Array(String elem, String[] arr) {
        for (byte i = 0; i < arr.length; i++){
            if (elem.equals(arr[i]))
                return i;
        }
        return -1;
    }

    public static boolean check_input_data(String[] arr) {
        return arr.length == 3 && isInArray(arr[1], operators) && ((isInArray(arr[0], nums_arab) && isInArray(arr[2], nums_arab)) || (isInArray(arr[0], nums_rim_input) && isInArray(arr[2], nums_rim_input)));
    }

    public static byte get_result(byte n_1, String operator, byte n_2) throws IOException {
        byte result = 0;
        switch (operator){
            case "+" :
                result = (byte) (n_1 + n_2);
                break;
            case "-" :
                result = (byte) (n_1 - n_2);
                break;
            case "*" :
                result = (byte) (n_1 * n_2);
                break;
            case "/" :
                if (n_2 == 0){
                    throw new IOException("Делить на ноль нельзя");
                }
                result = (byte) (n_1 / n_2);
                break;
        }
        return result;
    }
//    public static void test_get_index_Array() throws IOException {
//        if(get_index_Array("1", nums_arab) != 0){
//            throw new IOException("test_get_index_Array error with nums_arab");
//        }
//        if(get_index_Array("C", nums_rim) != 99){
//            throw new IOException("test_get_index_Array error with nums_rim");
//        }
//    }
//
//    public static void test_get_index_Array_without_elem() throws IOException {
//        if(get_index_Array("11", nums_arab) != -1){
//            throw new IOException("test_get_index_Array_without_elem error with nums_arab");
//        }
//        if(get_index_Array("IIII", nums_rim) != -1){
//            throw new IOException("test_get_index_Array_without_elem error with nums_rim");
//        }
//    }
//
//    public static void test_check_input_data() throws IOException {
//        if (check_input_data("1 + 11".split(" "))){
//            throw new IOException("test_check_input_data error with nums_arab > 10");
//        };
//        if (check_input_data("I + XI".split(" "))){
//            throw new IOException("test_check_input_data error with nums_rim > X");
//        };
//        if (! check_input_data("1 + 3".split(" "))){
//            throw new IOException("test_check_input_data error with nums_arab");
//        };
//        if (! check_input_data("I + X".split(" "))){
//            throw new IOException("test_check_input_data error with nums_rim");
//        };
//    }
//
//    public static void test_get_result() throws IOException {
//        if (get_result((byte) 1, "+", (byte) 5) != 6){
//            throw new IOException("test_get_result error +");
//        };
//        if (get_result((byte) 4, "/", (byte) 3) != 1){
//            throw new IOException("test_get_result error with / ");
//        };
//        try {
//           byte r = get_result((byte) 5, "+", (byte)0);
//        } catch (IOException e) {
//            if (!e.equals("Делить на ноль нельзя")){
//                throw new IOException("test_get_result error with / 0 ");
//            }
//        }
//    }
}
