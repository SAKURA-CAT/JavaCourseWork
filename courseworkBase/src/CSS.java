import java.util.Arrays;
import java.util.Objects;

/**
 * @ClassName: CSS
 * @Author: cuny
 * @Date: 2022/4/2 18:07
 * @Description: 创建名为Book、Course和Student的类来执行简单的课程选择系统（CSS）。
 * 学生可以选择一门或多门课程，并且其中一门课程还有一些本书的成员。
 **/


public class CSS {
    public static void main(String[] args){
        String id;
        String[] coursesName;
        id = args[0];
        coursesName = Arrays.copyOfRange(args, 1, args.length);
        StudentObj s = new StudentObj(id, coursesName);
        System.out.println(s.id);
        String courses="courses:";
        String books="books:";
        for (Course c: s.courses){
            courses = courses.concat(c.CourseName);
            courses = courses.concat(" ");
            for (Book b:c.books){
                books = books.concat(b.bookName);
                books = books.concat(" ");
            }
        }
        System.out.println(courses);
        System.out.println(books);
    }
}

class StudentObj{
    String id;
    Course[] courses;
    public StudentObj(String id, String[] courses){
        this.id = id;
        int index = 0;
        //int course_num = courses.length;
        this.courses = new Course[courses.length];
        for (String course : courses){
            Course c = new Course(course);
            this.courses[index] = c;
            index ++;
        }
    }
}

class Course{
    String CourseName;
    Book[] books;
    public Course(String CourseName){
        this.CourseName = CourseName;
        if (Objects.equals(CourseName, "Java")){
            this.books = new Book[2];
            this.books[0] = new Book("Java 8", 19.9);
            this.books[1] = new Book("Thinking in Java", 29.9);
        }else if (Objects.equals(CourseName, "Python")){
            this.books = new Book[1];
            this.books[0] = new Book("Python Programming", 49.9);
        }
    }
}

class Book{
    String bookName;
    double price;
    public Book(String bookName, double price){
        this.bookName = bookName;
        this.price = price;
    }
}