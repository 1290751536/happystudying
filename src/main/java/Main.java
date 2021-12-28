import cn.edu.jxnu.happystudying.util.JdbcUtils;
import cn.edu.jxnu.happystudying.util.Md5Utils;
import org.springframework.jdbc.core.JdbcTemplate;

public class Main {
    public static void main(String[] args) {
        System.out.println(Md5Utils.digest("123"));
    }
}
