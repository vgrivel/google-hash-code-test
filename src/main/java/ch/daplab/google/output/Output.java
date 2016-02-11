package ch.daplab.google.output;

import ch.daplab.google.dorwi.commands.Command;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by dori on 11.02.16.
 */
public class Output {
    String fileName;
    List<Command> commands;

    public Output(String fileName, List<Command> commands) {
        this.fileName = fileName;
        this.commands = commands;
    }

    public void write() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        writer.println(commands.size());
        for (Command command: commands){
            writer.println(command.toString());
        }
        writer.close();
    }
}
