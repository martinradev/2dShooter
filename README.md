2dShooter
=========

A java-based 2d shooter.

# Idea
The idea of the project is to develop a relevantly simple 2D shooter in java. Some of the planned feautures:
- Types of games: single player / multiplayer
- Bots. Maybe for now they will be implemented using something of the type of A* with just some propability that they will shoot in the right angle + adding some random error.
- Different weapons. Maybe two types of weapons: a simple one and a laser which will bounce off walls
- Loading maps from map files. For now I just plan to use a basic tiled map editor which supports objects like polygons and ellipses. Then I will just load the file and use it accordingly.
- Custom collision detection. For now maybe the collision detection will only work with convex objects since the case is simpler with convect ones.
- Use a binary space tree to check for possible to collide objects. For now, this MAY be included. It depends on the time it will take to develop the first iterations of the project.
- Possibly have rounds or even easier - almost immediate respawn.


# Development steps
- Make the basic geometric classes like: Vector2D, GeometricOject, Polygon, Circle
- Make the tiled map loader and parser
- Make the map renderer
- Create abstract game logic, implement single game
- Create abstract player, create my player
- Add keyboard listener to my player so that it moves and make sure that everything is rendered correctly
- Create abstract weapon, normal weapon, abstract bullet, normal bullet. Every object on the map except the background has to use a geometric object as body.
- Create collision detection. Check with random projectiles as a JUNIT test.
- Write bot logic. Possible influence: http://www.cs.rochester.edu/~brown/242/docs/QuakeIII.pdf

## Optional
- Add multiplayer. Use current user a socket server. Other players will be just socket 
