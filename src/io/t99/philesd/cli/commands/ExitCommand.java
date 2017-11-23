package io.t99.philesd.cli.commands;

import io.t99.philesd.cli.CLI;
import io.t99.philesd.cli.Command;
import io.t99.philesd.cli.CommandManager;

class ExitCommand extends Command {

    {
        CommandManager.register("exit", this);
    }

    public ExitCommand(int requiredContext) {

        super(requiredContext);

    }

    @Override
    public void execute() {

        switch (CLI.getContext()) {

            case BASE:
                System.exit(0);
                break;

            case CLISTREAM:
                // return to the base context/cli stream
                break;

        }

    }

    @Override
    public void help() {

        // output help message to the correct stream

    }

}