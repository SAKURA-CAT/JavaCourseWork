/**
 * @ClassName: SumOfArgs
 * @Author: cuny
 * @Date: 2022/4/9 16:29
 * @Description: 编写基于命令行形式的 SumOfArgs 程序，将命令行输入的所有整数相加，输出 和。注：输入中非整数的部分直接跳过，不进行求和，不论输入是什么，都不输出错误提示。
 **/


public class SumOfArgs {
    public static void main(String[] args){
        int result = 0;
        for (String arg : args) {
            if (isNumeric(arg)) {
                if (!isDecimal(arg)) {
                    result += Integer.parseInt(arg);
                }
            }
        }
        System.out.println(result);
    }
    public static boolean isNumeric(String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isDecimal(String str){
        // boolean a = str.replace("-", "").matches("\\d+.\\d+");
        return !str.replace("-", "").matches("\\d+.\\d+");
    }
}
