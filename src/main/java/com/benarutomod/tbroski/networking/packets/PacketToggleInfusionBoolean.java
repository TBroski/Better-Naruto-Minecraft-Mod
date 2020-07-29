package com.benarutomod.tbroski.networking.packets;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketToggleInfusionBoolean {

    //1 = Hand
    //2 = Body
    //3 = Leg

    private int caseInfusion;
    private boolean toClient;
    private boolean toggle;
    private int playerID;

    public PacketToggleInfusionBoolean(int caseInfusion, boolean toClient, boolean toggle, int playerID)
    {
        this.caseInfusion = caseInfusion;
        this.toClient = toClient;
        this.toggle = toggle;
        this.playerID = playerID;
    }

    public static void encode(PacketToggleInfusionBoolean msg, PacketBuffer buf)
    {
        buf.writeInt(msg.caseInfusion);
        buf.writeBoolean(msg.toClient);
        buf.writeBoolean(msg.toggle);
        buf.writeInt(msg.playerID);
    }

    public static PacketToggleInfusionBoolean decode(PacketBuffer buf)
    {
        int caseInfusion = buf.readInt();
        boolean toClient = buf.readBoolean();
        boolean toggle = buf.readBoolean();
        int playerID = buf.readInt();
        return new PacketToggleInfusionBoolean(caseInfusion, toClient, toggle, playerID);
    }

    public static void handle(PacketToggleInfusionBoolean msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
                switch (msg.caseInfusion)
                {
                    case 1:
                        if (msg.toClient)
                        {
                            ClientPlayerEntity player = (ClientPlayerEntity) Minecraft.getInstance().world.getEntityByID(msg.playerID);
                            LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                            IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                            playercap.setHandInfusionToggled(msg.toggle);
                            playercap.setPlayerLeftDojutsu(playercap.returnPlayerLeftDojutsu());
                            playercap.setPlayerRightDojutsu(playercap.returnPlayerRightDojutsu());
                        }
                        else {
                            LazyOptional<IPlayerHandler> capabilities = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                            IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                            playercap.setHandInfusionToggled(msg.toggle);
                            playercap.setPlayerLeftDojutsu(playercap.returnPlayerLeftDojutsu());
                            playercap.setPlayerRightDojutsu(playercap.returnPlayerRightDojutsu());
                        }
                        break;
                    case 2:
                        if (msg.toClient)
                        {
                            ClientPlayerEntity player = (ClientPlayerEntity) Minecraft.getInstance().world.getEntityByID(msg.playerID);
                            LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                            IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                            playercap.setBodyInfusionToggled(msg.toggle);
                            playercap.setPlayerLeftDojutsu(playercap.returnPlayerLeftDojutsu());
                            playercap.setPlayerRightDojutsu(playercap.returnPlayerRightDojutsu());
                        }
                        else {
                           LazyOptional<IPlayerHandler> capabilities = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                           IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                           playercap.setBodyInfusionToggled(msg.toggle);
                           playercap.setPlayerLeftDojutsu(playercap.returnPlayerLeftDojutsu());
                           playercap.setPlayerRightDojutsu(playercap.returnPlayerRightDojutsu());
                        }
                        break;
                    case 3:
                        if (msg.toClient)
                        {
                            ClientPlayerEntity player = (ClientPlayerEntity) Minecraft.getInstance().world.getEntityByID(msg.playerID);
                            LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                            IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                            playercap.setLegInfusionToggled(msg.toggle);
                            playercap.setPlayerLeftDojutsu(playercap.returnPlayerLeftDojutsu());
                            playercap.setPlayerRightDojutsu(playercap.returnPlayerRightDojutsu());
                        }
                        else {
                            LazyOptional<IPlayerHandler> capabilities = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                            IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                            playercap.setLegInfusionToggled(msg.toggle);
                            playercap.setPlayerLeftDojutsu(playercap.returnPlayerLeftDojutsu());
                            playercap.setPlayerRightDojutsu(playercap.returnPlayerRightDojutsu());
                        }
                        break;

                }

        });
        ctx.get().setPacketHandled(true);
    }
}
