package org.sdatag.airdrop.config;


import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class config {

    public static void register() {
        registerServerConfigs();
    }

    private static void registerServerConfigs() {
        ForgeConfigSpec.Builder Server_BUILDER =new ForgeConfigSpec.Builder();
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Server_BUILDER.build());
    }
}