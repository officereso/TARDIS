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
package me.eccentric_nz.TARDIS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import me.eccentric_nz.TARDIS.info.TARDISInfoMenu;
import me.eccentric_nz.TARDIS.rooms.TARDISSeedData;
import me.eccentric_nz.TARDIS.utility.TARDISAntiBuild;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

/**
 *
 * @author eccentric_nz
 */
public class TARDISTrackerInstanceKeeper {

    private final HashMap<Integer, Integer> trackDamage = new HashMap<Integer, Integer>();
    private final HashMap<Integer, Integer> trackHasDestination = new HashMap<Integer, Integer>();
    private final HashMap<Integer, String> trackRenderer = new HashMap<Integer, String>();
    private final HashMap<Integer, UUID> trackRescue = new HashMap<Integer, UUID>();
    private final HashMap<Integer, TARDISAntiBuild> trackAntiBuild = new HashMap<Integer, TARDISAntiBuild>();
    private final HashMap<String, Block> trackExterminate = new HashMap<String, Block>();
    private final HashMap<String, Block> trackLazarus = new HashMap<String, Block>();
    private final HashMap<String, Double[]> trackGravity = new HashMap<String, Double[]>();
    private final HashMap<String, Integer> trackBinder = new HashMap<String, Integer>();
    private final HashMap<UUID, Long> trackSetTime = new HashMap<UUID, Long>();
    private final HashMap<String, Sign> trackSign = new HashMap<String, Sign>();
    private final HashMap<String, String> trackBlock = new HashMap<String, String>();
    private final HashMap<UUID, UUID> trackChat = new HashMap<UUID, UUID>();
    private final HashMap<String, String> trackEnd = new HashMap<String, String>();
    private final HashMap<String, String> trackJettison = new HashMap<String, String>();
    private final HashMap<String, String> trackName = new HashMap<String, String>();
    private final HashMap<String, String> trackPerm = new HashMap<String, String>();
    private final HashMap<String, String> trackPlayers = new HashMap<String, String>();
    private final HashMap<String, String> trackPreset = new HashMap<String, String>();
    private final HashMap<String, String> trackSecondary = new HashMap<String, String>();
    private final HashMap<String, TARDISInfoMenu> trackInfoMenu = new HashMap<String, TARDISInfoMenu>();
    private final HashMap<String, TARDISSeedData> trackRoomSeed = new HashMap<String, TARDISSeedData>();
    private final List<Integer> trackDematerialising = new ArrayList<Integer>();
    private final List<Integer> trackInVortex = new ArrayList<Integer>();
    private final List<Integer> trackMaterialising = new ArrayList<Integer>();
    private final List<Integer> trackMinecart = new ArrayList<Integer>();
    private final List<Integer> trackSubmarine = new ArrayList<Integer>();
    private final List<String> trackArrangers = new ArrayList<String>();
    private final List<String> trackFarming = new ArrayList<String>();
    private final List<String> trackGeneticManipulation = new ArrayList<String>();
    private final List<String> trackRecipeView = new ArrayList<String>();
    private final List<String> trackReset = new ArrayList<String>();
    private final List<String> trackTransmat = new ArrayList<String>();
    private final List<String> trackZeroRoomOccupants = new ArrayList<String>();
    private String trackImmortalityGate = "";

    public HashMap<Integer, Integer> getTrackDamage() {
        return trackDamage;
    }

    public HashMap<Integer, Integer> getTrackHasDestination() {
        return trackHasDestination;
    }

    public HashMap<Integer, String> getTrackRenderer() {
        return trackRenderer;
    }

    public HashMap<Integer, UUID> getTrackRescue() {
        return trackRescue;
    }

    public HashMap<Integer, TARDISAntiBuild> getTrackAntiBuild() {
        return trackAntiBuild;
    }

    public HashMap<String, Block> getTrackExterminate() {
        return trackExterminate;
    }

    public HashMap<String, Block> getTrackLazarus() {
        return trackLazarus;
    }

    public HashMap<String, Double[]> getTrackGravity() {
        return trackGravity;
    }

    public HashMap<String, Integer> getTrackBinder() {
        return trackBinder;
    }

    public HashMap<UUID, Long> getTrackSetTime() {
        return trackSetTime;
    }

    public HashMap<String, Sign> getTrackSign() {
        return trackSign;
    }

    public HashMap<String, String> getTrackBlock() {
        return trackBlock;
    }

    public HashMap<UUID, UUID> getTrackChat() {
        return trackChat;
    }

    public HashMap<String, String> getTrackEnd() {
        return trackEnd;
    }

    public HashMap<String, String> getTrackJettison() {
        return trackJettison;
    }

    public HashMap<String, String> getTrackName() {
        return trackName;
    }

    public HashMap<String, String> getTrackPerm() {
        return trackPerm;
    }

    public HashMap<String, String> getTrackPlayers() {
        return trackPlayers;
    }

    public HashMap<String, String> getTrackPreset() {
        return trackPreset;
    }

    public HashMap<String, String> getTrackSecondary() {
        return trackSecondary;
    }

    public HashMap<String, TARDISInfoMenu> getTrackInfoMenu() {
        return trackInfoMenu;
    }

    public HashMap<String, TARDISSeedData> getTrackRoomSeed() {
        return trackRoomSeed;
    }

    public List<Integer> getTrackDematerialising() {
        return trackDematerialising;
    }

    public List<Integer> getTrackInVortex() {
        return trackInVortex;
    }

    public List<Integer> getTrackMaterialising() {
        return trackMaterialising;
    }

    public List<Integer> getTrackMinecart() {
        return trackMinecart;
    }

    public List<Integer> getTrackSubmarine() {
        return trackSubmarine;
    }

    public List<String> getTrackArrangers() {
        return trackArrangers;
    }

    public List<String> getTrackFarming() {
        return trackFarming;
    }

    public List<String> getTrackGeneticManipulation() {
        return trackGeneticManipulation;
    }

    public List<String> getTrackRecipeView() {
        return trackRecipeView;
    }

    public List<String> getTrackReset() {
        return trackReset;
    }

    public List<String> getTrackTransmat() {
        return trackTransmat;
    }

    public List<String> getTrackZeroRoomOccupants() {
        return trackZeroRoomOccupants;
    }

    public String getTrackImmortalityGate() {
        return trackImmortalityGate;
    }

    public void setTrackImmortalityGate(String trackImmortalityGate) {
        this.trackImmortalityGate = trackImmortalityGate;
    }
}
