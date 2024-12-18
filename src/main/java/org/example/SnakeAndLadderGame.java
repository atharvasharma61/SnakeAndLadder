package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SnakeAndLadderGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer snakes = scanner.nextInt();

        Map<Integer, Integer> snakesMap = new HashMap<>();
        for (int i = 0; i < snakes; i++) {
            snakesMap.put(scanner.nextInt(), scanner.nextInt());
        }

        Map<Integer, Integer> laddersMap = new HashMap<>();
        Integer ladders = scanner.nextInt();
        for (int i = 0; i < ladders; i++) {
            laddersMap.put(scanner.nextInt(), scanner.nextInt());
        }
        Integer playersCount = scanner.nextInt();
        List<Player> players = new ArrayList<>();
        for(int i = 0 ;i < playersCount; i++){
            String name = scanner.nextLine();
            players.add(new Player(name, 0));
        }
        startSnakeAndLadderGame(players, snakesMap, laddersMap);
    }

    private static void startSnakeAndLadderGame(List<Player> players, Map<Integer, Integer> snakesMap, Map<Integer, Integer> laddersMap) {
        while (true) {
            for (Player player : players) {
                Integer diceValue = Dice.roll();
                Integer newPosition = player.getPosition() + diceValue;
                if (newPosition > 100) {
                    continue;
                }
                while (snakesMap.containsKey(newPosition) || laddersMap.containsKey(newPosition)) {
                    if (snakesMap.containsKey(newPosition)) {
                        newPosition = snakesMap.get(newPosition);
                    }
                    if (laddersMap.containsKey(newPosition)) {
                        newPosition = laddersMap.get(newPosition);
                    }
                }
                player.setPosition(newPosition);
                if (newPosition == 100) {
                    System.out.println(player.getName() + " wins the game");
                    return;
                }
                System.out.println(player.getName() + " rolled a " + diceValue + " and moved from " + (newPosition - diceValue) + " to " + newPosition);
            }
        }
    }
}

/*

9
62 5
33 6
49 9
88 16
41 20
56 53
98 64
93 73
95 75
8
2 37
27 46
10 32
51 68
61 79
65 84
71 91
81 100
2
Gaurav
Sagar

* */