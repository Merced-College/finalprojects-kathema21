
package org.github.escaperoom;

public class RoomState {
    private int roomIter; //iterations for each room
    private boolean[] puzzlesCompleted;
    private boolean[] puzzlesVisited;

    public RoomState() {
        roomIter = 0;
        puzzlesCompleted = new boolean[]{false, false, false};
        puzzlesVisited = new boolean[]{false, false, false};
    }

    public void incRoomIter() {
        roomIter++;
    }

    public boolean isPuzzleVisited(int index) {return puzzlesVisited[index];}
    public boolean isPuzzleCompleted(int index) {return puzzlesCompleted[index];}
    public boolean[] getPuzzlesCompleted() {return puzzlesCompleted;}
    public boolean[] getPuzzlesVisited() {return puzzlesVisited;}

    public void setPuzzleVisited(int index) {puzzlesVisited[index] = true;}
    public void setPuzzleCompleted(int index) {puzzlesCompleted[index] = true;}
    
    public void setPuzzlesCompleted(boolean[] puzzlesCompleted) {this.puzzlesCompleted = puzzlesCompleted;}
    public void setPuzzlesVisited(boolean[] puzzlesVisited) {this.puzzlesVisited = puzzlesVisited;}

    public int getRoomIter() {return roomIter;} //bunch of assessors and mutators
}   
