package ch.daplab.google.input;

import ch.daplab.google.dorwi.commands.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dori on 11.02.16.
 */
public class Drone {

    final int capacity;
    final int id;
    List<Command> commands = new ArrayList<>();

    public Drone(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }
}
