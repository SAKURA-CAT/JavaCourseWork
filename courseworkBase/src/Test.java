/**
 * @ClassName: AbstractDemo
 * @Author: cuny
 * @Date: 2022/4/30 12:01
 * @Description: 查找语法错误
 **/

abstract class AbstractDemo {

    public static final int i = 0;

    abstract void func1();

    public static void func2() {

        System.out.println("变量i的值为:"+ i);

    }

}

class Sub extends AbstractDemo {

    public void func1(){
        System.out.println("变量i的值为:" + i);
    }

    public static void func2() {

        System.out.println("变量i的值为:" + i);

    }

}

class Test{
    public static void main(String[] args){
        AbstractDemo t = new Sub();
        t.func1();
        AbstractDemo.func2();
    }
}

