package org.sdatag.airdrop.event;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.sdatag.airdrop.AirDrop;
import org.sdatag.airdrop.block.entity.client.Flare_Block_Renderer;
import org.sdatag.airdrop.block.entity.ModBlockEntities;
import org.sdatag.airdrop.block.entity.client.gunDrop_Renderer;
import org.sdatag.airdrop.entity.ModentityTypes;
import org.sdatag.airdrop.entity.custom.Ac_130Entity;
import org.sdatag.airdrop.particle.ModParticles;
import org.sdatag.airdrop.particle.custom.CustomCampfireSmokeParticle;


@Mod.EventBusSubscriber(modid = AirDrop.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventClientBusEvents {

    @SubscribeEvent
    public static void registerRenders(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.Flare_Block_Entity.get(), Flare_Block_Renderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.gunDrop_Enity.get(), gunDrop_Renderer::new);
    }

    @SubscribeEvent
    public static void registerParticleProvidersEvent(final RegisterParticleProvidersEvent event) {
        event.register(ModParticles.CustomCampfireSmokeParticle.get(),
                CustomCampfireSmokeParticle.SignalProvider::new);
    }
    @Mod.EventBusSubscriber(modid = AirDrop.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents{
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event){
            event.put((EntityType<? extends LivingEntity>) ModentityTypes.AC130.get(), Ac_130Entity.setAttributes());
        }
    }
}

