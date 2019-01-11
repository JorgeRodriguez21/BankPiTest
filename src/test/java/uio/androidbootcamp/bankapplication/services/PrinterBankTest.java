package uio.androidbootcamp.bankapplication.services;

import org.junit.Test;
import uio.androidbootcamp.bankapplication.entities.*;
import uio.androidbootcamp.bankapplication.exceptions.NegativeValuesException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PrinterBankTest {

    @Test
    public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<PrinterBank> constructor = PrinterBank.class.getDeclaredConstructor();
        assertThat(true, is(Modifier.isPrivate(constructor.getModifiers())));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testShouldPrintClientInfoWhenReceiveClientWithoutAccounts(){
        Client client = new Client("Marco", "Vega", "34");

        String clientInfo = PrinterBank.printClientInfo(client);
        String clientInfoExpected = "Marco, Vega, 34";

        assertThat(clientInfo, is(clientInfoExpected));
    }

    @Test
    public void testShouldPrintAccountInfoWhenReceiveAccount(){
        AccountBank accountBank = new SavingsAccount("67");

        String accountInfo = PrinterBank.printAccountInfo(accountBank);
        String accountInfoExpected = "Cuenta: 67, Saldo: 0.0";

        assertThat(accountInfo, is(accountInfoExpected));
    }

    @Test
    public void testShouldPrintClientInfoAndAccountsInfoWhenReceiveClientWithAccounts() throws NegativeValuesException {
        Client client = new Client("Raul","Beltran","88");
        AccountBank accountBank1 = new CurrentAccount("33");
        AccountBank accountBank2 = new SavingsAccount(("90"));
        accountBank2.deposit(500.00);
        client.addOneAccount(accountBank1);
        client.addOneAccount(accountBank2);

        String result = PrinterBank.printClientInfo(client);
        String resultExpected = "Raul, Beltran, 88, Cuenta: 33, Saldo: 0.0, Cuenta: 90, Saldo: 500.0";

        assertThat(result, is(resultExpected));
    }
}
