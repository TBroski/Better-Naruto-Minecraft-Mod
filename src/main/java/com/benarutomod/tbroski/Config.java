package com.benarutomod.tbroski;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {

    public static class Common {

        public final ForgeConfigSpec.BooleanValue entityWorldDamage;
        public final ForgeConfigSpec.BooleanValue playerWorldDamage;
        public final ForgeConfigSpec.BooleanValue byakuganCanSeePlayers;
        public final ForgeConfigSpec.IntValue sharinganDodgedDamage;
        public final ForgeConfigSpec.IntValue susanooStageIncrement;

        public Common(ForgeConfigSpec.Builder builder) {

            builder.comment("BeNM Mod Configuration")
                    .push("benarutomod");

            playerWorldDamage = builder
                    .comment("Defines world damage from jutsu and items, such as wind explosion jutsu. The value true subscribes to world damage.")
                    .translation("config." + Main.MODID + ".playerWorldDamage")
                    .worldRestart()
                    .define("playerWorldDamage", true);

            entityWorldDamage = builder
                    .comment("Defines world damage from items, such as explosive kunai thrown by mobs. The value true subscribes to world damage.")
                    .translation("config." + Main.MODID + ".entityWorldDamage")
                    .worldRestart()
                    .define("entityWorldDamage", true);

            byakuganCanSeePlayers = builder
                    .comment("Defines if the byakugan can see players when active. The value true allows the byakugan to see players.")
                    .translation("config." + Main.MODID + ".byakuganCanSeePlayers")
                    .worldRestart()
                    .define("byakuganCanSeePlayers", true);

            sharinganDodgedDamage = builder
                    .comment("Defines the amount current damage may be reduced by if the player has the sharingan, such as if the player takes 10 damage the value [2] will reduce it to 8.")
                    .translation("config." + Main.MODID + ".sharinganDodgedDamage")
                    .worldRestart()
                    .defineInRange("sharinganDodgedDamage", 2, 1, 99);

            susanooStageIncrement = builder
                    .comment("Defines the increment in ticks the player's susanoo will evolve to the next state.")
                    .translation("config." + Main.MODID + ".susanooStageIncrement")
                    .worldRestart()
                    .defineInRange("susanooStageIncrement", 400, 20, 1000);

            builder.pop();
        }
    }

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;
    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }
}
