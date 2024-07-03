package org.sdatag.airdrop.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.sdatag.airdrop.AirDrop;
import org.sdatag.airdrop.block.Modblock;
import org.sdatag.airdrop.block.entity.custom.Flare_BlockEntity;
import org.sdatag.airdrop.block.entity.custom.gunDropEntity;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, AirDrop.MODID);


public static final RegistryObject<BlockEntityType<Flare_BlockEntity>> Flare_Block_Entity =
        BLOCK_ENTITIES.register("flare_block_entity", () ->
                BlockEntityType.Builder.of(Flare_BlockEntity::new,
                        Modblock.flare_block.get()).build(null));

    public static final RegistryObject<BlockEntityType<gunDropEntity>> gunDrop_Enity =
            BLOCK_ENTITIES.register("gundrop_entity", () ->
                    BlockEntityType.Builder.of(gunDropEntity::new,
                            Modblock.gunDropblock.get()).build(null));


    public static  void  register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}


