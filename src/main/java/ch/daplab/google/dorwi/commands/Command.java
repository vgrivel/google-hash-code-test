package ch.daplab.google.dorwi.commands;

/**
 * Created by dori on 11.02.16.
 */
public abstract class Command {

    char tag;
    int droneId;

    public Command(int droneId) {
        this.droneId = droneId;
    }


}
