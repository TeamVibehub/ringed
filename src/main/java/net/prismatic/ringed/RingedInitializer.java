package net.prismatic.ringed;

import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketSlots;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.util.EntityComponents;
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.prismatic.ringed.api.RingItem;
import net.prismatic.ringed.component.ShieldingComponent;
import net.prismatic.ringed.item.ShieldingRing;

public class RingedInitializer implements ModInitializer {

    public static final ComponentType<ShieldingComponent> SHIELDING =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("ringed", "shielding"), ShieldingComponent.class);

    static RingItem RING = new RingItem();
    static ShieldingRing SHIELDING_RING = new ShieldingRing();
    public static ItemGroup RINGED = FabricItemGroupBuilder.create(
            new Identifier("ringed", "ringed"))
            .icon(() -> new ItemStack(RING))
            .appendItems(stacks -> stacks.add(new ItemStack(RING)))
            .build();

    @Override
    public void onInitialize() {
        EntityComponents.setRespawnCopyStrategy(SHIELDING, RespawnCopyStrategy.ALWAYS_COPY);
        TrinketSlots.addSlot(SlotGroups.HAND, Slots.RING, new Identifier("trinkets", "textures/item/empty_trinket_slot_ring.png"));
        Registry.register(Registry.ITEM, new Identifier("ringed", "ring"), RING);
        Registry.register(Registry.ITEM, new Identifier("ringed", "shielding_ring"), SHIELDING_RING);
    }
}
