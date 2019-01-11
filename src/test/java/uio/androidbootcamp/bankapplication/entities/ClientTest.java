package uio.androidbootcamp.bankapplication.entities;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ClientTest {

    @Test
    public void testShouldSetNameWhenCreateClient(){
        String name = "Karol";

        Client client = new Client(name, "", "");

        assertThat(client.getName(),is(name));
    }

    @Test
    public void testShouldSetLastNameWhenCreatingClient(){
        String lastName = "Perez";

        Client client = new Client("", lastName, "");

        assertThat(client.getLastName(), is(lastName));
    }

    @Test
    public void testShouldSetIDWhenCreateClient(){
        String id = "12";

        Client client = new Client("","", id);

        assertThat(client.getId(),is(id));
    }

    @Test
    public void testShouldClientCouldAddCurrentAccounts(){
        Client client = new Client("","", "12");
        AccountBank account = new CurrentAccount("");

        client.addOneAccount(account);

        assertThat(1, is(client.getAccountsBank().size()));
    }

    @Test
    public void testShouldClientAddSavingAccounts(){
        Client client = new Client("","","12");
        AccountBank account = new SavingsAccount("");

        client.addOneAccount(account);

        assertThat(1,is(client.getAccountsBank().size()));
    }

}