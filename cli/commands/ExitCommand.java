package com.t99sdevelopment.philesd.cli.commands;

import com.t99sdevelopment.philesd.cli.CLI;
import com.t99sdevelopment.philesd.cli.Command;
import com.t99sdevelopment.philesd.cli.CommandManager;

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

            case CLI.BASE_CONTEXT:
                System.exit(0);
                break;

            case CLI.CLISTREAM_CONTEXT:
                // return to the base context/cli stream
                break;

        }

    }

    @Override
    public void help() {

        // output help message to the correct stream

    }

}