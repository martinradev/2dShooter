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
        StatisticsRow tsr = (StatisticsRow)t;
        StatisticsRow tsr1 = (StatisticsRow)t1;
        if (tsr.getName().equals("Name")) return -1;
        if (tsr1.getName().equals("Name")) return 1;
        Integer tsrFrags = Integer.parseInt(tsr.getFrags());
        Integer tsr1Frags = Integer.parseInt(tsr1.getFrags());
        int fragComp = tsrFrags.compareTo(tsr1Frags);
        if (fragComp != 0) return -fragComp;
        Integer tsrDeaths = Integer.parseInt(tsr.getDeaths());
        Integer tsr1Deaths = Integer.parseInt(tsr1.getDeaths());
        int deathsComp = tsrDeaths.compareTo(tsr1Deaths);
        if (deathsComp != 0) return deathsComp;
        return tsr.getName().compareTo(tsr1.getName());
    }
    
}
