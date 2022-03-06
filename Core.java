import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Core {

    public int width, height, nBuildings, nAntennas, reward, meanRange, meanSpeed;
    ArrayList<Building> buildingsX = new ArrayList<Building>();
    //ArrayList<Building> buildingsY = new ArrayList<Building>();
    ArrayList<Antenna> antennas = new ArrayList<Antenna>();


    public Core(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public ArrayList<String> execute(){
        antennas.sort(null);
        ArrayList<Antenna> placedAntennas = new ArrayList<Antenna>();
        Collections.sort(buildingsX, new Building.CmpX());
        int antennaEvery = (width-2)*(height-2)/nAntennas;
        for (Antenna a: antennas) {
            int biggestScore = 0;
            int bigX = -1, bigY = -1;
            boolean ss = false;
            for (int y = 1; y < height-1; y++) {
                for (int x = 1; x < width-1 ; x+=antennaEvery) {
                    int s = getScore(a,x,y);
                    if (s > biggestScore) {
                        for(Antenna pa : placedAntennas) {
                            if (pa.x == bigX && pa.y == bigY) {
                                ss = true;
                            }
                        }
                        if (!ss) {
                            biggestScore = s;
                            bigX = x;
                            bigY = y;
                        }
                    }
                }
            }
            if (bigX != -1) {
                a.x = bigX;
                a.y = bigY;
                placedAntennas.add(a);
            }
        }
        ArrayList<String> output = new ArrayList<String>();
        output.add(placedAntennas.size()+"");
        for (Antenna a : placedAntennas) {
            output.add(a.id + " " + a.x + " " + a.y);
        }
        return output;
    }
    
    public int getScore(Antenna a, int x, int y) {
        int score = 0;
        for(Building b : buildingsX) {
            if (Math.abs(b.x - a.x) + Math.abs(b.y - a.y) <= a.range) {
                score += (b.speed *a.speed - b.latency*(Math.abs(b.x - a.x) + Math.abs(b.y - a.y)));
            }
            else if (b.x > a.x+a.range) 
                return score;
        }
        return score;
    }
    
}