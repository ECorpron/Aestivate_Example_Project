import com.revature.models.Account;
import com.revature.models.AccountType;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Arrays;

public class AccountTypeTest {

    public static void main(String[] args) throws NoSuchFieldException {
        Class<AccountType> aClass = AccountType.class;
        Field accountType = Account.class.getDeclaredField("accountType");

//        System.out.println(accountType.getType().isEnum());
//
//        System.out.println(Arrays.toString(accountType.getType().getEnumConstants()));
//        System.out.println("Account Type name:"+aClass.getName());

        Object temp = new BigDecimal(10);
        System.out.println(temp.getClass().equals(BigDecimal.class));

    }
}
