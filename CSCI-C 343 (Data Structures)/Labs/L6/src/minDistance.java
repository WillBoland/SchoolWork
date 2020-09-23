import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

class minDistance {
    final static int GAP = 2;
    final static int MATCH = 0;
    final static int MISMATCH = 1;

    enum BASE { A, T, G, C }

    // recursive min distance
    static int minDistance (List<BASE> dna1, List<BASE> dna2) {
        try {
            int current = dna1.getFirst() == dna2.getFirst() ? MATCH : MISMATCH;
            int d1 = current + minDistance(dna1.getRest(), dna2.getRest());
            int d2 = GAP + minDistance(dna1.getRest(), dna2);
            int d3 = GAP + minDistance(dna1, dna2.getRest());
            return d1 < d2 ? d1 : d2;
        }
        catch (EmptyListE e) {
            if (dna1.isEmpty()) return GAP * dna2.length();
            else return GAP * dna1.length();
        }
    }

    static Map<Pair<List<BASE>,List<BASE>>,Integer> minDistanceMemo = new WeakHashMap<>();

    // memoized (top down) min distance
    static int mminDistance (List<BASE> dna11, List<BASE> dna21) {
        return minDistanceMemo.computeIfAbsent(new Pair<>(dna11, dna21), p -> {
            List<BASE> dna1 = p.getFirst();
            List<BASE> dna2 = p.getSecond();
            try {
                int current = dna1.getFirst() == dna2.getFirst() ? MATCH : MISMATCH;
                int d1 = current + mminDistance(dna1.getRest(), dna2.getRest());
                int d2 = GAP + mminDistance(dna1.getRest(), dna2);
                int d3 = GAP + mminDistance(dna1, dna2.getRest());
                return Math.min(d1,Math.min(d2,d3));
            }
            catch (EmptyListE e) {
                if (dna1.isEmpty()) return GAP * dna2.length();
                else return GAP * dna1.length();
            }
        });
    }

    // bottom up min distance
    static int buminDistance (List<BASE> dna11, List<BASE> dna21) {
        ArrayList<BASE> dna1 = dna11.toArrayList();
        ArrayList<BASE> dna2 = dna21.toArrayList();
        Map<Pair<Integer, Integer>, Integer> hash = new HashMap<>();

        //fills the hash map base cases
        for(int i = 0; i <= dna1.size(); i += 1)
        {
            hash.put(new Pair<>(i, 0), i * 2);
        }
        //still filling
        for(int i = 0; i <= dna2.size(); i += 1)
        {
            hash.put(new Pair<>(0, i), i * 2);
        }

        //go through starting at 0,0
        for(int i = 1; i <= dna1.size() - 1; i += 1) {
            for(int j = 1; j <= dna2.size() - 1; j += 1) {
                int current = (dna1.get(i) == dna2.get(j) ? MATCH : MISMATCH);
                int d1 = current + hash.get(new Pair<>(i - 1, j - 1)); //diagonal check
                int d2 = GAP + hash.get(new Pair<>(i, j - 1)); //up check
                int d3 = GAP + hash.get(new Pair<>(i - 1, j)); //down check

                hash.put(new Pair<>(i, j), Math.min(d1, Math.min(d2, d3)));
            }
        }

        return hash.get(new Pair<>(dna1.size() - 1, dna2.size() - 1));
    }
}
