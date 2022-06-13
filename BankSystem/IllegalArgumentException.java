/**
 * @InterfaceName: IllegalArgumentException
 * @Author: cuny
 * @Date: 2022/6/13 16:07
 * @Description: 自定义未知错误类
 **/


public class IllegalArgumentException extends Exception {
    IllegalArgumentException(String message){
        super(message);
    }
}
