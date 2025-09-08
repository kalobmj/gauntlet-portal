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

		// gauntlet portal object id
		int gauntletId = 37340;

		// gauntlet exit portal id
		int exitPortalId = 123;

		// gauntlet chest id
		int chestId = 1234;

		// code below will be:
		// if (
		// gauntletObject includes any of our ids,
		// add them to gauntletObjects (so we can loop over and color in overlay)
		// )

		GameObject portal = e.getGameObject();

        log.info("logging random object: {}", portal);
        log.info("logging random object id: {}", portal.getId());

        // we want to track portal
        // we want to go back, get multiple objects like the chest to color the hull
        // set default chest colors , user can change

		// if object id matches portal id
		if (portal.getId() == gauntletId) {
			log.info("gauntlet PORTAL spawned with ID: {}", portal.getId());
            log.info("logging PORTAL object: {}", portal);
		}

		// testing calling each object spawned and getting its id
		log.info("obj spawned: {}", portal);

	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says ", null);
		}
	}
}
