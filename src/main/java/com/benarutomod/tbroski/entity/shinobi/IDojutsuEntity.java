package com.benarutomod.tbroski.entity.shinobi;

import com.benarutomod.tbroski.common.BeNMDojutsu;

public interface IDojutsuEntity {

    BeNMDojutsu rightDojustsu();
    BeNMDojutsu leftDojustsu();

    void setRightDojustsu(BeNMDojutsu rightEye);
    void setLeftDojustsu(BeNMDojutsu leftEye);

    int eyeSlot();
}
