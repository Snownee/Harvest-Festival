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
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

@HFCommand
public class CommandCompleteQuest extends CommandBase {
	@Override
	@Nonnull
	public String getName() {
		return "complete_quest";
	}

	@Override
	@Nonnull
	public String getUsage(@Nonnull ICommandSender sender) {
		return "/hf complete_quest <quest> [player]";
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
		if (parameters.length == 1 || parameters.length == 2) {
			Quest quest = QuestHelper.getQuest(parameters[0]);
			if (quest == null) {
				throw new SyntaxErrorException("Quest " + parameters[0] + " does not exist");
			}
			EntityPlayerMP player = parameters.length == 1 ? CommandBase.getCommandSenderAsPlayer(sender) : CommandBase.getPlayer(
					server,
					sender,
					parameters[1]);
			if (!QuestHelper.INSTANCE.getCurrentQuests(player).contains(quest)) {
				QuestHelper.INSTANCE.revokeQuest(quest, player);
				QuestHelper.INSTANCE.startQuest(quest, player, null);
			}
			QuestHelper.INSTANCE.completeQuest(quest, player);
			sender.sendMessage(new TextComponentString(
					"Marked quest " + quest.getRegistryName() + " as completed for " + player.getName()));
		} else {
			throw new WrongUsageException(getUsage(sender));
		}
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
		if (args.length == 1) {
			return getListOfStringsMatchingLastWord(args, Quest.REGISTRY.getKeys());
		} else if (args.length == 2) {
			return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
		}

		return super.getTabCompletions(server, sender, args, targetPos);
	}
}
