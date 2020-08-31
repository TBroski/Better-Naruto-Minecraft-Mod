package com.benarutomod.tbroski.capabilities.player;

import com.benarutomod.tbroski.api.internal.BeNMBody;
import com.benarutomod.tbroski.api.internal.BeNMClan;
import com.benarutomod.tbroski.api.internal.BeNMJutsu;
import com.benarutomod.tbroski.api.internal.dojutsu.BeNMDojutsu;
import net.minecraft.item.ItemStack;

public interface IPlayerHandler {
	
	void setChakra(float amount);
	void addChakra(float amount);
	float returnChakra();
	
	
	void setmaxChakra(float amount);
	void addmaxChakra(float amount);
	float returnmaxChakra();


	void setregenChakra(float amount);
	void addregenChakra(float amount);
	float returnregenChakra();
	
	
	void setjoinWorld(boolean joined);
	boolean joinWorld();


	void setcolorChakra(int amount);
	int returncolorChakra();

	void setplayerEyeSlot(int amount);
	int returnplayerEyeSlot();

	void setTaijutsu(int amount);
	void addTaijutsu(int amount);
	int returnTaijutsu();

	void setGenjutsu(int amount);
	void addGenjutsu(int amount);
	int returnGenjutsu();

	void setBeNMPoints(int amount);
	void addBeNMPoints(int amount);
	int returnBeNMPoints();

	void setChakraControl(float amount);
	void addChakraControl(float amount);
	float returnChakraControl();

	void setShinobiLevel(int level);
	int returnShinobiLevel();

	void setPlayerBijuuLevel(int level);
	int returnPlayerBijuuLevel();

	void setPlayerClan(BeNMClan clan);
	BeNMClan returnPlayerClan();

	void setPlayerEntityAffiliation(String entityAffiliation);
	String returnPlayerEntityAffiliation();

	void setPlayerBijuu(String bijuu);
	String returnPlayerBijuu();

	void setPlayerLeftDojutsu(BeNMDojutsu playerDojutsu);
	BeNMDojutsu returnPlayerLeftDojutsu();

	void setPlayerRightDojutsu(BeNMDojutsu playerDojutsu);
	BeNMDojutsu returnPlayerRightDojutsu();

	void setPlayerBodyMode(BeNMBody playerBodyMode);
	BeNMBody returnPlayerBodyMode();

	void setSusanooMainHand(ItemStack mainHand);
	void setSusanooOffHand(ItemStack offHand);
	ItemStack getSusanooMainHand();
	ItemStack getSusanooOffHand();

	void setPlayerCurseMark(int tier);
	int returnPlayerCurseMark();

	void setPlayerToadSageMode(int tier);
	int returnPlayerToadSageMode();

	void setToggleJutsuMessage(boolean toggleJutsuMessage);
	boolean returnToggleJutsuMessage();

	void setToggleScrollRenderer(boolean toggleScrollRenderer);
	boolean returnToggleScrollRenderer();


	void setHandInfusionToggled(boolean handInfusion);
	boolean returnHandInfusionToggled();
	void setBodyInfusionToggled(boolean bodyInfusion);
	boolean returnBodyInfusionToggled();
	void setLegInfusionToggled(boolean legInfusion);
	boolean returnLegInfusionToggled();


	void setKeybind1(String k1name);
	void setKeybind2(String k2name);
	void setKeybind3(String k3name);
	void setKeybind4(String k4name);
	void setKeybind5(String k5name);
	void setKeybind6(String k6name);
	void setKeybind7(String k7name);
	void setKeybind8(String k8name);
	void setKeybind9(String k9name);

	String returnKeybind1();
	String returnKeybind2();
	String returnKeybind3();
	String returnKeybind4();
	String returnKeybind5();
	String returnKeybind6();
	String returnKeybind7();
	String returnKeybind8();
	String returnKeybind9();

	void setChakraBoolean(boolean has);
	boolean hasChakraBoolean();

	void setFireNature(boolean has);
	boolean hasFireNature();

	void setWaterNature(boolean has);
	boolean hasWaterNature();

	void setEarthNature(boolean has);
	boolean hasEarthNature();

	void setWindNature(boolean has);
	boolean hasWindNature();

	void setLightningNature(boolean has);
	boolean hasLightningNature();

	void setMagnetNature(boolean has);
	boolean hasMagnetNature();

	void setWoodNature(boolean has);
	boolean hasWoodNature();

	void setIceNature(boolean has);
	boolean hasIceNature();

	void setLavaNature(boolean has);
	boolean hasLavaNature();

	void setStormNature(boolean has);
	boolean hasStormNature();

	void setBoilNature(boolean has);
	boolean hasBoilNature();

	void setExplosionNature(boolean has);
	boolean hasExplosionNature();

	void setScorchNature(boolean has);
	boolean hasScorchNature();

	void setJutsuBoolean(BeNMJutsu jutsu, boolean has);
	boolean hasJutsuBoolean(BeNMJutsu jutsu);

/*	void setCloneJutsuBoolean(boolean has);
	void setBodyReplacementBoolean(boolean has);
	void setInvisibilityBoolean(boolean has);
	void setSummoningBoolean(boolean has);
	void setTransformationBoolean(boolean has);
	boolean hasCloneJutsuBoolean();
	boolean hasBodyReplacementBoolean();
	boolean hasInvisibilityBoolean();
	boolean hasSummoningBoolean();
	boolean hasTransformationBoolean();

	void setFireballJutsuBoolean(boolean has);
	void setPhoenixFlowerJutsuBoolean(boolean has);
	void setMoltenFistJutsuBoolean(boolean has);
	boolean hasFireballJutsuBoolean();
	boolean hasPhoenixFlowerJutsuBoolean();
	boolean hasMoltenFistJutsuBoolean();

	void setGalePalmJutsuBoolean(boolean has);
	void setWindExplosionJutsuBoolean(boolean has);
	void setWindArrowJutsuBoolean(boolean has);
	boolean hasGalePalmJutsuBoolean();
	boolean hasWindExplosionJutsuBoolean();
	boolean hasWindArrowJutsuBoolean();

	void setFlyingStonesJutsuBoolean(boolean has);
	void setMudMoatJutsuBoolean(boolean has);
	void setFistRockJutsuBoolean(boolean has);
	boolean hasFlyingStonesJutsuBoolean();
	boolean hasMudMoatJutsuBoolean();
	boolean hasFistRockJutsuBoolean();

	void setWaterShurikenJutsuBoolean(boolean has);
	void setRagingWavesJutsuBoolean(boolean has);
	void setWaterSharkBulletJutsuBoolean(boolean has);
	boolean hasWaterShurikenJutsuBoolean();
	boolean hasRagingWavesJutsuBoolean();
	boolean hasWaterSharkBulletJutsuBoolean();

	void setLightningBallJutsuBoolean(boolean has);
	void setStunGunJutsuBoolean(boolean has);
	void setLightningArrowJutsuBoolean(boolean has);
	boolean hasLightningBallJutsuBoolean();
	boolean hasStunGunJutsuBoolean();
	boolean hasLightningArrowJutsuBoolean();

	void setMatterRepulsionJutsuBoolean(boolean has);
	void setSelfLevitationJutsuBoolean(boolean has);
	void setLightningGodSelfJutsuBoolean(boolean has);
	void setIronFistJutsuBoolean(boolean has);
	void setSandWaveJutsuBoolean(boolean has);
	boolean hasMatterRepulsionJutsuBoolean();
	boolean hasSelfLevitationJutsuBoolean();
	boolean hasLightningGodSelfJutsuBoolean();
	boolean hasIronFistJutsuBoolean();
	boolean hasSandWaveJutsuBoolean();

	void setWoodCloneJutsuBoolean(boolean has);
	void setTreeSummoningJutsuBoolean(boolean has);
	void setSeaOfTreesJutsuBoolean(boolean has);
	void setFourPillarPrisonJutsuBoolean(boolean has);
	void setFourPillarHouseJutsuBoolean(boolean has);
	boolean hasWoodCloneJutsuBoolean();
	boolean hasTreeSummoningJutsuBoolean();
	boolean hasSeaOfTreesJutsuBoolean();
	boolean hasFourPillarPrisonJutsuBoolean();
	boolean hasFourPillarHouseJutsuBoolean();

	void setScorchingStreamRockJutsuBoolean(boolean has);
	void setLavaWallJutsuBoolean(boolean has);
	void setLavaShurikenJutsuBoolean(boolean has);
	void setGreatEruptionJutsuBoolean(boolean has);
	void setLavaCloneJutsuBoolean(boolean has);
	boolean hasScorchingStreamRockJutsuBoolean();
	boolean hasLavaWallJutsuBoolean();
	boolean hasLavaShurikenJutsuBoolean();
	boolean hasGreatEruptionJutsuBoolean();
	boolean hasLavaCloneJutsuBoolean();

	void setAmaterasuJutsuBoolean(boolean has);
	void setTsukuyomiJutsuBoolean(boolean has);
	void setSusanooJutsuBoolean(boolean has);
	boolean hasAmaterasuJutsuBoolean();
	boolean hasTsukuyomiJutsuBoolean();
	boolean hasSusanooJutsuBoolean();

	void setTailedBeastBombBoolean(boolean has);
	void setTailedBeastTransformationBoolean(boolean has);
	boolean hasTailedBeastBombBoolean();
	boolean hasTailedBeastTransformationBoolean();*/
}
