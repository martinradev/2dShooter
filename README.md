VirtualCommando 1.0
=========

VirtualCommando is a java based 2d shooter which normally operates as a respawn arena where each player (bot ot server player) respawns and kills other players. Currently, there have been implemented very simple bots and a multriplayer which has some latency.

# Aspects
- The game was developed and continiously is without the help of any game frameworks, libraries, etc.
- Currently, the game supports loading of special cases of tmx maps since there are also respawn points and a waypoint in the future for the bots.
- Collision detection is quite fast and it works for different geometric objects like convex polygons, ellipses, segements, etc
- Collision detection is improved by building a binary space tree to reduce the number of checked for collision static objects on the map
- Multiplayer has been implemented quite straightforward. Currently, the commands sent over a TCP connection are not encrypted. The messages are quite large as well and most probably most of the commands will be store in a short sequence of bits.
- Bots are entirely smart. They go back and forth and shoot in the direction of the player. Should be added a way point system and either a decision tree, ot fuzzy logic. Possible influence for the AI of the bots: http://www.cs.rochester.edu/~brown/242/docs/QuakeIII.pdf


# Issues
- game still contains bugs which are up to be resolved.

# Future ideas
- adding crates for health and new weapons like a lazer or something of that sort.
- adding a second level and setting a sprite for the walls.

Current screens of the game:
![Menu screen](https://github.com/martinradev/2dShooter/blob/master/screenshots/menu%20screen.png "Menu screen")

![Game screen](https://github.com/martinradev/2dShooter/blob/master/screenshots/game%20screen.png "Game screen")
