/*
 * Copyright (c) 2018, Lotto <https://github.com/devLotto>
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
package net.runelite.mixins;

import net.runelite.api.mixins.Inject;
import net.runelite.api.mixins.Mixin;
import net.runelite.rs.api.RSClient;

import java.util.HashMap;

@Mixin(RSClient.class)
public abstract class EntityHiderBridgeMixin implements RSClient
{
	@Inject
	public static boolean isHidingEntities;

	@Inject
	public static boolean hideOthers;

	@Inject
	public static boolean hideOthers2D;

	@Inject
	public static boolean hideFriends;

	@Inject
	public static boolean hideFriendsChatMembers;

	@Inject
	public static boolean hideClanChatMembers;

	@Inject
	public static boolean hideLocalPlayer;

	@Inject
	public static boolean hideLocalPlayer2D;

	@Inject
	public static boolean hideIgnores;

	@Inject
	public static boolean hideNPCs;

	@Inject
	public static boolean hideNPCs2D;

	@Inject
	public static boolean hidePets;

	@Inject
	public static boolean hideAttackers;

	@Inject
	public static boolean hideProjectiles;

	@Inject
	public static boolean hideDeadNPCs;

	@Inject
	public static HashMap<String, Integer> hiddenDeadNpcNames = new HashMap<>();

	@Inject
	@Override
	public void setIsHidingEntities(boolean state)
	{
		isHidingEntities = state;
	}

	@Inject
	@Override
	public void setOthersHidden(boolean state)
	{
		hideOthers = state;
	}

	@Inject
	@Override
	public void setOthersHidden2D(boolean state)
	{
		hideOthers2D = state;
	}

	@Inject
	@Override
	public void setFriendsHidden(boolean state)
	{
		hideFriends = state;
	}

	@Inject
	@Override
	public void setFriendsChatMembersHidden(boolean state)
	{
		hideFriendsChatMembers = state;
	}

	@Inject
	@Override
	public void setClanChatMembersHidden(boolean state)
	{
		hideClanChatMembers = state;
	}

	@Inject
	@Override
	public void setIgnoresHidden(boolean state)
	{
		hideIgnores = state;
	}

	@Inject
	@Override
	public void setLocalPlayerHidden(boolean state)
	{
		hideLocalPlayer = state;
	}

	@Inject
	@Override
	public void setLocalPlayerHidden2D(boolean state)
	{
		hideLocalPlayer2D = state;
	}

	@Inject
	@Override
	public void setNPCsHidden(boolean state)
	{
		hideNPCs = state;
	}

	@Inject
	@Override
	public void setNPCsHidden2D(boolean state)
	{
		hideNPCs2D = state;
	}

	@Inject
	@Override
	public void setPetsHidden(boolean state)
	{
		hidePets = state;
	}

	@Inject
	@Override
	public void setAttackersHidden(boolean state)
	{
		hideAttackers = state;
	}

	@Inject
	@Override
	public void setProjectilesHidden(boolean state)
	{
		hideProjectiles = state;
	}

	@Inject
	public void setDeadNPCsHidden(boolean state)
	{
		hideDeadNPCs = state;
	}

	@Inject
	@Override
	public void addHiddenDeadNpcName(String name)
	{
		name = name.toLowerCase();
		int hideCount = hiddenDeadNpcNames.getOrDefault(name, 0);
		if (hideCount == Integer.MAX_VALUE)
		{
			throw new RuntimeException("Dead NPC with name: " + name + " has passed the hide count limit");
		}

		hiddenDeadNpcNames.put(name, ++hideCount);
	}

	@Inject
	@Override
	public void removeHiddenDeadNpcName(String name)
	{
		name = name.toLowerCase();
		int hideCount = hiddenDeadNpcNames.getOrDefault(name, 0);
		if (hideCount == 0)
		{
			return;
		}

		hiddenDeadNpcNames.put(name, --hideCount);
	}
}
