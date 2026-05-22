import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.0 -- 20-01-2017
 */
public class MyDodo extends Dodo
{
    private int myNrOfEggsHatched;

    public MyDodo() {
        super( EAST );
        myNrOfEggsHatched = 0;
    }

    public void act() {
    }

    /**
     * Move one cell forward in the current direction.
     * 
     * <P> Initial: Dodo is somewhere in the world
     * <P> Final: If possible, Dodo has moved forward one cell
     *
     */
    public void move() {
        if ( canMove() ) {
            step();
        } else {
            showError( "I'm stuck!" );
        }
    }

    /**
     * Test if Dodo can move forward, (there are no obstructions
     *    or end of world in the cell in front of her).
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can move (no obstructions ahead)
     *                 false if Dodo can't move
     *                      (an obstruction or end of world ahead)
     */
    public boolean canMove() {
        if ( borderAhead() || fenceAhead() ){
            return false;}

        else { return true;}
    }

    /**
     * Turns the dodo 180 degrees, making them face the other way
     */
    public void turn180(){
        turnRight();
        turnRight();    
    }

    /**
     * Hatches the egg in the current cell by removing
     * the egg from the cell.
     * Gives an error message if there is no egg
     * 
     * <p> Initial: Dodo is somewhere in the world. There is an egg in Dodo's cell.
     * <p> Final: Dodo is in the same cell. The egg has been removed (hatched).     
     */    
    public void hatchEgg () {
        if ( onEgg() ) {
            pickUpEgg();
            myNrOfEggsHatched++;
        } else {
            showError( "There was no egg in this cell" );
        }
    }

    /**
     * Returns the number of eggs Dodo has hatched so far.
     * 
     * @return int number of eggs hatched by Dodo
     */
    public int getNrOfEggsHatched() {
        return myNrOfEggsHatched;
    }

    /**
     * Move given number of cells forward in the current direction.
     * 
     * <p> Initial:   
     * <p> Final:  
     * 
     * @param   int distance: the number of steps made
     */
    public void jump( int distance ) {
        int nrStepsTaken = 0;               // set counter to 0
        while ( nrStepsTaken < distance ) { // check if more steps must be taken  
            move();                         // take a step
            nrStepsTaken++;      // increment the counter
            System.out.println("moved");
            System.out.println(nrStepsTaken);
            if ( !canMove() ) {
                break;
            } 
        }

    }

    /**
     * Walks to edge of the world printing the coordinates at each step
     * 
     * <p> Initial: Dodo is on West side of world facing East.
     * <p> Final:   Dodo is on East side of world facing East.
     *              Coordinates of each cell printed in the console.
     */

    public void walkToWorldEdgePrintingCoordinates( ){
        while( canMove()){
            // print coordinates
            System.out.println(getX ());
            System.out.println(getY ());
            move();
            if (borderAhead() ){
                break;}
        }
    }

    /**
     * Makes the dodo walk to whatever border shes facing
     */
    public void walkToWorldEdge( ){
        while( canMove()){
            move();
            if (borderAhead() ){
                break;}
        }
    }

    /**
     * Test if Dodo can lay an egg.
     *          (there is not already an egg in the cell)
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can lay an egg (no egg there)
     *                 false if Dodo can't lay an egg
     *                      (already an egg in the cell)
     */

    public boolean canLayEgg( ){
        if( onEgg() ){
            return false;
        }else{
            return true;
        }
    } 

    /**
     * Makes the dodo hit a move if there isnt an obstacle or border in the way
     */
    public void grainAhead(){
        move();
        if (!borderAhead()||!fenceAhead() ){

            step();
            turn180();
            step();
            step();
            turn180();

        }
        else { turn180();
            turn180();
        }
    }

    /**
     * Makes the dodo climb over a fence if there is one ahead of her
     * may not work if there is an obstacle in the way
     */
    public void climbOverFence(){
        if (fenceAhead()){
            turnLeft();
            move();
            turnRight();
            move();
            move();
            turnRight();
            move();
            turnLeft();
        } 
        else {

        }
    }

    /**
     * makes the dodo move forward until they reach an egg 
     * (highly recommended to actually put an egg infront of her, else the-
     * program will crash
     */
    public void goToEgg(){
        while (!onEgg()){
            move();
        }

    }

    /**
     * Makes the dodo turn around and walk to the border
     */
    public void goBackToStartOfRowAndFaceBack(){
        turn180();
        while (!borderAhead()){
            move();}
        if (borderAhead()) {turn180();}
    }

    /**
     * Makes the dodo walk to the edge of the world while climbing over 
     * any fences
     */
    public void walkToWorldEdgeClimbingOverFences(){
        while( canMove()){
            move();
            if (borderAhead() ){
                break;}
            if(fenceAhead()){
                turnLeft();
                move();
                turnRight();
                move();
                move();
                turnRight();
                move();
                turnLeft();
            }
        }
    }

    /**
     * Makes the dodo walk to the end of the world while picking up any grain
     * in the way
     */
    public void picksUpGrainAndPrintsCoordinates(){
        while (canMove()){
            if (onGrain()){
                System.out.println(getX ());
                System.out.println(getY ());
                pickUpGrain();
            }
            move();
        }

    }

    /**
     * moves the dodo back a space by turning them around, then making her
     * move and turn around again
     */
    public void stepOneCellBackwards(){
        turn180();
        move();
        turn180();
    }

    /**
     * Makes the dodo walk to the end of the world while laying eggs in any unoccupied nests
     */
    public void worldEmptyNestsTopRow(){
        while(canMove()){
            if (onNest()){
                layEgg();}
            move();}
    }

    /**
     * Makes the dodo climb over fences while laying an egg in the first nest she comes across
     */
    public void walkToWorldEdgeClimbingOverFencesWhileLayingEggs(){
        while( canMove()){
            move();
            if (borderAhead() ){
                break;}
            if(fenceAhead()){
                turnLeft();
                move();
                turnRight();
                move();
                move();
                turnRight();
                move();
                turnLeft();
            }
            if (onNest()){
                layEgg();
                break;}
        }
    }
/**
 * Makes the dodo loop around a fenced area until he finds an egg (if there isnt an egg, she'll go on forever
 */
    public void walkToWorldEdgeClimbingOverTallerFences(){
        while (canMove()){
            turnRight();
            if (!fenceAhead()){
                move();
                turnRight();
            }
            if (fenceAhead() && !canMove()){
                turnLeft();
                move();
            }
                if (fenceAhead()){
                    turnLeft();
                    move();
                }
            if (onEgg()){
            break;}
        }
    }

}