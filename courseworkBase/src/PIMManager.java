/**
 * @ClassName: PIManager
 * @Author: cuny
 * @Date: 2022/5/1 15:25
 * @Description: 命令行形式的个人信息管理系统
 **/




import java.util.Scanner;
import java.io.*;

interface Date{
    void setDate(String s);
}

abstract class PIMEntity {
    String Priority; // every kind of item has a priority
    // default constructor sets priority to "normal"
    PIMEntity() {
        Priority = "normal";
    }
    // priority can be established via this constructor.
    PIMEntity(String priority) {
        Priority = priority;
    }
    // accessor method for getting the priority string
    public String getPriority() {
        return Priority;
    }
    // method that changes the priority string
    public void setPriority(String p) {
        Priority = p;
    }

    abstract public void fromString(String s);
    abstract public String toString();
}

class PIMTodo extends PIMEntity implements Date{
    String date,todoItem;
    public PIMTodo(){}
    public void setDate(String s){
        date = s;
    }
    void setTodoItem(String x){
        todoItem = x;
    }

    @Override
    public void fromString(String s) {}
    @Override
    public String toString() {
        return ("Item "+PIMManager.itemno+": TODO "+Priority+" "+date+" "+todoItem+".");
    }
}

class PIMNote extends PIMEntity{
    String noteItem;
    void setNoteItem(String a){
        noteItem = a;
    }

    @Override
    public void fromString(String s) {}
    @Override
    public String toString() {
        return("Item "+PIMManager.itemno+": NOTE "+Priority+" "+noteItem+".");
    }
}

class PIMAppointment extends PIMEntity implements Date{
    String date,description;
    public void setDate(String a){
        date = a;
    }
    void setDescription(String b){
        description = b;
    }

    @Override
    public void fromString(String s) {}

    @Override
    public String toString() {
        return ("Item "+PIMManager.itemno+": APPOINTMENT "+Priority+" "+date+" "+description+".");
    }
}

class PIMContact extends PIMEntity{
    String firstname,lastname,email;
    void setFirstname(String n){
        firstname = n;
    }
    void setLastname(String m){
        lastname = m;
    }
    void setEmail(String a){
        email = a;
    }

    @Override
    public void fromString(String s) {}

    @Override
    public String toString() {
        return("Item "+PIMManager.itemno+": CONTACT "+Priority+" "+firstname+" "+lastname+" "+email+".");
    }
}

public class PIMManager{
    static int itemno = 0;
    static String[] List = new String[100];
    public static void main(String[] args)throws IOException{
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to PIM.");

        FINISH:
        while(true){
            System.out.println("---Enter a command (supported commands are List Create Save Load Quit)---");
            String command = in.next();
            switch (command){
                case "List":
                    System.out.println("There are "+itemno+" items.");
                    for(int i = 1;i <= itemno;i++){
                        if(itemno == 0) break;
                        System.out.println(List[i]);
                    }
                    break;
                case "Create":
                    System.out.println("Enter an item type ( todo, note, contact or appointment )");
                    command = in.next();
                    switch (command){
                        case "todo":
                            PIMTodo todo = new PIMTodo();
                            System.out.println("Enter date for todo item:");
                            BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
                            command = read.readLine();
                            todo.setDate(command);
                            System.out.println("Enter todo text:");
                            command = read.readLine();
                            todo.setTodoItem(command);
                            System.out.println("Enter todo priority:");
                            command = read.readLine();
                            todo.setPriority(command);
                            itemno++;
                            String s = todo.toString();
                            List[itemno] = s;
                            break;
                        case "note":
                            PIMNote note = new PIMNote();
                            System.out.println("Enter note text:");
                            BufferedReader read1 = new BufferedReader(new InputStreamReader(System.in));
                            command = read1.readLine();
                            note.setNoteItem(command);
                            System.out.println("Enter note priority:");
                            command = in.next();
                            note.setPriority(command);
                            itemno++;
                            String s1 = note.toString();
                            List[itemno] = s1;
                            break;
                        case "contact":
                            PIMContact contact = new PIMContact();
                            System.out.println("Enter firstname of this person:");
                            BufferedReader read2 = new BufferedReader(new InputStreamReader(System.in));
                            command = read2.readLine();
                            contact.setFirstname(command);
                            System.out.println("Enter lastname of this person:");
                            command = read2.readLine();
                            contact.setLastname(command);
                            System.out.println("Enter email of this person:");
                            command = read2.readLine();
                            contact.setEmail(command);
                            System.out.println("Enter contact priority:");
                            command = in.next();
                            contact.setPriority(command);
                            itemno++;
                            String s2 = contact.toString();
                            List[itemno] = s2;
                            break;
                        case "appointment":
                            PIMAppointment appointment = new PIMAppointment();
                            System.out.println("Enter date for appointment item:");
                            BufferedReader read3 = new BufferedReader(new InputStreamReader(System.in));
                            command = read3.readLine();
                            appointment.setDate(command);
                            System.out.println("Enter appointment description:");
                            command = read3.readLine();
                            appointment.setDescription(command);
                            System.out.println("Enter appointment priority:");
                            command = in.next();
                            appointment.setPriority(command);
                            itemno++;
                            String s3 = appointment.toString();
                            List[itemno] = s3;
                            break;
                        default:
                            System.out.println("Input a wrong item type.");
                            break;
                    }
                    break;
                case "Save":
                    System.out.println("Items have been saved.");
                    break;
                case "Quit":
                    in.close();
                    break FINISH;
                default:
                    System.out.println("Input a wrong command.");
                    break;
            }
        }
    }
}