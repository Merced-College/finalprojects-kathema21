package org.github.escaperoom;

public abstract class Room {
    protected boolean[] finishedPuzzles = new boolean[]{false, false, false};
    protected boolean finished = false;
    protected RoomState state = new RoomState();

    public boolean isFinished() {return finished;}
    public void setFinished(boolean finished) {this.finished = finished;}
    public boolean[] getFinishedPuzzles() {return finishedPuzzles;}
    public RoomState getRoomState() {return state;}
    
    public void setFinishedPuzzle(int puzzle) {
        finishedPuzzles[puzzle - 1] = true;
    }

    public boolean allPuzzlesFinished() {
        for (boolean bool : finishedPuzzles) {
            if (!bool) return false;
        }
        return true;
    }

    public abstract void runRoom();
}
