package com.example;

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

@Slf4j
@PluginDescriptor(
	name = "Gauntlet Portal"
)
public class GauntletPortalPlugin extends Plugin
{

	// gauntlet portal object id
	int gauntletId = 37340;

	@Inject
	private Client client;

	@Inject
	private GauntletPortalConfig config;

    // keep track of portal object
    private final GameObject portal;


    //future keep track of <set> of game objects

	GauntletPortalConfig getConfig() { return config; }

	@Override
	protected void startUp() throws Exception
	{
		log.info("Gauntlet Portal started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Gauntlet Portal stopped!");
	}

	@Subscribe
	public void onGameObjectSpawned(GameObjectSpawned e)
	{
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

	@Provides
    GauntletPortalConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(GauntletPortalConfig.class);
	}
}
