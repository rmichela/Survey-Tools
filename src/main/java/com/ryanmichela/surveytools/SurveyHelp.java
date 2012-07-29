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

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.help.HelpTopic;

/**
 */
public class SurveyHelp extends HelpTopic{
    @Override
    public boolean canSee(CommandSender commandSender) {
        return commandSender instanceof Player;
    }

    @Override
    public String getName() {
        return "SurveyTools";
    }

    @Override
    public String getShortText() {
        return "Instructions for using Survey Tools";
    }

    @Override
    public String getFullText(CommandSender forWho) {
        String help = "&bSurvey Tools gives you distance measurements between\n" +
                      "two points using Redstone Torches.\n" +
                      "&6&l1.&f Place a &cRedstone Torch&f on the block you would\n" +
                      "like to &nmeasure from.&r\n" +
                      "&6&l2.&f Using a second &cRedstone Torch&f, &6shift-right-click&f\n" +
                      "the first &cRedstone Torch&f to set your survey marker.\n" +
                      "&6&l3.&f &6Shift-right-click&f another block to get a Survey\n" +
                      "Report with the distance to the first block.\n";
        return ChatColor.translateAlternateColorCodes('&', help);
    }
}
