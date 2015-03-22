public Comparator<Employee> getComparatorFor(final String field) {
    return new Comparator<Employee>() {
        Object field1, field2;
        try {
            field1 = method.invoke(o1, null);
            field2 = method.invoke(o2, null);
        } catch(Exception e){
            throw enw RuntimeException(e);
        }

        return ((Comparable) field1).compareTo(field2);
    }
}

