package SwordMaster.powers;

import SwordMaster.SwordMasterMod;
import SwordMaster.utils.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class BasicTrainingPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;
    public static String POWER_ID;
    public static String[] DESCRIPTIONS;
    public int maxCount = 3;
    public int currentIndex = 0;

    public BasicTrainingPower(AbstractCreature owner, int amount, int count) {
        this.currentIndex = count;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == CardType.ATTACK) {
            this.currentIndex--;
        }
        if (this.currentIndex == 0) {
            addToBot(PowerAction());
            this.currentIndex = maxCount;
        }
        updateDescription();
    }

    public abstract AbstractGameAction PowerAction();

    public void atEndOfTurn(boolean isPlayer) {
        flash();
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, ID));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.currentIndex + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }
}
