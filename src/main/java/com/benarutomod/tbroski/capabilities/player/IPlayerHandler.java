package com.benarutomod.tbroski.capabilities.player;

import com.benarutomod.tbroski.common.BeNMBody;
import com.benarutomod.tbroski.common.BeNMClan;
import com.benarutomod.tbroski.common.BeNMDojutsu;

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

	void setShinobiLevel(int amount);
	int returnShinobiLevel();

	void setPlayerClan(BeNMClan clan);
	BeNMClan returnPlayerClan();

	void setPlayerEntityAffiliation(String entityAffiliation);
	String returnPlayerEntityAffiliation();

	void setPlayerLeftDojutsu(BeNMDojutsu playerDojutsu);
	BeNMDojutsu returnPlayerLeftDojutsu();

	void setPlayerRightDojutsu(BeNMDojutsu playerDojutsu);
	BeNMDojutsu returnPlayerRightDojutsu();

	void setPlayerBodyMode(BeNMBody playerBodyMode);
	BeNMBody returnPlayerBodyMode();

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


	void setCloneJutsuBoolean(boolean has);
	void setBodyReplacementBoolean(boolean has);
	void setInvisibilityBoolean(boolean has);
	void setSummoningBoolean(boolean has);
	void setTransformationBoolean(boolean has);

	void setFireballJutsuBoolean(boolean has);
	void setPhoenixFlowerJutsuBoolean(boolean has);
	void setMoltenFistJutsuBoolean(boolean has);

	void setGalePalmJutsuBoolean(boolean has);
	void setWindExplosionJutsuBoolean(boolean has);
	void setWindArrowJutsuBoolean(boolean has);

	void setFlyingStonesJutsuBoolean(boolean has);
	void setMudMoatJutsuBoolean(boolean has);
	void setFistRockJutsuBoolean(boolean has);

	void setWaterShurikenJutsuBoolean(boolean has);
	void setRagingWavesJutsuBoolean(boolean has);
	void setWaterSharkBulletJutsuBoolean(boolean has);

	void setLightningBallJutsuBoolean(boolean has);
	void setStunGunJutsuBoolean(boolean has);
	void setLightningArrowJutsuBoolean(boolean has);

	void setMatterRepulsionJutsuBoolean(boolean has);
	void setSelfLevitationJutsuBoolean(boolean has);

	void setAmaterasuJutsuBoolean(boolean has);
	void setTsukuyomiJutsuBoolean(boolean has);


	boolean hasCloneJutsuBoolean();
	boolean hasBodyReplacementBoolean();
	boolean hasInvisibilityBoolean();
	boolean hasSummoningBoolean();
	boolean hasTransformationBoolean();

	boolean hasFireballJutsuBoolean();
	boolean hasPhoenixFlowerJutsuBoolean();
	boolean hasMoltenFistJutsuBoolean();

	boolean hasGalePalmJutsuBoolean();
	boolean hasWindExplosionJutsuBoolean();
	boolean hasWindArrowJutsuBoolean();

	boolean hasFlyingStonesJutsuBoolean();
	boolean hasMudMoatJutsuBoolean();
	boolean hasFistRockJutsuBoolean();

	boolean hasWaterShurikenJutsuBoolean();
	boolean hasRagingWavesJutsuBoolean();
	boolean hasWaterSharkBulletJutsuBoolean();

	boolean hasLightningBallJutsuBoolean();
	boolean hasStunGunJutsuBoolean();
	boolean hasLightningArrowJutsuBoolean();

	boolean hasMatterRepulsionJutsuBoolean();
	boolean hasSelfLevitationJutsuBoolean();

	boolean hasAmaterasuJutsuBoolean();
	boolean hasTsukuyomiJutsuBoolean();
}
