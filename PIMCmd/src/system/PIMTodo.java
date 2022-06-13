/**
 * @ClassName: PIMTodo
 * @Author: cuny
 * @Date: 2022/6/8 11:02
 * @Description: 个人信息管理-todo列表
 **/
package system;


import java.text.*;

public class PIMTodo extends PIMEntity implements MyDate{
    public static String kind = "TODO";
    public String Priority; // each TODO has a priority
    public String content; // each TODO has a content
    public java.util.Date deadline = null; // each TODO has a deadline

    // default constructor sets priority to "normal"
    public PIMTodo() {
        Priority = "normal";
    }

    @Override
    public String getPriority() {
        // TODO Auto-generated method stub
        return Priority;
    }

    @Override
    public void setPriority(String p) {
        // TODO Auto-generated method stub
        this.Priority = p;
    }
    /*
      load data from buffer string
     */
    @Override
    public void fromString(String[] bufferData) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        this.Priority = bufferData[1];
        this.deadline = formatter.parse(bufferData[2]);
        this.content = bufferData[3];
    }

    @Override
    public String toString() {
        return "[" + kind +" " + Priority
                + " " + dateToStr(deadline) + " " + content + "]";
    }

    @Override
    public String toBuffer(){
        return kind +"&&&&" + Priority
                + "&&&&" + dateToStr(deadline) + "&&&&" + content;
    }

    @Override
    public String dateToStr(java.util.Date ddl) {
        // TODO Auto-generated method stub
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(ddl);
    }
}

