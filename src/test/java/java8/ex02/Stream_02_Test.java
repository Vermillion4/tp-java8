package java8.ex02;

import java8.data.Data;
import java8.data.domain.Customer;
import java8.data.domain.Order;
import java8.data.domain.Pizza;
import org.junit.Test;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 02 - Transformation
 */
public class Stream_02_Test {

    @Test
    public void test_map() throws Exception {

        List<Order> orders = new Data().getOrders();

        // Trouver la liste des clients ayant déjà passés une commande
        Map<Object, List<Customer>> customersGroups=orders.stream()
        		.filter(person->person.getPizzas().size()>=1)
        		.map(p->p.getCustomer())
        		.collect(Collectors.groupingBy(customer->customer.getId()));
        List<Customer> result = customersGroups.values().stream()
        		.map(customer->customer.get(0)).collect(Collectors.toList());
        //Plus simple avec une utilisation de distinct a la sortie de map.
        assertThat(result, hasSize(2));
    }

    @Test
    public void test_flatmap() throws Exception {

        List<Order> orders = new Data().getOrders();

        // TODO calculer les statistiques sur les prix des pizzas vendues
        // TODO utiliser l'opération summaryStatistics
        IntSummaryStatistics result = orders.stream().flatMap(order->order.getPizzas().stream())
        				.collect(Collectors.summarizingInt(pizza->pizza.getPrice()));
        //flatMapToInt -> summaryStatistics equivalent.
        assertThat(result.getSum(), is(10900L));
        assertThat(result.getMin(), is(1000));
        assertThat(result.getMax(), is(1375));
        assertThat(result.getCount(), is(9L));
    }
}
