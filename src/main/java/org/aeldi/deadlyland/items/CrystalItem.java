package org.aeldi.deadlyland.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
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
        Iterable<ItemStack> armor = user.getArmorItems();
        switch (name) {
            case "red_crystal":
                atr = EntityAttributes.GENERIC_ATTACK_SPEED;
                armor.forEach((ItemStack item) -> {
                    item.addAttributeModifier(atr, new EntityAttributeModifier("generic.attack_speed", 0.3d, EntityAttributeModifier.Operation.ADDITION), EquipmentSlot.LEGS);
                });
                break;
            case "blue_crystal":
                atr = EntityAttributes.GENERIC_MOVEMENT_SPEED;
                armor.forEach((ItemStack item) -> {
                    item.addAttributeModifier(atr, new EntityAttributeModifier("generic.movement_speed", 0.3d, EntityAttributeModifier.Operation.ADDITION), EquipmentSlot.LEGS);
                });
                break;
            case "purple_crystal":
                atr = EntityAttributes.GENERIC_MAX_HEALTH;
                armor.forEach((ItemStack item) -> {
                    NbtCompound nbt = new NbtCompound();
                    item.addAttributeModifier(atr, new EntityAttributeModifier("generic.max_health", 4.0d, EntityAttributeModifier.Operation.ADDITION), EquipmentSlot.HEAD);
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
