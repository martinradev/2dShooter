/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.unit.action.ai.heuristics;

import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.geometry.MathUtil;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;

/**
 *
 * @author Marto
 */
public class ClosestPlayerHeuristic {

    public Player getClosestPlayer(Player p) {
        Player closestPlayer = null;
        double distance = MathUtil.POSITIVE_INFINITY;
        for (GraphicalObject player : Global.getGame().getGameEntities().getPlayers()) {
            if (p == player) {
                continue;
            }
            if (closestPlayer == null) {
                closestPlayer = (Player) player;
            } else {
                double tempDist = MathUtil.distance(player.getBody().getCenter(),
                        p.getBody().getCenter());
                if (tempDist < distance) {
                    distance = tempDist;
                    closestPlayer = (Player) player;
                }
            }
        }
        double tempDist = MathUtil.distance(Global.getGame().getMainPlayer().getBody().getCenter(),
                p.getBody().getCenter());
        if (tempDist < distance) {
            distance = tempDist;
            closestPlayer = (Player)Global.getGame()
            .getMainPlayer();
        }
        return closestPlayer;
    }
}
