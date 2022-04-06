package SwordMaster.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;

import SwordMaster.characters.swordMaster;
import SwordMaster.powers.FlowingStance;

public class ApplyFlowingStanceAction extends AbstractGameAction {
    private AbstractPlayer player;

    public ApplyFlowingStanceAction(AbstractPlayer target) {
        this.player = target;
        this.actionType = AbstractGameAction.ActionType.POWER;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.player != null) {
            addToBot(new ApplyPowerAction(player, player, new FlowingStance(player, 1), 1));
            ((swordMaster) player).updateFlowingStance(1);
        }
        this.isDone = true;
    }
}
