package org.sdatag.airdrop;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sdatag.airdrop.block.Modblock;
import org.sdatag.airdrop.entity.ModentityTypes;
import org.sdatag.airdrop.entity.custom.Ac_130Entity;

import java.util.List;
import java.util.Random;


@Mod.EventBusSubscriber(modid = AirDrop.MODID)
public class DropChance {
    public static final Logger LOG = LogManager.getLogger();
    private static int remainingDays;

    public static void scheduleAirdrops() {
        boolean consistentDropInterval = true;
        int dropIntervalInDays = 1;
        int startRange = 3;
        int endRange = 7;
        remainingDays = (int) (Math.random() * (endRange - startRange) + startRange);
        DayManager.addDayListener((level, day) -> handleDayChange(level, day, consistentDropInterval, dropIntervalInDays, startRange, endRange));
    }

    private static void handleDayChange(Level level, int day, boolean consistentDropInterval, int dropIntervalInDays, int startRange, int endRange) {
        if (consistentDropInterval) {
            if (day % dropIntervalInDays == 0)
                DayManager.addScheduledDelivery(DropChance::onAirdrop);
            return;
        }
        if (remainingDays <= 0) {
            DayManager.addScheduledDelivery(DropChance::onAirdrop);
            remainingDays = (int) (Math.random() * (endRange - startRange) + startRange);
        } else {
            remainingDays--;
        }
    }

    /**
     * Drops an airdrop in the given server level near a randomly selected player.
     * If no players are online, no airdrop is dropped.
     *
     * @param serverLevel the server level in which to drop the airdrop
     */
    private static void onAirdrop(Level serverLevel) {
        LOG.info("Dropping airdrop.");
        List<ServerPlayer> players = (List<ServerPlayer>) serverLevel.players();
        ServerPlayer randomPlayer = null;
        Vec3 dropPosition = null;
        if (!players.isEmpty()) {
            Random rand = new Random();
            randomPlayer = players.get(rand.nextInt(players.size()));
            LOG.info("Randomly selected player: " + randomPlayer.getName().getContents());
        } else {
            LOG.info("No players online.");
        }
        if (randomPlayer != null) {
            int radius = 200; // radius in blocks
            int innerRadius = 100; // inner radius where drop can't happen
            int yMax = 255; // Max height in the Minecraft world
            int x, z;
            // Repeat until we get coordinates outside of inner radius
            do {
                x = (int) (randomPlayer.getX() + (radius * Math.random()) - (radius / 2));
                z = (int) (randomPlayer.getZ() + (radius * Math.random()) - (radius / 2));
            } while (Math.sqrt(Math.pow(randomPlayer.getX() - x, 2) + Math.pow(randomPlayer.getZ() - z, 2)) < innerRadius);
            int y = yMax;
            boolean resetPosition = false;
            BlockPos pos = null;
            for (y = yMax; y > 0; y--) {
                pos = new BlockPos(x, y, z);
                if (serverLevel.getBlockState(pos).getBlock() == Blocks.WATER || serverLevel.getBlockState(pos).getTags().anyMatch(blockTagKey -> blockTagKey.location().equals(new ResourceLocation("minecraft:leaves")))) {
                    LOG.info("Invalid spot selected. Resetting...");
                    resetPosition = true;
                    break;
                }
                if (serverLevel.getBlockState(pos).isSolidRender(serverLevel, pos) && !serverLevel.isEmptyBlock(pos)) {
                    dropPosition = new Vec3(x, y + 1, z);
                    setDropLocation(new Vec3(dropPosition.x, dropPosition.y + 50, dropPosition.z) );
                    double acx = randomPlayer.getX() ;
                    double acy = randomPlayer.getY() + 50;
                    double acz = randomPlayer.getZ() ;

                    EntityType<Ac_130Entity> ac130Type = ModentityTypes.AC130.get();

                    Vec3 acLocation = new Vec3(acx, acy, acz);
                    Ac_130Entity ac130Entity = ac130Type.create(serverLevel);
                    if (ac130Entity != null) {
                        ac130Entity.moveTo(acLocation);
                        serverLevel.addFreshEntity(ac130Entity);
                    }
                    serverLevel.setBlock(new BlockPos(dropPosition.x, dropPosition.y, dropPosition.z), Modblock.flare_block.get().defaultBlockState(), 3);
                    LOG.info(String.format("Drop coordinates: X=%s, Y=%s, Z=%s", dropPosition.x, dropPosition.y, dropPosition.z));
                    break;
                }
            }
            if (resetPosition) {
                onAirdrop(serverLevel);

            } else {
                return;
            }
        }
    }

    private static void setDropLocation(Vec3 vec3) {
        dropLocation = vec3;
    }

    private static Vec3 dropLocation;


    public static Vec3 getDropLocation() {
        return dropLocation;
    }
}


