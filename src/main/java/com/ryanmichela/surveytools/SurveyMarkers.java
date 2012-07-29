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

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

/**
 */
public class SurveyMarkers {

    private File file;
    private YamlConfiguration locations;

    public SurveyMarkers(String locationsPath) {
        file = new File(locationsPath);
        if (file.exists()) {
            locations = YamlConfiguration.loadConfiguration(file);
        }
    }

    public void setSurveyMark(Player player, Location location) {
        ConfigurationSection worldSection = locations.createSection(player.getWorld().getName());
        ConfigurationSection locationSection = worldSection.createSection(player.getName());
        locationSection.set("x", location.getBlockX());
        locationSection.set("y", location.getBlockY());
        locationSection.set("z", location.getBlockZ());
    }

    public Location getSurveyMark(Player player) {
        ConfigurationSection worldSection = locations.getConfigurationSection(player.getWorld().getName());
        if (worldSection == null) return null;
        ConfigurationSection locationSection = worldSection.getConfigurationSection(player.getName());
        if (locationSection == null) return null;
        return new Location(player.getWorld(), locationSection.getDouble("x"), locationSection.getDouble("y"), locationSection.getDouble("z"));
    }

    public void clearSurveyMark(Player player) {
        ConfigurationSection worldSection = locations.getConfigurationSection(player.getWorld().getName());
        if (worldSection == null) return;
        worldSection.set(player.getName(), null);
    }

    public void save() {
        try {
            locations.save(file);
        } catch (IOException e) {
            STPlugin.instance.getLogger().warning("Failed to save survey markers to " + file.getAbsolutePath());
            STPlugin.instance.getLogger().warning("SurveyTools will continue to function but survey markers will be lost if the server restarts.");
        }
    }
}
