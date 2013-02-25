Libgdx examples in Scala
=====

This repository is serving as both a learning tool for myself, and (eventually) as 
a reference for implementing a bunch of the libgdx examples in scala.

Generally the scala in this project is not idomatic, and instead looks more like java. 
This is on purpose -- garbage generation is the enemy of smooth gaming.


Compiling
---

this should resolve all dependencies and compile:

./sbt
> project desktop
> compile


Running:
---

./run


Currently Implemented
=====

1. Box2D platformer character contols [demo'd on the badlogicgames blog](http://www.badlogicgames.com/wordpress/?p=2017)
  * see com/rathboma/playpen/box2dcharacter
2. Sprite Animation
  * see com/rathboma/playpen/animation
3. Scene2D
  * see com/rathboma/playpen/scene2d


LICENSE:

Apache 2.0