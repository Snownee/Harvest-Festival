package joshie.harvest.calendar.command;

import static joshie.harvest.calendar.HFCalendar.TICKS_PER_DAY;

import javax.annotation.Nonnull;

import joshie.harvest.api.calendar.CalendarDate;
import joshie.harvest.calendar.CalendarHelper;
import joshie.harvest.calendar.data.CalendarServer;
import joshie.harvest.core.HFTrackers;
import joshie.harvest.core.commands.CommandManager.CommandLevel;
import joshie.harvest.core.commands.HFCommand;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

@HFCommand
@SuppressWarnings("unused")
public class HFCommandNewDay extends CommandBase {
    @Override
    @Nonnull
    public String getName() {
        return "newDay";
    }

    @Override
    @Nonnull
    public String getUsage(@Nonnull ICommandSender sender) {
        return "/hf newDay";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return CommandLevel.OP_AFFECT_GAMEPLAY.ordinal();
    }

    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] parameters) throws CommandException {
        long i = sender.getEntityWorld().getWorldTime() + TICKS_PER_DAY;
        CalendarHelper.setWorldTime(server, (i - i % TICKS_PER_DAY) - 1);
        CalendarServer calendar = HFTrackers.getCalendar(sender.getEntityWorld());
        CalendarDate date = calendar.getDate();
        notifyCommandListener(sender, this, "Year: " + date.getYear());
        notifyCommandListener(sender, this, "Day: " + date.getDay());
        notifyCommandListener(sender, this, "Season: " + date.getSeason());
        notifyCommandListener(sender, this, "Weekday: " + date.getWeekday());
    }
}
