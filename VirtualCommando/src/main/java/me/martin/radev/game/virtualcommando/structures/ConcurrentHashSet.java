/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.structures;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrencyHashSet uses a {@link ConcurrentHashMap} as a base. Should be used 
 * when multiple threads are supposed to use the set.
 * @param <K> 
 * @author Marto
 */
public class ConcurrentHashSet<K> {

    private ConcurrentHashMap<K,Boolean> map;

    /**
     *
     */
    public ConcurrentHashSet() {
        map = new ConcurrentHashMap<>();
    }

    /**
     *
     * @param e
     * @return
     */
    public boolean add(K e) {
        return map.put(e, true) != null;
    }

    /**
     *
     * @param o
     * @return
     */
    public boolean remove(Object o) {
        return map.remove(o) != null;
    }

    /**
     *
     * @return
     */
    public Set<K> getHashSet() {
        return map.keySet();
    }

    
}
