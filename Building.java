import java.util.Comparator;

public class Building {

    public int x, y, latency, speed;
    public boolean shared = false;
    public int score;

    public Building(int x, int y, int latency, int speed){
        this.x = x;
        this.y = y;
        this.latency = latency;
        this.speed = speed;
    }
    
    public static class CmpX implements Comparator<Building> {
        public int compare(Building a, Building b) {
            return a.x - b.x;
        }
    }
    public static class CmpY implements Comparator<Building> {
        public int compare(Building a, Building b) {
            return a.y - b.y;
        }
    }
}