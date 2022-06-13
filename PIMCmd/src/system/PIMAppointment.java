/**
 * @ClassName: PIMAppointment
 * @Author: cuny
 * @Date: 2022/6/8 11:08
 * @Description: Appointment items must be PIMEntity defined in a class named PIMAppointment.
 * Each appointment must have a priority (a string), a date and a description (a string).
 **/
package system;

import java.text.*;
import java.util.Date;

public class PIMAppointment extends PIMEntity implements MyDate{
    public static String kind = "APPOINTMENT";
    public String Priority; // each appointment has a priority
    public String content; // each appointment has a content
    public java.util.Date deadline = null; // each TODO has a deadline

    public PIMAppointment() {
        Priority = "normal";
    }

    PIMAppointment(String priority){
        this.Priority = priority;
    }

    @Override
    public String getPriority() {
        // TODO Auto-generated method stub
        return super.getPriority();
    }

    @Override
    public void setPriority(String p) {
        // TODO Auto-generated method stub
        this.Priority = p;
    }

    @Override
    public void fromString(String[] bufferData) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        this.Priority = bufferData[1];
        this.deadline = formatter.parse(bufferData[2]);
        this.content = bufferData[3];
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "[" + kind + " " + Priority
                + " " + dateToStr(deadline) + " " + content + "]";
    }

    @Override
    public String dateToStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(date);
    }

    @Override
    public String toBuffer(){
        return kind + "&&&&" + Priority + "&&&&" + dateToStr(deadline) + "&&&&" + content;
    }

}
