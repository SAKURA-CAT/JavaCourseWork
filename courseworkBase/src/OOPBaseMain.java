/**
 * @author: cuny
 * @fileName: OOPBaseMain.java
 * @create_time: 2022/03/11 下午6:55
 * @introduce: 面向对象编程的测试程序
 **/
class Person {
    private String name;
    private int age;

    // Person的三种构造方法
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name) {
        this.name = name;
        this.age = 12;
    }

    public Person() {
    }

    // 获取name
    public String getName() {
        return this.name;
    }

    // 获取age
    public int getAge() {
        return this.age;
    }
    // 设置age
    public void setAge(int age) {
        this.age = age;
    }

    // run方法
    public void run(){
        System.out.println("Person.run");
    }
}
class Student extends Person {
    private int score; // 分数
    public int getScore(){  // 获取分数
        return this.score;
    }
    public void setScore(int score) {  // 设置分数
        this.score = score;
    }

    public Student(String name, int age, int score){
        super(name, age);
        this.score = score;
    }

    @Override // 覆写run方法
    public void run(){
        System.out.println("Student.run");
    }
}

public class OOPBaseMain {
    public static void main(String[] args){
        Student li = new Student("李抗", 21, 100);
        li.setAge(22);
        System.out.println(li.getAge());
        li.run();
    }

}