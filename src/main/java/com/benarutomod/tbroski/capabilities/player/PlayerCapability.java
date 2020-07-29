package com.benarutomod.tbroski.capabilities.player;

import com.benarutomod.tbroski.common.BeNMBody;
import com.benarutomod.tbroski.common.BeNMClan;
import com.benarutomod.tbroski.common.BeNMDojutsu;
import com.benarutomod.tbroski.init.BodyInit;
import com.benarutomod.tbroski.init.ClanInit;
import com.benarutomod.tbroski.init.DojutsuInit;
import com.benarutomod.tbroski.util.helpers.BodyHelper;
import com.benarutomod.tbroski.util.helpers.ClanHelper;
import com.benarutomod.tbroski.util.helpers.DojutsuHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

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
	private boolean bodyToggled;
	private boolean handToggled;
	private boolean legToggled;
	private String entityAffiliation = "";
	private BeNMDojutsu playerLeftDojutsu = DojutsuInit.NULL;
	private BeNMDojutsu playerRightDojutsu = DojutsuInit.NULL;
	private BeNMBody playerBody = BodyInit.NULL;
	private BeNMClan clan = ClanInit.NULL;
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
	private boolean cloneJutsu;
	private boolean replacementJutsu;
	private boolean summoningJutsu;
	private boolean transformationJutsu;
	private boolean invisibilityJutsu;
	private boolean fireballJutsu;
	private boolean phoenixFlowerJutsu;
	private boolean moltenFistJutsu;
	private boolean waterShurikenJutsu;
	private boolean ragingWavesJutsu;
	private boolean waterSharkBulletJutsu;
	private boolean galePalmJutsu;
	private boolean windExplosionJutsu;
	private boolean windArrowJutsu;
	private boolean flyingStonesJutsu;
	private boolean mudMoatJutsu;
	private boolean fistRockJutsu;
	private boolean lightningBallJutsu;
	private boolean stunGunJutsu;
	private boolean lightningArrowJutsu;
	private boolean amaterasuJutsu;
	private boolean tsukuyomiJutsu;
	private boolean joined;
	
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
    public void setToggleScrollRenderer(boolean toggleScrollRenderer)
    {
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
    public void setCloneJutsuBoolean(boolean has)
    {
        this.cloneJutsu = has;
    }
    @Override
    public boolean hasCloneJutsuBoolean()
    {
        return this.cloneJutsu;
    }
    @Override
    public void setBodyReplacementBoolean(boolean has)
    {
        this.replacementJutsu = has;
    }
    @Override
    public boolean hasBodyReplacementBoolean()
    {
        return this.replacementJutsu;
    }
    @Override
    public void setInvisibilityBoolean(boolean has)
    {
        this.invisibilityJutsu = has;
    }
    @Override
    public boolean hasInvisibilityBoolean()
    {
        return this.invisibilityJutsu;
    }
    @Override
    public void setSummoningBoolean(boolean has)
    {
        this.summoningJutsu = has;
    }
    @Override
    public boolean hasSummoningBoolean()
    {
        return this.summoningJutsu;
    }
    @Override
    public void setTransformationBoolean(boolean has)
    {
        this.transformationJutsu = has;
    }
    @Override
    public boolean hasTransformationBoolean()
    {
        return this.transformationJutsu;
    }

    @Override
    public void setFireballJutsuBoolean(boolean has)
    {
        this.fireballJutsu = has;
    }
    @Override
    public boolean hasFireballJutsuBoolean()
    {
        return this.fireballJutsu;
    }

    @Override
    public void setPhoenixFlowerJutsuBoolean(boolean has)
    {
        this.phoenixFlowerJutsu = has;
    }
    @Override
    public boolean hasPhoenixFlowerJutsuBoolean()
    {
        return this.phoenixFlowerJutsu;
    }

    @Override
    public void setMoltenFistJutsuBoolean(boolean has)
    {
        this.moltenFistJutsu = has;
    }
    @Override
    public boolean hasMoltenFistJutsuBoolean()
    {
        return this.moltenFistJutsu;
    }


    @Override
    public void setWaterShurikenJutsuBoolean(boolean has)
    {
        this.waterShurikenJutsu = has;
    }
    @Override
    public boolean hasWaterShurikenJutsuBoolean()
    {
        return this.waterShurikenJutsu;
    }

    @Override
    public void setRagingWavesJutsuBoolean(boolean has)
    {
        this.ragingWavesJutsu = has;
    }
    @Override
    public boolean hasRagingWavesJutsuBoolean()
    {
        return this.ragingWavesJutsu;
    }

    @Override
    public void setWaterSharkBulletJutsuBoolean(boolean has)
    {
        this.waterSharkBulletJutsu = has;
    }
    @Override
    public boolean hasWaterSharkBulletJutsuBoolean()
    {
        return this.waterSharkBulletJutsu;
    }


    @Override
    public void setFlyingStonesJutsuBoolean(boolean has)
    {
        this.flyingStonesJutsu = has;
    }
    @Override
    public boolean hasFlyingStonesJutsuBoolean()
    {
        return this.flyingStonesJutsu;
    }

    @Override
    public void setMudMoatJutsuBoolean(boolean has)
    {
        this.mudMoatJutsu = has;
    }
    @Override
    public boolean hasMudMoatJutsuBoolean()
    {
        return this.mudMoatJutsu;
    }

    @Override
    public void setFistRockJutsuBoolean(boolean has)
    {
        this.fistRockJutsu = has;
    }
    @Override
    public boolean hasFistRockJutsuBoolean()
    {
        return this.fistRockJutsu;
    }


    @Override
    public void setGalePalmJutsuBoolean(boolean has)
    {
        this.galePalmJutsu = has;
    }
    @Override
    public boolean hasGalePalmJutsuBoolean()
    {
        return this.galePalmJutsu;
    }

    @Override
    public void setWindExplosionJutsuBoolean(boolean has)
    {
        this.windExplosionJutsu = has;
    }
    @Override
    public boolean hasWindExplosionJutsuBoolean()
    {
        return this.windExplosionJutsu;
    }

    @Override
    public void setWindArrowJutsuBoolean(boolean has)
    {
        this.windArrowJutsu = has;
    }
    @Override
    public boolean hasWindArrowJutsuBoolean()
    {
        return this.windArrowJutsu;
    }


    @Override
    public void setLightningBallJutsuBoolean(boolean has)
    {
        this.lightningBallJutsu = has;
    }
    @Override
    public boolean hasLightningBallJutsuBoolean()
    {
        return this.lightningBallJutsu;
    }

    @Override
    public void setStunGunJutsuBoolean(boolean has)
    {
        this.stunGunJutsu = has;
    }
    @Override
    public boolean hasStunGunJutsuBoolean()
    {
        return this.stunGunJutsu;
    }

    @Override
    public void setLightningArrowJutsuBoolean(boolean has)
    {
        this.lightningArrowJutsu = has;
    }
    @Override
    public boolean hasLightningArrowJutsuBoolean()
    {
        return this.lightningArrowJutsu;
    }


    @Override
    public void setAmaterasuJutsuBoolean(boolean has)
    {
        this.amaterasuJutsu = has;
    }
    @Override
    public boolean hasAmaterasuJutsuBoolean()
    {
        return this.amaterasuJutsu;
    }

    @Override
    public void setTsukuyomiJutsuBoolean(boolean has)
    {
        this.tsukuyomiJutsu = has;
    }
    @Override
    public boolean hasTsukuyomiJutsuBoolean()
    {
        return this.tsukuyomiJutsu;
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
            tag.putString("playeraffiliation", instance.returnPlayerEntityAffiliation());
            tag.putString("playerleftdojutsu", instance.returnPlayerLeftDojutsu().getString());
            tag.putString("playerrightdojutsu", instance.returnPlayerRightDojutsu().getString());
            tag.putString("playerbody", instance.returnPlayerBodyMode().getString());
            tag.putString("playerclan", instance.returnPlayerClan().getString());

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

            tag.putBoolean("haschakra", instance.hasChakraBoolean());
            //Jutsu
            tag.putBoolean("clone", instance.hasCloneJutsuBoolean());
            tag.putBoolean("invisibility", instance.hasInvisibilityBoolean());
            tag.putBoolean("summoning", instance.hasSummoningBoolean());
            tag.putBoolean("replacement", instance.hasBodyReplacementBoolean());
            tag.putBoolean("transformation", instance.hasTransformationBoolean());
            //Fire
            tag.putBoolean("fireball", instance.hasFireballJutsuBoolean());
            tag.putBoolean("phoenixflower", instance.hasPhoenixFlowerJutsuBoolean());
            tag.putBoolean("moltenfist", instance.hasMoltenFistJutsuBoolean());
            //Water
            tag.putBoolean("watershuriken", instance.hasWaterShurikenJutsuBoolean());
            tag.putBoolean("ragingwaves", instance.hasRagingWavesJutsuBoolean());
            tag.putBoolean("watersharkbullet", instance.hasWaterSharkBulletJutsuBoolean());
            //Wind
            tag.putBoolean("galepalm", instance.hasGalePalmJutsuBoolean());
            tag.putBoolean("windexplosion", instance.hasWindExplosionJutsuBoolean());
            tag.putBoolean("windarrow", instance.hasWindArrowJutsuBoolean());
            //Earth
            tag.putBoolean("flyingstones", instance.hasFlyingStonesJutsuBoolean());
            tag.putBoolean("mudmoat", instance.hasMudMoatJutsuBoolean());
            tag.putBoolean("fistrock", instance.hasFistRockJutsuBoolean());
            //Lightning
            tag.putBoolean("lightningball", instance.hasLightningBallJutsuBoolean());
            tag.putBoolean("stungun", instance.hasStunGunJutsuBoolean());
            tag.putBoolean("lightningarrow", instance.hasLightningArrowJutsuBoolean());
            //Sharingan
            tag.putBoolean("amaterasu", instance.hasAmaterasuJutsuBoolean());
            tag.putBoolean("tsukuyomi", instance.hasTsukuyomiJutsuBoolean());
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
            instance.setPlayerLeftDojutsu(DojutsuHelper.getDojutsuFromString(((CompoundNBT) tag).getString("playerleftdojutsu")));
            instance.setPlayerRightDojutsu(DojutsuHelper.getDojutsuFromString(((CompoundNBT) tag).getString("playerrightdojutsu")));
            instance.setPlayerBodyMode(BodyHelper.getBodyFromString(((CompoundNBT) tag).getString("playerbody")));
            instance.setPlayerClan(ClanHelper.getClanFromString(((CompoundNBT) tag).getString("playerclan")));

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

            instance.setChakraBoolean(((CompoundNBT) tag).getBoolean("haschakra"));
            //Jutsu
            instance.setCloneJutsuBoolean(((CompoundNBT) tag).getBoolean("clone"));
            instance.setSummoningBoolean(((CompoundNBT) tag).getBoolean("summoning"));
            instance.setInvisibilityBoolean(((CompoundNBT) tag).getBoolean("invisibility"));
            instance.setBodyReplacementBoolean(((CompoundNBT) tag).getBoolean("replacement"));
            instance.setTransformationBoolean(((CompoundNBT) tag).getBoolean("transformation"));
            //Fire
            instance.setFireballJutsuBoolean(((CompoundNBT) tag).getBoolean("fireball"));
            instance.setPhoenixFlowerJutsuBoolean(((CompoundNBT) tag).getBoolean("phoenixflower"));
            instance.setMoltenFistJutsuBoolean(((CompoundNBT) tag).getBoolean("moltenfist"));
            //Water
            instance.setWaterShurikenJutsuBoolean(((CompoundNBT) tag).getBoolean("watershuriken"));
            instance.setRagingWavesJutsuBoolean(((CompoundNBT) tag).getBoolean("ragingwaves"));
            instance.setWaterSharkBulletJutsuBoolean(((CompoundNBT) tag).getBoolean("watersharkbullet"));
            //Lightning
            instance.setLightningBallJutsuBoolean(((CompoundNBT) tag).getBoolean("lightningball"));
            instance.setStunGunJutsuBoolean(((CompoundNBT) tag).getBoolean("stungun"));
            instance.setLightningArrowJutsuBoolean(((CompoundNBT) tag).getBoolean("lightningarrow"));
            //Earth
            instance.setFlyingStonesJutsuBoolean(((CompoundNBT) tag).getBoolean("flyingstones"));
            instance.setMudMoatJutsuBoolean(((CompoundNBT) tag).getBoolean("mudmoat"));
            instance.setFistRockJutsuBoolean(((CompoundNBT) tag).getBoolean("fistrock"));
            //Wind
            instance.setGalePalmJutsuBoolean(((CompoundNBT) tag).getBoolean("galepalm"));
            instance.setWindExplosionJutsuBoolean(((CompoundNBT) tag).getBoolean("windexplosion"));
            instance.setWindArrowJutsuBoolean(((CompoundNBT) tag).getBoolean("windarrow"));
            //Shaingan
            instance.setAmaterasuJutsuBoolean(((CompoundNBT) tag).getBoolean("amaterasu"));
            instance.setTsukuyomiJutsuBoolean(((CompoundNBT) tag).getBoolean("tsukuyomi"));
        }
    }
}