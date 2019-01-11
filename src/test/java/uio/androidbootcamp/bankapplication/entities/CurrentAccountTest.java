package uio.androidbootcamp.bankapplication.entities;

import org.junit.Before;
import org.junit.Test;
import uio.androidbootcamp.bankapplication.exceptions.NegativeValuesException;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpper2000Exception;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpperBalanceException;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CurrentAccountTest {

    private CurrentAccount currentAccount;

    @Before
    public void init(){
        currentAccount = new CurrentAccount("");
    }


    @Test
    public void testShouldSetDiscountPercentageWhenCurrentAccountIsCreated(){
        double discountPercentage = 0.01;

        assertThat(discountPercentage,is(this.currentAccount.getDiscountPercentage()));
    }

    @Test(expected = NegativeValuesException.class)
    public void testShouldThrowExceptionWhenReceiveNegativeValuesForDeposit() throws NegativeValuesException {
        double depositQuantity = -3;


        this.currentAccount.deposit(depositQuantity);
    }



    @Test
    public void testShouldDepositWhenReceivePositiveValues() throws NegativeValuesException {
        double depositQunaity = 3;

        double onePercentageDiscount = depositQunaity/100;

        this.currentAccount.deposit(depositQunaity);

        assertThat(depositQunaity-onePercentageDiscount, is(this.currentAccount.getBalance()));
    }



    @Test(expected = NegativeValuesException.class)
    public void testShouldThrowExceptionWhenReceiveNegativeValuesForWithdraw() throws NegativeValuesException, ValueUpperBalanceException, ValueUpper2000Exception {
        double withdrawQuantity = -3;


        this.currentAccount.withdraw(withdrawQuantity);
    }


    @Test(expected = ValueUpperBalanceException.class)
    public void testShouldThrowExceptionWhenPositiveValueForWithdrawIsUpperBalance() throws NegativeValuesException, ValueUpperBalanceException, ValueUpper2000Exception {
        double withdrawQuantity = 3;

        this.currentAccount.deposit(withdrawQuantity-2);

        this.currentAccount.withdraw(withdrawQuantity);
    }


    @Test
    public void testShouldWithdrawWhenReceivePositiveValuesLowerThanBalance() throws NegativeValuesException, ValueUpperBalanceException, ValueUpper2000Exception {
        double withdrawQuantity = 3;
        double depositQuantity = withdrawQuantity+2;

        double onePercentageDiscount = new BigDecimal(depositQuantity).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP).doubleValue();
        this.currentAccount.deposit(depositQuantity);

        this.currentAccount.withdraw(withdrawQuantity);

        assertThat(depositQuantity - withdrawQuantity-onePercentageDiscount, is(this.currentAccount.getBalance()));
    }


    @Test
    public void testShouldWithdrawWhenReceivePositiveValuesEqualsBalance() throws NegativeValuesException, ValueUpperBalanceException, ValueUpper2000Exception {
        double withdrawQuantity = 3;
        this.currentAccount.deposit(withdrawQuantity);
        double balanceZero = 0;

        this.currentAccount.withdraw(withdrawQuantity-withdrawQuantity/100);

        assertThat(balanceZero, is(this.currentAccount.getBalance()));
    }


    @Test
    public void testShouldDiscountNPercentageWhenCallDeposit() throws NegativeValuesException  {
        double depositQuantity = 3;

        double onePercentageDiscount = depositQuantity/100;

        this.currentAccount.deposit(depositQuantity);

        assertThat(depositQuantity-onePercentageDiscount, is(this.currentAccount.getBalance()));
    }

    @Test(expected = ValueUpper2000Exception.class)
    public void testShouldExceptionWhenWithdrawQuantityIsUpper2000() throws NegativeValuesException, ValueUpperBalanceException, ValueUpper2000Exception {
        double withdrawQuantity = 2100.00;
        currentAccount.deposit(withdrawQuantity+100);

        currentAccount.withdraw(withdrawQuantity);
    }

    @Test
    public void testShouldSetInterest000015WhenCreateCurrentAccount(){
        double interest = 0.00015;

        assertThat(interest, is(currentAccount.getInterest()));
    }

    @Test
    public void testShouldStatusActiveWhenCreateCurrentAccount(){
            String status = "Activa";

            assertThat(status, is(currentAccount.getStatus()));
    }

    @Test
    public void testShouldCurrentAccountBeAccountBank(){
        assertThat(true, is(currentAccount instanceof AccountBank));
    }

    @Test
    public void testShouldCreateAccountWithGivenId(){
        String idAccount = "1";

        CurrentAccount currentAccount = new CurrentAccount(idAccount);

        assertThat(currentAccount.getId(), is(idAccount));
    }

    @Test
    public void shouldCloneCurrentAccount() throws NegativeValuesException {
        CurrentAccount accountBank = new CurrentAccount("2");
        accountBank.deposit(100.00);

        CurrentAccount resultObjet = accountBank.clone();

        assertThat(resultObjet.getId(), is(accountBank.getId()));
        assertThat(resultObjet.getDiscountPercentage(), is(accountBank.getDiscountPercentage()));
        assertThat(resultObjet.getInterest(), is(accountBank.getInterest()));
        assertThat(resultObjet.getStatus(), is(accountBank.getStatus()));
        assertThat(resultObjet.getBalance(), is(accountBank.getBalance()));
    }



}
