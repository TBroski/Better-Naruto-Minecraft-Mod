package com.benarutomod.tbroski;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {

    public static class Server {

        public final ForgeConfigSpec.BooleanValue entityWorldDamage;
        public final ForgeConfigSpec.BooleanValue byakuganCanSeePlayers;
        public final ForgeConfigSpec.IntValue sharinganDodgedDamage;

        public Server(ForgeConfigSpec.Builder builder) {

            builder.comment("BeNM Mod Configuration")
                    .push("benarutomod");

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

            builder.pop();
        }
    }

    public static final ForgeConfigSpec SERVER_SPEC;
    public static final Server SERVER;
    static {
        final Pair<Server, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Server::new);
        SERVER_SPEC = specPair.getRight();
        SERVER = specPair.getLeft();
    }
}
