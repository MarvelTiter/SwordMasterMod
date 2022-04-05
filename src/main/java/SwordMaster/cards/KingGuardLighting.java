package SwordMaster.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import SwordMaster.SwordMasterMod;
import SwordMaster.actions.ApplyKingGuardLightingAction;
import SwordMaster.characters.swordMaster;

public class KingGuardLighting extends Master_AbstractCard {
    public static final String ID = SwordMasterMod.makeID(KingGuardLighting.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = SwordMasterMod.makeCardPath("King_Guard_Lighting.png");
    private static final int COST = 1;
    private static final CardType CARD_TYPE = CardType.SKILL;
    private static final CardColor COLOR = swordMaster.Enums.COLOR_LIGHT_BLUE;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public KingGuardLighting() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CARD_TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 3;
    }

    @Override // com.megacrit.cardcrawl.cards.AbstractCard
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyKingGuardLightingAction(m, this.baseMagicNumber));
    }

    @Override // com.megacrit.cardcrawl.cards.AbstractCard
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    @Override // com.megacrit.cardcrawl.cards.AbstractCard
    public AbstractCard makeCopy() {
        return new KingGuardLighting();
    }
}
