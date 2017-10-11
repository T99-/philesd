package com.t99sdevelopment.philesd.cli;
//Created by T99 at 12:01 AM, October [DAY...], 2017.

import com.t99sdevelopment.philesd.exception.CommandNotFoundException;

import java.util.HashMap;

public class CommandManager {

    private static HashMap<String, Command> commands = new HashMap<>();

    public static void execute(String commandName) throws CommandNotFoundException {

        if (commands.containsKey(commandName)) {

            commands.get(commandName).execute();

        } else {

            throw new CommandNotFoundException("The given command did not exist.");

        }

    }

    public static void register(String commandName, Command command) {

        commands.put(commandName, command);

    }

}