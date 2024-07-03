package org.sdatag.airdrop.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.sdatag.airdrop.AirDrop;
import org.sdatag.airdrop.block.Modblock;
import org.sdatag.airdrop.item.custom.Flare_Block_Item;

public class Moditems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AirDrop.MODID);

    public static final RegistryObject<Item> Flare_Block_Item = ITEMS.register("flare_block",
            () -> new Flare_Block_Item(Modblock.flare_block.get(), new Item.Properties()));

    public static final RegistryObject<Item> gunDrop_Item = ITEMS.register("gun_drop",
            () -> new Flare_Block_Item(Modblock.gunDropblock.get(), new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
