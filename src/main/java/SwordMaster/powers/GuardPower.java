package SwordMaster.powers;

import SwordMaster.SwordMasterMod;
import SwordMaster.actions.ReduceFlowingStanceAction;
import SwordMaster.utils.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class GuardPower extends AbstractPower {
    public AbstractCreature source;
    public static final String POWER_ID = SwordMasterMod.makeID("Guard");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture(SwordMasterMod.makePowerPath("Guard_p.png"));
    private static final Texture tex32 = TextureLoader.getTexture(SwordMasterMod.makePowerPath("Guard.png"));

    public GuardPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.amount = amount;
        this.owner = owner;
        this.priority = 4;
        this.type = AbstractPower.PowerType.BUFF;
        if (this.amount <= 0) {
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        float a = 0.3f;
        if (this.owner.hasPower(AutoGuardPower.POWER_ID)){
            a = a * 1.5f;
        }
        return damage * (1 - a);
    }

    @Override
    public void atEndOfRound() {
        if (this.amount > 1) {
            addToTop(new ReducePowerAction(this.owner,this.owner,POWER_ID,1));
        } else {
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        flash();
        if (this.owner.hasPower(AutoGuardPower.POWER_ID)){
            this.owner.getPower(AutoGuardPower.POWER_ID).flash();
        }
        return damageAmount;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
