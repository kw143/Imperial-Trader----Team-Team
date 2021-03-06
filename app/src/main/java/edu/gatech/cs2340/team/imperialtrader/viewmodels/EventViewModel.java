package edu.gatech.cs2340.team.imperialtrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.Random;

import edu.gatech.cs2340.team.imperialtrader.model.Model;
import edu.gatech.cs2340.team.imperialtrader.entity.Player;
import edu.gatech.cs2340.team.imperialtrader.model.PlayerInteractor;

import static edu.gatech.cs2340.team.imperialtrader.entity.Good.FIREARMS;
import static edu.gatech.cs2340.team.imperialtrader.entity.Good.WATER;

/**
 * Event View Model to create random events
 */
public class EventViewModel extends AndroidViewModel {
    private final PlayerInteractor model;
    private final Player player;

    /**
     * EventViewModel constructor
     * @param application Application
     */
    public EventViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance().getPlayerInteractor();
        player = model.getPlayer();
    }

    /**
     * Random event generator method
     * @return String describing random event
     */
    public String randomEvent() {
        Random random = new Random();
        final int bound = 12;
        final int number = random.nextInt(bound);

        if (number == 3) {
            player.getShip().setHealth(player.getShip().getHealth() - 10);
            updatePlayer(player);
            return "Your ship went through a bad storm and lost 10 health.";
        } else if (number == 4) {
            if (player.getInventory().hasGood(WATER)) {
                player.getInventory().subtract(WATER, player.getInventory().getCount(WATER) / 2);
            }
            updatePlayer(player);
            return "Your ship went through the Bermuda Triangle and you lost half of your water.";
        } else if (number == 5) {
            player.setMoney(player.getMoney() + 1000);
            updatePlayer(player);
            return "You discovered a treasure chest and found $1000 worth of coins inside.";
        } else if (number == 6) {
            if (player.getInventory().hasGood(FIREARMS)) {
                player.getInventory().subtract(FIREARMS, player.getInventory().getCount(FIREARMS));
            }
            updatePlayer(player);
            return "A rival ship attacked you and you had to use all "
                    + "of your firearms to fight them off.";
        } else if (number == 7) {
            final int addQuantity = 40;
            player.getInventory().add(FIREARMS, addQuantity);
            updatePlayer(player);
            return "A friendly ship approached you and gifted you 40 firearms "
                    + "to fight off other ships.";
        } else if ((number == 8) || (number == 9)) {
            return "Police";
        } else if (number > 9) {
            return "Pirates";
        } else {
            return "You arrived safely at your destination.";
        }
    }

    /**
     * Update player method
     * @param p Player
     */
    private void updatePlayer(Player p) {
        model.updatePlayer(p);
    }
}