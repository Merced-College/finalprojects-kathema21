// Katelynn Prater - 7/18/25
// abstract class to serve as template for rooms

package org.github.escaperoom;

public abstract class Room {
    protected boolean[] finishedPuzzles = new boolean[]{false, false, false}; //set as finishing puzzles
    protected boolean finished = false;
    protected RoomState state = new RoomState(); //data stored for room

    public boolean isFinished() {return finished;}
    public void setFinished(boolean finished) {this.finished = finished;}
    public boolean[] getFinishedPuzzles() {return finishedPuzzles;}
    public RoomState getRoomState() {return state;}
    
    public void setFinishedPuzzle(int index) {
        finishedPuzzles[index] = true; //int puzzle
    }

    public boolean allPuzzlesFinished() { //to check for moving into next room
        for (boolean bool : finishedPuzzles) {
            if (!bool) return false;
        }
        return true;
    }

    public abstract void runRoom();
} // end room class
