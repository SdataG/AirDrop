package org.sdatag.airdrop.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.sdatag.airdrop.AirDrop;
import org.sdatag.airdrop.block.custom.gunDropblock;
import org.sdatag.airdrop.item.Moditems;
import org.sdatag.airdrop.block.custom.flare_block;


import java.util.function.Supplier;

public class Modblock {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, AirDrop.MODID);

    public static final RegistryObject<Block> flare_block = BLOCKS.register("flare_block",
            ()-> new flare_block(BlockBehaviour.Properties.of(Material.STONE).noOcclusion()));
    public static final RegistryObject<Block> gunDropblock = BLOCKS.register("gun_drop",
            ()-> new gunDropblock(BlockBehaviour.Properties.of(Material.STONE).noOcclusion()));



    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
       RegistryObject<T> toReturn = BLOCKS.register(name, block);
       registerBlockItem(name, toReturn);
       return toReturn;
    }


    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return Moditems.ITEMS.register(name, ()-> new BlockItem(block.get(),
                new Item.Properties()));
    }

    public static  void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
