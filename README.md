## Members :

- AOUESSAR oussama @aouessar
- HADDOUK ouriel hai @haddouko
- JUAN-GESTA Etienne @Netien
- LECLEIRE sebastien @lecleire
- MEDJADI mohamed @medjadi
- NOUREL astyax @asere
- RAMIRES Bryan @Bryan



# Link to youtube vidéo :
Brief explanation of the project 

https://www.youtube.com/watch?v=2DVZCdISgPI&t=451s

# Extensions done :
This is a list of what has been done in the second version of the project.

### Space teleportation :
	- You can build a void type block
	- Once you are near one of the void blocks you can press "T" to teleport to a starting point wich is (0, 40, 0)
	- You can't teleport yourself if you are not in a range of 4 blocks arround a void type block

### Introduction of new forces :
	- Gravity force is applied to the entire world.
	- Slipperry terrain according to the biome.
	- the player character can bounce almost as a ball.
	- Parabolic fall when running and jumping (real life jump).

### Plugins Extensions :
	- Give the ability to create plugins based on the API of the project
	- A plugin is a Thread that can access to the API (actually of all the project),
	  by giving the .jar file of the project (assembled with sbt assembly).
	- A plugin once ready will be assembled in a .jar format
	- A .jar plugin can be put in the desktop/lib/ directory, sbt will detect it automatically

### Creative mode :
	- Job done:
	- You can switch modes between normal mode and creative mode, by pressing "1" for creative mode and "0" for normal mode
	- In the creative mode you can fly indefinitely with no gravity affecting you, and you have specific controls according to the current gamemode.
	- How:
	- First, we had to refactor all the Player controller class and the players inputs to make it independent from one other. We have now an ActionListener(EntityInputs\Listeners\CharacterInputs)
	- that connects all inputProcessors. The PlayerMotor classes are used by the gamePlayer to handle its movements. This way, it is much more modular. Changing the players controls
	- is now just about pluging a new playerMotor, and it is what we do for "survival" and creative game modes. Even if we want to code an AI for movements, we have to plug the
	- IA Motor here and all the rest will work fine. All the architecture has been set to predict and be ready for futur possible evolutions.
	- After creating these new classes, we used the observer Pattern to communicate with the game mode. Physics controller and GamePlayer are Observers that subscribe to the GameMode
	- which is the Subject. When the GameMode changes, it tells all the observers to update. That's how we change gravity and set the players controls when we change gameMode.
	- Precisions:
	- In creative mode, there is no gravity and there is one more movement. Pressing ctrl will drop down your character.

### Time portals :
	- Job done:
	- We added the command pattern to our game. There is a queue that contains all commands we want to execute. (For instance, we have a command for telporting the player, destroying or putting a block...)
	- By using this pattern, it is now very easy to make time portals. We have to enqueue in a new queue every opposite commands that are enqueud from the command queue.
	- Then, to make a travel time in the past, we have to execute every opposite commands to make the rollback.
	- The architecture is set to implement this new feature easily, but we didn't implement it.

### Bioms :
	- We can generate different bioms of blocks (sand, cobble, stone..etc) need to be improved.
	- Every block has a biome type that contains all the biome data. For the moment, we only have one biome. the logic that drows different biomes through the map wasn't
	- coded, but all the data structure is set.
	- Biome types are set while generating chunks.



### Various Sceenshots :
![enter image description here](https://imgur.com/e0zmhYD.png)
![enter image description here](https://imgur.com/lzx5dnq.png)
![enter image description here](https://imgur.com/5WwETVW.png)
![enter image description here](https://imgur.com/fh9PU3m.png)
![enter image description here](https://imgur.com/LmndpBu.png)
