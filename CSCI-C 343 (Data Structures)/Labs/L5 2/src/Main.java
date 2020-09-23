import java.util.HashMap;

class NoChange extends Exception {}

public class Main {

    public static void main (String[] args) throws NoChange {
        List<Coin> scoins =
                new NodeL<>(new Coin(25),
                        new NodeL<>(new Coin(10),
                                new NodeL<>(new Coin(5),
                                        new NodeL<>(new Coin(1),
                                                new EmptyL<>()))));

        System.out.println();

        HashMap<Coin,Integer> hash = new HashMap<>();
        makeChangeGreedy(hash,3, scoins);
        System.out.printf("Making change for %d using usual coins: %s%n",3, hash);

        hash.clear();
        makeChangeGreedy(hash,7, scoins);
        System.out.printf("Making change for %d using usual coins: %s%n",7, hash);

        hash.clear();
        makeChangeGreedy(hash,44, scoins);
        System.out.printf("Making change for %d using usual coins: %s%n",44, hash);

        hash.clear();
        makeChangeGreedy(hash,117, scoins);
        System.out.printf("Making change for %d using usual coins: %s%n",117, hash);

        System.out.println();

        List<Coin> wcoins =
                new NodeL<>(new Coin(17),
                        new NodeL<>(new Coin(7),
                                new NodeL<>(new Coin(3),
                                        new EmptyL<>())));

        hash.clear();
        makeChangeGreedy(hash,3, wcoins);
        System.out.printf("Making change for %d using weird coins: %s%n",3, hash);

        hash.clear();
        makeChangeGreedy(hash,7, wcoins);
        System.out.printf("Making change for %d using weird coins: %s%n",7, hash);

        hash.clear();
        makeChangeGreedy(hash,44, wcoins);
        System.out.printf("Making change for %d using weird coins: %s%n",44, hash);

        try {
            hash.clear();
            makeChangeGreedy(hash, 117, wcoins);
            System.out.printf("Making change for %d using weird coins: %s%n", 117, hash);
        }
        catch (NoChange e) {
            System.out.printf("Failed to make change for %d using weird coins%n", 117);
        }

        System.out.println();

        hash.clear();
        makeChangeSearch(hash,3, wcoins);
        System.out.printf("Making change for %d using weird coins: %s%n",3, hash);

        hash.clear();
        makeChangeSearch(hash,7, wcoins);
        System.out.printf("Making change for %d using weird coins: %s%n",7, hash);

        hash.clear();
        makeChangeSearch(hash,44, wcoins);
        System.out.printf("Making change for %d using weird coins: %s%n",44, hash);

        hash.clear();
        makeChangeSearch(hash,117, wcoins);
        System.out.printf("Making change for %d using weird coins: %s%n",117, hash);

    }


    // given list of coins sorted from largest to smallest, add up to the given amount
    // use the greedy approach to do this
    static void makeChangeGreedy (HashMap<Coin,Integer> result, int amount, List<Coin> coins) throws NoChange {
        Coin current = new Coin(0);
        try { current = coins.getFirst(); } catch (Exception e) { throw new NoChange(); }

        if(amount == 0) { return; }

        //we can add the coin
        if(current.getValue() <= amount) {
            result.put(current, (result.containsKey(current) ? result.get(current) + 1 : 1));
            makeChangeGreedy(result, amount - current.getValue(), coins);
        } else {
            try { makeChangeGreedy(result, amount, coins.getRest()); } catch (Exception e) { throw new NoChange(); }
        }
    }

    // given list of coins sorted from largest to smallest, add up to the given amount
    // use dynamic programming to do this
    static void makeChangeSearch (HashMap<Coin,Integer> result, int amount, List<Coin> coins) throws NoChange {
        Coin current = new Coin(0);
        try { current = coins.getFirst(); } catch (Exception e) { throw new NoChange(); }

        if(amount == 0) { return; }

        //we can add the coin
        if(current.getValue() <= amount) {
            try {
                makeChangeSearch(result, amount - current.getValue(), coins);
                result.put(current, (result.containsKey(current) ? result.get(current) + 1 : 1));
            } catch (NoChange e) {
                try { makeChangeSearch(result, amount, coins.getRest()); } catch (Exception er) { throw new NoChange(); }
            }
        } else {
            try { makeChangeSearch(result, amount, coins.getRest()); } catch (Exception e) { throw new NoChange(); }
        }
    }
}
