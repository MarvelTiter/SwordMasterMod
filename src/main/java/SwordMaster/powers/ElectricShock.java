package SwordMaster.powers;

import SwordMaster.SwordMasterMod;
import SwordMaster.utils.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ElectricShock extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;
    public static final String POWER_ID = SwordMasterMod.makeID("ElectricShock");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture(SwordMasterMod.makePowerPath("ElectricShock_p.png"));
    private static final Texture tex32 = TextureLoader.getTexture(SwordMasterMod.makePowerPath("ElectricShock.png"));

    public ElectricShock(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.priority = 4;
        this.type = AbstractPower.PowerType.DEBUFF;
        if (this.amount <= 0) {
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return damage + ((float) this.amount);
        }
        return damage;
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.NORMAL) {
            flash();
            addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
            updateDescription();
        }
        return damageAmount;
    }

    public void atEndOfTurn(boolean isPlayer) {
//        if (!this.owner.hasPower(HorrorPower.POWER_ID)) {

//        }
        flash();
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public AbstractPower makeCopy() {
        return new ElectricShock(this.owner, this.amount);
    }

}
