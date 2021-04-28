package algo;

import java.util.HashMap;
import java.util.Map;

public class CoinChange {

    public static void main(String[] args) {
        System.out.println(new CoinChange().coinChange(new int[]{186,419,83,408}, 6249));
    }

    public int coinChange(int[] coins, int amount) {
        if (amount == 0)
            return 0;
        if (coins.length < 1) return -1;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < coins.length; i++) {
            map.put(coins[i], 1);
        }
        return minOf(coins, amount, map);
    }

    private int minOf(int[] coins, int amount, Map<Integer, Integer> map) {
        if (amount < 0)
            return -1;
        if (amount == 0)
            return 0;
        Integer e = map.get(amount);
        if (e != null)
            return e;

        int result = Integer.MAX_VALUE;
        for (int coin : coins) {
            int min = minOf(coins, amount - coin, map);
            if (min >= 0) {
                min ++;
                if (min < result) result = min;
            }
        }

        result = result == Integer.MAX_VALUE ? -1 : result;
        map.put(amount, result);
        return result;
    }
}
