package me.eccentric_nz.TARDIS.travel;

import me.eccentric_nz.TARDIS.TARDIS;
import me.eccentric_nz.TARDIS.database.resultset.ResultSetThrottle;
import me.eccentric_nz.TARDIS.enumeration.SpaceTimeThrottle;
import me.eccentric_nz.TARDIS.enumeration.TravelType;
import me.eccentric_nz.TARDIS.messaging.TARDISMessage;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ComehereAction {

    private final TARDIS plugin;

    public ComehereAction(TARDIS plugin) {
        this.plugin = plugin;
    }

    public void doTravel(ComehereRequest request) {
        // get players
        Player acceptor = plugin.getServer().getPlayer(request.getAccepter());
        Player requester = plugin.getServer().getPlayer(request.getRequester());
        boolean hidden = request.isHidden();
        // get space time throttle
        SpaceTimeThrottle spaceTimeThrottle = new ResultSetThrottle(plugin).getSpeed(request.getAccepter().toString());
        int ch = Math.round(plugin.getArtronConfig().getInt("comehere") * spaceTimeThrottle.getArtronMultiplier());
        if (request.getLevel() < ch) {
            TARDISMessage.send(acceptor, "NOT_ENOUGH_ENERGY");
            TARDISMessage.send(requester, "NOT_ENOUGH_ENERGY");
            return;
        }
        World w = request.getCurrent().getWorld();
        Location oldSave = null;
        HashMap<String, Object> bid = new HashMap<>();
        bid.put("tardis_id", request.getId());
        HashMap<String, Object> bset = new HashMap<>();
        if (w != null) {
            oldSave = new Location(w, request.getCurrent().getX(), request.getCurrent().getY(), request.getCurrent().getZ());
            // set fast return location
            bset.put("world", request.getCurrent().getWorld().getName());
            bset.put("x", request.getCurrent().getX());
            bset.put("y", request.getCurrent().getY());
            bset.put("z", request.getCurrent().getZ());
            bset.put("direction", request.getCurrentDirection().toString());
            bset.put("submarine", request.isSubmarine());
        } else {
            hidden = true;
            // set fast return location
            bset.put("world", request.getDestination().getWorld().getName());
            bset.put("x", request.getDestination().getX());
            bset.put("y", request.getDestination().getY());
            bset.put("z", request.getDestination().getZ());
            bset.put("submarine", (request.isSubmarine()) ? 1 : 0);
        }
        plugin.getQueryFactory().doUpdate("back", bset, bid);
        HashMap<String, Object> tid = new HashMap<>();
        tid.put("tardis_id", request.getId());
        HashMap<String, Object> set = new HashMap<>();
        set.put("world", request.getDestination().getWorld().getName());
        set.put("x", request.getDestination().getBlockX());
        set.put("y", request.getDestination().getBlockY());
        set.put("z", request.getDestination().getBlockZ());
        set.put("direction", request.getDestinationDirection().toString());
        set.put("submarine", (request.isSubmarine()) ? 1 : 0);
        if (hidden) {
            HashMap<String, Object> sett = new HashMap<>();
            sett.put("hidden", 0);
            HashMap<String, Object> ttid = new HashMap<>();
            ttid.put("tardis_id", request.getId());
            plugin.getQueryFactory().doUpdate("tardis", sett, ttid);
        }
        plugin.getQueryFactory().doSyncUpdate("next", set, tid);
        plugin.getTrackerKeeper().getHasDestination().put(request.getId(), new TravelCostAndType(plugin.getArtronConfig().getInt("comehere"), TravelType.COMEHERE));
        TARDISMessage.send(acceptor, "REQUEST_RELEASE", requester.getName());
        TARDISMessage.send(requester, "REQUEST_ACCEPTED", acceptor.getName(), "travel");
    }
}
