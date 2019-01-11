package uio.androidbootcamp.bankapplication.entities;

import org.junit.Before;
import org.junit.Test;
import uio.androidbootcamp.bankapplication.exceptions.NegativeValuesException;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpper1000Exception;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpperBalanceException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class SavingsAccountTest {

    SavingsAccount savingsAccount;

    @Before
    public void init(){
        savingsAccount = new SavingsAccount("");
    }

    @Test
    public void shouldHaveTheSameBalanceWhenWithdrawingZero() throws NegativeValuesException, ValueUpperBalanceException, ValueUpper1000Exception {
        double depositQuantity = 3.00;
        double withdrawQuantity = 0.00;

        savingsAccount.deposit(depositQuantity);

        savingsAccount.withdraw(withdrawQuantity);

        assertThat(depositQuantity, is(savingsAccount.getBalance()));

    }


    @Test(expected = NegativeValuesException.class)
    public void testShouldThrowExceptionWhenReceiveNegativeValuesForDeposit() throws NegativeValuesException {
        int depositQuantity = -3;

        savingsAccount.deposit(depositQuantity);
    }

    @Test
    public void testShouldDepositWhenReceivePositiveValues() throws NegativeValuesException {
        double depositQuantity = 3;

        savingsAccount.deposit(depositQuantity);

        assertThat(depositQuantity, is(savingsAccount.getBalance()));
    }

    @Test(expected = NegativeValuesException.class)
    public void testShouldThrowExceptionWhenReceiveNegativeValuesForWithdraw() throws NegativeValuesException, ValueUpperBalanceException, ValueUpper1000Exception {
        int withdrawQuantity = -3;

        savingsAccount.withdraw(withdrawQuantity);
    }

    @Test
    public void testShouldWithdrawWhenReceivePositiveValues() throws NegativeValuesException, ValueUpperBalanceException, ValueUpper1000Exception {
        double withdrawQuantity = 3.00;

        savingsAccount.deposit(withdrawQuantity);
        double balanceZero = 0.00;

        savingsAccount.withdraw(withdrawQuantity);

        assertThat(balanceZero, is(savingsAccount.getBalance()));
    }

    @Test(expected = ValueUpperBalanceException.class)
    public void testShouldThrowExceptionWhenWithdrawValueIsUpperBalance() throws NegativeValuesException, ValueUpperBalanceException, ValueUpper1000Exception {
        double withdrawQuantity = 3.00;

        savingsAccount.deposit(withdrawQuantity-1.00);

        savingsAccount.withdraw(withdrawQuantity);
    }

    @Test(expected = ValueUpper1000Exception.class)
    public void testShouldThrowExceptionWhenWithdrawValueIsUpper1000() throws NegativeValuesException, ValueUpperBalanceException, ValueUpper1000Exception {
        double withdrawQuantity = 1200.00;

        savingsAccount.deposit(withdrawQuantity);

        savingsAccount.withdraw(withdrawQuantity);
    }

    @Test
    public void testShouldSetInterest0001WhenCreateSavingAccount(){
        double interest = 0.0001;

        assertThat(interest, is(savingsAccount.getInterest()));
    }

    @Test
    public void testShouldSetStatusActiveWhenCreateSavingAccount(){
        String status = "Activa";

        assertThat(status, is(savingsAccount.getStatus()));
    }

    @Test
    public void testSavingAccountBeAccountBank(){
        assertThat(true, is(savingsAccount instanceof AccountBank));
    }

    @Test
    public void testShouldCreateSavingAccountWithGivenId(){
        String id = "2";

        SavingsAccount savingsAccount = new SavingsAccount(id);

        assertThat(savingsAccount.getId(), is(id));
    }

    @Test
    public void testShouldClone(){
        SavingsAccount savingsAccount = new SavingsAccount("4");

        SavingsAccount savingsAccountResult = savingsAccount.clone();

        assertThat(savingsAccountResult.getId(), is(savingsAccount.getId()));
        assertThat(savingsAccountResult.getInterest(), is(savingsAccount.getInterest()));
        assertThat(savingsAccountResult.getStatus(), is(savingsAccount.getStatus()));
        assertThat(savingsAccountResult.getBalance(), is(savingsAccount.getBalance()));
    }


}
