package edu.gatech.cs2340.team.imperialtrader.views;

public interface RegionClickListener {

    /**
     * Listener for mapClicked
     */
    void mapClicked();

    /**
     * listen for when new event should happen
     */
    void toEventClicked();

    /**
     * listen for when a button is clicked
     * to transition to port
     */
    void toPortClicked();
}
