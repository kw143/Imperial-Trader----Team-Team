package edu.gatech.cs2340.team.imperialtrader.views;

/**
 * Interface for clicks relating to trades
 */

interface TradeClickListener {

    /**
     * Listener for onInventoryClicked
     */
    void onInventoryClicked();

    /**
     * Listener for toBuyClicked
     */
    void toBuyClicked();

    /**
     * Listener for toSellClicked
     */
    void toSellClicked();
}
