package SwordMaster.actions;

import SwordMaster.cards.FlowingStanceForce;
import SwordMaster.powers.FlowingStance;
import SwordMaster.powers.FlowingStanceForcePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class FlowingStanceRiseAction extends AbstractGameAction {
    public int[] multiDamage;
    private AbstractPlayer p;
    private AbstractMonster m;
    private int times;
    private DamageInfo.DamageType damageType;

    public FlowingStanceRiseAction(AbstractPlayer p, AbstractMonster m, int amount, int times, DamageInfo.DamageType damageType) {
        this.m = m;
        this.times = times;
        this.damageType = damageType;
        this.amount = amount;
        this.p = p;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
    }

    public void update() {

        if (this.p.hasPower(FlowingStanceForcePower.POWER_ID)) {
            times += 1;
            this.p.getPower(FlowingStanceForcePower.POWER_ID).flash();
        }

        if (this.p.hasPower(FlowingStance.POWER_ID)) {
            times += 1;
            this.p.getPower(FlowingStance.POWER_ID).flash();
        }

        if (times > 0) {
            for (int i = 0; i < times; ++i) {
                DamageInfo dInfo = new DamageInfo(p, this.amount, this.damageType);
                AbstractGameAction action = new DamageAction(m, dInfo, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
                addToBot(action);
            }
        }
        this.isDone = true;
    }
}
