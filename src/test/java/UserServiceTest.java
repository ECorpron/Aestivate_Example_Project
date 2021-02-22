import com.revature.models.AppUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserServiceTest {

    public static void main(String[] args) {
        Map<String, Object> search = new HashMap<>();
        search.put("username", "ecorpron");
        search.put("password", "password");

        AppUser dummy = new AppUser();
        ArrayList<AppUser> auth = dummy.find(search);
        System.out.println(auth.get(0).toString());
    }
}
