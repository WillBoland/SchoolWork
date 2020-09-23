import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

class LineWrap {
    private int lineWidth;

    LineWrap (int lineWidth) {
        this.lineWidth = lineWidth;
    }

    //-------------------------------------------------------------------------
    /**
     * The greedy implementation keeps consuming words from the given list
     * of words while keeping track of how much space is left on the current line.
     * If the current word with a preceding space would fit on the current line, the
     * algorithm continues with the remaining words and an adjusted space.
     * If the word preceded by a space does not fit on the current line, a new line
     * is inserted instead and the algorithm continues with the rest of the words and
     * an adjusted space.
     */
    String greedy (List<String> words, int space) {
        //try to add the words. If we fail, we are at end of string, so return ""
        try {
            if(words.getFirst().length() + 1 <= space) { //we can add the word, so do that.
                return " " + words.getFirst() + greedy(words.getRest(), space - (words.getFirst().length() + 1));
            } else { //we cannot add word on this line, try again on a new line
                return "\n" + words.getFirst() + greedy(words.getRest(), lineWidth - words.getFirst().length());
            }
        } catch (NoSuchElementE e) {
            return ""; //we are at end of input
        }
    }

    /**
     * A simple way of running the greedy algorithm
     */
    static String runGreedy (String s, int lineWidth) throws NoSuchElementE {
        List<String> words = List.fromArray(s.split("\\s"));
        LineWrap wrap = new LineWrap(lineWidth);
        String w = words.getFirst();
        String para = w + wrap.greedy(words.getRest(), lineWidth - w.length());
        return para;
    }

    //-------------------------------------------------------------------------

    /** A hash table for use with the top down dynamic programming solution */
    static Map<Pair<List<String>,Integer>,Pair<String,Integer>> hash = new HashMap<>();

    /**
     * The greedy algorithm always adds words to the current line as long as they
     * would fit. The dynamic programming algorithm instead considers two options
     * for each word: add it to the current line, or insert a new line before
     * the word. For each possibility, an estimate of "badness" is calculated
     * and the best option is chosen. The "badness" of a solution is the sum
     * of the cubes of the amount of space left on each line (except the last line).
     * For example, if the line width is 6 and we have the following text:
     *
     * 1234
     * 12 45
     * 123
     * 12
     *
     * then we calculate the badness as follows: the first line has 2 unused spaces
     * at the end, the next line has 1, and the third has 3. The final line is perfect
     * by definition. So the "badness" estimate is:
     * 2^3 + 1^3 + 3^3 = 8 + 1 + 27 = 36
     *
     * In contrast if the text was:
     *
     * 1234
     * 12 45
     * 123 12
     *
     * the "badness" would be: 2^3 + 1^3 = 8 + 1 = 9
     *
     * so we prefer the second arrangement.
     *
     * I strongly suggest you first write a plain recursive solution and test it on the small
     * example (test1). Once that words properly, you can add the hash table management to
     * get your final dynamic programming solution. Without the hash table, the algorithm
     * will really not work on even a moderate size paragraph.
     */
    Pair<String,Integer> dpTD(List<String> words, int space) {
        return hash.computeIfAbsent(new Pair<>(words, space), p -> {
            //we use p as a running version of words rather than words itself
            try {
                if(p.getFst().getFirst().length() + 1 <= space) {
                    //setup our pairs
                    Pair<String, Integer> a = new Pair<String, Integer>( " " + p.getFst().getFirst() + dpTD(p.getFst().getRest(), space - (p.getFst().getFirst().length() + 1)).getFst(),
                            dpTD(p.getFst().getRest(), space - p.getFst().getFirst().length() - 1).getSnd()); //badness is just whatever our recursive call gives us

                    Pair<String, Integer> b = new Pair<String, Integer>("\n" + p.getFst().getFirst() + dpTD(p.getFst().getRest(), lineWidth - p.getFst().getFirst().length()).getFst(),
                            dpTD(p.getFst().getRest(), lineWidth - (p.getFst().getFirst().length())).getSnd() + space*space*space); //badness is our previous badness plus our recursive

                    return (a.getSnd() < b.getSnd() ? a : b); //compare the values of the 2 calls
                } else { //we cant add a space so just newline it
                    return new Pair<String, Integer>("\n" + p.getFst().getFirst() + dpTD(p.getFst().getRest(), lineWidth - p.getFst().getFirst().length()).getFst(),
                            dpTD(p.getFst().getRest(), lineWidth - (p.getFst().getFirst().length())).getSnd() + space*space*space); //badness is same as b
                }
            } catch (NoSuchElementE e) {
                return new Pair<String, Integer>("", 0); //we are at end of input, score 0 due to last line
            }
        });
    }


    /**
     * A simple way of running the dynamic programming algorithm
     */
    static String runDP (String s, int lineWidth) throws NoSuchElementE {
        hash = new WeakHashMap<>();
        List<String> words = List.fromArray(s.split("\\s"));
        LineWrap wrap = new LineWrap(lineWidth);
        String w = words.getFirst();
        Pair<String, Integer> sub = wrap.dpTD(words.getRest(), lineWidth - w.length());
        String para = w + sub.getFst();
        return para;
    }

    //-------------------------------------------------------------------------
    /**
     * Here you are to implement the dynamic programming algorithm in a bottom-up fashion.
     * You should still use a hash table as shown below but its management is done
     * "manually" not implicitly when entering / exiting recursive calls.
     */
    static String dpBU (String s, int lineWidth) {
        Map<Pair<Integer,Integer>,Pair<String,Integer>> hash = new HashMap<>();
        String[] words = s.split("\\s"); //split like we did above
        String firstWord = words[0]; //save first word like we did above
        words = Arrays.copyOfRange(words, 1, words.length); //rest of words like we did above
        //fill it up. Index, Space left
        for(int i = 0; i <= lineWidth; i += 1) {
            hash.put(new Pair<>(words.length, i), new Pair<>("", 0)); //base
        }
        //loop through starting at bottom right
        for(int i = words.length - 1; i >= 0; i -= 1) { //index
            for (int j = 0; j <= lineWidth; j += 1) { //space left
                //can add word
                if (words[i].length() + 1 <= j) {
                    //make space
                    Pair<String, Integer> sameL = new Pair<>(" " + words[i] + hash.get(new Pair<>(i + 1, j - words[i].length() - 1)).getFst(), hash.get(new Pair<>(i + 1, j - words[i].length() - 1)).getSnd());
                    //make newline
                    Pair<String, Integer> newL = new Pair<>("\n" + words[i] + hash.get(new Pair<>(i + 1, lineWidth - words[i].length())).getFst(), hash.get(new Pair<>(i + 1, lineWidth - words[i].length())).getSnd() + j*j*j);

                    //compare them and break the ties
                    if (sameL.getSnd() < newL.getSnd()) {
                        hash.put(new Pair<>(i, j), sameL);
                    } else {
                        hash.put(new Pair<>(i, j), newL);
                    }
                } else { //just do newline
                    Pair<String, Integer> newL = new Pair<>("\n" + words[i] + hash.get(new Pair<>(i + 1, lineWidth - words[i].length())).getFst(), hash.get(new Pair<>(i + 1, lineWidth - words[i].length())).getSnd() + j*j*j);
                    hash.put(new Pair<>(i, j), newL);
                }
            }
        }
        //word we took and the final results
        return firstWord + hash.get(new Pair<>(0, lineWidth - firstWord.length())).getFst();
    }
}