/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.graphics.statistics;

import java.util.Comparator;

/**
 *
 * @author Marto
 */
public class StatisticsComparator implements Comparator<Object> {

    @Override
    public int compare(Object t, Object t1) {
        if (t == null ) return 1;
        if (t1 == null) return -1;
        if (t.getClass() == StatisticsRow.class) {
            StatisticsRow sr1 = (StatisticsRow)t;
            if (t1.getClass() == StatisticsRow.class) {
                StatisticsRow sr2 = (StatisticsRow)t1;
                return sr2.compareTo(sr1);
            } else if (t1.getClass() == String.class) {
                return sr1.getName().compareTo(t1.toString());
            } else {
                System.out.println("error");
                return 0;
            }
        } else if (t.getClass() == String.class) {
            if (t1.getClass() == StatisticsRow.class) {
                StatisticsRow sr2 = (StatisticsRow)t1;
                return sr2.getName().compareTo(t.toString());
            } else if (t1.getClass() == String.class) {
                return t1.toString().compareTo(t.toString());
            } else {
                System.out.println("error");
                return 0;
            }
        }
        System.out.println("error");
        return 0;
    }
    
}
