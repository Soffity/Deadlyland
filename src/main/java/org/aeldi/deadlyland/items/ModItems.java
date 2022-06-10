package org.aeldi.deadlyland.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.aeldi.deadlyland.Deadlyland;

public class ModItems {

    public static final FoodComponent BAOBAB_FRUIT = new FoodComponent.Builder().hunger(4).saturationModifier(1.75f).build();
    public static final ToolItem NETHERITE_HAMMER = new ToolItem(ToolMaterials.NETHERITE, new FabricItemSettings().group(Deadlyland.MOD_GROUP));

    public static final CrystalItem PINK_CRYSTAL = new CrystalItem(new FabricItemSettings().group(Deadlyland.MOD_GROUP), "pink_crystal");

    public static final CrystalItem RED_CRYSTAL = new CrystalItem(new FabricItemSettings().group(Deadlyland.MOD_GROUP), "red_crystal");
    public static final CrystalItem GREEN_CRYSTAL = new CrystalItem(new FabricItemSettings().group(Deadlyland.MOD_GROUP), "green_crystal");
    public static final CrystalItem BLUE_CRYSTAL = new CrystalItem(new FabricItemSettings().group(Deadlyland.MOD_GROUP), "blue_crystal");
    public static final CrystalItem PURPLE_CRYSTAL = new CrystalItem(new FabricItemSettings().group(Deadlyland.MOD_GROUP), "purple_crystal");

    public static void registerItems() {
        registerItem("netherite_hammer", NETHERITE_HAMMER);
        registerItem("baobab_fruit", new Item(new FabricItemSettings().group(Deadlyland.MOD_GROUP).food(BAOBAB_FRUIT)));
        registerItem("pink_crystal", PINK_CRYSTAL);
        registerItem("red_crystal", RED_CRYSTAL);
        registerItem("green_crystal", GREEN_CRYSTAL);
        registerItem("blue_crystal", BLUE_CRYSTAL);
        registerItem("purple_crystal", PURPLE_CRYSTAL);
    }

    public static void registerItem(String name, Item item) {
        Registry.register(Registry.ITEM, new Identifier(Deadlyland.mod_namespace, name), item);
    }
}
