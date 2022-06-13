/**
 * @ClassName: CunyNotebook
 * @Author: cuny
 * @Date: 2022/6/4 11:36
 * @Description: 记事本程序的运行入口
 **/

import utils.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class CunyNotebook {
    public static void main(String[] args) throws IOException, ParseException {
        Exhibit exhibit = new Exhibit();
        Notebook notebookObj = new Notebook();
        int mode;
        exhibit.introduce();
        //System.out.println("hello world");
        while (true){
            mode = exhibit.modeChoose();
            switch (mode) {
                case 1 ->  // 添加
                        notebookObj.add(exhibit.add());
                case 2 ->  // 删除
                        notebookObj.delete(exhibit.delete(notebookObj.contentLength()));
                case 3 ->{
                    List<Object> record = exhibit.change(notebookObj.contentLength());
                    notebookObj.changeById((Integer) record.get(0), (String) record.get(1));
                }
                case 4 -> {  // 保存
                    notebookObj.save();
                    exhibit.save();
                }
                case 5 -> {  // 展示
                    notebookObj.print();
                    exhibit.print();
                }
                case 6 -> // 计算文件长度
                        exhibit.scanFile(notebookObj);
                case 7 -> {  // 退出
                    System.out.println("good bye.");
                    return;
                }
                default -> exhibit.errPrint();
            }
        }
    }
}
