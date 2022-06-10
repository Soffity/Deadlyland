package org.aeldi.deadlyland.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.tag.ItemTags;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
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
        switch(name){
            case "red_crystal":
                atr = EntityAttributes.GENERIC_ATTACK_SPEED.setTracked(true);
                user.getInventory()
                        .armor
                        .get(2)
                        .addAttributeModifier(atr, new EntityAttributeModifier("generic.attack_speed", 0.5d, EntityAttributeModifier.Operation.ADDITION), EquipmentSlot.CHEST);
                break;
            case "blue_crystal":
                atr = EntityAttributes.GENERIC_MOVEMENT_SPEED.setTracked(true);
                user.getInventory()
                        .armor
                        .get(0)
                        .addAttributeModifier(atr, new EntityAttributeModifier("generic.movement_speed", 1.0d, EntityAttributeModifier.Operation.ADDITION),EquipmentSlot.FEET);
                break;
            case "purple_crystal":
                atr = EntityAttributes.GENERIC_MAX_HEALTH.setTracked(true);
                user.getInventory()
                        .armor
                        .get(1)
                        .addAttributeModifier(atr, new EntityAttributeModifier("generic.max_health", 4.0d, EntityAttributeModifier.Operation.ADDITION),EquipmentSlot.LEGS);
                break;
            case "green_crystal":
                user.jump();
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 9999,1,false,false));
                break;
        }
        int slot = user.getInventory().selectedSlot;
        user.getInventory().updateItems();
        user.getInventory().main.get(slot).decrement(1);
        return super.use(world, user, hand);
    }
}
