package command;

import command.login.LogOutCommand;
import command.login.LoginCommand;
import command.login.RegisterCommand;
import command.moderator.*;
import command.speakers.AvailableReportsShowPageSpeakerCommand;
import command.speakers.ReassignReportSpeakerCommand;
import command.speakers.ReportShowPageSpeakerCommand;
import command.user.AvailableMeetingShowPageUserCommand;
import command.user.MeetingParticipationUserCommand;
import command.user.UserParticipationMeetingPageCommand;
import db.Entity.MainUser;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static final Map<String, Command> commands = new TreeMap<String, Command>();

    static{
        commands.put("mainPageCommand", new MainCommand());
        commands.put("loginCommand", new LoginCommand());
        commands.put("registerCommand", new RegisterCommand());
        commands.put("languageSwitcherCommand", new LocalCommand());
        commands.put("logOutCommand", new LogOutCommand());
        

        //moderator commands
        commands.put("createMeetingModeratorCommand", new CreateMeetingModeratorCommand());
        commands.put("moderatorTableCommand", new MeetingShowPageModeratorsCommand());
        commands.put("moderatorCountParticipantsTable", new ModeratorsCountParticipantsAndReportsPageCommand());
        commands.put("moderReportTableCommand", new ReportShowPageModeratorsCommand());
        commands.put("moderatorReportCreationCommand", new CreateReportPageModeratorCommand());
        commands.put("meetingSortingForModeratorsCommand", new MeetingSortingForModeratorsCommand());
        commands.put("transferUrlCommand", new TransferCommand());

        //speaker commands
        commands.put("speakerTableCommand", new AvailableReportsShowPageSpeakerCommand());
        commands.put("speakerReportTableCommand", new ReportShowPageSpeakerCommand());
        commands.put("reassignSpeakerCommand", new ReassignReportSpeakerCommand());

        //user commands
        commands.put("userTableCommand", new AvailableMeetingShowPageUserCommand());
        commands.put("userMeetingParticipation", new MeetingParticipationUserCommand());
        commands.put("participationMeetingUserPage", new UserParticipationMeetingPageCommand());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }
}
