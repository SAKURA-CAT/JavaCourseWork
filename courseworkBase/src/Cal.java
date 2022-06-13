/**
 * @ClassName: Calendar
 * @Author: cuny
 * @Date: 2022/4/9 14:24
 * @Description: 编写基于命令行形式的日历绘制程序，要求程序能根据用户输入完成某年日历的绘 制或特定日期的星期输出。
 **/

import java.text.ParseException;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Cal {
    public static void main(String[] args) throws ParseException {
        if (args.length==0){  // 参数为空的时候需要抛出异常
            throw new NullPointerException("参数为空，请输入参数.");
        }else if (args.length==2){  // 显示整个月份的日历
            // 年 月
            int year = Integer.parseInt(args[0]);
            int month = Integer.parseInt(args[1]);
            // System.out.println("你输入的日期为" + args[0] + "年" + args[1] + "月");
            CalendarForMonth cfm = new CalendarForMonth(year, month);
            cfm.printCal();
        }else if (args.length==3){  // 显示特定时间的星期
            // 年 月 日
            int year = Integer.parseInt(args[0]);
            int month = Integer.parseInt(args[1]);
            int day = Integer.parseInt(args[2]);
            CalendarForMonth cfm = new CalendarForMonth(year, month);
            cfm.whatWeekS(day);
        }else{
            throw new TypeNotPresentException("输入参数格式有误", null);
        }
    }
    //public static void placeholder(){}
}

class CalendarForMonth{
    protected int year, month, montMaxDays;
    protected String year_s, month_s;
    protected Calendar c;
    protected Date d;
    private static final String[] weekName = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    // 构造方法
    public CalendarForMonth(int year, int month) throws ParseException {
        this.year = year;
        this.month = month;
        this.year_s = Integer.toString(year);
        if (Integer.toString(month).length() == 1){
            this.month_s = "0" + month;
        }
        this.c = Calendar.getInstance();
        this.setDate(1);
        this.montMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    // 判断month月1号是星期几
    public int whatWeek(int date) throws ParseException {
        setDate(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }
    public void whatWeekS(int date) throws ParseException {
        System.out.println(weekName[whatWeek(date)-1]);
    }
    public void setDate(int date) throws ParseException {
        String date_s;
        if (date < 10){
            date_s = "0" + date;
        }else{
            date_s = Integer.toString(date);
        }
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        this.d = dateFormat1.parse(this.year_s + "-" + this.month_s + "-" + date_s);
        this.c.setTime(d);
    }
    public String addBlank(int num){
        String blank = "";
        for (int i = 0; i < num - 1; i ++){
            blank = blank.concat("   ");
        }
        return blank;
    }
    // 打印日期主函数
    public void printCal() throws ParseException {
        int i, nowWeek;
        String row, day;
        // 先打印抬头
        System.out.println("Su Mo Tu We Th Fr Sa");
        // 接下来打印这个月的日期(一行一行打印)
        for (i = 1; i <= this.montMaxDays;){
            nowWeek = whatWeek(i);
            // 首行开头
            row = addBlank(nowWeek);
            while(true){
                if (i < 10){
                    day = "0" + i;
                }else{
                    day = Integer.toString(i);
                }
                row = row.concat(day + " ");
                i++;
                if (i > this.montMaxDays || nowWeek == 7){
                    break;
                }
                nowWeek = whatWeek(i);
            }
            System.out.println(row);
        }
    }
}