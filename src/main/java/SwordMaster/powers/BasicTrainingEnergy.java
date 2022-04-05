package SwordMaster.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import SwordMaster.SwordMasterMod;
import SwordMaster.utils.TextureLoader;

public class BasicTrainingEnergy extends BasicTrainingPower {
    public static String POWER_ID = SwordMasterMod.makeID("BasicTrainingEnergy");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;

    private static final Texture tex84 = TextureLoader.getTexture(SwordMasterMod.makePowerPath("BasicTrainingE_p.png"));
    private static final Texture tex32 = TextureLoader.getTexture(SwordMasterMod.makePowerPath("BasicTrainingE.png"));

    public BasicTrainingEnergy(AbstractCreature owner, int amount, int count) {
        super(owner, amount, count);
        this.name = NAME;
        this.ID = POWER_ID;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public AbstractPower makeCopy() {
        return new BasicTrainingEnergy(this.owner, this.amount, this.currentIndex);
    }

    @Override
    public AbstractGameAction PowerAction() {
        return new GainEnergyAction(this.amount);
    }

}
