package me.ohvalsgod.cutechat.player.data.settings;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerSettings {

    private boolean foundDiamonds, tips;
    private ChatStatus chatStatus;


    public PlayerSettings() {
        foundDiamonds = true;
        tips = true;
        chatStatus = ChatStatus.BOTH;
    }

}
