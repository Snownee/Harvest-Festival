package joshie.harvest.quests.command;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import joshie.harvest.api.quests.Quest;
import joshie.harvest.core.commands.CommandManager.CommandLevel;
import joshie.harvest.core.commands.HFCommand;
import joshie.harvest.quests.QuestHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

@HFCommand
public class CommandListQuests extends CommandBase {
	@Override
	@Nonnull
	public String getName() {
		return "list_quests";
	}

	@Override
	@Nonnull
	public String getUsage(@Nonnull ICommandSender sender) {
		return "/hf list_quests [player]";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return CommandLevel.OP_AFFECT_GAMEPLAY.ordinal();
	}

	@Override
	public void execute(
			@Nonnull MinecraftServer server,
			@Nonnull ICommandSender sender,
			@Nonnull String[] parameters) throws CommandException {
		if (parameters.length == 0 || parameters.length == 1) {
			EntityPlayerMP player = parameters.length == 0 ? CommandBase.getCommandSenderAsPlayer(sender) : CommandBase.getPlayer(
					server,
					sender,
					parameters[0]);
			List<Quest> quests = QuestHelper.INSTANCE.getCurrentQuests(player);
			if (quests.isEmpty()) {
				sender.sendMessage(new TextComponentString(player.getName() + " has no quests"));
			} else {
				sender.sendMessage(new TextComponentString(player.getName() + " has the following quests:"));
				for (Quest quest : quests) {
					sender.sendMessage(new TextComponentString("- " + quest.getRegistryName() + ": " + quest.getStage()));
				}
			}
		} else {
			throw new WrongUsageException(getUsage(sender));
		}
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
		if (args.length == 1) {
			return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
		}

		return super.getTabCompletions(server, sender, args, targetPos);
	}
}
