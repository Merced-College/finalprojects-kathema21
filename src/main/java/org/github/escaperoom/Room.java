package org.github.escaperoom;

public abstract class Room {
    protected boolean[] finishedPuzzles = new boolean[]{false, false, false};
    protected boolean finished = false;

    public boolean isFinished() {return finished;}
    public void setFinished(boolean finished) {this.finished = finished;}
    public boolean[] getFinishedPuzzles() {return finishedPuzzles;}
    
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
