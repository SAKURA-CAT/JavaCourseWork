/**
 * @ClassName: PIMCmdGUI
 * @Author: cuny
 * @Date: 2022/6/8 12:56
 * @Description: PIM系统的GUI版本
 **/

import system.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class PIMCmdGUI extends JFrame {
    private static class PIMManagerGUI extends PIMManager{
        PIMManagerGUI() throws IOException, ParseException {
            loadBuffer();
        }

        public String showData(){
            StringBuilder text = new StringBuilder("<html><body><p align=\\\"center\\\">您的个人信息如下:<br/>");
            for (String s: operate){
                text.append(s).append("<br/>");
            }
            text.append("</p></body></html>");
            return text.toString();
        }
    }

    private final PIMManagerGUI pimManager = new PIMManagerGUI();
    private final JPanel panel=new JPanel();
    private final JComboBox<String> cmb=new JComboBox<>();    //创建JComboBox
    private final JLabel showInfo=new JLabel();    //用于显示信息
    private final JTextField jtf=new JTextField(16);    //用于输入信息
    // 添加按钮处理
    class AddListener implements ActionListener{
        private String itemName= null;
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String text = jtf.getText();
                if(text.length()!=0) {
                    cmb.addItem(jtf.getText());    //添加项
                    PIMEntity obj = null;
                    String[] bufferData = text.split(" ");
                    String[] newBufferData = new String[bufferData.length + 1];
                    newBufferData[0] = itemName;
                    System.arraycopy(bufferData, 0, newBufferData, 1, bufferData.length);
                    bufferData = newBufferData;
                    switch (itemName){
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
                    pimManager.addOperate(obj);
                    showInfo.setText("添加成功");
                    PIMManager.saveData();
                } else {
                    panel.add(showInfo);
                    showInfo.setText("请输入想要添加的信息!");
                }
            }catch (Exception err){
                panel.add(showInfo);
                showInfo.setText("信息格式错误!");
            }
        }
    }

    class DeleteListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(cmb.getSelectedIndex()!=-1) {
                //先获得要删除的项的值
                String strDel= Objects.requireNonNull(cmb.getSelectedItem()).toString();
                cmb.removeItem(strDel);    //删除项
                panel.add(showInfo);
                showInfo.setText("删除成功，删除了："+strDel);
            } else {
                panel.add(showInfo);
                showInfo.setText("请选择要删除的星座");
            }
        }
    }

    class ShowListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.add(showInfo);
            showInfo.setText(pimManager.showData());
        }
    }

    private final AddListener addListener = new AddListener();

    class MyItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            String str=e.getItem().toString();
            if (str.equals("--请选择--")){
                addListener.itemName = null;
            }else{
                addListener.itemName = str;
            }
        }
    }

    public PIMCmdGUI() throws IOException, ParseException {
        JFrame frame=new JFrame("PIMCmd-个人信息管理系统");
        cmb.addItem("--请选择--");    //向下拉列表中添加一项
        cmb.addItem("TODO");
        cmb.addItem("NOTE");
        cmb.addItem("APPOINTMENT");
        cmb.addItem("CONTACT");
        panel.add(cmb);
        JLabel label = new JLabel("添加新的信息：");
        panel.add(label);
        panel.add(jtf);
        JButton buttonShow = new JButton("显示");
        panel.add(buttonShow);
        JButton buttonAdd = new JButton("新增");
        panel.add(buttonAdd);
        frame.add(panel);
        //buttonAdd.addActionListener();
        buttonAdd.addActionListener(addListener);       //“添加”按钮的事件
        ShowListener showListener = new ShowListener();
        buttonShow.addActionListener(showListener);
        cmb.addItemListener(new MyItemListener());    //下拉列表的事件
        frame.setBounds(300,200,700,400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) throws IOException, ParseException {
        new PIMCmdGUI();
    }
}
