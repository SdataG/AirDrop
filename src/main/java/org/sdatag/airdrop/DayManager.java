package org.sdatag.airdrop;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
@Mod.EventBusSubscriber()
public class DayManager {
    private static int streak;
    private static int dayCount;
    private static List<DayListener> listeners = new ArrayList<>();
    private static List<ScheduledDelivery> scheduledDeliveries = new ArrayList<>();

    public static void addDayListener(DayListener listener) {
        listeners.add(listener);
    }

    public static void addScheduledDelivery(ScheduledDelivery delivery) {
        scheduledDeliveries.add(delivery);
    }

    public static void removeDayListener(DayListener listener) {
        listeners.remove(listener);
    }

    public static void removeScheduledDelivery(ScheduledDelivery delivery) {
        scheduledDeliveries.remove(delivery);
    }

    @SubscribeEvent
    public static void onTick(TickEvent.LevelTickEvent event){
        if(!(event.level instanceof ServerLevel)) return;
        int possibleDay = (int) (event.level.getDayTime() / 24000);
        if (event.level.getDayTime() % 24000 < 6000) {
            for (ScheduledDelivery delivery : scheduledDeliveries) {
                delivery.onDelivery(event.level);
            }
            scheduledDeliveries.clear();
        }
        if (possibleDay != streak) {
            dayCount += (possibleDay < streak) ? 1 : possibleDay - streak;
            for (DayListener listener : listeners) {
                listener.onDayChange(event.level, dayCount);
            }
        }
        streak = possibleDay;
    }

    public interface DayListener {
        void onDayChange(Level level, int day);
    }

    public interface ScheduledDelivery {
        void onDelivery(Level level);
    }
}
