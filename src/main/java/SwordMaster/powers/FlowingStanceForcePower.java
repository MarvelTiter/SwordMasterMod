package SwordMaster.powers;

import SwordMaster.characters.swordMaster;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import SwordMaster.SwordMasterMod;
import SwordMaster.utils.TextureLoader;

public class FlowingStanceForcePower extends AbstractPower {
    public AbstractCreature source;
    public static final String POWER_ID = SwordMasterMod.makeID("FlowingStanceForce");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader
            .getTexture(SwordMasterMod.makePowerPath("FlowingStanceForce_p.png"));
    private static final Texture tex32 = TextureLoader
            .getTexture(SwordMasterMod.makePowerPath("FlowingStanceForce.png"));

    public FlowingStanceForcePower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = AbstractPower.PowerType.BUFF;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        reduceCardEnergyInHand();
        updateDescription();
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (type == DamageInfo.DamageType.NORMAL && card.hasTag(swordMaster.Enums.FlowingForce) && card.hasTag(swordMaster.Enums.FlowingDamage)) {
            damage += 1;
        }
        return damage;
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        reduceCardEnergy(card);
    }

    private void reduceCardEnergyInHand() {
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            reduceCardEnergy(card);
        }
    }

    private void reduceCardEnergy(AbstractCard card) {
        if (card.hasTag(swordMaster.Enums.FlowingForce) && card.hasTag(swordMaster.Enums.FlowingEnergy)) {
            if (!card.isCostModifiedForTurn) {
                card.setCostForTurn(card.costForTurn - 1);
            }
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
