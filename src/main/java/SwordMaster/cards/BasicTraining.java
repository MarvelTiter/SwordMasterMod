package SwordMaster.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import SwordMaster.SwordMasterMod;
import SwordMaster.characters.swordMaster;
import SwordMaster.powers.BasicTrainingDraw;
import SwordMaster.powers.BasicTrainingEnergy;

public class BasicTraining extends Master_AbstractCard {
    public static final String ID = SwordMasterMod.makeID(BasicTraining.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = SwordMasterMod.makeCardPath("Basic_Training.png");
    private static final int COST = 0;
    private static final CardType CARD_TYPE = CardType.SKILL;
    private static final CardColor COLOR = swordMaster.Enums.COLOR_LIGHT_BLUE;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public BasicTraining() {
        // 卡牌ID，卡牌名称，图片路径，费用，描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CARD_TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 1;
    }

    @Override
    public void upgrade() {
        // 卡牌升级后的效果
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.DESCRIPTION;
            this.rawDescription += cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int stackE = 3;
        if (p.hasPower(BasicTrainingEnergy.POWER_ID)) {
            BasicTrainingEnergy power = (BasicTrainingEnergy) p.getPower(BasicTrainingEnergy.POWER_ID);
            stackE = Math.min(stackE, power.currentIndex);
        }
        addToBot(new ApplyPowerAction(p, p, new BasicTrainingEnergy(p, 1, stackE), 1));
        if (this.upgraded) {
            addToBot(new ApplyPowerAction(p, p, new BasicTrainingDraw(p, 1, stackE), 1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new BasicTraining();
    }
}
