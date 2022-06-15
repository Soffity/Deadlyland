package org.aeldi.deadlyland.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CrystalItem extends Item {
    public String name;

    public CrystalItem(Settings settings, String name) {
        super(settings);
        this.name = name;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText(stack.getTranslationKey() + ".tooltip").formatted(Formatting.AQUA));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        EntityAttribute atr;
        switch (name) {
            case "red_crystal":
                atr = EntityAttributes.GENERIC_ATTACK_SPEED;
                ItemStack item_x = user.getInventory().armor.get(2);
                var def_x = item_x.getAttributeModifiers(EquipmentSlot.CHEST);
                item_x.addAttributeModifier(atr, new EntityAttributeModifier("attack", 0.5d, EntityAttributeModifier.Operation.ADDITION), EquipmentSlot.HEAD);
                def_x.forEach((d_atr, d_atr_mod) -> {
                    item_x.addAttributeModifier(d_atr, d_atr_mod, EquipmentSlot.HEAD);
                });
                break;
            case "blue_crystal":
                atr = EntityAttributes.GENERIC_MOVEMENT_SPEED;
                ItemStack item_0x = user.getInventory().armor.get(0);
                var def_0x = item_0x.getAttributeModifiers(EquipmentSlot.FEET);
                item_0x.addAttributeModifier(atr, new EntityAttributeModifier("moverment", 0.05d, EntityAttributeModifier.Operation.ADDITION), EquipmentSlot.HEAD);
                def_0x.forEach((d_atr, d_atr_mod) -> {
                    item_0x.addAttributeModifier(d_atr, d_atr_mod, EquipmentSlot.FEET);
                });
                break;
            case "purple_crystal":
                atr = EntityAttributes.GENERIC_MAX_HEALTH;
                ItemStack item_1x = user.getInventory().armor.get(3);
                var def_1x = item_1x.getAttributeModifiers(EquipmentSlot.HEAD);
                item_1x.addAttributeModifier(atr, new EntityAttributeModifier("health_boost", 4.0d, EntityAttributeModifier.Operation.ADDITION), EquipmentSlot.HEAD);
                def_1x.forEach((d_atr, d_atr_mod) -> {
                    item_1x.addAttributeModifier(d_atr, d_atr_mod, EquipmentSlot.HEAD);
                });
                break;
            case "green_crystal":
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 9999, 1, false, false));
                break;
        }
        int slot = user.getInventory().selectedSlot;
        user.getInventory().main.get(slot).decrement(1);
        return super.use(world, user, hand);
    }
}
