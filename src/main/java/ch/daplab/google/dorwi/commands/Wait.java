package ch.daplab.google.dorwi.commands;

/**
 * Created by dori on 11.02.16.
 */
public class Wait extends Command {
    int turns;

    public Wait(int droneId, int turns) {
        super(droneId);
        this.tag = 'W';
        this.turns = turns;
    }
}
