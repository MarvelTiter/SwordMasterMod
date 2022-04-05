package SwordMaster.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import SwordMaster.powers.ElectricShock;

public class ApplyKingGuardLightingAction extends AbstractGameAction {
    private AbstractMonster targetMonster;

    public ApplyKingGuardLightingAction(AbstractMonster target, int amount) {
        this.targetMonster = target;
        this.amount = amount;
        this.actionType = AbstractGameAction.ActionType.DEBUFF;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.targetMonster != null && this.targetMonster.getIntentBaseDmg() > 0) {
            AbstractMonster m = this.targetMonster;
            addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new ElectricShock(m, this.amount), this.amount));
        }
        this.isDone = true;
    }
}
