# Game-of-Life
Conway's Game of Life, built in Java using Swing.

## Rules
The Game of Life is a two-dimensiona grid of square cells, where each cell is either dead or alive. Each cell interacts with it's 8 adjacent neighbours, and at each step forward in time the state of each cell is determined by these three rules:
1. Any live cell with two or three live neighbours survives.
2. Any dead cell with three live neighbours becomes a live cell.
3. All other live cells die in the next generation. Similarly, all other dead cells stay dead.
The initial states of the first generation is generated at random, and from there each cell's state is determined by the aforementioned rules.

<img src="https://i.imgur.com/VRIhVgs.png"/>
