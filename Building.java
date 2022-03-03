public class Building implements Comparable<Building> {

    public int x, y, latency, speed;
    public boolean shared = false;
    public int score;

    public Building(int x, int y, int latency, int speed){
        this.x = x;
        this.y = y;
        this.latency = latency;
        this.speed = speed;
    }
    
    public int compareTo(Building b) {
        return b.speed - speed;
    }

}