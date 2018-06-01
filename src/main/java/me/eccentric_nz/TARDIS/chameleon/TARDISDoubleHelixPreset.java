/*
 * Copyright (C) 2016 eccentric_nz
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
package me.eccentric_nz.TARDIS.chameleon;

/**
 * A chameleon conversion is a repair procedure that technicians perform on
 * TARDIS chameleon circuits. The Fourth Doctor once said that the reason the
 * TARDIS' chameleon circuit was stuck was because he had "borrowed" it from
 * Gallifrey before the chameleon conversion was completed.
 *
 * @author eccentric_nz
 */
public class TARDISDoubleHelixPreset extends TARDISPreset {

    private final String blueprint_id = "[[109,0,109,0],[109,0,109,0],[109,0,109,0],[109,0,109,0],[109,0,109,0],[109,0,109,0],[109,0,109,0],[109,0,109,0],[96,44,89,0],[0,0,68,0]]";
    private final String blueprint_data = "[[0,0,4,0],[2,0,6,0],[1,0,5,0],[1,0,5,0],[1,0,5,0],[3,0,7,0],[0,0,4,0],[0,0,4,0],[10,8,0,0],[0,0,4,0]]";
    private final String stained_id = "[[95,0,95,0],[95,0,95,0],[95,0,95,0],[95,0,95,0],[95,0,95,0],[95,0,95,0],[95,0,95,0],[95,0,95,0],[96,95,95,0],[0,0,68,0]]";
    private final String stained_data = "[[8,0,8,0],[8,0,8,0],[8,0,8,0],[8,0,8,0],[8,0,8,0],[8,0,8,0],[8,0,8,0],[8,0,8,0],[10,8,4,0],[0,0,4,0]]";
    private final String glass_id = "[[20,0,20,0],[20,0,20,0],[20,0,20,0],[20,0,20,0],[20,0,20,0],[20,0,20,0],[20,0,20,0],[20,0,20,0],[96,20,20,0],[0,0,68,0]]";
    private final String glass_data = "[[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0],[10,0,0,0],[0,0,4,0]]";

    public TARDISDoubleHelixPreset() {
        setBlueprint_id(blueprint_id);
        setBlueprint_data(blueprint_data);
        setStained_id(stained_id);
        setStained_data(stained_data);
        setGlass_id(glass_id);
        setGlass_data(glass_data);
    }
}