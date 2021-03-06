package net.prismatic.equipmentplus.api;

import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketItem;
import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.prismatic.equipmentplus.core.EquipmentPlusAbilities;
import org.apache.logging.log4j.LogManager;


public class Ring extends TrinketItem {
    private final int type;
    private final StatusEffectInstance effect;
    private final EntityAttributeModifier modifier;

    public Ring(int type) {
        super(new Settings().maxCount(1));
        if (type <= 0) {
            this.type = -1;
        } else {
            this.type = type;
        }
        this.effect = null;
        this.modifier = null;
    }

    public Ring(StatusEffectInstance effect) {
        super(new Settings().maxCount(1));
        this.type = 0;
        this.effect = effect;
        this.modifier = null;
    }

    public Ring(EntityAttributeModifier modifier) {
        super(new Settings().maxCount(1));
        this.type = 0;
        this.effect = null;
        this.modifier = modifier;
    }

    @Override
    public void onEquip(PlayerEntity player, ItemStack stack) {
        if (this.type <= 0) {
            LogManager.getLogger("EquipmentPlus").error("Someone fucked up! [Type of '" + this.getName().asString() + "' was <= 0 during construction]");
            EquipmentPlusAbilities.RING.get(ComponentProvider.fromEntity(player)).type = 0;
        }

        EquipmentPlusAbilities.RING.get(ComponentProvider.fromEntity(player)).state = true;

        if (this.type > 0) {
            EquipmentPlusAbilities.RING.get(ComponentProvider.fromEntity(player)).type = this.type;
        }

        if (this.modifier != null) {
            EquipmentPlusAbilities.RING.get(ComponentProvider.fromEntity(player)).modifier = this.modifier;
        }

        if (this.effect != null) {
            EquipmentPlusAbilities.RING.get(ComponentProvider.fromEntity(player)).effect = this.effect;
        }
    }

    @Override
    public void onUnequip(PlayerEntity player, ItemStack stack) {
        EquipmentPlusAbilities.RING.get(ComponentProvider.fromEntity(player)).type = 0;
        EquipmentPlusAbilities.RING.get(ComponentProvider.fromEntity(player)).state = false;
        EquipmentPlusAbilities.RING.get(ComponentProvider.fromEntity(player)).modifier = null;
        EquipmentPlusAbilities.RING.get(ComponentProvider.fromEntity(player)).effect = null;
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return slot.equals(Slots.RING);
    }
}