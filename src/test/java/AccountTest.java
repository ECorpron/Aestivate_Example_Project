import com.revature.models.Account;
import com.revature.models.AccountType;

public class AccountTest {

    public static void main(String[] args) {
        Account test = new Account("test", AccountType.CHECKING, 1, 0.0);

        System.out.println(AccountType.CHECKING.ordinal());
        System.out.println(AccountType.JOINT.ordinal());
        System.out.println(AccountType.SAVINGS.ordinal());

        System.out.println(AccountType.CHECKING);


        //test.save();
    }
}
