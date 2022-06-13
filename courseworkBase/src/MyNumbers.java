import java.util.ArrayList;
import java.util.Scanner;
/**
 * @ClassName: MyNumbers
 * @Author: cuny
 * @Date: 2022/4/30 15:10
 * @Description:
 * (1)设计并实现自然数 (MyNaturalNumbers)及整数(MyInteger）类。
 * (2)自然数及整数类至少能表示及处理20位自然数或整数数据。
 * (3)自然数及整数类能表示及处理任意位数的自然数或整数数据。（选作。第五 章内容）
 * (4)自然数及整数类的设计应体现体系结构（即应设计实现完整的继承关系）。
 * (5)自然数及整数类应提供数据赋值、数据输出、加法计算、减法计算功能。
 * (6)自然数及整数类应提供乘法运算、除法运算（选作）。
 * (7)自然数及整数类的赋值应能正确接收带千分符号的格式及不带千分符号的格式。
 * (8)自然数及整数类的赋值，对于无效数据应向用户提示信息（选作，第五章及 第六章内容)。
 * (9)自然数及整数类应通过重写equals方法实现对两个数据的大小比较。
 * (10)自然数及整数类应通过重写toString方法实现对数据的格式化。
 * (11)自然数类应定义toMyInteger方法，该方法实现创建一个同值的整数类实例。
 * (12)整数类应可通过自然数对象实例创建等值的整数对象实例。
 * (13)自然数及整数类中应定义成员变量length，表示当前数据的长度。
 * (14)自然数及整数类中应定义静态成员变量MAX_VALUE、 MIN_VALUE，表示数据的最大及最小取值。（选作）
 * *********************** 整体设计思路如下 *********************** *
 * 自然数和整数都有赋值、数据输出、加法、减法、除法等一系列共同的操作，所以考虑使用一个基类来继承这些属性。
 * 数据的存储采用数组列表来记录每一位的操作，因此从理论上来讲可以表示任何长度的数字
 * 类内属性除了用于记录"数据绝对值"的array以外，还有一个flag，用于标注自然数和整数的符号位，自然数恒为True(+)，整数可为True和False
 * 这时候我们出现了一个问题，对于0而言，会出现-0和+0的情况，这个后面再说。
 **/


public class MyNumbers {
    public static void main(String[] args){
        Numbers n1 = new Numbers();
        System.out.println("n1:" + n1.getNumbers());
        Numbers n2 = new Numbers();
        System.out.println("n2:" + n2.getNumbers());
        System.out.println("equal? " + n1.equals(n2));
        Numbers n3 = n1.addNumbers(n2);
        System.out.println("n3:" + n3.getNumbers());
        Numbers n4 = n1.subNumbers(n2);
        System.out.println("n4:" + n4.getNumbers());
    }
}

class Numbers implements Cloneable{
    /*
    Numbers基本类，用于规定一些基本操作，由于MyNaturalNumbers和MyInteger两者的操作大致相同，所以也不用搞抽象类。
     */
    protected int flag; // 正负标识位
    protected int length; // 数组长度
    protected final ArrayList<Integer> numbers = new ArrayList<>();
    protected double MIN_VALUE, MAX_VALUE;


    /**
     * 内部构造方法, 这是一个内部使用的构造方法，在对象外无法使用，主要用于根据运算结果得到一个新的Number对象
     * @param numbers ArrayList格式，内部数据为int，倒序排列。
     * @param flag 这个数据是正是负
     */
    Numbers(ArrayList<Integer> numbers, int flag){
        this.numbers.addAll(numbers);
        // 赋值完毕,再进行一次遍历，如果数组最高位为0且size>1，则删除最高位
        while (this.numbers.get(this.numbers.size() - 1) == 0 && this.numbers.size() > 1){
            this.numbers.remove(this.numbers.size() - 1);
        }
        this.length = this.numbers.size();
        this.flag = flag;
    }

    /**
     * 无参构造方法，会提示用户输入数据字符串
     */
    public Numbers(){
        System.out.println("请输入数值:");
        Scanner sc=new Scanner(System.in);
        String str;
        str = sc.next();
        // str为用户输入的数据，接下来进行数据生成, todo 后面在这里需要重构加上格式判断
        char first_key = str.charAt(0);
        if (first_key == '+'){
            this.flag = 1;
            setNumbers(str.substring(1));
        }else if (first_key == '-'){
            this.flag = -1;
            setNumbers(str.substring(1));
        }else{
            this.flag = 1; //默认为正数
            setNumbers(str);
        }
    }

    public String toString(){
        return getNumbers();
    }

    /**
     * 有参构造方法，输入一个字符串构造整个对象
     * @param s 输入的字符串，如"1234"
     */
    public Numbers(String s) {
        // str为用户输入的数据，接下来进行数据生成, todo 后面在这里需要重构加上格式判断
        char first_key = s.charAt(0);
        if (first_key == '+'){
            this.flag = 1;
            setNumbers(s.substring(1));
        }else if (first_key == '-'){
            this.flag = -1;
            setNumbers(s.substring(1));
        }else{
            this.flag = 1; //默认为正数
            setNumbers(s);
        }
    }

    public void setNumbers(String numbers){
        for (int i = numbers.length() - 1; i >= 0; i--){
            // 开始给数组赋值，高位赋值在数组高位，即倒叙赋值
            this.numbers.add(Character.getNumericValue(numbers.charAt(i)));
        }
        // 赋值完毕,再进行一次遍历，如果数组最高位为0且size>1，则删除最高位
        while (this.numbers.get(this.numbers.size() - 1) == 0 && this.numbers.size() > 1){
            this.numbers.remove(this.numbers.size() - 1);
        }
        this.length = this.numbers.size();
        // 接着为数字生成MAX_VALUE和MIN_VALUE，实际上是这个n位数字能表示的最大值 10^n -1和最小值10^(n-1)
    }

    public String getNumbers(){
        String number = this.numbers.get(this.length - 1).toString();
        for (int i = this.length - 2; i >= 0; i--){
            number = number.concat(this.numbers.get(i).toString());
        }
        if (this.flag == 1){
            return number;
        }else {
            return '-' + number;
        }
    }

    /**
     * 进行加运算，等价于a+b
     * 在数学上的加运算就是对每一位进行相加，
     * @param numbers 加数
     * @return 一个新的number类型对象，为相加后的结果
     */
    public Numbers addNumbers(Numbers numbers){
        // 首先对每一位进行相加
        ArrayList<Integer> result_number = new ArrayList<>();
        int a, b, c, num;
        boolean if_same = this.flag == numbers.flag;
        int equal_abs = equalsAbs(numbers);
        for (int i = 0; i < Math.max(numbers.length, this.length); i ++){
            if (i >= this.length){
                a = 0;
            }else {
                a = this.numbers.get(i);
            }
            if (i >= numbers.length){
                b = 0;
            }else {
                b = numbers.numbers.get(i);
            }
            result_number.add(a * this.flag + b * numbers.flag);
        }
        // 开始处理result_number的每一个元素的值
        c = 0;
        for (int i = 0; i < result_number.size(); i ++) {
            num = result_number.get(i) + c;
            if (num >= 10){  // 大于10则-10
                num -= 10;
                result_number.set(i, num);
                c = 1;
            }else if(num < 0 && num > -10){  // 小于0看情况，分为+10 和 取绝对值
                if (if_same){  // 同号则取绝对值
                    result_number.set(i, Math.abs(num));
                    c = 0;
                }else{
                    // 异号则判断两者的绝对值谁大谁小,如果被加数绝对值大，则+10，c = -1；反之则取绝对值
                    if (equal_abs == 1){
                        result_number.set(i, num + 10);
                        c = -1;
                    }else{  // equal_abs 在这里不可能等于0
                        result_number.set(i, Math.abs(num));
                        c = 0;
                    }
                }
            }else if(num <= -10){  // 必然是同号
                result_number.set(i, Math.abs(num + 10));
                c = -1;
            }else{
                result_number.set(i, num);
                c = 0;
            }
        }
        // 最终加上c
        result_number.add(c);
        // 然后确定符号，如果同号就直接选择this.flag，如果不同号则根据equal_abs来判断是否需要变号
        if (if_same){
            return new Numbers(result_number, this.flag);
        }else{
            if (equal_abs >= 0){
                return new Numbers(result_number, this.flag);
            }else{
                return new Numbers(result_number, -this.flag);
            }
        }
    }

    @Override
    public Object clone() {
        Numbers tmp = null;
        try{
            tmp = (Numbers)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public Numbers subNumbers(Numbers numbers){
        Numbers re_numbers = (Numbers) numbers.clone();
        re_numbers.flag = - re_numbers.flag;
        return addNumbers(re_numbers);
    }

    /**
     * 判断两个Numbers 是否相同
     * @param numbers 另一个numbers对象
     * @return 相同return 0， this > numbers return 1, 否则return -1
     */
    public int equals(Numbers numbers){
        // 首先判断两者的正负情况
        if (this.flag * numbers.flag == -1){
            return this.flag;
        }
        // 此时两者的符号相同
        int flag;
        flag = this.flag;
        // 接着判断两者位数是否相同
        if (this.length > numbers.length){
            return flag;
        }else if(this.length < numbers.length){
            return flag * -1;
        }
        // 此时这时候符号和位数相同，开始逐位比较
        for (int i = 0; i < this.length; i++){
            if (this.numbers.get(i) > numbers.numbers.get(i)){
                return flag;
            }else if(this.numbers.get(i) < numbers.numbers.get(i)){
                return flag * -1;
            }
        }
        return 0;
    }

    private int equalsAbs(Numbers numbers){
        // 接着判断两者位数是否相同
        if (this.length > numbers.length){
            return 1;
        }else if(this.length < numbers.length){
            return -1;
        }
        // 位数相同，开始逐位比较
        for (int i = 0; i < this.length; i++){
            if (this.numbers.get(i) > numbers.numbers.get(i)){
                return 1;
            }else if(this.numbers.get(i) < numbers.numbers.get(i)){
                return -1;
            }
        }
        return 0;
    }

}


class MyNaturalNumbers extends Numbers{

    public MyNaturalNumbers() {
        super();
        this.flag = 1;
    }

    public MyInteger toMyInteger(){
        return new MyInteger(this.numbers, this.flag);
    }

}

class MyInteger extends Numbers{

    public MyInteger(ArrayList<Integer> numbers, int flag) {
        super(numbers, flag);
    }
}