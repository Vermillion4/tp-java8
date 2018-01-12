package java8.ex02;

import java8.data.Account;
import java8.data.Person;
import org.junit.Test;

import java.util.function.BiFunction;

/**
 * Exercice 02 - java.util.function.BiFunction
 */
public class Function_02_Test {

    //  tag::buildAccount[]
    // TODO Compléter la fonction buildAccount
    // TODO la fonction possède 2 paramètres en entrée : une personne et un solde
    BiFunction<Person, Integer, Account> buildAccount = (Person personne,Integer solde)->{
    	Account account=new Account();
    	account.setBalance(solde);
    	account.setOwner(personne);
    	return account;
    };
    //  end::buildAccount[]

    @Test
    public void test_build_account() throws Exception {

    	Person johntest=new Person("John","France",80,"pass");
        // TODO invoquer la fonction buildAccount pour que le test soit passant
        Account account = buildAccount.apply(johntest, 500);

        assert account.getBalance().equals(500);
        assert account.getOwner().getFirstname().equals("John");
        assert account.getOwner().getLastname().equals("France");
        assert account.getOwner().getAge().equals(80);
        assert account.getOwner().getPassword().equals("pass");
    }


}
