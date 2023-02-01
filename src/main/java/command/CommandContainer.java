package command;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static final Map<String, Command> commands = new TreeMap<String, Command>();

    static{
        commands.put("mainPageCommand", new MainCommand());

    }

}
