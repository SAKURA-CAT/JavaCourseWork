/**
 * @ClassName: PIMManager
 * @Author: cuny
 * @Date: 2022/6/8 11:09
 * @Description: 信息管理系统的逻辑功能测试类
 **/

import java.io.*;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import system.*;

public class PIMManager {
    //save the 100 operation
    private final static String bufferPath = pathJoin(new File("").getAbsolutePath(), "buffer", "PIMBuffer");

    private static String pathJoin(String... args){
        StringBuilder path = new StringBuilder(args[0]);
        for (int i = 1; i < args.length; i++){
            path.append(File.separator).append(args[i]);
        }
        return path.toString();
    }
    public static Queue<String> operate = new LinkedList<>();

    private static Queue<String> buffer = new LinkedList<>();

    private static final String introduce = """
                                            Welcome to Cuny's PIM. \040
                                            This is a personal information management system written by Cuny.""";

    private static final String modeSwitch = """
                                             ---Enter a command (supported commands are List Create Save Load Quit)---\040
                                             1: List PIMCmd items\040
                                             2: Create a new item\040
                                             3: Save your PIMCmd\040
                                             4: Load from local buffer\040
                                             others: Quit""";

    private static final String newIterm = """
                                           ---Enter an item type ( todo, note, contact or appointment)---
                                           1: todo
                                           2: note
                                           3: appointment
                                           4: contract""";

    public PIMManager(){}

    public static void main(String[] args) throws ParseException, IOException {
        Scanner in = new Scanner(System.in); //创建Scanner对象
        System.out.println(introduce);
        String command;
        while (true) {
            int k = 1;
            System.out.println(modeSwitch);
            command = in.nextLine(); //获取输入的一行字符串
            switch (command) {
                case "1":
                    System.out.println("There are [" + operate.size() + "] items.");
                    if (operate.size() > 0)
                        for (String s : operate) {
                            System.out.println("Item " + k + ": " + s);
                            k++;
                        }
                    break;
                case "2":
                    System.out.println(newIterm);
                    String itemType = in.nextLine();
                    String[] data = new String[5];
                    switch (itemType) {
                        case "1" -> {  // PIMTodo
                            PIMTodo todo = new PIMTodo();
                            // input date(String format), then turn it into Date format
                            System.out.println("Enter date for todo item(yyyy/mm/dd): ");
                            data[2] = in.nextLine();
                            // input specific content
                            System.out.println("Enter todo text:");
                            data[3] = in.nextLine();
                            // input priority
                            System.out.println("Enter todo priority:");
                            System.out.println("AVAILABLE: ex-urgent , urgent , normal, unrestricted");
                            data[1] = in.nextLine();
                            todo.fromString(data);
                            String opt = todo.toString();
                            operate.add(opt);
                            buffer.add(todo.toBuffer());
                        }
                        case "2" -> {  // PIMNote
                            PIMNote note = new PIMNote();
                            // input specific content
                            System.out.println("Enter note text:");
                            data[2] = in.nextLine();
                            // input priority
                            System.out.println("Enter note priority:");
                            System.out.println("AVAILABLE: ex-urgent , urgent , normal, unrestricted");
                            data[1] = in.nextLine();
                            note.fromString(data);
                            String opt1 = note.toString();
                            operate.add(opt1);
                            buffer.add(note.toBuffer());
                        }
                        case "3" -> { // PIMAppointment
                            PIMAppointment appointment = new PIMAppointment();
                            // input date(String format), then turn it into Date format
                            System.out.println("Enter date for appointment item(yyyy/mm/dd): ");
                            data[2] = in.nextLine();
                            // input specific content
                            System.out.println("Enter appointment text:");
                            data[3] = in.nextLine();
                            // input priority
                            System.out.println("Enter appointment priority:");
                            System.out.println("AVAILABLE: ex-urgent , urgent , normal, unrestricted");
                            data[1] = in.nextLine();
                            appointment.fromString(data);
                            String opt2 = appointment.toString();
                            operate.add(opt2);
                            buffer.add(appointment.toBuffer());
                        }
                        case "4" -> {  // PIMContact
                            PIMContact contact = new PIMContact();
                            // input priority
                            System.out.println("Enter contact priority:");
                            System.out.println("AVAILABLE: ex-urgent , urgent , normal, unrestricted");
                            data[1] = in.nextLine();
                            System.out.println("Enter contact firstname:");
                            data[2] = in.nextLine();
                            System.out.println("Enter contact familyname:");
                            data[3] = in.nextLine();
                            System.out.println("Enter contact email address:");
                            data[4] = in.nextLine();
                            // initialize new PIMContact
                            contact.fromString(data);
                            String opt3 = contact.toString();
                            operate.add(opt3);
                            buffer.add(contact.toBuffer());
                        }
                        default -> System.out.println("Invalid Command! ");
                    }
                case "3":
                    saveData();
                    break;
                case "4":
                    loadBuffer();
                    break;
                default:
                    System.out.print("bye.");
                    in.close();
                    return;
            }
        }
    }

    public static void loadBuffer() throws IOException, ParseException {
        File PIMBuffer;
        PIMBuffer = new File(bufferPath);
        String s;
        if (!PIMBuffer.exists()){
            // 文件不存在，创建文件
            boolean flag = PIMBuffer.createNewFile();
            if (!flag){  // 创建失败
                throw new IOException();
            }
        }else{
            // 文件存在，从文件中加载
            BufferedReader bufferedReader = new BufferedReader(new FileReader(bufferPath));
            operate = new LinkedList<>();
            buffer = new LinkedList<>();
            while ((s = bufferedReader.readLine()) != null){
                // 逐行加载记录
                //System.out.println(s);
                String[] bufferData = s.split("&&&&");
                PIMEntity obj = null;
                switch (bufferData[0]){
                    case "TODO" -> {
                        obj = new PIMTodo();
                        obj.fromString(bufferData);
                    }
                    case "NOTE" -> {
                        obj = new PIMNote();
                        obj.fromString(bufferData);
                    }
                    case "CONTACT" -> {
                        obj = new PIMContact();
                        obj.fromString(bufferData);
                    }
                    case "APPOINTMENT" -> {
                        obj = new PIMAppointment();
                        obj.fromString(bufferData);
                    }
                }
                assert obj != null;
                operate.add(obj.toString());
                buffer.add(obj.toBuffer());
            }
        }
        System.out.println("Items have been loaded.");
    }
    public static void saveData() throws IOException {
        if (buffer.size() > 0){
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(bufferPath));
            for (String s: buffer)
                bufferedWriter.write(s + "\n");
            bufferedWriter.close();
            System.out.println("Items have been saved.");
        }else{
            System.out.println("Item is empty.");
        }
    }


    public void addOperate(PIMEntity obj){
        operate.add(obj.toString());
    }
}

