package com.example;

import com.google.common.collect.Sets;
import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameObject;
import net.runelite.api.GameState;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GameObjectDespawned;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import java.util.Set;

@Slf4j
@PluginDescriptor(
	name = "Gauntlet Portal"
)
public class GauntletPortalPlugin extends Plugin
{
	@Inject private Client client;
	@Inject private OverlayManager overlayManager;
	@Inject private GauntletPortalOverlay overlay;
	@Inject private GauntletPortalConfig config;

	// Set of gauntlet objects to outline
	private final Set<GameObject> gauntletObjects = Sets.newConcurrentHashSet();

	// Integer Set of object IDs
	private final Set<Integer> gauntletIds = Set.of(36082, 37340, 37341);

	@Provides
	GauntletPortalConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(GauntletPortalConfig.class);
	}

	Set<GameObject> getGauntletObjects() { return gauntletObjects; }

	GauntletPortalConfig getConfig() { return config; }

	@Override
	protected void startUp() throws Exception
	{
		log.info("Gauntlet Portal started!");
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Gauntlet Portal stopped!");
		overlayManager.remove(overlay);
		gauntletObjects.clear();
	}

	@Subscribe
	public void onGameObjectSpawned(GameObjectSpawned e)
	{
		GameObject obj = e.getGameObject();
		if (gauntletIds.contains(obj.getId())) {
			gauntletObjects.add(obj);
		}
	}

	@Subscribe
	public void onGameObjectDespawned(GameObjectDespawned e) { gauntletObjects.remove(e.getGameObject()); }

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOADING)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Gauntlet Portal says: LOADING ", null);
			gauntletObjects.clear();
		}
	}
}
