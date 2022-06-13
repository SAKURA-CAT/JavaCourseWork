/**
 * @ClassName: PIMContact
 * @Author: cuny
 * @Date: 2022/6/8 11:06
 * @Description: Contact items must be PIMEntity defined in a class named PIMContact.
 * Each contact item must have a priority (a string), and strings for each of the following: first name, last name, email address.
 * There is one additional requirement on the implementation of the 4 item classes listed above,
 *      the 2 classes that involve a date must share an interface that you define.
 * You must formally create this interface and have both PIMAppointment and PIMTodo implement this interface.
 **/
package system;

import java.text.SimpleDateFormat;

public class PIMContact extends PIMEntity {
    public static String kind = "CONTACT";
    public String Priority; // each contact has a priority
    public String firstName;
    public String familyName;
    public String emailAddress;

    public PIMContact() {
        Priority = "normal";
    }

    @Override
    public String getPriority() {
        // TODO Auto-generated method stub
        return super.getPriority();
    }

    @Override
    public void setPriority(String p) {
        // TODO Auto-generated method stub
        super.setPriority(p);
    }

    @Override
    public void fromString(String[] bufferData) {
        this.Priority = bufferData[1];
        this.firstName = bufferData[2];
        this.familyName = bufferData[3];
        this.emailAddress = bufferData[4];
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "[" + kind + " " + Priority
                + " " + firstName + " " + familyName + " " + emailAddress + "]";
    }

    @Override
    public String toBuffer(){
        return kind + "&&&&" + Priority + "&&&&" + firstName + "&&&&" + familyName + "&&&&" + emailAddress;
    }

}


