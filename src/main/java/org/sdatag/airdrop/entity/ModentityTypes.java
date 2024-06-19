package org.sdatag.airdrop.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.sdatag.airdrop.AirDrop;
import org.sdatag.airdrop.entity.custom.Ac_130Entity;

public class ModentityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AirDrop.MODID);

    public static final RegistryObject<EntityType<Ac_130Entity>> AC130 =
            ENTITY_TYPES.register("ac_130",
                    () -> EntityType.Builder.of(Ac_130Entity::new, MobCategory.MISC)
                            .sized(1, 1f).build(new ResourceLocation(AirDrop.MODID, "ac_130").toString()));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
