package com.example.apartmentsforrent.persistence;

public class Checker {

    public <T extends Comparable<T>> boolean check(T from, T to, T checking, T zero) {
        if (from == null && to == null) {
            return true;
        } else if (to == null) {
            return (checking.compareTo(from) > 0 || checking.equals(from));
        } else {
            if (from == null) {
                from = zero;
            }
            return ((checking.compareTo(from) > 0 || checking.equals(from)) &&
                    (to.compareTo(checking) > 0) || to.equals(checking));
        }
    }
}
