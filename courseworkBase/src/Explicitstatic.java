/**
 * @ClassName: Explicitstatic
 * @Author: cuny
 * @Date: 2022/4/9 17:24
 * @Description: 第四章作业
 **/


public class Explicitstatic {
    static Cups cups1 = new Cups();
    static Cups cups2 = new Cups();
    public static void main(String[] args){
        System.out.println("Inside main()");
        Cups.cup1.f(99);// (Explictstaticjpg
    }
}

class Cups {
    static Cup cup1;
    static Cup cup2;
    static {
        cup1 = new Cup(1);
        cup2 = new Cup(2);
    }
    public Cups(){
        System.out.println( "Cups() ");
    }
}
class Cup {
    Cup(int marker) {
        System.out.println("cup(" + marker + ")");
    }

    void f(int marker){
        System.out.println("f(" + marker +")");
    }
}
