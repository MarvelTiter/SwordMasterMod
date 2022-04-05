package SwordMaster.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import SwordMaster.SwordMasterMod;
import SwordMaster.utils.TextureLoader;

public class TelekineticSwordsPower extends AbstractPower {
    public AbstractCreature source;
    public static final String POWER_ID = SwordMasterMod.makeID("TelekineticSwords");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader
            .getTexture(SwordMasterMod.makePowerPath("TelekineticSwords_p.png"));
    private static final Texture tex32 = TextureLoader
            .getTexture(SwordMasterMod.makePowerPath("TelekineticSwords.png"));
    private int max = 0;

    public TelekineticSwordsPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.max = amount;
        this.priority = 4;
        this.type = AbstractPower.PowerType.BUFF;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        this.amount--;
        if (this.amount == 0) {
            addToBot(new GainEnergyAction(1));
            addToBot(new DrawCardAction(1));
            this.amount = max;
        }
        this.updateDescription();
    }

    // public float atDamageReceive(float damage, DamageInfo.DamageType type) {
    // if (type == DamageInfo.DamageType.NORMAL) {
    // return damage + ((float) this.amount);
    // }
    // return damage;
    // }

    // public int onAttacked(DamageInfo info, int damageAmount) {
    // // if (info.type == DamageInfo.DamageType.NORMAL) {
    // // flash();
    // // addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
    // // updateDescription();
    // // }
    // return damageAmount;
    // }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
