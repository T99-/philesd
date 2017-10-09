package com.t99sdevelopment.philesd.cli;
//Created by T99 at 12:01 AM, October [DAY...], 2017.

import java.util.HashMap;

public class CommandManager {

    private static HashMap<String, Command> commands = new HashMap<>();

    protected static void execute(String commandName) {

        if (commands.containsKey(commandName)) {

            commands.get(commandName).execute();

        } else {

            // send text to relevant output stream notifying user that the command does not exist -- check context first

        }

    }

    public static void register(String commandName, Command command) {

        commands.put(commandName, command);

    }

}