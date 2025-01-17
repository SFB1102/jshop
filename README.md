# JSHOP Usage:

The JSHOP planner has to search modi right now, a blind depth-first search and Monte Carlo Tree Search.

1. Blind depth first search

java -jar PathToJAR DOMAINFILE PROBLEMFILE -s [--all] [-d INT1-10]

Without "--all" the planner will stop after the first solution found, with it the planner will search for all solutions. 
With the option "-d" the output can be made more verbose with 1 being the lowest amount of additional output, and it increasing with each number up to a max of 10

2. Monte Carlo Tree Search 

java -jar PathToJAR DOMAINFILE PROBLEMFILE -m MCTSRUNS -c COSTFUNCTION -e EXPANSIONPOLICY [-b] [--recursiveSimulation NUMBER]

* MCTSRUNS can be any positive integer. 

* "-b": enables branch and bound pruning (i.e. prune against best solution found so far)

* "--recursiveSimulation NUMBER": makes "NUMBER" many "depth-first-search" like runs that use backtracking

* EXPANSIONPOLICY can be:  
  + "simple" : Standard MCTS behavior  
  + "deadEnd" : Deletes DeadEnd Nodes to prevent repeat visits of same deadend. The best performing setting.  
  + "primitive" : Collapses MCTS Search Nodes unless the task is primitive. This is an old experimental setting.  
  + "landmark" : Same MCTS config as "simple" but use landmarks as search guidance. To work options "-l" and "-lf LANDMRAKFILE" are also required.   

* COSTFUNCTION can be:  
  + "UNIT": All actions have unit cost  
  + "BASIC": Actions have cost as specified in the domain file.  
  + "STATEDEPENDENT": Uses hand crafted costfunctions for Minecraft, Blocksworld and Rovers domains does not work for other domains.  
  + "NLG": Only usable with Minecraft domain, calls the NLG System for cost estimates.  
  
  If the option "NLG" or "STATEDEPENDENT" is chosen, the abstraction level for Minecraft instructions can be chosen by adding option "--level ABSTRACTION". ABSTARCTION can be "BLOCK", "MEDIUM" or "HIGHLEVEL".  

Additional options for MCTS, their default is already set according to best performance:  
* "-p" or "--planFile" Plan will be printed into File with this name, will overwrite anything already in File and create new File if none with that name is present
* "--noRec": Disables the collapsing of MCTS Search Nodes. Really bad for performance  
* "--noRandom": The first MCTS run will not include any randomness and follow the method ordering given in the domain  
* "--printTree": Prints a .dot style output of the MCTS search tree  
* "--min": Uses the minimum cost when updating the MCTS Nodes instead of the UCT-formula  
* "--fastSimulation": The states generated in simulations are not saved  
* "-bf": Use branch and bound pruning and fastSimulations  
* "-exp REALNUMBER": Set the exploration factor of MCTS to given real number  
* "--randomSeed NUMBER": Set the seed of the random generator to NUMBER  

Currently best config to start planning using the integrated NLG system:
java -jar $PATH_TO_JAR $PATH_TO_DOMAIN $PATH_TO_PROBLEM -m 1000000 -t 1 -exp 10 -c NLG --level MEDIUM -e deadEnd -wf $PATH_TO_WEIGHT_FILE
 
