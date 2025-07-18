package org.github.escaperoom;
import java.util.ArrayList;

public class StateTracker {
    private final ArrayList<Room> gameState;
    private final static StateTracker tracker = new StateTracker();
    private boolean incineratedCompanion;
    private boolean savedCompanion;
    private boolean helpInitiated;
    private int iters;

    private StateTracker() {
        gameState = new ArrayList<>();
        incineratedCompanion = false;
        savedCompanion = false;
        helpInitiated = false;
        initializeRooms();
    }

    private void initializeRooms() {
        Room r1 = new Room1();
       // Room r2 = new Room2(player);
       // Room r3 = new Room3(player);
       // Room r4 = new Room4(player);
       // Room r5 = new Room5(player);
        gameState.add(r1);
       // gameState.add(r2);
       // gameState.add(r3);
      //  gameState.add(r4);
      //  gameState.add(r5);
    }

    public static StateTracker getInstance() {
        return tracker;
    }

    public ArrayList<Room> getGlobalState() {return gameState;}
    public boolean incineratedCompanion() {return incineratedCompanion;}
    public boolean savedCompanion() {return savedCompanion;}
    public void setIncineratedCompanion() {incineratedCompanion = true;}
    public void setSavedCompanion() {savedCompanion = true;}
    public boolean getHelpInitiated() {return helpInitiated;}
    public void setHelpInitiated() {helpInitiated = true;}
    public int getGlobalIters() {return iters;}
    public void incGlobalIter() {iters += 1;}


    public RoomState getRoomState(int roomIndex) {
        Room r = gameState.get(roomIndex);
        return r.getRoomState();
    }

    public int getRoomIter(int roomIndex) {
        RoomState s = getRoomState(roomIndex);
        return s.getRoomIter();
    }

    public boolean[] getFinishedPuzzles(int roomIndex) {
        RoomState s = getRoomState(roomIndex);
        return s.getPuzzlesCompleted();
    }

    public boolean[] getStartedPuzzles(int roomIndex) {
        RoomState s = getRoomState(roomIndex);
        return s.getPuzzlesVisited();
    }

    public boolean isPuzzleVisited(int roomIndex, int puzzleIndex) {
        RoomState s = getRoomState(roomIndex);
        return s.isPuzzleVisited(puzzleIndex);
    }

    public boolean isPuzzleFinished(int roomIndex, int puzzleIndex) {
        RoomState s = getRoomState(roomIndex);
        return s.isPuzzleCompleted(puzzleIndex);
    }

    public boolean isRoomDone(int roomIndex) {
        boolean[] completed = getFinishedPuzzles(roomIndex);
        for (boolean b : completed) {
            if (!b) return false;
        }
        return true;
    }

    public void setPuzzleCompleted(int roomIndex, int puzzleIndex) {
        RoomState s = getRoomState(roomIndex);
        s.setPuzzleCompleted(puzzleIndex);
    }

    public void setPuzzleStarted(int roomIndex, int puzzleIndex) {
        RoomState s = getRoomState(roomIndex);
        s.setPuzzleVisited(puzzleIndex);
    }

    public Room getRoom(int roomIndex) {
        return gameState.get(roomIndex);
    }

    public void incRoomIter(int roomIndex) {
        RoomState r = getRoomState(roomIndex);
        r.incRoomIter();   
    }

    public int getNumRemainingPuzzles(int roomIndex) {
        RoomState r = getRoomState(roomIndex);
        int i = 0;
        for (boolean puzzle : r.getPuzzlesCompleted()) {
            if (puzzle == false) {i++;}
        }
        return i;
    }


}
