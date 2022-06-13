/**
 * @ClassName: MapTest
 * @Author: cuny
 * @Date: 2022/4/30 12:20
 * @Description: Map测试
 **/
import java.util.Map;
import java.util.HashMap;

public class MapTest {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Java Programming", 123);
        System.out.println(map.get("Java Programming")); // 123
    }
}