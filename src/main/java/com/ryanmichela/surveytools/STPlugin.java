//    Copyright (C) 2012  Ryan Michela
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

package com.ryanmichela.surveytools;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldSaveEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 */
public class STPlugin extends JavaPlugin implements Listener {
    public static STPlugin instance;

    public SurveyMarkers markers;

    @Override
    public void onLoad() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
            saveDefaultConfig();
        }
    }

    @Override
    public void onEnable() {
        instance = this;
        markers = new SurveyMarkers(getDataFolder().getAbsolutePath() + "/markers.yml");

        // Wire up events
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new SetMarkerHandler(), this);
        getServer().getPluginManager().registerEvents(new MarkerBreakHandler(), this);
    }

    @EventHandler
    private void onWorldSave(WorldSaveEvent event) {
        STPlugin.instance.markers.save();
    }
}
