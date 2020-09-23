import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

public class SeamCarving {
    private int[] pixels;
    private int type, height, width;

    //The key to the hash table is a pixel position. The value stored at each key
    // is the "seam" that starts with this pixel all the way to the bottom
    // of the image and its cost.
    public Map<Position, Pair<List<Position>, Integer>> hash = new WeakHashMap<>();

<<<<<<< HEAD
    // Field getters
    int[] getPixels () { return pixels; }
    int getHeight () { return height; }
    int getWidth () { return width; }
=======
    int[] getPixels() {
        return pixels;
    }

    int getType() {
        return type;
    }

    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9

    // Read and write images

    void readImage(String filename) throws IOException {
        BufferedImage image = ImageIO.read(new File(filename));
        type = image.getType();
        height = image.getHeight();
        width = image.getWidth();
        pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);
    }

    void writeImage(String filename) throws IOException {
        BufferedImage image = new BufferedImage(width, height, type);
        image.setRGB(0, 0, width, height, pixels, 0, width);
        ImageIO.write(image, "jpg", new File(filename));
    }

    // Accessing pixels and their neighbors

<<<<<<< HEAD
    /***
     * By convention, h is the vertical index and w and the horizontal index.
     * The array of pixels is stored as follows:
     * [(0,0), (0,1), (0,2), ... (0,width-1), (1,0), (1,1), (1,2), ... (1,width-1), ...]
     * Red, Green, Blue (c.getColor())
     */
=======
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    Color getColor(int h, int w) {
        int pixel = pixels[w + h * width];
        return new Color(pixel, true);
    }

<<<<<<< HEAD
    /**
     * This method takes the position of a pixel (h,w) and returns a list of its
     * neighbors' positions in the horizontal and vertical directions.
     * In the general case, these would be at  positions:
     * (h+1,w), (h-1,w), (h,w+1), (h,w-1).
     * Of course, care must be taken when dealing with pixels along the boundaries.
     */
    ArrayList<Position> getHVneighbors(int h, int w) {
        final int maxWidth = width - 1;
        final int maxHeight = height - 1;
        //calculated positions
        Position below = new Position(h + 1, w);
        Position above = new Position(h - 1, w);
        Position right = new Position(h, w + 1);
        Position left = new Position(h, w - 1);

        //we are in upper left corner
        if(h == 0 && w == 0)
            return new ArrayList<Position>(Arrays.asList(new Position[]{right, below}));

        //we are in upper right corner
        if(h == 0 && w == maxWidth)
            return new ArrayList<Position>(Arrays.asList(new Position[]{left, below}));

        //we are in bottom left corner
        if(h == maxHeight && w == 0)
            return new ArrayList<Position>(Arrays.asList(new Position[]{right, above}));

        //we are in bottom right corner
        if(h == maxHeight && w == maxWidth)
            return new ArrayList<Position>(Arrays.asList(new Position[]{left, above}));

        //we are not in a corner, are we in a side?
        //top
        if(h == 0)
            return new ArrayList<Position>(Arrays.asList(new Position[]{left, right, below}));

        //bottom
        if(h == maxHeight)
            return new ArrayList<Position>(Arrays.asList(new Position[]{left, right, above}));

        //left
        if(w == 0)
            return new ArrayList<Position>(Arrays.asList(new Position[]{right, below, above}));
        //right
        if(w == maxWidth)
            return new ArrayList<Position>(Arrays.asList(new Position[]{left, below, above}));

        //we are in a normal space
        return new ArrayList<Position>(Arrays.asList(new Position[]{left, right, below, above}));
    }

    /**
     * This method takes the position of a pixel (h,w) and returns a list of its
     * neighbors' positions that are below and touching it.
     * In the general case, these would be at  positions:
     * (h+1,w-1), (h+1,w), (h+1,w+1)
     * Of course, care must be taken when dealing with pixels along the boundaries.
     * Below neighbors are:
     *          X
     *        / | \
     *        X X X
     */
    ArrayList<Position> getBelowNeighbors(int h, int w) {
        final int maxWidth = width - 1;
        final int maxHeight = height - 1;
        //calculated positions
        Position diagLeft = new Position(h + 1, w - 1);
        Position middle = new Position(h + 1, w);
        Position diagRight = new Position(h + 1, w + 1);

        //bottom, so no more below neighbors
        if(h == maxHeight)
            return new ArrayList<Position>();

        //left
        if(w == 0)
            return new ArrayList<Position>(Arrays.asList(new Position[]{middle, diagRight}));
        //right
        if(w == maxWidth)
            return new ArrayList<Position>(Arrays.asList(new Position[]{middle, diagLeft}));

        //we are in a normal space
        return new ArrayList<Position>(Arrays.asList(new Position[]{middle, diagRight, diagLeft}));
    }

    /**
     * This method takes the position of a pixel (h,w) and computes its 'energy'
     * which is an estimate of how it differs from its neighbors. The computation
     * is as follows. First, using the method getColor, get the colors of the pixel
     * and all its neighbors in the horizontal and vertical dimensions. The energy
     * is the sum of the squares of the differences along each of the RGB components
     * of the color. For example, given two colors c1 and c2 (for the current pixel
     * and one of its neighbors), we would compute this component of the energy as:
     *   square (c1.getRed() - c2.getRed()) +
     *   square (c1.getGreen() - c2.getGreen()) +
     *   square (c1.getBlue() - c2.getBlue())
     * The total energy is this quantity summed over all the neighbors in the
     * horizontal and vertical dimensions.
     */
    int computeEnergy(int h, int w) {
        int sum = 0;
        for(Position neigh: getHVneighbors(h, w)) {
            Integer nH = neigh.getFirst();
            Integer nW = neigh.getSecond();
            sum += Math.pow(getColor(h, w).getRed() - getColor(nH, nW).getRed(), 2) +
                    Math.pow(getColor(h, w).getGreen() - getColor(nH, nW).getGreen(), 2) +
                    Math.pow(getColor(h, w).getBlue() - getColor(nH, nW).getBlue(), 2);
        }
        return sum;
=======
    ArrayList<Position> getHVneighbors(int h, int w) {
        ArrayList<Position> neighbors = new ArrayList<>();

        if (w == 0) neighbors.add(new Position(h, w + 1));
        else if (w + 1 == width) neighbors.add(new Position(h, w - 1));
        else {
            neighbors.add(new Position(h, w - 1));
            neighbors.add(new Position(h, w + 1));
        }

        if (h == 0) neighbors.add(new Position(h + 1, w));
        else if (h + 1 == height) neighbors.add(new Position(h - 1, w));
        else {
            neighbors.add(new Position(h + 1, w));
            neighbors.add(new Position(h - 1, w));
        }
        return neighbors;
    }

    ArrayList<Position> getBelowNeighbors(int h, int w) {
        ArrayList<Position> neighbors = new ArrayList<>();
        if (h + 1 == height) return neighbors;
        neighbors.add(new Position(h + 1, w));
        if (w == 0) {
            neighbors.add(new Position(h + 1, w + 1));
            return neighbors;
        } else if (w + 1 == width) {
            neighbors.add(new Position(h + 1, w - 1));
            return neighbors;
        } else {
            neighbors.add(new Position(h + 1, w + 1));
            neighbors.add(new Position(h + 1, w - 1));
            return neighbors;
        }
    }

    // Computing energy at given pixel

    int computeEnergy(int h, int w) {
        Color c = getColor(h, w);
        Function<Integer, Integer> sq = n -> n * n;
        int energy = 0;
        for (Position p : getHVneighbors(h, w)) {
            Color nc = getColor(p.getFirst(), p.getSecond());
            energy += sq.apply(nc.getRed() - c.getRed());
            energy += sq.apply(nc.getGreen() - c.getGreen());
            energy += sq.apply(nc.getBlue() - c.getBlue());
        }
        return energy;
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    // Find seam starting from (h,w) going down and return list of positions and cost
    // and then pick best seam starting from some position on the first row

    Map<Position, Pair<List<Position>, Integer>> hash = new WeakHashMap<>();

    Pair<List<Position>, Integer> findSeam(int h, int w) {
        return hash.computeIfAbsent(new Position(h, w), p -> {
<<<<<<< HEAD
            int energy = computeEnergy(h, w);   //computed energy on given h, w
            ArrayList<Position> neighbors = getBelowNeighbors(h, w); //get below neighbors
            //we are empty, so base case. Return < [<h,w>], energy >
            if(neighbors.isEmpty()) {
                //base case of a Pair containing a list with a position and empty list, then calculated energy
                Pair<List<Position>, Integer> base = new Pair<List<Position>, Integer>(new Node<Position>(p, new Empty<>()), energy);
                return base;
            } else { //we have things left, so consider each neighbor below
                //we have only 2 neighbors cause we are on a side
                if (neighbors.size() == 2) {
                    //setup a pair of findSeam(neighbors[0].getFirst, neighbors[0].getSecond) and compare to findSeam(neighbors[1].getFirst, neighbors[1].getSecond)
                    //the calculated cost is recursive + calculated energy from above. Returns the smallest energy for the seam
                    List<Position> p1 = new Node<>(p, findSeam(neighbors.get(0).getFirst(), neighbors.get(0).getSecond()).getFirst());
                    List<Position> p2 = new Node<>(p, findSeam(neighbors.get(1).getFirst(), neighbors.get(1).getSecond()).getFirst());

                    Pair<List<Position>, Integer> a = new Pair<>(p1, findSeam(neighbors.get(0).getFirst(), neighbors.get(0).getSecond()).getSecond() + energy);
                    Pair<List<Position>, Integer> b = new Pair<>(p2, findSeam(neighbors.get(1).getFirst(), neighbors.get(1).getSecond()).getSecond() + energy);
                    return min(a, b);
                }

                List<Position> p1 = new Node<>(p, findSeam(neighbors.get(0).getFirst(), neighbors.get(0).getSecond()).getFirst());
                List<Position> p2 = new Node<>(p, findSeam(neighbors.get(1).getFirst(), neighbors.get(1).getSecond()).getFirst());
                List<Position> p3 = new Node<>(p, findSeam(neighbors.get(2).getFirst(), neighbors.get(2).getSecond()).getFirst());

                //we have all 3 cause we are not on a side
                Pair<List<Position>, Integer> a = new Pair<>(p1, findSeam(neighbors.get(0).getFirst(), neighbors.get(0).getSecond()).getSecond() + energy);
                Pair<List<Position>, Integer> b = new Pair<>(p2, findSeam(neighbors.get(1).getFirst(), neighbors.get(1).getSecond()).getSecond() + energy);
                Pair<List<Position>, Integer> c = new Pair<>(p3, findSeam(neighbors.get(2).getFirst(), neighbors.get(2).getSecond()).getSecond() + energy);
                return min(a, min(b, c));
            }
        });
    }

    //returns the least costing of 2 given Pair scores
    Pair<List<Position>, Integer> min(Pair<List<Position>, Integer> a, Pair<List<Position>, Integer> b) {
        return (a.getSecond() <= b.getSecond() ? a : b);
    }

    /**
     * This next method is relatively short. It performs the following actions:
     *   - clears the hash table
     *   - iterate over the first row of the image, computing the seam
     *     from its position and returning the best one.
     */
    Pair<List<Position>,Integer> bestSeam() {
        //clears the hashtable
        this.hash.clear();

        //iterate over first row of image, find the best findSeam() compared to the current one
        Pair<List<Position>,Integer> best = findSeam(0, 0);
        for(int w = 0; w < width; w += 1) {
            Pair<List<Position>,Integer> current = findSeam(0, w); //get the seam for this current position in the first row
            best = (best.getSecond() <= current.getSecond() ? best : current); //if our best is less or equal to the current, keep it
        }
        return best;
    }

    /**
     * The last method puts its all together:
     *   - it finds the best seam
     *   - then it creates a new array of pixels representing an image of dimensions
     *     (height,width-1)
     *   - it then copies the old array pixels to the new arrays skipping the pixels
     *     in the seam
     *   - the method does not return anything: instead it updates the width and
     *     pixels instance variables to the new values.
     */
    void cutSeam () {
        //finds the best seam
        List<Position> positions = bestSeam().getFirst();

        //creates a new array of pixels representing an image of dimensions
        int[] newPixels = new int[(width - 1) * height]; //(height, width - 1)
        int newWidth = width - 1;

        /*
         * copies old array pixels to the new arrays skipping the pixels in the seam
         * pixels[w + h * width]; gets the pixel at the current width and height
         */
        try {
            //loop over height
            for (int h = 0; h < height; h += 1) {
                boolean skipped = false;
                //loop over width
                for (int w = 0; w < width; w += 1) {
                    //if we are not the same, add it. Otherwise, skip it by shifting over 1 width space
                    int posH = positions.getFirst().getFirst();
                    int posW = positions.getFirst().getSecond();
                    if (posH != h || posW != w) {
                        //did we skip already this row? if so, get offset by 1
                        newPixels[w + h * newWidth] = skipped ? pixels[w + 1 + h * width] : pixels[w + h * width];
                    } else {
                        skipped = true;
                        newPixels[w + h * newWidth] = pixels[w + 1 + h * width];
                    }
                }
                positions = positions.getRest();
            }
        } catch (Exception e) {

        }
        //updates width and pixels instance variables to the new values
        this.width = newWidth;
        this.pixels = newPixels;
=======

                    int energy = computeEnergy(h, w);
                    ArrayList<Position> below = getBelowNeighbors(h, w);
                    if (below.isEmpty()) {
                        return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), new Empty<>()),
                                energy);
                    } else {
                        if (below.size() == 1) {
                            Pair<List<Position>, Integer> s = findSeam(below.get(0).getFirst(), below.get(0).getFirst());
                            return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s.getFirst()),
                                    energy + s.getSecond());
                        } else if (below.size() == 2) {
                            Pair<List<Position>, Integer> s1 = findSeam(below.get(0).getFirst(), below.get(0).getSecond());
                            Pair<List<Position>, Integer> s2 = findSeam(below.get(1).getFirst(), below.get(1).getSecond());
                            if (s1.getSecond() <= s2.getSecond()) {
                                return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s1.getFirst()),
                                        energy + s1.getSecond());
                            } else {
                                return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s2.getFirst()),
                                        energy + s2.getSecond());
                            }
                        } else if (below.size() == 3) {
                            Pair<List<Position>, Integer> s1 = findSeam(below.get(0).getFirst(), below.get(0).getSecond());
                            Pair<List<Position>, Integer> s2 = findSeam(below.get(1).getFirst(), below.get(1).getSecond());
                            Pair<List<Position>, Integer> s3 = findSeam(below.get(2).getFirst(), below.get(2).getSecond());

                            if (s1.getSecond() <= s2.getSecond()) {
                                if (s1.getSecond() <= s3.getSecond()) {
                                    return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s1.getFirst()),
                                            energy + s1.getSecond());
                                } else {
                                    return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s3.getFirst()),
                                            energy + s3.getSecond());
                                }
                            } else {
                                if (s2.getSecond() <= s3.getSecond()) {
                                    return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s2.getFirst()),
                                            energy + s2.getSecond());
                                } else {
                                    return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s3.getFirst()),
                                            energy + s3.getSecond());
                                }
                            }
                        }
                    }
                    return null;
                });
    }



    Pair<List<Position>, Integer> bestSeam() {
        hash.clear();
        int cost = Integer.MAX_VALUE;
        List<Position> seam = new Empty<>();
        for (int w = 0; w < width; w++) {
            Pair<List<Position>, Integer> r = findSeam(0, w);
            if (r.getSecond() < cost) {
                seam = r.getFirst();
                cost = r.getSecond();
            }
        }
        return new Pair<>(seam, cost);
    }

    // Putting it all together; find best seam and copy pixels without that seam

    void cutSeam() {
        try {
            List<Position> seam = bestSeam().getFirst();
            int[] newPixels = new int[height * (width - 1)];
            for (int h = 0; h < height; h++) {
                int nw = 0;
                for (int w = 0; w < width; w++) {
                    if (seam.isEmpty()) {
                        newPixels[nw + h * (width - 1)] = pixels[w + h * width];
                    }
                    else {
                        Position p = seam.getFirst();
                        if (p.getFirst() == h && p.getSecond() == w) {
                            seam = seam.getRest();
                            nw--;
                        } else {
                            newPixels[nw + h * (width - 1)] = pixels[w + h * width];
                        }
                    }
                    nw++;
                }
            }
            width = width - 1;
            pixels = newPixels;
        } catch (EmptyListE e) {
            throw new Error("Bug");
        }
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }
}
