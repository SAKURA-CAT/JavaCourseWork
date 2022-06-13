package utils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName: Exhibit
 * @Author: cuny
 * @Date: 2022/6/4 16:42
 * @Description: 记事本的展示类，用于打印一些交互和展示信息，能够做到文本对齐、交互矫正。
 **/


public class Exhibit {
    /**
     * 程序介绍，介绍一下本程序的主要功能
     */
    public void introduce(){
        System.out.print(
                """
                #————————————————————————CunyNotebook: Cuny的个人记事本程序————————————————————————————#
                |     本程序使用Java进行编写,字符串打印主要由Exhibit类完成, 核心逻辑处理由Notebook类完成      |
                |                最终由CunyNotebook类连接上述两个类,并实现人机交互                        |
                |                       接下来请根据提示,进行记事本的相关操作                             |
                #———————————————————————————————————————————————————————————————————————————————————#
                输入回车以继续..."""
        );
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    public int modeChoose(){
        while (true){
            System.out.println(
                    """
                    请输入数字选择相应操作：
                    1: 添加记录
                    2: 删除记录
                    3: 修改记录
                    4: 保存记录
                    5: 展示记录
                    6: 计算文件长度
                    7: 退出""");
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine().trim();
            try{
                return Integer.parseInt(text);
            }catch (java.lang.NumberFormatException e){
                //System.out.println("输入有误，请输入1到4之间的数字！");
                errPrint();
            }
        }
    }

    public void errPrint(){
        System.err.println("输入有误，请输入1到7之间的数字！");
    }

    public void errPrint(int start, int end){
        System.err.println("输入有误，请输入" + start + "到" + end + "之间的数字！");
    }

    public String add(){
        System.out.print("请输入需要添加的记录内容:  ");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine().trim();
        System.out.println("------------添加成功------------");
        return text;
    }

    public void print(){
        System.out.print("输入回车以继续...");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine().trim();
    }

    public int delete(int maxLen){
        int index;
        while (true){
            System.out.print("请输入需要被删除的记录编号:  ");
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine().trim();
            try{
                if ((index = Integer.parseInt(text)) <= maxLen){
                    return index - 1;
                }else{
                    errPrint(1, maxLen);  // todo 解决一下打印顺序出错的问题
                }
            }catch (NumberFormatException e){
                //System.out.println("输入有误，请输入1到4之间的数字！");
                errPrint(1, maxLen);
            }
        }
    }

    public void save(){
        System.out.println("保存成功!");
    }

    // 改变元素结构，需要传递两个参数
    public List<Object> change(int maxLen){
        List<Object> record = new ArrayList<>();
        int id;
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("请输入需要被修改的记录id:  ");
            String idString = scanner.nextLine().trim();
            try{
                id = Integer.parseInt(idString) - 1;
                if (id >= maxLen || id < 0){
                    errPrint(1, maxLen);
                }
                record.add(id);
                break;
            }catch (NumberFormatException e){
                errPrint(1, maxLen);
            }
        }
        System.out.print("请输入修改的内容:  ");
        record.add(scanner.nextLine().trim());
        return record;
    }

    // 读取文件，相对路径为当前运行路径
    public void scanFile(Notebook obj){
        Scanner scanner = new Scanner(System.in);
        while (true){
            try{
                System.out.print("请输入需要被统计的文件路径:  ");
                String filePath = scanner.nextLine().trim();
                System.out.println("该文件共有" + obj.readFile(filePath) + "个字符");
                break;
            }catch (FileNotFoundException e){
                System.err.println("文件不存在!");
            }catch (IOException e){
                System.err.println("未知错误!");
            }
        }
    }

}
