package com.gudigudigudi.sb.Controller;

import com.gudigudigudi.sb.Model.Customer;
import com.gudigudigudi.sb.Model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @RequestMapping(value = "/{user}",method = RequestMethod.GET)
    public User getUser(@PathVariable Long user){
        return new User();
    }

    @RequestMapping(value = "/{user}/customers",method = RequestMethod.GET)
    List<Customer> getUserCustomers(@PathVariable Long user){
        return new List<Customer>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Customer> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] ts) {
                return null;
            }

            @Override
            public boolean add(Customer customer) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Customer> collection) {
                return false;
            }

            @Override
            public boolean addAll(int i, Collection<? extends Customer> collection) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Customer get(int i) {
                return null;
            }

            @Override
            public Customer set(int i, Customer customer) {
                return null;
            }

            @Override
            public void add(int i, Customer customer) {

            }

            @Override
            public Customer remove(int i) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Customer> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Customer> listIterator(int i) {
                return null;
            }

            @Override
            public List<Customer> subList(int i, int i1) {
                return null;
            }
        };
    }

    @RequestMapping(value = "/{user}",method = RequestMethod.DELETE)
    public User deleteUser(@PathVariable Long user){
        return new User();
    }
}
