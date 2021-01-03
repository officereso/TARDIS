/*
 * Copyright (C) 2020 eccentric_nz
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
package me.eccentric_nz.TARDIS.travel;

import me.eccentric_nz.TARDIS.TARDIS;
import me.eccentric_nz.TARDIS.TARDISConstants;
import me.eccentric_nz.TARDIS.custommodeldata.GUISaves;
import me.eccentric_nz.TARDIS.database.resultset.ResultSetDestinations;
import me.eccentric_nz.TARDIS.utility.TARDISNumberParsers;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

/**
 * The "Hollywood" sign was among the Earth cultural items the Threshold stole and moved to the town of Wormwood on the
 * Moon. The moon was later destroyed; the sign likely was also.
 *
 * @author eccentric_nz
 */
public class TARDISSaveSignPageTwo {

    private final TARDIS plugin;
    private final ItemStack[] pageTwo;
    private final List<Integer> slots = new LinkedList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44));
    private final int id;

    public TARDISSaveSignPageTwo(TARDIS plugin, int id) {
        this.plugin = plugin;
        this.id = id;
        pageTwo = getItemStack();
    }

    /**
     * Constructs an inventory for the Save Sign GUI.
     *
     * @return an Array of itemStacks (an inventory)
     */
    private ItemStack[] getItemStack() {
        HashMap<Integer, ItemStack> dests = new HashMap<>();
        // saved destinations
        HashMap<String, Object> did = new HashMap<>();
        did.put("tardis_id", id);
        ResultSetDestinations rsd = new ResultSetDestinations(plugin, did, true);
        int i = 1;
        ItemStack[] stack = new ItemStack[54];
        if (rsd.resultSet()) {
            ArrayList<HashMap<String, String>> data = rsd.getData();
            // cycle through saves
            for (HashMap<String, String> map : data) {
                if (map.get("type").equals("0")) {
                    if (i > 44 && i < 90) {
                        ItemStack is = new ItemStack(TARDISConstants.GUI_IDS.get(i), 1);
                        ItemMeta im = is.getItemMeta();
                        im.setDisplayName(map.get("dest_name"));
                        List<String> lore = new ArrayList<>();
                        lore.add(map.get("world"));
                        lore.add(map.get("x"));
                        lore.add(map.get("y"));
                        lore.add(map.get("z"));
                        lore.add(map.get("direction"));
                        lore.add((map.get("submarine").equals("1")) ? "true" : "false");
                        if (!map.get("preset").isEmpty()) {
                            lore.add(map.get("preset"));
                        }
                        im.setLore(lore);
                        is.setItemMeta(im);
                        int slot;
                        if (!map.get("slot").equals("-1")) {
                            slot = TARDISNumberParsers.parseInt(map.get("slot"));
                        } else {
                            slot = slots.get(0);
                        }
                        dests.put(slot, is);
                        slots.remove(Integer.valueOf(slot));
                    }
                    i++;
                }
            }

            for (Integer s = 0; s < 45; s++) {
                stack[s] = dests.getOrDefault(s, null);
            }
            // add button to allow rearranging saves
            ItemStack tool = new ItemStack(Material.ARROW, 1);
            ItemMeta rearrange = tool.getItemMeta();
            rearrange.setDisplayName("Rearrange saves");
            rearrange.setCustomModelData(GUISaves.REARRANGE_SAVES.getCustomModelData());
            tool.setItemMeta(rearrange);
            // add button to allow deleting saves
            ItemStack bucket = new ItemStack(Material.BUCKET, 1);
            ItemMeta delete = bucket.getItemMeta();
            delete.setDisplayName("Delete save");
            delete.setCustomModelData(GUISaves.DELETE_SAVE.getCustomModelData());
            bucket.setItemMeta(delete);
            // add button to go to back to previous page
            ItemStack prev = new ItemStack(GUISaves.GO_TO_PAGE_1.getMaterial(), 1);
            ItemMeta page = prev.getItemMeta();
            page.setDisplayName(GUISaves.GO_TO_PAGE_1.getName());
            page.setCustomModelData(GUISaves.GO_TO_PAGE_1.getCustomModelData());
            prev.setItemMeta(page);
            // add button to load TARDIS areas
            ItemStack map = new ItemStack(Material.MAP, 1);
            ItemMeta switchto = map.getItemMeta();
            switchto.setDisplayName("Load TARDIS areas");
            switchto.setCustomModelData(GUISaves.LOAD_TARDIS_AREAS.getCustomModelData());
            map.setItemMeta(switchto);
            for (int m = 45; m < 54; m++) {
                switch (m) {
                    case 45:
                        stack[m] = tool;
                        break;
                    case 47:
                        stack[m] = bucket;
                        break;
                    case 51:
                        stack[m] = prev;
                        break;
                    case 53:
                        stack[m] = map;
                        break;
                    default:
                        stack[m] = null;
                        break;
                }
            }
        }
        return stack;
    }

    public ItemStack[] getPageTwo() {
        return pageTwo;
    }
}
