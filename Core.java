import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

public class Core {

    public int width, height, nBuildings, nAntennas, reward;
    ArrayList<Building> buildings = new ArrayList<Building>();
    ArrayList<Antenna> antennas = new ArrayList<Antenna>();

    public ArrayList<String> execute(){
        
        ArrayList<String> output = new ArrayList<String>();
        antennas.sort(null);
        buildings.sort(null);
        ArrayList<Antenna> placedAntennas = new ArrayList<Antenna>();
        for (Building b : buildings) {
            if (antennas.size() == 0)
                break;
            boolean affected = false;
            for (Antenna a : placedAntennas) {
                if (Math.abs(a.x - b.x) + Math.abs(a.y - b.y) <= a.range) {
                    affected = true;
                    b.shared = true;
                    b.score = b.speed*a.speed - b.latency * (Math.abs(a.x - b.x) + Math.abs(a.y - b.y));
                    break;
                }
            }
            if (!affected) {
                Antenna ant = antennas.get(0);
                ant.x = b.x;
                ant.y = b.y;
                antennas.remove(0);
                placedAntennas.add(ant);
            }
        }

        for (Building b : buildings) {
            if (antennas.size() == 0) {
                break;
            }
            if (b.shared) {
                Antenna ant = antennas.get(0);
                if (b.speed*ant.speed - b.latency * (Math.abs(ant.x - b.x) + Math.abs(ant.y - b.y)) > b.score) {
                    ant.x = b.x;
                    ant.y = b.y;
                    antennas.remove(0);
                    placedAntennas.add(ant);
                }
            }
        }
        output.add(placedAntennas.size()+"");
        for (Antenna a : placedAntennas) {
            output.add(a.id + " " + a.x + " " + a.y);
        }
        return output;
    }
}
