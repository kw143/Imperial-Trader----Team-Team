package edu.gatech.cs2340.team.imperialtrader.views;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import edu.gatech.cs2340.team.imperialtrader.R;
import edu.gatech.cs2340.team.imperialtrader.entity.Player;
import edu.gatech.cs2340.team.imperialtrader.entity.Region;
import edu.gatech.cs2340.team.imperialtrader.viewmodels.PlayerViewModel;
import edu.gatech.cs2340.team.imperialtrader.viewmodels.EventViewModel;
import edu.gatech.cs2340.team.imperialtrader.viewmodels.RegionViewModel;


import static edu.gatech.cs2340.team.imperialtrader.entity.Good.NARCOTICS;

/**
 * Fragment for events
 */
public class EventFrag extends Fragment {

    private EventClickListener eventClickListener;

    @Override
    /**
     * onAttach method
     * @paramm context
     */
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            eventClickListener = (EventClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " must implement OnHeadlineSelectedListener");
        }
    }

    private PlayerViewModel playerViewModel;
    private Player player;

    @Override
    /**
     * onCreateView method
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event,
                container, false);
        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        RegionViewModel regionViewModel = ViewModelProviders.of(this).get(RegionViewModel.class);
        player = playerViewModel.getPlayer();
        EventViewModel eventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);

        TextView eventMessage = view.findViewById(R.id.eventMessage);
        TextView deathMessage = view.findViewById(R.id.deathMessage);

        Button proceedButton = view.findViewById(R.id.proceedButton);
        String returnMessage = eventViewModel.randomEvent();

        final int delay = 5000;

        if ("Pirates".equals(returnMessage)) {
            eventMessage.setText("A ship of pirates approaches you! They demand that you "
                    + "surrender half of your money.");

            Button fightButton = view.findViewById(R.id.fightButton);
            Button obligeButton = view.findViewById(R.id.obligeButton);
            TextView resultMessage = view.findViewById(R.id.resultMessage);
            proceedButton.setVisibility(View.INVISIBLE);
            TextView question = view.findViewById(R.id.question);
            question.setVisibility(View.VISIBLE);
            ImageView line = view.findViewById(R.id.staticLine);
            line.setVisibility(View.VISIBLE);


            fightButton.setText("Fight!");
            fightButton.setVisibility(View.VISIBLE);
            fightButton.setOnClickListener(v -> {
                final int profit = 1000;
                final int damage = 40;
                player.setMoney(player.getMoney() + profit);
                player.getShip().setHealth(player.getShip().getHealth() - damage);
                playerViewModel.updatePlayer(player);
                resultMessage.setText("You defeated the pirates and stole $1000, "
                        + "but your ship lost a lot of health.");
                resultMessage.setVisibility(View.VISIBLE);
                fightButton.setVisibility(View.INVISIBLE);
                obligeButton.setVisibility(View.INVISIBLE);
                question.setVisibility(View.INVISIBLE);
                line.setVisibility(View.INVISIBLE);
                if (player.getShip().getHealth() <= 0) {
                    deathMessage.setVisibility(View.VISIBLE);
                    player = new Player("default");
                    player.setCurRegion(regionViewModel.getHomeRegion(), -1);
                    playerViewModel.updatePlayer(player);
                    new android.os.Handler().postDelayed(
                            () -> eventClickListener.toHomeClicked(),
                            delay);
                } else {
                    proceedButton.setVisibility(View.VISIBLE);
                }
            });

            obligeButton.setText("Oblige.");
            obligeButton.setVisibility(View.VISIBLE);
            obligeButton.setOnClickListener(v -> {

                int halfMoney = player.getMoney() / 2;
                
                player.setMoney(player.getMoney() - halfMoney);
                player.getShip().setHealth(player.getShip().getHealth() - 10);
                playerViewModel.updatePlayer(player);
                resultMessage.setText("You surrendered. The pirates took $"
                        + halfMoney + " and did 10 damage to your ship.");
            
                resultMessage.setVisibility(View.VISIBLE);
                fightButton.setVisibility(View.INVISIBLE);
                obligeButton.setVisibility(View.INVISIBLE);
                question.setVisibility(View.INVISIBLE);
                line.setVisibility(View.INVISIBLE);
                if (player.getShip().getHealth() <= 0) {
                    deathMessage.setVisibility(View.VISIBLE);
                    player = new Player("default");
                    player.setCurRegion(regionViewModel.getHomeRegion(), -1);
                    playerViewModel.updatePlayer(player);
                    new android.os.Handler().postDelayed(
                            () -> eventClickListener.toHomeClicked(),
                            delay);
                } else {
                    proceedButton.setVisibility(View.VISIBLE);
                }
            });

        } else if ("Police".equals(returnMessage)) {
            // POLICE EVENT
            eventMessage.setText("You have been approached by the police. "
                    + "They want to check your bags.");

            Button fightButton = view.findViewById(R.id.fightButton);
            Button obligeButton = view.findViewById(R.id.obligeButton);
            TextView resultMessage = view.findViewById(R.id.resultMessage);
            proceedButton.setVisibility(View.INVISIBLE);
            TextView question = view.findViewById(R.id.question);
            question.setVisibility(View.VISIBLE);
            ImageView line = view.findViewById(R.id.staticLine);
            line.setVisibility(View.VISIBLE);


            fightButton.setText("Fight!");
            fightButton.setVisibility(View.VISIBLE);
            fightButton.setOnClickListener(v -> {
                final int profit = 300;
                final int damage = 20;
                player.setMoney(player.getMoney() + profit);
                player.getShip().setHealth(player.getShip().getHealth() - damage);
                playerViewModel.updatePlayer(player);
                resultMessage.setText("You defeated the police and stole $300, "
                        + "but your ship lost health.");
                resultMessage.setVisibility(View.VISIBLE);
                fightButton.setVisibility(View.INVISIBLE);
                obligeButton.setVisibility(View.INVISIBLE);
                question.setVisibility(View.INVISIBLE);
                line.setVisibility(View.INVISIBLE);
                if (player.getShip().getHealth() <= 0) {
                    deathMessage.setVisibility(View.VISIBLE);
                    player = new Player("default");
                    player.setCurRegion(regionViewModel.getHomeRegion(), -1);
                    playerViewModel.updatePlayer(player);
                    new android.os.Handler().postDelayed(
                            () -> eventClickListener.toHomeClicked(),
                            delay);
                } else {
                    proceedButton.setVisibility(View.VISIBLE);
                }
            });

            obligeButton.setText("Oblige.");
            obligeButton.setVisibility(View.VISIBLE);
            obligeButton.setOnClickListener(v -> {
                if (player.getInventory().hasGood(NARCOTICS)) {
                    // confiscate narcotics and fine player
                    player.getInventory().subtract(NARCOTICS,
                            player.getInventory().getCount(NARCOTICS));
                    player.setMoney(player.getMoney() - 1000);
                    playerViewModel.updatePlayer(player);
                    resultMessage.setText("The police confiscated your narcotics "
                            + "and fined you $1000!");
                } else {
                    resultMessage.setText("The police didn't find anything.");
                }
                resultMessage.setVisibility(View.VISIBLE);
                proceedButton.setVisibility(View.VISIBLE);
                fightButton.setVisibility(View.INVISIBLE);
                obligeButton.setVisibility(View.INVISIBLE);
                question.setVisibility(View.INVISIBLE);
                line.setVisibility(View.INVISIBLE);
            });
        } else {
            // not police or pirates
            eventMessage.setText(returnMessage);
            if (player.getShip().getHealth() <= 0) {
                deathMessage.setVisibility(View.VISIBLE);
                player = new Player("default");
                player.setCurRegion(regionViewModel.getHomeRegion(), -1);
                playerViewModel.updatePlayer(player);
                new android.os.Handler().postDelayed(
                        () -> eventClickListener.toHomeClicked(),
                        delay);
            }
        }


        proceedButton.setText("Proceed");
        proceedButton.setOnClickListener(v -> eventClickListener.travelClicked());

        return view;
    }
}