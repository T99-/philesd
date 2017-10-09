package com.t99sdevelopment.philesd.cli;

public abstract class Command {

    private int requiredContext;

    public Command(int requiredContext) {

        this.requiredContext = requiredContext;

    }

    public abstract void execute();
    public abstract void help();

}