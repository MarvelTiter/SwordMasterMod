package SwordMaster.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;

import SwordMaster.characters.swordMaster;
import SwordMaster.powers.FlowingStance;

public class ReduceFlowingStanceAction extends AbstractGameAction {
    private AbstractPlayer player;

    public ReduceFlowingStanceAction(AbstractPlayer target) {
        this.player = target;
        this.actionType = AbstractGameAction.ActionType.POWER;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.player != null) {
            addToTop(new ReducePowerAction(player, player, FlowingStance.POWER_ID, 1));
            ((swordMaster) player).updateFlowingStance(-1);
        }
        this.isDone = true;
    }
}
