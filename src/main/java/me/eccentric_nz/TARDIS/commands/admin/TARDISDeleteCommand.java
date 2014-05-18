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
package me.eccentric_nz.TARDIS.commands.admin;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import me.eccentric_nz.TARDIS.TARDIS;
import me.eccentric_nz.TARDIS.builders.TARDISMaterialisationData;
import me.eccentric_nz.TARDIS.database.QueryFactory;
import me.eccentric_nz.TARDIS.database.ResultSetCurrentLocation;
import me.eccentric_nz.TARDIS.database.ResultSetTardis;
import me.eccentric_nz.TARDIS.database.ResultSetTravellers;
import static me.eccentric_nz.TARDIS.destroyers.TARDISExterminator.deleteFolder;
import me.eccentric_nz.TARDIS.enumeration.COMPASS;
import me.eccentric_nz.TARDIS.enumeration.MESSAGE;
import me.eccentric_nz.TARDIS.enumeration.SCHEMATIC;
import me.eccentric_nz.tardischunkgenerator.TARDISChunkGenerator;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldType;
import org.bukkit.block.Biome;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author eccentric_nz
 */
public class TARDISDeleteCommand {

    private final TARDIS plugin;

    public TARDISDeleteCommand(TARDIS plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("deprecation")
    public boolean deleteTARDIS(CommandSender sender, String[] args) {
        // this should be run from the console if the player running it is the player to be deleted
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.getName().equals(args[1])) {
                HashMap<String, Object> where = new HashMap<String, Object>();
                where.put("uuid", player.getUniqueId().toString());
                ResultSetTravellers rst = new ResultSetTravellers(plugin, where, false);
                if (rst.resultSet()) {
                    sender.sendMessage(plugin.getPluginName() + "You cannot be in your TARDIS when you delete it!");
                    return true;
                }
            }
        }
        // Look up this player's UUID
        UUID uuid = plugin.getServer().getOfflinePlayer(args[1]).getUniqueId();
        if (uuid == null) {
            uuid = plugin.getGeneralKeeper().getUUIDCache().getIdOptimistic(args[1]);
            plugin.getGeneralKeeper().getUUIDCache().getId(args[1]);
        }
        if (uuid != null) {
            HashMap<String, Object> where = new HashMap<String, Object>();
            where.put("uuid", uuid.toString());
            ResultSetTardis rs = new ResultSetTardis(plugin, where, "", false);
            if (rs.resultSet()) {
                int id = rs.getTardis_id();
                int tips = rs.getTIPS();
                SCHEMATIC schm = rs.getSchematic();
                String chunkLoc = rs.getChunk();
                String[] cdata = chunkLoc.split(":");
                String name = cdata[0];
                World cw = plugin.getServer().getWorld(name);
                if (cw == null) {
                    sender.sendMessage(plugin.getPluginName() + "The server could not find the TARDIS world, has it been deleted?");
                    return true;
                }
                int restore = getRestore(cw);
                // check if player is in the TARDIS
                HashMap<String, Object> wheret = new HashMap<String, Object>();
                wheret.put("tardis_id", id);
                ResultSetTravellers rst = new ResultSetTravellers(plugin, wheret, true);
                QueryFactory qf = new QueryFactory(plugin);
                HashMap<String, Object> whered = new HashMap<String, Object>();
                whered.put("tardis_id", id);
                if (rst.resultSet()) {
                    qf.doDelete("travellers", whered);
                }
                // get the current location
                Location bb_loc = null;
                COMPASS d = COMPASS.EAST;
                Biome biome = null;
                HashMap<String, Object> wherecl = new HashMap<String, Object>();
                wherecl.put("tardis_id", id);
                ResultSetCurrentLocation rsc = new ResultSetCurrentLocation(plugin, wherecl);
                if (rsc.resultSet()) {
                    bb_loc = new Location(rsc.getWorld(), rsc.getX(), rsc.getY(), rsc.getZ());
                    d = rsc.getDirection();
                    biome = rsc.getBiome();
                }
                if (bb_loc == null) {
                    sender.sendMessage(plugin.getPluginName() + MESSAGE.NO_CURRENT.getText());
                    return true;
                }
                // destroy the TARDIS
                if ((plugin.getConfig().getBoolean("creation.create_worlds") && !plugin.getConfig().getBoolean("creation.default_world")) || name.contains("TARDIS_WORLD_")) {
                    // delete TARDIS world
                    List<Player> players = cw.getPlayers();
                    for (Player p : players) {
                        p.kickPlayer("World scheduled for deletion!");
                    }
                    if (plugin.getPM().isPluginEnabled("Multiverse-Core")) {
                        plugin.getServer().dispatchCommand(plugin.getConsole(), "mv remove " + name);
                    }
                    if (plugin.getPM().isPluginEnabled("MultiWorld")) {
                        plugin.getServer().dispatchCommand(plugin.getConsole(), "mw unload " + name);
                    }
                    if (plugin.getPM().isPluginEnabled("WorldBorder")) {
                        // wb <world> clear
                        plugin.getServer().dispatchCommand(plugin.getConsole(), "wb " + name + " clear");
                    }
                    plugin.getServer().unloadWorld(cw, true);
                    File world_folder = new File(plugin.getServer().getWorldContainer() + File.separator + name + File.separator);
                    if (!deleteFolder(world_folder)) {
                        plugin.debug("Could not delete world <" + name + ">");
                    }
                } else {
                    plugin.getInteriorDestroyer().destroyInner(schm, id, cw, restore, args[1], tips);
                }
                if (!rs.isHidden()) {
                    final TARDISMaterialisationData pdd = new TARDISMaterialisationData();
                    pdd.setChameleon(false);
                    pdd.setDirection(d);
                    pdd.setLocation(bb_loc);
                    pdd.setDematerialise(false);
                    pdd.setPlayer(plugin.getServer().getOfflinePlayer(uuid));
                    pdd.setHide(false);
                    pdd.setOutside(false);
                    pdd.setSubmarine(rsc.isSubmarine());
                    pdd.setTardisID(id);
                    pdd.setBiome(biome);
                    plugin.getPresetDestroyer().destroyPreset(pdd);
                }
                // delete the TARDIS from the db
                HashMap<String, Object> wherec = new HashMap<String, Object>();
                wherec.put("tardis_id", id);
                qf.doDelete("chunks", wherec);
                HashMap<String, Object> wherea = new HashMap<String, Object>();
                wherea.put("tardis_id", id);
                qf.doDelete("tardis", wherea);
                HashMap<String, Object> whereo = new HashMap<String, Object>();
                whereo.put("tardis_id", id);
                qf.doDelete("doors", whereo);
                HashMap<String, Object> whereb = new HashMap<String, Object>();
                whereb.put("tardis_id", id);
                qf.doDelete("blocks", whereb);
                HashMap<String, Object> wherev = new HashMap<String, Object>();
                wherev.put("tardis_id", id);
                qf.doDelete("travellers", wherev);
                HashMap<String, Object> whereg = new HashMap<String, Object>();
                whereg.put("tardis_id", id);
                qf.doDelete("gravity_well", whereg);
                HashMap<String, Object> wheres = new HashMap<String, Object>();
                wheres.put("tardis_id", id);
                qf.doDelete("destinations", wheres);
                sender.sendMessage(plugin.getPluginName() + "The TARDIS was removed from the world and database successfully.");
            } else {
                sender.sendMessage(plugin.getPluginName() + "Could not find player [" + args[1] + "] in the database!");
                return true;
            }
        } else {
            sender.sendMessage(plugin.getPluginName() + "Could not find UUID for player [" + args[1] + "]!");
            return true;
        }
        return true;
    }

    private int getRestore(World w) {
        if (w == null || w.getWorldType() == WorldType.FLAT || w.getName().equals("TARDIS_TimeVortex") || w.getGenerator() instanceof TARDISChunkGenerator) {
            return 0;
        }
        Environment env = w.getEnvironment();
        switch (env) {
            case NETHER:
                return 87;
            case THE_END:
                return 121;
            default:
                return 1;
        }
    }
}
