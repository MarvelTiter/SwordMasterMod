package SwordMaster.cards;

import SwordMaster.SwordMasterMod;
import SwordMaster.characters.swordMaster;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PhantomSlash extends Master_AbstractCard {
    public static final String ID = SwordMasterMod.makeID(PhantomSlash.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = SwordMasterMod.makeCardPath("Phantom_Slash.png");
    private static final int COST = 1;
    private static final AbstractCard.CardType CARD_TYPE = AbstractCard.CardType.ATTACK;
    private static final AbstractCard.CardColor COLOR = swordMaster.Enums.COLOR_LIGHT_BLUE;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;

    public PhantomSlash() {
        // 卡牌ID，卡牌名称，图片路径，费用，描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CARD_TYPE, COLOR, RARITY, TARGET);
        this.tags.add(swordMaster.Enums.HitPower);
        this.baseMagicNumber = 3;
        this.baseDamage = 1;
    }

    @Override
    public void upgrade() {
        // 卡牌升级后的效果
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(-1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int ac = getAttackCount() / this.baseMagicNumber;
        for (int i = 0; i < ac; i++) {
            addToBot(CustomAttackAction(p,m, AbstractGameAction.AttackEffect.SHIELD));
        }
        resetAttackCounter();
    }

    @Override
    public boolean glowCheckHit() {
        return getAttackCount() >= this.baseMagicNumber;
    }
}
