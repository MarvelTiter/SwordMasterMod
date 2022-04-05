package SwordMaster.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import SwordMaster.SwordMasterMod;
import SwordMaster.utils.TextureLoader;

public class SwordBarrierPower extends AbstractPower {
    public AbstractCreature source;
    public static final String POWER_ID = SwordMasterMod.makeID("SwordBarrierPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture(SwordMasterMod.makePowerPath("SwordBarrier_p.png"));
    private static final Texture tex32 = TextureLoader.getTexture(SwordMasterMod.makePowerPath("SwordBarrier.png"));

    public SwordBarrierPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.priority = 99;
        this.type = AbstractPower.PowerType.BUFF;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        addToBot(new GainBlockAction(owner, owner, amount));
    }

    @Override
    public void atEndOfRound() {
        flash();
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
