/*
 * Copyright (c) 2018, Tomas Slusny <slusnucky@gmail.com>
 * Copyright (c) 2019, Jajack
 * Copyright (c) 2019, Siraz <https://github.com/Sirazzz>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.sanlite.client.plugins.playerindicatorsextended;

import com.google.inject.Provides;
import net.runelite.api.*;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.api.events.PlayerSpawned;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.Notifier;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.ChatIconManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.ColorUtil;
import net.runelite.client.util.Text;
import org.apache.commons.lang3.StringUtils;
import net.sanlite.client.game.SafeDeathPvpRegions;

import javax.inject.Inject;
import java.awt.*;
import java.util.List;

import static net.runelite.api.FriendsChatRank.UNRANKED;
import static net.runelite.api.MenuAction.*;

@PluginDescriptor(
	name = "Player Indicators Extended",
	description = "Highlight players on-screen and/or on the minimap",
	tags = {"highlight", "minimap", "overlay", "players", "friend", "finder", "offline", "pvp", "name", "notifications", "sanlite"},
	conflicts = "Player Indicators",
	enabledByDefault = false
)
public class PlayerIndicatorsExtendedPlugin extends Plugin
{
	@Inject
	private OverlayManager overlayManager;

	@Inject
	private PlayerIndicatorsExtendedConfig config;

	@Inject
	private PlayerIndicatorsExtendedService playerIndicatorsService;

	@Inject
	private PlayerIndicatorsExtendedOverlay playerIndicatorsOverlay;

	@Inject
	private PlayerIndicatorsExtendedTileOverlay playerIndicatorsTileOverlay;

	@Inject
	private PlayerIndicatorsExtendedMinimapOverlay playerIndicatorsMinimapOverlay;

	@Inject
	private Client client;

	@Inject
	private ChatIconManager chatIconManager;

	@Inject
	private Notifier notifier;

	private int lastPlayerSpawnNotificationGameTick = -1;
	private List<String> ignoredPlayerNames;

	@Provides
	PlayerIndicatorsExtendedConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(PlayerIndicatorsExtendedConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(playerIndicatorsOverlay);
		overlayManager.add(playerIndicatorsTileOverlay);
		overlayManager.add(playerIndicatorsMinimapOverlay);
		ignoredPlayerNames = getIgnoredPlayerNames();
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(playerIndicatorsOverlay);
		overlayManager.remove(playerIndicatorsTileOverlay);
		overlayManager.remove(playerIndicatorsMinimapOverlay);
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged configChanged)
	{
		if (configChanged.getGroup().equals("playerindicators") && configChanged.getKey().equals("ignoredPlayerNames"))
		{
			ignoredPlayerNames = getIgnoredPlayerNames();
		}
	}

	private List<String> getIgnoredPlayerNames()
	{
		return Text.fromCSV(config.getIgnoredPlayerNames());
	}

	@Subscribe
	public void onMenuEntryAdded(MenuEntryAdded menuEntryAdded)
	{
		if (!config.colorFriendPlayerMenu() && !config.colorFriendsChatMemberPlayerMenu() && !config.colorTeamMemberPlayerMenu() &&
				!config.colorOthersPlayerMenu() && !config.colorListOnePlayerMenu() && !config.colorListTwoPlayerMenu() &&
				!config.colorListThreePlayerMenu() && !config.colorListFourPlayerMenu() && !config.colorListFivePlayerMenu())
		{
			return;
		}

		int type = menuEntryAdded.getType();
		if (type >= 2000)
		{
			type -= 2000;
		}

		int identifier = menuEntryAdded.getIdentifier();
		if (type == FOLLOW.getId() || type == TRADE.getId()
				|| type == SPELL_CAST_ON_PLAYER.getId() || type == ITEM_USE_ON_PLAYER.getId()
				|| type == PLAYER_FIRST_OPTION.getId()
				|| type == PLAYER_SECOND_OPTION.getId()
				|| type == PLAYER_THIRD_OPTION.getId()
				|| type == PLAYER_FOURTH_OPTION.getId()
				|| type == PLAYER_FIFTH_OPTION.getId()
				|| type == PLAYER_SIXTH_OPTION.getId()
				|| type == PLAYER_SEVENTH_OPTION.getId()
				|| type == PLAYER_EIGTH_OPTION.getId()
				|| type == RUNELITE_PLAYER.getId())
		{
			Player[] players = client.getCachedPlayers();
			Player player = null;

			if (identifier >= 0 && identifier < players.length)
			{
				player = players[identifier];
			}

			if (player == null || StringUtils.isEmpty(player.getName()))
			{
				return;
			}

			PlayerIndicatorType playerIndicatorType = playerIndicatorsService.getMenuEntryPlayerIndicatorType(player);
			if (playerIndicatorType == null || playerIndicatorType == PlayerIndicatorType.UNKNOWN_PLAYER)
				return;

			switch (playerIndicatorType)
			{
				case FRIEND:
					colorMenuEntry(player, playerIndicatorType, config.getFriendColor(), config.colorFriendPlayerMenu());
					break;
				case CUSTOM_LIST_1:
					colorMenuEntry(player, playerIndicatorType, config.getListOneColor(), config.colorListOnePlayerMenu());
					break;
				case CUSTOM_LIST_2:
					colorMenuEntry(player, playerIndicatorType, config.getListTwoColor(), config.colorListTwoPlayerMenu());
					break;
				case CUSTOM_LIST_3:
					colorMenuEntry(player, playerIndicatorType, config.getListThreeColor(), config.colorListThreePlayerMenu());
					break;
				case CUSTOM_LIST_4:
					colorMenuEntry(player, playerIndicatorType, config.getListFourColor(), config.colorListFourPlayerMenu());
					break;
				case CUSTOM_LIST_5:
					colorMenuEntry(player, playerIndicatorType, config.getListFiveColor(), config.colorListFivePlayerMenu());
					break;
				case FRIENDS_CHAT_MEMBERS:
					colorMenuEntry(player, playerIndicatorType, config.getFriendsChatMemberColor(), config.colorFriendsChatMemberPlayerMenu());
					break;
				case TEAM_CAPE_MEMBER:
					colorMenuEntry(player, playerIndicatorType, config.getTeamMemberColor(), config.colorTeamMemberPlayerMenu());
					break;
				case NON_CLAN_MEMBER:
					colorMenuEntry(player, playerIndicatorType, config.getOthersColor(), config.colorOthersPlayerMenu());
					break;
			}
		}
	}

	private void colorMenuEntry(Player player, PlayerIndicatorType playerIndicatorType, Color entryColor, boolean colorMenuEntry)
	{
		if (playerIndicatorType == null || entryColor == null || !colorMenuEntry)
		{
			return;
		}

		int image = -1;
		if (playerIndicatorType == PlayerIndicatorType.FRIENDS_CHAT_MEMBERS)
		{
			final FriendsChatRank rank = playerIndicatorsService.getFriendsChatRank(player);
			if (rank != UNRANKED)
			{
				image = chatIconManager.getIconNumber(rank);
			}
		}

		MenuEntry[] menuEntries = client.getMenuEntries();
		MenuEntry lastEntry = menuEntries[menuEntries.length - 1];

		// strip out existing <col...
		String target = lastEntry.getTarget();
		int idx = target.indexOf('>');
		if (idx != -1)
		{
			target = target.substring(idx + 1);
		}

		lastEntry.setTarget(ColorUtil.prependColorTag(target, entryColor));

		if (image != -1 && config.showFriendsChatRanks())
		{
			lastEntry.setTarget("<img=" + image + ">" + lastEntry.getTarget());
		}

		client.setMenuEntries(menuEntries);
	}

	@Subscribe
	public void onPlayerSpawned(PlayerSpawned event)
	{
		checkPlayerSpawned(event);
	}

	private void checkPlayerSpawned(PlayerSpawned event)
	{
		Player player = event.getPlayer();
		if (player == null || player == client.getLocalPlayer() || !config.notifyOnOtherPlayerSpawned() ||
				(config.notifyOnlyOnSkulledPlayers() && !player.isSkulled()))
			return;

		PlayerIndicatorType playerIndicatorType = playerIndicatorsService.getPlayerIndicatorType(player);
		if (playerIndicatorType == null ||
			playerIndicatorType.equals(PlayerIndicatorType.FRIENDS_CHAT_MEMBERS) && !config.notifyFriendsChatMembersSpawning() ||
			playerIndicatorType.equals(PlayerIndicatorType.FRIEND) && !config.notifyFriendsSpawning())
			return;

		// Only send notifications in PvP zones
		if (client.getVar(Varbits.PVP_SPEC_ORB) != 1 && client.getVar(Varbits.IN_WILDERNESS) != 1 &&
				client.getWorldType().stream().noneMatch(x -> x == WorldType.DEADMAN) && !config.notifyInPvpSafezone())
			return;

		// Only send notifications if player is attackable
		if (config.notifyOnlyAttackablePlayers() && !isPlayerAttackable(player))
		{
			return;
		}

		// Do not trigger if the region is a safe death PvP zone (e.g. Duel Arena)
		if (SafeDeathPvpRegions.inSafeDeathPvpArea(client) && !config.disableSafeDeathPvpAreaBlacklist())
			return;

		// Check if enough time has expired since the last notification
		if (client.getTickCount() < lastPlayerSpawnNotificationGameTick + config.delayBetweenPlayerSpawnedNotifications())
			return;

		if (ignoredPlayerNames.contains(player.getName()))
			return;

		notifier.notify("[" + player.getName() + "] has spawned!");
		lastPlayerSpawnNotificationGameTick = client.getTickCount();
	}

	private boolean isPlayerAttackable(Player player)
	{
		if (client.getLocalPlayer() == null)
			return false;

		int ownCombatLevel = client.getLocalPlayer().getCombatLevel();
		int lowestAttackable = ownCombatLevel;
		int highestAttackable = ownCombatLevel;

		if (WorldType.isPvpWorld(client.getWorldType()))
		{
			lowestAttackable -= 15;
			highestAttackable += 15;
		}

		if (client.getVar(Varbits.IN_WILDERNESS) == 1)
		{
			Widget levelRangeWidget = client.getWidget(WidgetInfo.PVP_WILDERNESS_LEVEL);
			if (levelRangeWidget == null)
				return false;

			int wildLevel = Integer.parseInt(levelRangeWidget.getText().split(" ")[1].split("<")[0]);
			lowestAttackable -= wildLevel;
			highestAttackable += wildLevel;
		}

		return player.getCombatLevel() >= lowestAttackable && player.getCombatLevel() <= highestAttackable;
	}
}
