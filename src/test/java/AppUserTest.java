import com.revature.models.AppUser;

public class AppUserTest {

    public static void main(String[] args) {
        AppUser test1 = new AppUser(5, "test", "Class", "elicorpron", "password");
        AppUser test2 = new AppUser("Eli", "Corpron", "ecorpron", "password");

        test1.save();
        test2.save();
    }
}
