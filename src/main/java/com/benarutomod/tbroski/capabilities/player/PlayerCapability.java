package com.benarutomod.tbroski.capabilities.player;

import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.internal.BeNMBody;
import com.benarutomod.tbroski.api.internal.BeNMClan;
import com.benarutomod.tbroski.api.internal.jutsu.BeNMJutsu;
import com.benarutomod.tbroski.api.internal.dojutsu.BeNMDojutsu;
import com.benarutomod.tbroski.init.BodyInit;
import com.benarutomod.tbroski.init.ClanInit;
import com.benarutomod.tbroski.init.DojutsuInit;
import com.benarutomod.tbroski.util.helpers.BodyHelper;
import com.benarutomod.tbroski.util.helpers.ClanHelper;
import com.benarutomod.tbroski.util.helpers.DojutsuHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerCapability implements IPlayerHandler {

    private String key1 = "";
    private String key2 = "";
    private String key3 = "";
    private String key4 = "";
    private String key5 = "";
    private String key6 = "";
    private String key7 = "";
    private String key8 = "";
    private String key9 = "";

    private List<BeNMClan> learntClans = new ArrayList<>();
	private float chakra;
	private float maxchakra;
	private float regenchakra;
	private float chakraControl;
	private int chakracolor;
	private int playereyeslot;
	private int taijutsu;
	private int genjutsu;
	private int benmpoints;
	private int playerLevel;
	private int playerBijuuLevel;
	private int truthSeekingOrbs;
	private boolean bodyToggled;
	private boolean handToggled;
	private boolean legToggled;
	private String entityAffiliation = "";
	private String bijuu = "";
	private BeNMDojutsu playerLeftDojutsu = DojutsuInit.NULL;
	private BeNMDojutsu playerRightDojutsu = DojutsuInit.NULL;
	private BeNMBody playerBody = BodyInit.NULL;
	private BeNMClan clan = ClanInit.NULL;
	private ItemStack susanooMainHand = ItemStack.EMPTY;
	private ItemStack susanooOffHand = ItemStack.EMPTY;
	private int curseMark;
	private int toadSage;
	private boolean toggleMessageJutsu;
	private boolean toggleScrollRenderer;
    private boolean chakraBoolean;
    private boolean fireNature;
    private boolean lightningNature;
    private boolean windNature;
    private boolean earthNature;
    private boolean waterNature;
    private boolean magnetNature;
    private boolean woodNature;
    private boolean iceNature;
    private boolean lavaNature;
    private boolean boilNature;
    private boolean explosionNature;
    private boolean stormNature;
    private boolean scorchNature;
	private boolean joined;

	private Map<BeNMJutsu, Boolean> jutsuBooleans = new HashMap<>();
	
	@Override
    public float returnChakra() {
        return this.chakra;
    }
	@Override
	public void setChakra(float amount) {
		this.chakra = amount;
	}
    @Override
    public void addChakra(float amount) {
        this.chakra += amount;
        
        if (this.chakra > this.maxchakra)
        	this.chakra = this.maxchakra;

        if (this.chakra < 0)
            this.chakra = 0;
    }
    

    @Override
    public boolean joinWorld() {
    	return this.joined;
    }
    @Override
    public void setjoinWorld(boolean joined) {
    	this.joined = joined;
    }
    

    @Override
    public void setmaxChakra(float amount) {
    	this.maxchakra = amount;
    }
    @Override
    public void addmaxChakra(float amount) {
    	this.maxchakra += amount;
    }
    @Override
    public float returnmaxChakra() {
    	return this.maxchakra;
    }
    

    @Override
    public void setregenChakra(float amount) {
    	this.regenchakra = amount;
    }
    @Override
    public void addregenChakra(float amount) {
    	this.regenchakra += amount;
    }
    @Override
    public float returnregenChakra() {
    	return this.regenchakra;
    }


    @Override
    public void setcolorChakra(int amount) {
	    this.chakracolor = amount;
    }
    @Override
    public int returncolorChakra() {
	    return this.chakracolor;
    }


    @Override
    public void setplayerEyeSlot(int amount)
    {
        this.playereyeslot = amount;
    }
    @Override
    public int returnplayerEyeSlot()
    {
        return this.playereyeslot;
    }


    @Override
    public void setTaijutsu(int amount)
    {
        this.taijutsu = amount;
    }
    @Override
    public void addTaijutsu(int amount)
    {
        this.taijutsu += amount;

        if (this.taijutsu > 20)
        {
            this.taijutsu = 20;
        }

        if (this.taijutsu < 0)
        {
            this.taijutsu = 0;
        }
    }
    @Override
    public int returnTaijutsu()
    {
        return this.taijutsu;
    }


    @Override
    public void setGenjutsu(int amount)
    {
        this.genjutsu = amount;
    }
    @Override
    public void addGenjutsu(int amount)
    {
        this.genjutsu += amount;

        if (this.genjutsu > 50)
        {
            this.genjutsu = 20;
        }

        if (this.genjutsu < 0)
        {
            this.genjutsu = 0;
        }
    }
    @Override
    public int returnGenjutsu()
    {
        return this.genjutsu;
    }


    @Override
    public void setBeNMPoints(int amount)
    {
        this.benmpoints = amount;
    }
    @Override
    public void addBeNMPoints(int amount)
    {
        this.benmpoints += amount;
    }
    @Override
    public int returnBeNMPoints()
    {
        return this.benmpoints;
    }


    @Override
    public void setChakraControl(float amount)
    {
        this.chakraControl = amount;
    }
    @Override
    public void addChakraControl(float amount)
    {
        this.chakraControl += amount;
    }
    @Override
    public float returnChakraControl()
    {
        return this.chakraControl;
    }


    @Override
    public void setShinobiLevel(int amount)
    {
        this.playerLevel = amount;
    }
    @Override
    public int returnShinobiLevel()
    {
        return this.playerLevel;
    }


    @Override
    public void setPlayerClan(BeNMClan clan) {
	    if (!this.getLearntClans().contains(clan))
	        this.addLearntClan(clan);
        this.clan = clan;
    }

    @Override
    public BeNMClan returnPlayerClan() {
	    if (this.clan == null) {
	        this.clan = ClanInit.NULL;
        }
        return this.clan;
    }

    @Override
    public void addLearntClan(BeNMClan clan) {
        this.learntClans.add(clan);
    }

    @Override
    public List<BeNMClan> getLearntClans() {
        return this.learntClans;
    }


    @Override
    public void setPlayerEntityAffiliation(String entityAffiliation)
    {
        this.entityAffiliation = entityAffiliation;
    }
    @Override
    public String returnPlayerEntityAffiliation()
    {
        return this.entityAffiliation;
    }

    @Override
    public void setPlayerBijuu(String bijuu)
    {
        this.bijuu = bijuu;
    }
    @Override
    public String returnPlayerBijuu()
    {
        return this.bijuu;
    }
    @Override
    public void setPlayerBijuuLevel(int level)
    {
        this.playerBijuuLevel = level;
    }
    @Override
    public int returnPlayerBijuuLevel()
    {
        return this.playerBijuuLevel;
    }

    @Override
    public void setPlayerLeftDojutsu(BeNMDojutsu playerDojutsu)
    {
        this.playerLeftDojutsu = playerDojutsu;
    }
    @Override
    public BeNMDojutsu returnPlayerLeftDojutsu()
    {
        return this.playerLeftDojutsu;
    }
    @Override
    public void setPlayerRightDojutsu(BeNMDojutsu playerDojutsu)
    {
        this.playerRightDojutsu = playerDojutsu;
    }
    @Override
    public BeNMDojutsu returnPlayerRightDojutsu()
    {
        return this.playerRightDojutsu;
    }
    @Override
    public void setPlayerBodyMode(BeNMBody playerBodyMode)
    {
        this.playerBody = playerBodyMode;
    }
    @Override
    public BeNMBody returnPlayerBodyMode()
    {
        if (this.playerBody == null) {
            this.playerBody = BodyInit.NULL;
        }
        return this.playerBody;
    }

    @Override
    public void setTruthSeekingOrbAmount(int amount) {
        this.truthSeekingOrbs = amount;
        if (this.truthSeekingOrbs > 10)
            this.truthSeekingOrbs = 10;
    }

    @Override
    public int getTruthSeekingOrbAmount() {
        return this.truthSeekingOrbs;
    }

    @Override
    public void setSusanooMainHand(ItemStack mainHand) {
        this.susanooMainHand = mainHand;
    }
    @Override
    public void setSusanooOffHand(ItemStack offHand) {
        this.susanooOffHand = offHand;
    }
    @Override
    public ItemStack getSusanooMainHand() {
        return this.susanooMainHand;
    }
    @Override
    public ItemStack getSusanooOffHand() {
        return this.susanooOffHand;
    }

    @Override
    public void setPlayerCurseMark(int tier) {
        this.curseMark = tier;
    }
    @Override
    public int returnPlayerCurseMark() {
	    return this.curseMark;
    }
    @Override
    public void setPlayerToadSageMode(int tier) {
        this.toadSage = tier;
    }
    @Override
    public int returnPlayerToadSageMode() {
        return this.toadSage;
    }

    @Override
    public void setToggleJutsuMessage(boolean toggleMessageJutsu)
    {
        this.toggleMessageJutsu = toggleMessageJutsu;
    }
    @Override
    public boolean returnToggleJutsuMessage()
    {
        return this.toggleMessageJutsu;
    }

    @Override
    public void setToggleScrollRenderer(boolean toggleScrollRenderer) {
        this.toggleScrollRenderer = toggleScrollRenderer;
    }
    @Override
    public boolean returnToggleScrollRenderer()
    {
        return this.toggleScrollRenderer;
    }


    @Override
    public void setHandInfusionToggled(boolean handInfusion)
    {
        this.handToggled = handInfusion;
    }
    @Override
    public boolean returnHandInfusionToggled()
    {
        return this.handToggled;
    }
    @Override
    public void setBodyInfusionToggled(boolean bodyInfusion)
    {
        this.bodyToggled = bodyInfusion;
    }
    @Override
    public boolean returnBodyInfusionToggled()
    {
        return this.bodyToggled;
    }
    @Override
    public void setLegInfusionToggled(boolean legInfusion)
    {
        this.legToggled = legInfusion;
    }
    @Override
    public boolean returnLegInfusionToggled()
    {
        return this.legToggled;
    }
    @Override
    public void setKeybind1(String name)
    {
        this.key1 = name;
    }
    @Override
    public String returnKeybind1()
    {
        return this.key1;
    }
    @Override
    public void setKeybind2(String name)
    {
        this.key2 = name;
    }
    @Override
    public String returnKeybind2()
    {
        return this.key2;
    }
    @Override
    public void setKeybind3(String name)
    {
        this.key3 = name;
    }
    @Override
    public String returnKeybind3()
    {
        return this.key3;
    }
    @Override
    public void setKeybind4(String name)
    {
        this.key4 = name;
    }
    @Override
    public String returnKeybind4()
    {
        return this.key4;
    }
    @Override
    public void setKeybind5(String name)
    {
        this.key5 = name;
    }
    @Override
    public String returnKeybind5()
    {
        return this.key5;
    }
    @Override
    public void setKeybind6(String name)
    {
        this.key6 = name;
    }
    @Override
    public String returnKeybind6()
    {
        return this.key6;
    }
    @Override
    public void setKeybind7(String name)
    {
        this.key7 = name;
    }
    @Override
    public String returnKeybind7()
    {
        return this.key7;
    }
    @Override
    public void setKeybind8(String name)
    {
        this.key8 = name;
    }
    @Override
    public String returnKeybind8()
    {
        return this.key8;
    }
    @Override
    public void setKeybind9(String name)
    {
        this.key9 = name;
    }
    @Override
    public String returnKeybind9()
    {
        return this.key9;
    }


    @Override
    public void setChakraBoolean(boolean has)
    {
        this.chakraBoolean = has;
    }
    @Override
    public boolean hasChakraBoolean()
    {
        return this.chakraBoolean;
    }

    @Override
    public void setFireNature(boolean has)
    {
        this.fireNature = has;
    }
    @Override
    public boolean hasFireNature()
    {
        return this.fireNature;
    }
    @Override
    public void setWaterNature(boolean has)
    {
        this.waterNature = has;
    }
    @Override
    public boolean hasWaterNature()
    {
        return this.waterNature;
    }
    @Override
    public void setEarthNature(boolean has)
    {
        this.earthNature = has;
    }
    @Override
    public boolean hasEarthNature()
    {
        return this.earthNature;
    }
    @Override
    public void setLightningNature(boolean has)
    {
        this.lightningNature = has;
    }
    @Override
    public boolean hasLightningNature()
    {
        return this.lightningNature;
    }
    @Override
    public void setWindNature(boolean has)
    {
        this.windNature = has;
    }
    @Override
    public boolean hasWindNature()
    {
        return this.windNature;
    }
    @Override
    public void setMagnetNature(boolean has)
    {
        this.magnetNature = has;
    }
    @Override
    public boolean hasMagnetNature()
    {
        return this.magnetNature;
    }
    @Override
    public void setWoodNature(boolean has)
    {
        this.woodNature = has;
    }
    @Override
    public boolean hasWoodNature()
    {
        return this.woodNature;
    }
    @Override
    public void setIceNature(boolean has)
    {
        this.iceNature = has;
    }
    @Override
    public boolean hasIceNature()
    {
        return this.iceNature;
    }
    @Override
    public void setStormNature(boolean has)
    {
        this.stormNature = has;
    }
    @Override
    public boolean hasStormNature()
    {
        return this.stormNature;
    }
    @Override
    public void setScorchNature(boolean has)
    {
        this.scorchNature = has;
    }
    @Override
    public boolean hasScorchNature()
    {
        return this.scorchNature;
    }

    @Override
    public void setJutsuBoolean(BeNMJutsu jutsu, boolean has) {
	    if (this.jutsuBooleans.containsKey(jutsu)) {
            this.jutsuBooleans.replace(jutsu, has);
            return;
        }
	    this.jutsuBooleans.put(jutsu, has);
    }

    @Override
    public boolean hasJutsuBoolean(BeNMJutsu jutsu) {
        return this.jutsuBooleans.getOrDefault(jutsu, false);
    }

    @Override
    public void setExplosionNature(boolean has)
    {
        this.explosionNature = has;
    }
    @Override
    public boolean hasExplosionNature()
    {
        return this.explosionNature;
    }
    @Override
    public void setBoilNature(boolean has)
    {
        this.boilNature = has;
    }
    @Override
    public boolean hasBoilNature()
    {
        return this.boilNature;
    }
    @Override
    public void setLavaNature(boolean has)
    {
        this.lavaNature = has;
    }
    @Override
    public boolean hasLavaNature()
    {
        return this.lavaNature;
    }

    public static class Storage implements Capability.IStorage<IPlayerHandler>
    {
        @Nullable
        @Override
        public INBT writeNBT(Capability<IPlayerHandler> capability, IPlayerHandler instance, Direction side)
        {
            CompoundNBT tag = new CompoundNBT();

            tag.putBoolean("joined", instance.joinWorld());
            tag.putBoolean("handinfused", instance.returnHandInfusionToggled());
            tag.putBoolean("bodyinfused", instance.returnBodyInfusionToggled());
            tag.putBoolean("leginfused", instance.returnLegInfusionToggled());
            tag.putBoolean("jutsumessage", instance.returnToggleJutsuMessage());
            tag.putBoolean("scrollrenderer", instance.returnToggleScrollRenderer());
            tag.putInt("cursemark", instance.returnPlayerCurseMark());
            tag.putInt("toadsage", instance.returnPlayerToadSageMode());
            tag.putFloat("chakra", instance.returnChakra());
            tag.putFloat("maxchakra", instance.returnmaxChakra());
            tag.putFloat("regenchakra", instance.returnregenChakra());
            tag.putFloat("chakracontrol", instance.returncolorChakra());
            tag.putFloat("chakracontrol", instance.returnChakraControl());
            tag.putInt("colorchakra", instance.returncolorChakra());
            tag.putInt("genjutsu", instance.returnGenjutsu());
            tag.putInt("taijutsu", instance.returnTaijutsu());
            tag.putInt("benmpoints", instance.returnBeNMPoints());
            tag.putInt("playereyeslot", instance.returnplayerEyeSlot());
            tag.putInt("shinobilevel", instance.returnShinobiLevel());
            tag.putInt("bijuulevel", instance.returnPlayerBijuuLevel());
            tag.putInt("truth_seeking_orbs", instance.getTruthSeekingOrbAmount());
            tag.putString("playeraffiliation", instance.returnPlayerEntityAffiliation());
            tag.putString("bijuu", instance.returnPlayerBijuu());
            tag.putString("playerleftdojutsu", instance.returnPlayerLeftDojutsu().getString());
            tag.putString("playerrightdojutsu", instance.returnPlayerRightDojutsu().getString());
            tag.putString("playerbody", instance.returnPlayerBodyMode().getName());
            tag.putString("playerclan", instance.returnPlayerClan().getString());
            CompoundNBT susanooMHand = new CompoundNBT();
            instance.getSusanooMainHand().write(susanooMHand);
            tag.put("susanoomainhand", susanooMHand);
            CompoundNBT susanooOHand = new CompoundNBT();
            instance.getSusanooOffHand().write(susanooOHand);
            tag.put("susanoooffhand", susanooOHand);

            //Keybinds for Jutsu
            tag.putString("key1", instance.returnKeybind1());
            tag.putString("key2", instance.returnKeybind2());
            tag.putString("key3", instance.returnKeybind3());
            tag.putString("key4", instance.returnKeybind4());
            tag.putString("key5", instance.returnKeybind5());
            tag.putString("key6", instance.returnKeybind6());
            tag.putString("key7", instance.returnKeybind7());
            tag.putString("key8", instance.returnKeybind8());
            tag.putString("key9", instance.returnKeybind9());

            //Nature
            tag.putBoolean("firenature", instance.hasFireNature());
            tag.putBoolean("waternature", instance.hasWaterNature());
            tag.putBoolean("windnature", instance.hasWindNature());
            tag.putBoolean("earthnature", instance.hasEarthNature());
            tag.putBoolean("lightningnature", instance.hasLightningNature());
            tag.putBoolean("magnetnature", instance.hasMagnetNature());
            tag.putBoolean("woodnature", instance.hasWoodNature());
            tag.putBoolean("lavanature", instance.hasLavaNature());
            tag.putBoolean("icenature", instance.hasIceNature());
            tag.putBoolean("explosionnature", instance.hasExplosionNature());
            tag.putBoolean("scorchnature", instance.hasScorchNature());
            tag.putBoolean("stormnature", instance.hasStormNature());
            tag.putBoolean("boilnature", instance.hasBoilNature());

            tag.putBoolean("haschakra", instance.hasChakraBoolean());

            for (BeNMJutsu jutsu : BeNMRegistry.JUTSUS.getValues()) {
                tag.putBoolean(jutsu.getCorrelatedPlugin().getPluginId() + "_" + jutsu.getName() + "_save", instance.hasJutsuBoolean(jutsu));
            }
            for (BeNMClan clan : BeNMRegistry.CLANS.getValues()) {
                tag.putBoolean("learntclan_" + clan.getString() + "_save", instance.getLearntClans().contains(clan));
            }
            return tag;
        }

        @Override
        public void readNBT(Capability<IPlayerHandler> capability, IPlayerHandler instance, Direction side, INBT nbt)
        {
            INBT tag = nbt;
            instance.setjoinWorld(((CompoundNBT) tag).getBoolean("joined"));
            instance.setHandInfusionToggled(((CompoundNBT) tag).getBoolean("handinfused"));
            instance.setBodyInfusionToggled(((CompoundNBT) tag).getBoolean("bodyinfused"));
            instance.setLegInfusionToggled(((CompoundNBT) tag).getBoolean("leginfused"));
            instance.setToggleJutsuMessage(((CompoundNBT) tag).getBoolean("jutsumessage"));
            instance.setToggleScrollRenderer(((CompoundNBT) tag).getBoolean("scrollrenderer"));
            instance.setPlayerCurseMark(((CompoundNBT) tag).getInt("cursemark"));
            instance.setPlayerToadSageMode(((CompoundNBT) tag).getInt("toadsage"));
            instance.setChakra(((CompoundNBT) tag).getFloat("chakra"));
            instance.setmaxChakra(((CompoundNBT) tag).getFloat("maxchakra"));
            instance.setregenChakra(((CompoundNBT) tag).getFloat("regenchakra"));
            instance.setChakraControl(((CompoundNBT) tag).getFloat("chakracontrol"));
            instance.setcolorChakra(((CompoundNBT) tag).getInt("colorchakra"));
            instance.setTaijutsu(((CompoundNBT) tag).getInt("taijutsu"));
            instance.setGenjutsu(((CompoundNBT) tag).getInt("genjutsu"));
            instance.setplayerEyeSlot(((CompoundNBT) tag).getInt("playereyeslot"));
            instance.setBeNMPoints(((CompoundNBT) tag).getInt("benmpoints"));
            instance.setShinobiLevel(((CompoundNBT) tag).getInt("shinobilevel"));
            instance.setPlayerEntityAffiliation(((CompoundNBT) tag).getString("playeraffiliation"));
            instance.setPlayerBijuu(((CompoundNBT) tag).getString("bijuu"));
            instance.setPlayerBijuuLevel(((CompoundNBT) tag).getInt("bijuulevel"));
            instance.setTruthSeekingOrbAmount(((CompoundNBT) tag).getInt("truth_seeking_orbs"));
            instance.setPlayerLeftDojutsu(DojutsuHelper.getDojutsuFromString(((CompoundNBT) tag).getString("playerleftdojutsu")));
            instance.setPlayerRightDojutsu(DojutsuHelper.getDojutsuFromString(((CompoundNBT) tag).getString("playerrightdojutsu")));
            instance.setPlayerBodyMode(BodyHelper.getBodyFromString(((CompoundNBT) tag).getString("playerbody")));
            instance.setPlayerClan(ClanHelper.getClanFromString(((CompoundNBT) tag).getString("playerclan")));
            instance.setSusanooMainHand(ItemStack.read(((CompoundNBT) tag).getCompound("susanoomainhand")));
            instance.setSusanooOffHand(ItemStack.read(((CompoundNBT) tag).getCompound("susanoooffhand")));

            //Keybinds for Jutsu
            instance.setKeybind1(((CompoundNBT) tag).getString("key1"));
            instance.setKeybind2(((CompoundNBT) tag).getString("key2"));
            instance.setKeybind3(((CompoundNBT) tag).getString("key3"));
            instance.setKeybind4(((CompoundNBT) tag).getString("key4"));
            instance.setKeybind5(((CompoundNBT) tag).getString("key5"));
            instance.setKeybind6(((CompoundNBT) tag).getString("key6"));
            instance.setKeybind7(((CompoundNBT) tag).getString("key7"));
            instance.setKeybind8(((CompoundNBT) tag).getString("key8"));
            instance.setKeybind9(((CompoundNBT) tag).getString("key9"));

            //Nature
            instance.setFireNature(((CompoundNBT) tag).getBoolean("firenature"));
            instance.setWaterNature(((CompoundNBT) tag).getBoolean("waternature"));
            instance.setEarthNature(((CompoundNBT) tag).getBoolean("earthnature"));
            instance.setWindNature(((CompoundNBT) tag).getBoolean("windnature"));
            instance.setLightningNature(((CompoundNBT) tag).getBoolean("lightningnature"));
            instance.setMagnetNature(((CompoundNBT) tag).getBoolean("magnetnature"));
            instance.setWoodNature(((CompoundNBT) tag).getBoolean("woodnature"));
            instance.setLavaNature(((CompoundNBT) tag).getBoolean("lavanature"));
            instance.setIceNature(((CompoundNBT) tag).getBoolean("icenature"));
            instance.setScorchNature(((CompoundNBT) tag).getBoolean("scorchnature"));
            instance.setStormNature(((CompoundNBT) tag).getBoolean("stormnature"));
            instance.setBoilNature(((CompoundNBT) tag).getBoolean("boilnature"));
            instance.setExplosionNature(((CompoundNBT) tag).getBoolean("explosionnature"));

            instance.setChakraBoolean(((CompoundNBT) tag).getBoolean("haschakra"));

            for (BeNMJutsu jutsu : BeNMRegistry.JUTSUS.getValues()) {
                instance.setJutsuBoolean(jutsu, ((CompoundNBT) tag).getBoolean(jutsu.getCorrelatedPlugin().getPluginId() + "_" + jutsu.getName() + "_save"));
            }
            for (BeNMClan clan : BeNMRegistry.CLANS.getValues()) {
                if (((CompoundNBT) tag).getBoolean("learntclan_" + clan.getString() + "_save"))
                    instance.addLearntClan(clan);
            }
        }
    }
}