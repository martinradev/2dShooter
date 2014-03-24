/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.structures;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Marto
 */
public class ConcurrentHashSet<K> {

    private ConcurrentHashMap<K,Boolean> map;

    public ConcurrentHashSet() {
        map = new ConcurrentHashMap<>();
    }

    public boolean add(K e) {
        return map.put(e, true) != null;
    }

    public boolean remove(Object o) {
        return map.remove(o) != null;
    }

    public Set<K> getHashSet() {
        return map.keySet();
    }

    
}
