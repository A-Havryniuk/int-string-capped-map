package com.epam.autotasks.collections;

import java.util.*;

class IntStringCappedMap extends AbstractMap<Integer, String> {
    LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
    private final long capacity;
    private int currentLength = 0;

    public IntStringCappedMap(final long capacity) {
        this.capacity = capacity;
    }
    public long getCapacity() {
        return this.capacity;
    }

    @Override
    public Set<Entry<Integer, String>> entrySet() {

        return new AbstractSet<>() {
            @Override
            public Iterator<Entry<Integer, String>> iterator() {
                Iterator<Entry<Integer, String>> iterator = map.entrySet().iterator();
                return new Iterator<>() {

                    @Override
                    public boolean hasNext() {
                       return iterator.hasNext();
                    }

                    @Override
                    public Entry<Integer, String> next() {
                        return iterator.next();
                    }
                };
            }

            @Override
            public int size() {
               return IntStringCappedMap.this.size();
            }
        };
    }

    @Override
    public String get(final Object key) {
       return this.map.get(key);
    }

    @Override
    public String put(final Integer key, final String value) {
       if(value.length() > this.capacity)
           throw new IllegalArgumentException();
       String result = null;
       if(map.get(key)!= null)
       {
           result = map.get(key);
           map.remove(key);
           currentLength -= result.length();
       }
       map.put(key, value);
       currentLength += value.length();
       while(currentLength>capacity)
       {
           currentLength -= map.get(new ArrayList<Integer>(map.keySet()).get(0)).length();
           map.remove(new ArrayList<Integer>(map.keySet()).get(0));
       }
       return result;
    }

    @Override
    public  String remove(final Object key) {
        if(key == null)
            throw new NullPointerException();
        if(map.containsKey(key)) {
            this.currentLength -= map.get(key).length();
            return this.map.remove(key);
        }
        return null;
    }

    @Override
    public int size() {
        return this.map.size();
    }
}

