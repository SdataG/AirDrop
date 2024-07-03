package org.sdatag.airdrop;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.sdatag.airdrop.Sound.ModSounds;
import org.sdatag.airdrop.entity.ModentityTypes;
import org.sdatag.airdrop.entity.client.Ace_130Renderer;
import org.sdatag.airdrop.entity.custom.Ac_130Entity;
import org.sdatag.airdrop.item.Moditems;
import org.sdatag.airdrop.block.Modblock;
import org.sdatag.airdrop.block.entity.ModBlockEntities;
import org.sdatag.airdrop.block.entity.client.Flare_Block_Renderer;
import org.sdatag.airdrop.config.config;
import org.sdatag.airdrop.particle.ModParticles;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AirDrop.MODID)
public class AirDrop {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "airdrop";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "airdrop" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    // Creates a new Block with the id "airdrop:example_block", combining the namespace and path
    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    // Creates a new BlockItem with the id "airdrop:example_block", combining the namespace and path
    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public AirDrop() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        config.register();

        GeckoLib.initialize();

        // Register the Deferred Register to the mod event bus so blocks get registered
        Modblock.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        Moditems.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        ModParticles.register(modEventBus);
        ModentityTypes.register(modEventBus);
        ModSounds.register(modEventBus);
        DropChance.scheduleAirdrops();
    }
    public static final RegistryObject<Item> Flare_Block_Item = ITEMS.register("flare_block",
            () -> new BlockItem(Modblock.flare_block.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event){
            BlockEntityRenderers.register(ModBlockEntities.Flare_Block_Entity.get(), Flare_Block_Renderer::new);
            EntityRenderers.register(ModentityTypes.AC130.get(), Ace_130Renderer::new);

        }

    }
}
