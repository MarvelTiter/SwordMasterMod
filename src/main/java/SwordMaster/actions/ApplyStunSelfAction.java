package SwordMaster.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import SwordMaster.powers.StunSelfPower;

public class ApplyStunSelfAction extends AbstractGameAction {
    public ApplyStunSelfAction(AbstractCreature target, AbstractCreature source) {
        this(target, source, 1);
    }

    public ApplyStunSelfAction(AbstractCreature target, AbstractCreature source, int amount) {
        this.target = target;
        this.source = source;
        this.amount = amount;
        this.actionType = AbstractGameAction.ActionType.DEBUFF;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.target, this.source,
                    new StunSelfPower(this.target, this.amount), this.amount));
        }
        tickDuration();
        this.isDone = true;
    }
}
