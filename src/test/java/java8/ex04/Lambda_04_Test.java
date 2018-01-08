package java8.ex04;


import java8.data.Data;
import java8.data.Person;
import java8.data.Account;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Exercice 04 - FuncCollection
 * Exercice synthèse des exercices précédents
 */
public class Lambda_04_Test {

    // tag::interfaces[]
    interface GenericPredicate<T> {
        // TODO
    	public boolean test(T test);
    }

    interface GenericMapper<T, E> {
        // TODO
    	public E map(T test);
    }

    interface Processor<T> {
        // TODO
    	public void process(T test);
    }
    // end::interfaces[]

    // tag::FuncCollection[]
    class FuncCollection<T> {

        private Collection<T> list = new ArrayList<>();

        public void add(T a) {
            list.add(a);
        }

        public void addAll(Collection<T> all) {
            for(T el:all) {
                list.add(el);
            }
        }
    // end::FuncCollection[]

        // tag::methods[]
        private FuncCollection<T> filter(GenericPredicate<T> predicate) {
            FuncCollection<T> result = new FuncCollection<>();
            // TODO
            for(T something:this.list) {
            	if(predicate.test(something)) {
            		result.add(something);
            	}
            }
            return result;
        }

        private <E> FuncCollection<E> map(GenericMapper<T, E> mapper) {
            FuncCollection<E> result = new FuncCollection<>();
            // TODO
            for(T something:this.list) {
        		result.add(mapper.map(something));
            }
            return result;
        }

        private void forEach(Processor<T> processor) {
           // TODO
        	for(T something:this.list){
        		processor.process(something);
        	}
        }
        // end::methods[]

    }



    // tag::test_filter_map_forEach[]
    @Test
    public void test_filter_map_forEach() throws Exception {

        List<Person> personList = Data.buildPersonList(100);
        FuncCollection<Person> personFuncCollection = new FuncCollection<>();
        personFuncCollection.addAll(personList);
        
        personFuncCollection
                // TODO filtrer, ne garder uniquement que les personnes ayant un age > 50
                .filter(
                	person->person.getAge()>50
                )
                // TODO transformer la liste de personnes en liste de comptes. Un compte a par défaut un solde à 1000.
                .map(person->{
                	Account personalAccount=new Account();
                	personalAccount.setBalance(1000);
                	personalAccount.setOwner(person);
                	return personalAccount;
                }
                )
                // TODO vérifier que chaque compte a un solde à 1000.
                // TODO vérifier que chaque titulaire de compte a un age > 50
                .forEach( account->{
                		assert account.getOwner().getAge()>50;
                		assert account.getBalance()==1000;
                	}
                );

    }
    // end::test_filter_map_forEach[]

    // tag::test_filter_map_forEach_with_vars[]
    @Test
    public void test_filter_map_forEach_with_vars() throws Exception {

        List<Person> personList = Data.buildPersonList(100);
        FuncCollection<Person> personFuncCollection = new FuncCollection<>();
        personFuncCollection.addAll(personList);

        // TODO créer un variable filterByAge de type GenericPredicate
        // TODO filtrer, ne garder uniquement que les personnes ayant un age > 50
        GenericPredicate<Person> filterByAge = person->person.getAge()>50;

        // TODO créer un variable mapToAccount de type GenericMapper
        // TODO transformer la liste de personnes en liste de comptes. Un compte a par défaut un solde à 1000.
        GenericMapper<Person,Account> mapToAccount = person->{
        	Account account=new Account();
        	int balance=1000;
        	account.setBalance(balance);
        	account.setOwner(person);
        	return account;
        };

        // TODO créer un variable verifyAccount de type GenericMapper
        // TODO vérifier que chaque compte a un solde à 1000.
        // TODO vérifier que chaque titulaire de compte a un age > 50
        Processor<Account> verifyAccount = accountPerson->{
        	assert accountPerson.getBalance().equals(1000);
        	assert accountPerson.getOwner().getAge()>50;
        };

        
        personFuncCollection
                .filter(filterByAge)
                .map(mapToAccount)
                .forEach(verifyAccount);


    }
    // end::test_filter_map_forEach_with_vars[]


}
