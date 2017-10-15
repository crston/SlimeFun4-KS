package me.mrCookieSlime.ExoticGarden;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Tree {
	
	String sapling, schematic, texture, fruit;
	List<Material> soils;
	
	public Tree(String name, String fruit, String texture, Material... soil) {
		this.sapling = name + "_묘묙";
		this.schematic = name + "_TREE";
		this.texture = texture;
		this.fruit = fruit;
		this.soils = Arrays.asList(soil);
	}
	
	public Schematic getSchematic() throws IOException {
	    return Schematic.loadSchematic(new File("plugins/ExoticGarden/" + schematic + ".schematic"));
	}
	
	public ItemStack getItem() {
		return SlimefunItem.getByName(sapling).getItem();
	}
	
	public String getTexture() {
		return this.texture;
	}

	public ItemStack getFruit() {
		return SlimefunItem.getByName(fruit).getItem();
	}

	public String getSapling() {
		return this.sapling;
	}
	
	public boolean isSoil(Material material) {
		return soils.contains(material);
	}

}
