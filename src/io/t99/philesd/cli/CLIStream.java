package io.t99.philesd.cli;

public class CLIStream {

    public Context context;
    public StringBuffer contents = new StringBuffer();

    public CLIStream(String name, Context context) {

        this.context = context;
        StreamManager.register(name, this);

    }

    public void print(String string) {

        contents.append(string);

        if (this.equals(CLI.focus)) {

            System.out.print(string);

        }

    }

    public void println(String string) {

        contents.append(string + "\n");

        if (this.equals(CLI.focus)) {

            System.out.println(string);

        }

    }

    public void readFull() {

        System.out.println(contents.toString());

    }

    public boolean equals(CLIStream stream) {

        return stream.toString().equals(contents.toString());

    }
}