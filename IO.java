import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class IO extends Thread {

    private Scanner _reader;
    private FileWriter _output;
    private Core _core;
    private String _name;
    private File file;

    public IO(String name) throws IOException {
        file = new File(name);
        _name = name;
        _core = new Core();
        _reader = new Scanner(file);
        _output = new FileWriter(name.substring(0, name.length()-3)+"-output.txt");
    }

    public void readFile() throws IOException {
        _core.width = _reader.nextInt();
        _core.height = _reader.nextInt();
        _core.nBuildings = _reader.nextInt();
        _core.nAntennas = _reader.nextInt();
        _core.reward = _reader.nextInt();
        for (int i = 0; i < _core.nBuildings; i++) {
            int x = _reader.nextInt();
            int y = _reader.nextInt();
            int latency = _reader.nextInt();
            int speed = _reader.nextInt();
            Building b = new Building(x, y, latency, speed);
            _core.buildings.add(b);
        }

        for (int i = 0; i < _core.nAntennas; i++) {
            int range = _reader.nextInt();
            int speed = _reader.nextInt();
            Antenna a = new Antenna(range, speed, i);
            _core.antennas.add(a);
        }
        _reader.close();
    }

    public void run() {
        super.run();
        try {
            readFile();
            writeResults();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Deu merda");
        } 
    }
    
    public void writeResults() throws IOException {
        for (String s : _core.execute()) {
            _output.write(s+"\n");
        }
        _output.flush();
        _output.close();
        System.out.println("O output do ficheiro "+ _name + " foi concluido com sucesso.");
    }
}