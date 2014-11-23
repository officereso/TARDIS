/*
 * Copyright (C) 2014 eccentric_nz
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package me.eccentric_nz.TARDIS.api;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 *
 * @author eccentric_nz
 */
public interface TardisAPI {

    /**
     * Fetches a map of TARDIS owners and ids.
     *
     * @return a map of TARDIS owner names and TARDIS ids
     */
    public HashMap<String, Integer> getTimelordMap();

    /**
     * Retrieves a TARDIS's current location.
     *
     * @param id the TARDIS id to retrieve the location for
     * @return the current TARDIS location or null if not found
     */
    public Location getTARDISCurrentLocation(int id);

    /**
     * Retrieves a TARDIS's current location.
     *
     * @param p the Time Lord of the TARDIS to retrieve the location for
     * @return the current TARDIS location or null if not found
     */
    public Location getTARDISCurrentLocation(Player p);

    /**
     * Retrieves a TARDIS's current location.
     *
     * @param uuid the UUID of the TARDIS' Time Lord to retrieve the location
     * for
     * @return the current TARDIS location or null if not found
     */
    public Location getTARDISCurrentLocation(UUID uuid);

    /**
     * Retrieves a TARDIS's next location.
     *
     * @param id the TARDIS id to retrieve the location for
     * @return the current TARDIS location or null if not found
     */
    public Location getTARDISNextLocation(int id);

    /**
     * Get a random location from a provided list of worlds in the specified
     * environment. If environment is null, then it will choose a random one.
     *
     * @param worlds a List of world names to search
     * @param environment the world type to search
     * @param flag a Parameters object determining what flags to check for when
     * getting the Location
     * @return a random Location or null if one could not be found
     */
    public Location getRandomLocation(List<String> worlds, World.Environment environment, Parameters flag);

    /**
     * Get a random location from a provided list of worlds in the specified
     * environment. If environment is null, then it will choose a random one.
     *
     * @param worlds a List of world names to search
     * @param environment the world type to search
     * @param p the player to get the location for
     * @return a random Location or null if one could not be found
     */
    public Location getRandomLocation(List<String> worlds, World.Environment environment, Player p);

    /**
     * Get a random location from a provided list of worlds in a random
     * environment.
     *
     * @param worlds a List of world names to search
     * @param p the player to get the location for
     * @return a random Location or null if one could not be found
     */
    public Location getRandomLocation(List<String> worlds, Player p);

    /**
     * Get a random OVERWORLD location from a random world.
     *
     * @param p the player to get the location for
     * @return a random Location or null if one could not be found
     */
    public Location getRandomOverworldLocation(Player p);

    /**
     * Get a random OVERWORLD location from a specific world.
     *
     * @param world the world to search
     * @param p the player to get the location for
     * @return a random Location or null if one could not be found
     */
    public Location getRandomOverworldLocation(String world, Player p);

    /**
     * Get a random OVERWORLD location from a random world.
     *
     * @param p the player to get the location for
     * @return a random Location or null if one could not be found
     */
    public Location getRandomNetherLocation(Player p);

    /**
     * Get a random NETHER location from a specific world.
     *
     * @param world the world to search
     * @param p the player to get the location for
     * @return a random Location or null if one could not be found
     */
    public Location getRandomNetherLocation(String world, Player p);

    /**
     * Get a random THE_END location from a random world.
     *
     * @param p the player to get the location for
     * @return a random Location or null if one could not be found
     */
    public Location getRandomEndLocation(Player p);

    /**
     * Get a random THE_END location from a specific world.
     *
     * @param world the world to search
     * @param p the player to get the location for
     * @return a random Location or null if one could not be found
     */
    public Location getRandomEndLocation(String world, Player p);

    /**
     * Get a string list of worlds on the server.
     *
     * @return a list of worlds
     */
    public List<String> getWorlds();

    /**
     * Get the TARDIS a player is in.
     *
     * @param p the player to query
     * @return a string containing the Time Lord (player name) of the TARDIS the
     * player is in
     */
    public String getTARDISPlayerIsIn(Player p);

    /**
     * Get the TARDIS a player is in.
     *
     * @param uuid the UUID of the player
     * @return a string containing the Time Lord (player name) of the TARDIS the
     * player is in
     */
    public String getTARDISPlayerIsIn(UUID uuid);

    /**
     * Get the players in a TARDIS.
     *
     * @param id the tardis_id of the TARDIS
     * @return a List of player names
     */
    public List<String> getPlayersInTARDIS(int id);

    /**
     * Get the players in a TARDIS.
     *
     * @param p the Player who is the TARDIS' Time Lord
     * @return a List of (online) player names
     */
    public List<String> getPlayersInTARDIS(Player p);

    /**
     * Get the players in a TARDIS.
     *
     * @param uuid the UUID of the TARDIS' Time Lord
     * @return a List of (online) player names
     */
    public List<String> getPlayersInTARDIS(UUID uuid);

    /**
     * Get the companions of a TARDIS.
     *
     * @param id the tardis_id of the TARDIS
     * @return a List of companion names
     */
    public List<String> getTARDISCompanions(int id);

    /**
     * Get the companions of a TARDIS.
     *
     * @param p the Player who is the TARDIS' Time Lord
     * @return a List of (online) companion names
     */
    public List<String> getTARDISCompanions(Player p);

    /**
     * Get the companions of a TARDIS.
     *
     * @param uuid the UUID of the TARDIS' Time Lord
     * @return a List of (online) companion names
     */
    public List<String> getTARDISCompanions(UUID uuid);

    /**
     * Get whether a player is in a Zero room.
     *
     * @param p the player to query
     * @return true if the player is in the Zero room, otherwise false
     */
    public boolean isPlayerInZeroRoom(Player p);

    /**
     * Get whether a player is in a Zero room.
     *
     * @param uuid the UUID of the player to query
     * @return true if the player is in the Zero room, otherwise false
     */
    public boolean isPlayerInZeroRoom(UUID uuid);

    /**
     * Get whether a player is currently genetically modified.
     *
     * @param p the player to query
     * @return true if the player is in the Zero room, otherwise false
     */
    public boolean isPlayerGeneticallyModified(Player p);

    /**
     * Get whether a player is currently genetically modified.
     *
     * @param uuid the UUID of the player to query
     * @return true if the player is in the Zero room, otherwise false
     */
    public boolean isPlayerGeneticallyModified(UUID uuid);

}