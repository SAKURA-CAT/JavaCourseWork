package utils;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: Notebook
 * @Author: cuny
 * @Date: 2022/6/4 11:46
 * @Description: 记事本类，实现信息的记录、删除和存储,这一块并不实现交互逻辑
 **/

public class Notebook extends StringProcess{
    // 规定文件读取的路径，为本文件所在的位置
    // free init
    private final String filePath = Objects.requireNonNull(this.getClass().getResource("")).getPath() + "buffer";
    private final Records records;

    public static void main(String[] args){
        /*
          测试入口，测试Notebook的各种操作内容
         */
        System.out.println("hello world form Notebook");
    }
    /*
      类的初始化，不需要任何的参数构建，仅仅构建内部参数
     */
    public Notebook() throws IOException, ParseException {
        // 在编译后的此文件的同目录下，生成一个buffer
        File buffer;
        buffer = new File(filePath);
        if (!buffer.exists()){
            // 文件不存在存在，创建文件
            boolean flag = buffer.createNewFile();
            if (!flag){  // 创建失败
                throw new IOException();
            }
        }
        // 此时filePath文件必然存在，我们以BufferedReader方式读取buffer
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        records = new Records(bufferedReader);
        bufferedReader.close();
    }
    /*
        创建一个记录收集类，这里面将包含所有的记录
     */
    private static class Records{
        /*
          Records作为日志收集类，记录以一条一条的形式出现，每一条有一个完整的数据结构Record
         */
        private final List<Record> contents;
        private static class Record extends StringProcess{
            public String content;
            public Date createTime;
            public Date updateTime;

            public static String timeFormat = "yyyy.MM.dd-HH:mm:ss";
            /**
             * Record类用于构建每一条记录，每次创建会同时需要一些附加信息，比如创建时间、更新时间等
             * @param content 日志记录的内容，定义为字符串
             * @param createTime 日志记录的内容，定义为字符串
             * @param updateTime 日志记录的内容，定义为字符串
             */
            public Record(String content, Date createTime, Date updateTime){
                this.content = content;
                this.createTime = createTime;
                this.updateTime = updateTime;
            }

            public Record(String content){
                this.content = content;
                this.createTime = new Date();
                this.updateTime = createTime;
            }

            // 比较记录的创建时间和更新时间是否相同，不同则返回false
            public boolean theRecordHasChanged(){
                return !createTime.equals(updateTime);
            }

            // 打印此条记录
            public void print(int iterm){
                SimpleDateFormat format =   new SimpleDateFormat(timeFormat);
                String createTime = format.format(this.createTime);
                if (theRecordHasChanged()){
                    String updateTime = format.format(this.updateTime);
                    System.out.println(iterm + "." + content + "    stringNum:"+ stringContentLength(content) +
                                        "    create: " + createTime + "  update: " + updateTime);
                }else{
                    System.out.println(iterm + "." + content + "    stringNum:"+ stringContentLength(content) +
                                        "    create: " + createTime);
                }
            }

            // 返回一个字符串，写入缓存文件当中
            public String contentForWrite(){
                SimpleDateFormat format =   new SimpleDateFormat(timeFormat);
                String createTime = format.format(this.createTime);
                String updateTime = format.format(this.updateTime);
                return content + "&&&" + createTime + "&&&" + updateTime+"\n";
            }

            // 修改该Record的数据，同时修改updateTime
            public void change(String newContent){
                this.content = newContent;
                this.updateTime = new Date();
            }
        }

        private static final String timeFormat = Record.timeFormat;

        /**
         * 从加载的buffer中还原Records
         * @param buffer 加载的File buffer
         */
        public Records(BufferedReader buffer) throws IOException, ParseException {
            // 初始化data
            contents = new ArrayList<>();
            String s;
            while ((s = buffer.readLine()) != null){
                // 逐行加载记录
                //System.out.println(s);
                contents.add(loadRecordFromBuffer(s));
            }
        }
        public Record loadRecordFromBuffer(String buffString) throws ParseException {
            // 将字符串翻译为Record结构
            String[] buffSplit = buffString.split("&&&");
            SimpleDateFormat format = new SimpleDateFormat(timeFormat);
            // 第一个为content，第二个为createTime，第三个为updateTime
            return new Record(buffSplit[0], format.parse(buffSplit[1]), format.parse(buffSplit[2]));
        }

        private Record createNewRecord(String content){
            return new Record(content);
        }

        private void add(String newContent){
            contents.add(createNewRecord(newContent));
        }

        // 打印当前记录
        public void print(){
            int i;
            for (i = 0; i < contents.size(); i++){
                System.out.print("| ");
                contents.get(i).print(i + 1);
            }
        }
    }


    /*
        下面是一些功能实现的开放接口
     */
    // 打印所有数据
    public void print(){
        System.out.println("# —————————————— 当前记录 —————————————— #");
        records.print();
        System.out.println("# ————————————————————————————————————— #");
    }
    // 添加数据
    public void add(String newContent){
        records.add(newContent);
    }
    // 删除数据
    public void delete(int index){
        records.contents.remove(index);
    }
    // 获取记录的数量
    public int contentLength(){
        return records.contents.size();
    }
    // 获取第index条数据
    public Records.Record get(int index){
        return records.contents.get(index);
    }
    // 修改某一条数据的内容
    public void changeById(int index, String newContent){
        get(index).change(newContent);
    }
    // 保存数据到filePath
    public void save() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        for (int i = 0; i < contentLength(); i++){
            bufferedWriter.write(get(i).contentForWrite());
        }
        bufferedWriter.close();
    }
}


/*
  字符串处理程序，可以统计输入字符串的字数，也可以读取文件生成字符串统计个数
 */
class StringProcess{
    public int stringContentLength(String content){
        // 如果最后一个字符是\n，就删除
        if (content.length() != 0 && content.charAt(content.length() - 1) == '\n'){
            content = content.substring(0, content.length() - 1);
        }
        return content.length();
    }

    /*
      读取文件内容，统计字符串个数
      抛出FileNotFoundException时说明文件不存在
     */
    public int readFile(String filePath) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filePath));
        String str;
        int sumLen = 0;
        while ((str = in.readLine())!= null){
            sumLen += stringContentLength(str);
        }
        return sumLen;
    }
}