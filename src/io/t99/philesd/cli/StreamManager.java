package io.t99.philesd.cli;

import java.util.HashMap;

public class StreamManager {

    private static HashMap<String, CLIStream> streams = new HashMap<>();

    public static void enter(String streamName) {



    }

    public static void register(String streamName, CLIStream stream) {

         streams.put(streamName, stream);

    }

}