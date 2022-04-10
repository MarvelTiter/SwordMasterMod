package SwordMaster.powers;

import SwordMaster.characters.swordMaster;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import SwordMaster.SwordMasterMod;
import SwordMaster.actions.ReduceFlowingStanceAction;
import SwordMaster.utils.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;

public class FlowingStance extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;
    public static final String POWER_ID = SwordMasterMod.makeID("FlowingStance");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture(SwordMasterMod.makePowerPath("FlowingStance_p.png"));
    private static final Texture tex32 = TextureLoader.getTexture(SwordMasterMod.makePowerPath("FlowingStance.png"));

    public FlowingStance(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.priority = 4;
        this.type = AbstractPower.PowerType.BUFF;
        if (this.amount <= 0) {
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        reduceCardEnergyInHand();
        updateDescription();
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (type == DamageInfo.DamageType.NORMAL && card.hasTag(swordMaster.Enums.FlowingStance)
                && card.hasTag(swordMaster.Enums.FlowingDamage)) {
            damage += 1;
        }
        return damage;
    }

    @Override
    public float modifyBlock(float blockAmount, AbstractCard card) {
        if (card.hasTag(swordMaster.Enums.FlowingStance) && card.hasTag(swordMaster.Enums.FlowingBlock)){
            return  blockAmount + 1;
        }
        return  blockAmount;
    }

    @Override
    public void onRemove() {
        resumeCardEnergy();
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        reduceCardEnergy(card);
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (this.amount <= 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        } else {
            addToBot(new ReduceFlowingStanceAction((AbstractPlayer) this.owner));
        }
    }

    private void resumeCardEnergy() {
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card.hasTag(swordMaster.Enums.FlowingStance) && card.hasTag(swordMaster.Enums.FlowingEnergy)) {
                if (card.isCostModifiedForTurn) {
                    card.setCostForTurn(card.costForTurn + 1);
                }
            }
        }
    }

    private void reduceCardEnergyInHand() {
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            reduceCardEnergy(card);
        }
    }

    private void reduceCardEnergy(AbstractCard card) {
        if (card.hasTag(swordMaster.Enums.FlowingStance) && card.hasTag(swordMaster.Enums.FlowingEnergy)) {
            if (!card.isCostModifiedForTurn) {
                card.setCostForTurn(card.costForTurn - 1);
            }
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public AbstractPower makeCopy() {
        return new FlowingStance(this.owner, this.amount);
    }

}
