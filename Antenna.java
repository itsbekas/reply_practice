public class Antenna implements Comparable<Antenna> {

    public int x, y, range, speed, id;

    public Antenna(int range, int speed, int id) {
        this.range = range;
        this.speed = speed;
        this.id = id;
    }

    public int compareTo(Antenna a) {
        return a.speed - speed;
    }

    public String toString() {
        return range + " " + speed;
    }
}