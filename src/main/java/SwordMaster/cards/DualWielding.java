package SwordMaster.cards;

import SwordMaster.SwordMasterMod;
import SwordMaster.characters.swordMaster;
import SwordMaster.powers.DualWieldingPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DualWielding extends Master_AbstractCard {
    public static final String ID = SwordMasterMod.makeID(DualWielding.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = SwordMasterMod.makeCardPath("Dual_Wielding.png");
    private static final int COST = 2;
    private static final AbstractCard.CardType CARD_TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = swordMaster.Enums.COLOR_LIGHT_BLUE;
    private static final AbstractCard.CardRarity RARITY = CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;

    public DualWielding() {
        // 卡牌ID，卡牌名称，图片路径，费用，描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CARD_TYPE, COLOR, RARITY, TARGET);
        this.tags.add(swordMaster.Enums.FlowingStance);
        this.tags.add(swordMaster.Enums.FlowingEnergy);
    }

    @Override
    public void upgrade() {
        // 卡牌升级后的效果
        if (!this.upgraded) {
            upgradeName();
            this.retain = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new DualWieldingPower(p,1),1));
    }

    @Override
    public boolean glowCheckHit() {
        return getAttackCount() >= 6;
    }
}
