package SwordMaster.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;

import SwordMaster.SwordMasterMod;
import SwordMaster.characters.swordMaster;
import SwordMaster.powers.LoseThronsPower;

public class GuardCross extends Master_AbstractCard {
    public static final String ID = SwordMasterMod.makeID(GuardCross.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = SwordMasterMod.makeCardPath("Guard_Cross.png");
    private static final int COST = 1;
    private static final int BASIC_BLOCK = 8;
    private static final int BASIC_MAGIC = 3;
    private static final int UPGRADE_BLOCK = 2;
    private static final int UPGRADE_MAGIC = 1;
    private static final CardType CARD_TYPE = CardType.SKILL;
    private static final CardColor COLOR = swordMaster.Enums.COLOR_LIGHT_BLUE;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public GuardCross() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CARD_TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = BASIC_BLOCK;
        this.baseMagicNumber = BASIC_MAGIC;
    }

    @Override // com.megacrit.cardcrawl.cards.AbstractCard
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
        addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, this.baseMagicNumber)));
        addToBot(new ApplyPowerAction(p, p, new LoseThronsPower(p, this.baseMagicNumber)));
    }

    @Override // com.megacrit.cardcrawl.cards.AbstractCard
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_BLOCK);
            upgradeMagicNumber(UPGRADE_MAGIC);
        }
    }

    @Override // com.megacrit.cardcrawl.cards.AbstractCard
    public AbstractCard makeCopy() {
        return new GuardCross();
    }
}
